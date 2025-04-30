package springBootMVCShopping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import springBootMVCShopping.command.LoginCommand;
import springBootMVCShopping.domain.AuthInfoDTO;
import springBootMVCShopping.repository.LoginRepository;
import springBootMVCShopping.repository.MemberRepository;

@Service
public class LoginService {

	@Autowired
	LoginRepository loginRepository;
	@Autowired
	MemberRepository memberRepository;
	@Autowired
	PasswordEncoder passwordEncoder;

    public void execute(LoginCommand loginCommand, BindingResult result
			, HttpSession session, HttpServletResponse repsonse) {
    	
		AuthInfoDTO auth = memberRepository.loginSelectOne(loginCommand.getUserId());
		
		if(auth == null) {
			
			System.out.println("아이디가 존재하지 않습니다.");
			result.rejectValue("userId", "loginCommand.userId", "아이디가 존재하지 않습니다.");
		}else {	
			if(passwordEncoder.matches(loginCommand.getUserPw(), auth.getUserPw())) {
				
				System.out.println("로그인 되었습니다.");
				session.setAttribute("auth", auth);
		
				// 자동 로그인 쿠키 생성
				if(loginCommand.isAutoLogin()) {
					Cookie cookie = new Cookie("autoLogin", loginCommand.getUserId());
					cookie.setPath("/");
					cookie.setMaxAge(60*60*24*30);
					repsonse.addCookie(cookie);
				}
		
			
				//아이디 저장 쿠키 생성
				if(loginCommand.isIdStore()) {
					Cookie cookie = new Cookie("idStore", loginCommand.getUserId());
					cookie.setPath("/");
					cookie.setMaxAge(60*60*24*30);
					repsonse.addCookie(cookie);
				}
				//쿠키 삭제
				else {
					Cookie cookie = new Cookie("idStore", "");
					cookie.setPath("/");
					cookie.setMaxAge(0);
					repsonse.addCookie(cookie);
				}
			
		}else {
			System.out.println("비밀번호가 일치하지 않습니다.");
			result.rejectValue("userPw", "loginCommand.userPw", "비밀번호가 일치하지 않습니다.");
			}
		}
	}

}
