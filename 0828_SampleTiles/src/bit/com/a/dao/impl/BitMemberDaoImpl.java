package bit.com.a.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bit.com.a.dao.BitMemberDao; 
import bit.com.a.model.MemberDto;

@Repository // == 저장소 DaoImpl에서 DB에 접근한다
public class BitMemberDaoImpl implements BitMemberDao {

	@Autowired
	SqlSession sqlSession;

	String namespace = "Member.";

	@Override
	public boolean addMember(MemberDto mem) throws Exception {

		int n = sqlSession.insert(namespace + "addmember", mem);

		return n > 0 ? true : false;
	}

	@Override
	public int idcheck(String id) throws Exception {

		int count = sqlSession.selectOne(namespace + "idcheck", id);

		return count;
	}

	public int logincheck(MemberDto dto) throws Exception {

		int count = sqlSession.selectOne(namespace + "logincheck", dto);

		return count;
	}

}
