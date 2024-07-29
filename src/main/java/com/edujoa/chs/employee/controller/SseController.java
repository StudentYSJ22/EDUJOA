package com.edujoa.chs.employee.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ConcurrentMap;

@RestController
public class SseController {

    // 사용자별 SSE 연결을 저장할 맵
    private final ConcurrentMap<String, CopyOnWriteArrayList<SseEmitter>> userEmitters = new ConcurrentHashMap<>();

    // SSE 스트림을 제공하는 엔드포인트
    @GetMapping("/stream")
    public SseEmitter stream(@RequestParam String userId) {
        SseEmitter emitter = new SseEmitter();
        userEmitters.computeIfAbsent(userId, k -> new CopyOnWriteArrayList<>()).add(emitter);

        // 연결 완료, 타임아웃, 에러 시 리스트에서 제거
        emitter.onCompletion(() -> removeEmitter(userId, emitter));
        emitter.onTimeout(() -> removeEmitter(userId, emitter));
        emitter.onError(e -> removeEmitter(userId, emitter));

        try {
            emitter.send(SseEmitter.event().name("INIT").data("connected"));
        } catch (IOException e) {
            removeEmitter(userId, emitter);
        }

        return emitter;
    }

    private void removeEmitter(String userId, SseEmitter emitter) {
        CopyOnWriteArrayList<SseEmitter> emitters = userEmitters.get(userId);
        if (emitters != null) {
            emitters.remove(emitter);  // emitter 제거
        }
    }

    // 이벤트를 전송하는 메서드
    public void sendEvent(String userId, String message) {
        CopyOnWriteArrayList<SseEmitter> emitters = userEmitters.get(userId);
        if (emitters != null) {
            for (SseEmitter emitter : emitters) {
                try {
                    emitter.send(SseEmitter.event().name("ALERT").data(message));  // 메시지 전송
                } catch (IOException e) {
                    emitters.remove(emitter);  // 오류 발생 시 emitter 제거
                }
            }
        }
    }
}
