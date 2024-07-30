package com.edujoa.ssz.webmail.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.edujoa.ssz.webmail.model.dto.ReceivedMail;
import com.edujoa.ssz.webmail.model.service.EmailReceiverService;
import com.edujoa.ssz.webmail.model.service.MailService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MailController {
	private final MailService service;
	private final EmailReceiverService emailReceiverService;
	
		//EmailReceiverService에서 받아온 메일 바로 화면에 뿌리는 메소드
	 @GetMapping("/mailbox")
	    public String run(Model model) {
	        try {
	            List<ReceivedMail> emails = emailReceiverService.receiveEmails();
	            System.out.println("service들어가기 전 emails: "+emails);
	            service.insertReceivedMail(emails);
	            List<ReceivedMail>mails=service.selectReceivedMails();
	            System.out.println("insert끝나고 select된 emails: "+mails);
	            model.addAttribute("emails", mails);
	        } catch (Exception e) {
	            e.printStackTrace(); // 예외를 로깅하거나 적절히 처리
	            model.addAttribute("error", "이메일을 불러오는 중 문제가 발생했습니다.");
	        }
	        return "/ssz/mailbox";
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
	/*@GetMapping("/{folder}")
    public String getEmails(@PathVariable String folder, Model model) {
        List<Mail> emails;
        if (folder.equals("inbox")) {
            emails = emailService.getInboxEmails();
        } else if (folder.equals("trash")) {
            emails = emailService.getTrashEmails();
        } else {
            emails = new ArrayList<>();
        }
        model.addAttribute("emails", emails);
        return "emailList :: emailListFragment"; // emailListFragment는 emails를 렌더링하는 HTML 부분
    }

    @PostMapping("/delete")
    @ResponseBody
    public ResponseEntity<?> deleteEmails(@RequestBody EmailActionRequest request) {
        emailService.moveToTrash(request.getEmails());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/restore")
    @ResponseBody
    public ResponseEntity<?> restoreEmails(@RequestBody EmailActionRequest request) {
        emailService.restoreFromTrash(request.getEmails());
        return ResponseEntity.ok().build();
    }*/
}
