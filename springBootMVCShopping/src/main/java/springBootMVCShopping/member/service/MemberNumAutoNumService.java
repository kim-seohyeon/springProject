package springBootMVCShopping.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import springBootMVCShopping.repository.MemberRepository;

@Service
public class MemberNumAutoNumService {

	@Autowired
	MemberRepository memberRepository;
	public void execute(Model model) {
		String memberNum = memberRepository.autoMemberNum();
		//request.attribute
		model.addAttribute("memberNum", memberNum);
		
	}
}
