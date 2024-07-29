package com.edujoa.ssz.webmail.model.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.MessagePart;
import com.google.api.services.gmail.model.MessagePartHeader;
import com.google.api.services.gmail.model.Message;

@Service
public class GmailService {
	private static final String APPLICATION_NAME = "Gmail API Java Quickstart";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";
    private static final List<String> SCOPES = Collections.singletonList(GmailScopes.GMAIL_READONLY);
    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";

    private Gmail service;

    public GmailService() throws IOException, GeneralSecurityException {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        service = new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        InputStream in = GmailService.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }

    // Gmail API를 사용하는 메서드들을 여기에 추가하세요
    public List<Message> getRecentEmails(int maxResults) throws IOException {
        String userId = "me";
        ListMessagesResponse listResponse = service.users().messages().list(userId)
                .setMaxResults(Long.valueOf(maxResults))
                .setLabelIds(Collections.singletonList("INBOX"))
                .execute();

        List<Message> messages = new ArrayList<>();
        if (listResponse.getMessages() != null) {
            messages.addAll(listResponse.getMessages());
        }

        // 메시지 세부 정보 가져오기
        for (int i = 0; i < messages.size(); i++) {
            Message message = messages.get(i);
            Message fullMessage = service.users().messages().get(userId, message.getId()).execute();
            messages.set(i, fullMessage);
        }

        return messages;
    }

    // 이메일 내용을 읽기 쉬운 형식으로 변환하는 도우미 메서드
    public String getEmailContent(Message message) {
        StringBuilder builder = new StringBuilder();

        // 제목 가져오기
        String subject = message.getPayload().getHeaders().stream()
                .filter(header -> "Subject".equals(header.getName()))
                .findFirst()
                .map(MessagePartHeader::getValue)
                .orElse("(제목 없음)");
        builder.append("제목: ").append(subject).append("\n");

        // 보낸 사람 가져오기
        String from = message.getPayload().getHeaders().stream()
                .filter(header -> "From".equals(header.getName()))
                .findFirst()
                .map(MessagePartHeader::getValue)
                .orElse("(보낸 사람 정보 없음)");
        builder.append("보낸 사람: ").append(from).append("\n");

        // 본문 가져오기
        String bodyContent = getBodyContent(message.getPayload());
        builder.append("내용:\n").append(bodyContent);

        return builder.toString();
    }

    private String getBodyContent(MessagePart payload) {
        if (payload.getParts() == null) {
            return new String(Base64.getUrlDecoder().decode(payload.getBody().getData()));
        }
        
        StringBuilder bodyBuilder = new StringBuilder();
        for (MessagePart part : payload.getParts()) {
            if (part.getMimeType().equals("text/plain")) {
                bodyBuilder.append(new String(Base64.getUrlDecoder().decode(part.getBody().getData())));
            }
        }
        return bodyBuilder.toString();
    }
    
}
