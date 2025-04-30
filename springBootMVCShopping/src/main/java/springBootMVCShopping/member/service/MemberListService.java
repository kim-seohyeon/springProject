package springBootMVCShopping.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import springBootMVCShopping.domain.MemberDTO;
import springBootMVCShopping.repository.MemberRepository;

@Service
public class MemberListService {

	@Autowired
	MemberRepository memberRepository;
	public void execute(Model model) {
		
		List<MemberDTO> list = memberRepository.memberSelectAll();
		model.addAttribute("list", list);
	}
}
