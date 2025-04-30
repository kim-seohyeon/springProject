package springBootMVCShopping.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springBootMVCShopping.repository.MemberRepository;


@Service
public class MemberDeleteService {

	@Autowired
	MemberRepository memberRepository;
	public void execute(String memberNum) {
		 memberRepository.memberDelete(memberNum);
	}

}
