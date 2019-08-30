package bit.com.a.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bit.com.a.dao.BitMemberDao;
import bit.com.a.model.MemberDto;
import bit.com.a.service.BitMemberService;

@Service
public class BitMemberServiceImpl implements BitMemberService {
	/* 자동적으로 springframework가 생성(연결)해준다 , 
	       상속 받은 클래스가 누군지 자동으로 찾아준다?
	 */ 
	@Autowired	// DI:(의존성) , 알아서 동적할당!?!?!
	private BitMemberDao bitMemberDao; // 인스턴스
	// BitMemberDao bitMemberDao = new BitMemberDaoImpl();

	@Override
	public boolean addMember(MemberDto mem) throws Exception {
		// TODO Auto-generated method stub
		return bitMemberDao.addMember(mem);
	}

	@Override
	public int idcheck(String id) throws Exception {
		// TODO Auto-generated method stub
		return bitMemberDao.idcheck(id);
	}

	@Override
	public int logincheck(MemberDto dto) throws Exception {
		// TODO Auto-generated method stub
		return bitMemberDao.logincheck(dto);
	}


	
}
