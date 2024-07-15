package com.edujoa.chs.employee.controller;


import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChsMailService {
    private final JavaMailSender mailSender;

    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("chs02229@naver.com"); // 발신자 주소 설정
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }

    public void registerUser(String email, String empId) {
        System.out.println(email + empId);
        String password = "0000";
        sendEmail(email, "Your Account Information",
                "Your ID: " + empId + "\nYour Password: " + password);
    }
}

