package com.edujoa.ssz.webmail.model.service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.edujoa.ssz.webmail.model.dto.Mail;
import com.edujoa.ssz.webmail.model.dto.MailAttachment;
import com.edujoa.ssz.webmail.model.dto.ReceivedMail;

import jakarta.mail.Address;
import jakarta.mail.Flags;
import jakarta.mail.Folder;
import jakarta.mail.Message;
import jakarta.mail.Multipart;
import jakarta.mail.Part;
import jakarta.mail.Session;
import jakarta.mail.Store;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeUtility;

@Service
public class EmailReceiverService {

	@Value("${spring.mail.host}")
	private String mailHost;

	@Value("${spring.mail.port}")
	private int mailPort;

	@Value("${spring.mail.username}")
	private String username;

	@Value("${spring.mail.password}")
	private String password;

	private String uploadDir;

	public List<ReceivedMail> receiveEmails() {
		String inbox = "inbox";
		List<ReceivedMail> emails = new ArrayList<>();
		Properties properties = new Properties();
		properties.put("mail.store.protocol", "imaps");
		properties.put("mail.imap.host", mailHost);
		properties.put("mail.imap.port", mailPort);
		properties.put("mail.imap.ssl.enable", "true");

		try {
			Session emailSession = Session.getDefaultInstance(properties);
			Store store = emailSession.getStore("imaps");
			store.connect(mailHost, username, password);

			Folder emailFolder = store.getFolder("INBOX");
			emailFolder.open(Folder.READ_ONLY);

			Message[] messages = emailFolder.getMessages();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			for (Message message : messages) {
				// Extract sender
				String sender = ((InternetAddress) message.getFrom()[0]).getAddress();

				// Extract subject
				String subject = MimeUtility.decodeText(message.getSubject());

				// Extract date
				Date receivedDate = message.getReceivedDate();
				String date = (receivedDate != null) ? dateFormat.format(receivedDate) : "00:00";

				// Extract content
				String content = MimeUtility.decodeText(getTextFromMessage(message));
				int mailNumber = message.getMessageNumber();
				String mailNumberStr = Integer.toString(mailNumber);
				String mailType = convertFlagsToString(message.getFlags());
				List<String> sendto = getAddresses(message.getRecipients(Message.RecipientType.TO));
				List<String> ccto = getAddresses(message.getRecipients(Message.RecipientType.CC));
				List<String> Bccto = getAddresses(message.getRecipients(Message.RecipientType.BCC));

				String fileName = null;
				String contentType = null;
				String fileUrl = null;
				if (message.getContent() instanceof Multipart) {
					Multipart multipart = (Multipart) message.getContent();
					List<MailAttachment> attachments = getAttachmentsFromMultipart(multipart);

					// Assuming only one attachment per email for simplicity
					if (!attachments.isEmpty()) {
						MailAttachment attachment = attachments.get(0);
						fileName = attachment.getFileName();
						contentType = attachment.getContentType();
						// Generate file URL
						fileUrl = generateFileUrl(fileName);
					}
				}
				// 여기는 받는 메일이라서 receivedMail 객체로 변경해야함
				ReceivedMail rcvMail = ReceivedMail.builder().rcvMailId(mailNumberStr).rcvMailSender(sender)
						.rcvMailReceiver(sendto).rcvMailCc(ccto).rcvMailBcc(Bccto).rcvMailTitle(subject)
						.rcvMailContent(content).rcvMailDate(date).rcvMailFileName(fileName)
						.rcvMailContentType(contentType).rcvMailType(inbox).rcvMailFileUrl(fileUrl) // 첨부파일 URL 추가
						.rcvMailRead("0").build();
				emails.add(rcvMail);
			}

			emailFolder.close(false);
			store.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return emails;
	}

	private String generateFileUrl(String fileName) {
		if (fileName == null) {
			return null;
		}
		return uploadDir + "/" + fileName; // 파일 저장 경로와 파일 이름을 조합하여 URL 생성
	}

	private List<String> getAddresses(Address[] addresses) {
		List<String> result = new ArrayList<>();
		if (addresses != null) {
			for (Address address : addresses) {
				result.add(((InternetAddress) address).getAddress());
			}
		}
		return result;
	}

	private String getTextFromMessage(Message message) throws Exception {
		Object content = message.getContent();

		if (content instanceof String) {
			return (String) content;
		} else if (content instanceof Multipart) {
			return getTextFromMultipart((Multipart) content);
		} else {
			return "Unsupported content type";
		}
	}

	private String getTextFromMultipart(Multipart multipart) throws Exception {
		StringBuilder result = new StringBuilder();
		int count = multipart.getCount();

		for (int i = 0; i < count; i++) {
			Part part = multipart.getBodyPart(i);

			if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
				// Handle attachment
				result.append("[Attachment: ").append(part.getFileName()).append("]\n");
			} else if (part.isMimeType("text/plain")) {
				result.append(part.getContent().toString());
			} else if (part.isMimeType("text/html")) {
				result.append(part.getContent().toString());
			} else if (part.isMimeType("multipart/*")) {
				result.append(getTextFromMultipart((Multipart) part.getContent()));
			}
		}

		return result.toString();
	}

	private List<MailAttachment> getAttachmentsFromMultipart(Multipart multipart) throws Exception {
		List<MailAttachment> attachments = new ArrayList<>();
		int count = multipart.getCount();

		for (int i = 0; i < count; i++) {
			Part part = multipart.getBodyPart(i);

			if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
				MailAttachment attachment = MailAttachment.builder().fileName(part.getFileName())
						.contentType(part.getContentType()).fileUrl(generateFileUrl(part.getFileName())).build();
				attachments.add(attachment);
			} else if (part.isMimeType("multipart/*")) {
				attachments.addAll(getAttachmentsFromMultipart((Multipart) part.getContent()));
			}
		}

		return attachments;
	}

	private String convertFlagsToString(Flags flags) {
		StringBuilder flagString = new StringBuilder();
		if (flags.contains(Flags.Flag.SEEN))
			flagString.append("SEEN ");
		if (flags.contains(Flags.Flag.ANSWERED))
			flagString.append("ANSWERED ");
		if (flags.contains(Flags.Flag.DELETED))
			flagString.append("DELETED ");
		if (flags.contains(Flags.Flag.FLAGGED))
			flagString.append("FLAGGED ");
		if (flags.contains(Flags.Flag.RECENT))
			flagString.append("RECENT ");
		if (flags.contains(Flags.Flag.DRAFT))
			flagString.append("DRAFT ");
		if (flags.contains(Flags.Flag.USER))
			flagString.append("USER ");
		return flagString.toString().trim();
	}
}
