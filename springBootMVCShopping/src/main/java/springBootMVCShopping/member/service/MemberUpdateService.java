package springBootMVCShopping.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import springBootMVCShopping.command.MemberCommand;
import springBootMVCShopping.domain.MemberDTO;
import springBootMVCShopping.repository.MemberRepository;

@Service
public class MemberUpdateService {

    private final PasswordEncoder passwordEncoder;

	@Autowired
	MemberRepository memberRepository;

    MemberUpdateService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    
	public int execute(MemberCommand memberCommand) {
		System.out.println(memberCommand.getMemberNum());
		MemberDTO dto = memberRepository.memberSelectOne(memberCommand.getMemberNum());
		System.out.println(memberCommand.getMemberPw());
		System.out.println(dto.getMemberPw());
		
		int i = 0; 
		if(passwordEncoder.matches(memberCommand.getMemberPw(), dto.getMemberPw())) {
			
			dto.setMemberNum(memberCommand.getMemberNum());
			dto.setMemberName(memberCommand.getMemberName());
			dto.setMemberId(memberCommand.getMemberId());
			dto.setMemberAddr(memberCommand.getMemberAddr());
			dto.setMemberAddrDetail(memberCommand.getMemberAddrDetail());
			dto.setMemberPost(memberCommand.getMemberPost());
			dto.setMemberRegist(memberCommand.getMemberRegist());
			dto.setMemberGender(memberCommand.getMemberGender());
			dto.setMemberPhone1(memberCommand.getMemberPhone1());
			dto.setMemberPhone2(memberCommand.getMemberPhone2());
			dto.setMemberEmail(memberCommand.getMemberEmail());
			dto.setMemberBirth(memberCommand.getMemberBirth());

			memberRepository.memberUpdate(dto);
			
			i = 1;
		}
		
		return i;
		
	}
}
