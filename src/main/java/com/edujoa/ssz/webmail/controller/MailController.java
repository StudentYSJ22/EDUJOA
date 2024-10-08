package com.edujoa.ssz.webmail.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edujoa.ssz.webmail.model.dto.Mail;
import com.edujoa.ssz.webmail.model.dto.ReceivedMail;
import com.edujoa.ssz.webmail.model.service.EmailReceiverService;
import com.edujoa.ssz.webmail.model.service.MailService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MailController {
	private final MailService service;
	private final EmailReceiverService emailReceiverService;
	
		//DB에 저장된 메일들 바로 화면에 뿌리는 메소드
	 @GetMapping("/mailbox")
	    public String run(Model model) {
	        try {
	            List<ReceivedMail>mails=service.selectReceivedMails();
	            model.addAttribute("emails", mails);
	        } catch (Exception e) {
	            e.printStackTrace(); // 예외를 로깅하거나 적절히 처리
	            model.addAttribute("error", "이메일을 불러오는 중 문제가 발생했습니다.");
	        }
	        return "/ssz/mailbox";
	    }
	 //새로고침 눌렀을 때만 gmail에서 메일 가져와서 insert하고 select으로 꺼내옴
	 @PostMapping("/mailbox/refresh")
	 @ResponseBody
	 public List<ReceivedMail> refresh(Model model) {
		 List<ReceivedMail> emails=emailReceiverService.receiveEmails();//서버에서 가져오고
		 service.insertReceivedMail(emails); //가져온거 db에 저장하고
		 List<ReceivedMail> mails=service.selectReceivedMails(); //저장한거 select
		 model.addAttribute("emails",mails); //model에 담아서 전달
		 return mails;
	 }
	 @PostMapping("/mailbox/delete")
	    public ResponseEntity<?> deleteEmails(@RequestBody List<Long> mailIds) {
	        System.out.println("삭제할 이메일 IDs: " + mailIds);
	        int result = service.delete(mailIds);
	        if (result > 0) {
	            return ResponseEntity.ok(result);
	        } else {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("이메일 삭제에 실패했습니다.");
	        }
	    }
	
	//페이지 이동용 메소드
	@GetMapping("/mailbox/mailsend")
	public String mailSend() {
		return "/ssz/sendmail";
	}
	
	@PostMapping("/mailbox/sendmail")
	public String mailSendToDb(@RequestBody Map<String, String>param) {
		System.out.println("jsp에서 가로챈 이메일: "+param);
		//content에 자동으로 미리 설정해놓은 내용 들어가기 때문에 가로챈 내용에는 공백 있으니 trim할 것.
		String mailContent=param.get("message").trim();
		System.out.println("트림해서 가져온 메일내용 잘나오냐? "+mailContent);
		int result = service.createSendMail(param);
		if(result>0) {
			return "/ssz/sendmail";
		}else {
			return "/#";
		}	
	}
	@GetMapping("/mailbox/maildetail")
	public String getMailDetail(@RequestParam String emailId, Model model) {
	    // emailId를 사용하여 데이터베이스에서 해당 메일 정보를 조회
	    ReceivedMail email = service.getSelectedMail(emailId);
	    System.out.println(email);
	    model.addAttribute("email", email);
	    return "/ssz/maildetail";
	}
	@GetMapping("/mailbox/sentmaildetail")
	public String getSentMailDetail(@RequestParam String emailId, Model model) {
		// emailId를 사용하여 데이터베이스에서 해당 메일 정보를 조회
		Mail email = service.getSelectedSentMail(emailId);
		System.out.println(email);
		model.addAttribute("email", email);
		return "/ssz/sentmaildetail";
	}
	@GetMapping("/mailbox/sendtempmail")
	public String getSendTempMail(@RequestParam String emailId, Model model) {
		// emailId를 사용하여 데이터베이스에서 해당 메일 정보를 조회
		System.out.println("매개변수: "+emailId);
		Mail email = service.getSelectedSentMail(emailId);
		System.out.println("임시저장된 거 꺼내와야해: "+email);
		model.addAttribute("email", email);
		return "/ssz/sendtempmail";
	}
	@GetMapping("/mailbox/deletebox")
	public String getDeletedMail(Model model) {
		List<ReceivedMail> deletedMail = service.getDeletedMail();
		model.addAttribute("deletedMail", deletedMail);
		return "/ssz/deletebox";
	}
	@GetMapping("/mailbox/tempbox")
	public String getTempMail(Model model) {
		List<Mail> TempMail = service.getTempMail();
		System.out.println("임시저장된 메일: "+TempMail);
		model.addAttribute("TempMail", TempMail);
		return "/ssz/tempbox";
	}
	@GetMapping("/mailbox/sentbox")
	public String getSentMail(Model model) {
		List<Mail> SentMail = service.getSentMail();
		System.out.println("임시저장된 메일: "+SentMail);
		model.addAttribute("SentMail", SentMail);
		return "/ssz/sentbox";
	}
	@PostMapping("/mailbox/restore")
    public ResponseEntity<?> restoreEmails(@RequestBody List<Long> mailIds) {
        System.out.println("복구할 이메일 IDs: " + mailIds);
        int result = service.restore(mailIds);
        if (result > 0) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("이메일 복구에 실패했습니다.");
        }
    }
	@PostMapping("/mailbox/saveDraft")
	@ResponseBody
	public int saveDraft(@RequestBody Map<String,String> param) {
		int result = service.saveDraft(param);
		if(result>0) {
			return result;
		}else {
			return 0;
		}
	}
}
