package springBootMVCShopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import springBootMVCShopping.command.LoginCommand;
import springBootMVCShopping.command.MailCommand;
import springBootMVCShopping.domain.AuthInfoDTO;
import springBootMVCShopping.repository.MemberRepository;
import springBootMVCShopping.service.EmailService;

@Controller
public class IndexController {
	@Autowired
	MemberRepository memberRepository;
	@Autowired
	EmailService emailService;
	
	@GetMapping("/")
	public String index(@ModelAttribute("loginCommand") LoginCommand loginCommand, HttpServletRequest request) {
		Cookie [] cookies = request.getCookies();
		if(cookies != null && cookies.length > 0) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals("idStore")) {
					System.out.println("idStore 쿠키 있음");
					loginCommand.setIdStore(true);
					loginCommand.setUserId(cookie.getValue());
				}
				if(cookie.getName().equals("autoLogin")) {
					System.out.println("autoLogin 쿠키 있음");
					String userId = cookie.getValue();
					AuthInfoDTO auth = memberRepository.loginSelectOne(userId);
					//자동로그인 : 쿠키를 이용해서로그인 session을 만들어줌
					HttpSession session = request.getSession();
					session.setAttribute("auth", auth);
				}

			}
		}
		
		return "index";
	}
	
	@GetMapping("/mailling")
	public String mailSend() {
		
		return "email";
	}
	
	@PostMapping("/mailling")
	public String mailSend(MailCommand mailCommand) {
		emailService.execute(mailCommand);
		return "redirect:/";
	}
}
