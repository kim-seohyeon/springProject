package springBootMVCShopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import springBootMVCShopping.service.user.EmailCheckService;

@RestController
public class CheckRestController {

	@Autowired
	EmailCheckService emailCheckService;
	
	@GetMapping("/help/userConfirm")
	public String confirm(String chk) {
		
		Integer i = emailCheckService.execute(chk);
		if(i!=0) return "인증되었습니다.";
		else return "이미 인증되었습니다.";
		
	}
	
}
