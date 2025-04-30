package springBootMVCShopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import springBootMVCShopping.command.MemberCommand;
import springBootMVCShopping.member.service.MemberDeleteService;
import springBootMVCShopping.member.service.MemberDetailService;
import springBootMVCShopping.member.service.MemberListService;
import springBootMVCShopping.member.service.MemberNumAutoNumService;
import springBootMVCShopping.member.service.MemberUpdateService;
import springBootMVCShopping.member.service.MemberWriteService;


@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	MemberNumAutoNumService memberNumAutoNumService;
	@Autowired
	MemberWriteService memberWriteService;
	@Autowired
	MemberListService memberListService;
	@Autowired
	MemberDetailService memberDetailService;
	@Autowired
	MemberUpdateService memberUpdateService;
	@Autowired
	MemberDeleteService memberDeleteService;
	
	@GetMapping("/memberList")
	public String list(Model model) {
		memberListService.execute(model);
		return "member/memberList";
	}
	
	@GetMapping("/memberWrite")
	public String write(Model model) {
		memberNumAutoNumService.execute(model);
		return "member/memberForm";
		
	}
	
	@PostMapping("/memberWrite")
	public String write(MemberCommand memberCommand) {
		memberWriteService.execute(memberCommand);
		return "redirect:memberList";
	}
	
	@GetMapping("/memberDetail")
	public String detail(Model model, String memberNum) {
		memberDetailService.execute(model, memberNum);
		return "member/memberDetail";
	}
	
	@GetMapping("/memberUpdate")
	public String update(Model model, String memberNum) {
		memberDetailService.execute(model, memberNum);
		return "member/memberModify";
	}
	
	@PostMapping("/memberUpdate")
	public String update(MemberCommand memberCommand, Model model) {
		int i = memberUpdateService.execute(memberCommand);
		if(i == 1) {			
			return "redirect:memberDetail?memberNum="+memberCommand.getMemberNum();
		}
		else {
			memberDetailService.execute(model, memberCommand.getMemberNum());
			model.addAttribute("pwErr", "비밀번호가 틀렸습니다. ");
			return "member/memberModify";
		}
	}
	
	@GetMapping("/memberDelete")
	public String delete(String memberNum) {
		memberDeleteService.execute(memberNum);
		return "redirect:memberList";
	}
}
