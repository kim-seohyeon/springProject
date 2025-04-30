package springBootMVCShopping.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import springBootMVCShopping.domain.AuthInfoDTO;
import springBootMVCShopping.domain.MemberDTO;
import springBootMVCShopping.domain.UserChangePasswordDTO;

@Repository
public class MemberRepository {
	
	@Autowired
	SqlSession sqlSession;
	String namespace="memberSql";
	String statement;
	
	String sql;
	@Autowired
	JdbcTemplate jdbcTemaplate;
	
	public String autoMemberNum() {
		sql = " select concat('mem_', nvl(substr(max(MEMBER_NUM),5),10000)+1) from members";
		//queryForObject : row가 하나일 때
		return jdbcTemaplate.queryForObject(sql, String.class);
	}

	public int memberInsert(MemberDTO dto) {
		sql = " insert into members(MEMBER_NUM, MEMBER_ID, MEMBER_NAME, MEMBER_PW"
				+ "					, MEMBER_ADDR, MEMBER_ADDR_DETAIL, MEMBER_POST, MEMBER_REGIST"
				+ "					, MEMBER_GENDER, MEMBER_PHONE1, MEMBER_PHONE2, MEMBER_EMAIL"
				+ "					, MEMBER_BIRTH)"
				+ " values(?, ?, ?, ? "
				+ "		 , ?, ?, ?, ?"
				+ "		 , ?, ?, ?, ?"
				+ "		 , ?)";
		                              
		return jdbcTemaplate.update(sql, dto.getMemberNum(), dto.getMemberId(), dto.getMemberName(), dto.getMemberPw()
									   , dto.getMemberAddr(), dto.getMemberAddrDetail(), dto.getMemberPost(), dto.getMemberRegist()
									   , dto.getMemberGender(), dto.getMemberPhone1(), dto.getMemberPhone2(), dto.getMemberEmail()
									   , dto.getMemberBirth());
	}

	public List<MemberDTO> memberSelectAll() {

		sql = " select MEMBER_NUM, MEMBER_ID, MEMBER_NAME, MEMBER_PW"
				+ " 	, MEMBER_ADDR, MEMBER_ADDR_DETAIL, MEMBER_POST, MEMBER_REGIST"
				+ " 	, MEMBER_GENDER, MEMBER_PHONE1, MEMBER_PHONE2, MEMBER_EMAIL"
				+ " 	, MEMBER_BIRTH"
				+ " from members order by member_num desc";
		
		return jdbcTemaplate.query(sql, new BeanPropertyRowMapper<MemberDTO>(MemberDTO.class));
	}

	public MemberDTO memberSelectOne(String memberNum) {

		sql = " select MEMBER_NUM, MEMBER_ID, MEMBER_NAME, MEMBER_PW"
				+ " 	, MEMBER_ADDR, MEMBER_ADDR_DETAIL, MEMBER_POST, MEMBER_REGIST"
				+ " 	, MEMBER_GENDER, MEMBER_PHONE1, MEMBER_PHONE2, MEMBER_EMAIL"
				+ " 	, MEMBER_BIRTH"
				+ " from members where member_num = ?";
		return jdbcTemaplate.queryForObject(sql, new BeanPropertyRowMapper<MemberDTO>(MemberDTO.class), memberNum);
	}

	public int memberUpdate(MemberDTO dto) {
		
		sql = " update members"
				+ " set   MEMBER_NAME = ?, MEMBER_ADDR = ?, MEMBER_ADDR_DETAIL = ?"
				+ "		, MEMBER_POST = ?, MEMBER_PHONE1 = ?, MEMBER_PHONE2 = ?"
				+ "		, MEMBER_EMAIL = ?, MEMBER_BIRTH = ?"
				+ " where member_num = ?";

		return jdbcTemaplate.update(sql, dto.getMemberName(), dto.getMemberAddr(), dto.getMemberAddrDetail()
									   , dto.getMemberPost(), dto.getMemberPhone1(), dto.getMemberPhone2()
									   , dto.getMemberEmail(), dto.getMemberBirth(), dto.getMemberNum());
		
	}

	public int memberDelete(String memberNum) {

		sql = " delete from members where member_num = ?";
		
		
		return jdbcTemaplate.update(sql, memberNum);
	}


	public AuthInfoDTO loginSelectOne(String userId) {

		statement = namespace + ".memberSelectOne";
		return sqlSession.selectOne(statement, userId);	
		
	}

	public int userPwUpdate(UserChangePasswordDTO dto) {
		
		statement = namespace + ".userPwUpdate";
		return sqlSession.update(statement, dto);	
	
	}

	public Integer emailCheckUpdate(String userId) {
		statement = namespace + ".emailCheckUpdate";
		return sqlSession.update(statement, userId);		
		
	}

	
}
