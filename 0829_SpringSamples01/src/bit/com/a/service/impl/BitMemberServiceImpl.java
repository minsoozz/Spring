package bit.com.a.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bit.com.a.dao.BitMemberDao;
import bit.com.a.model.MemberDto;
import bit.com.a.service.BitMemberService;

@Service
public class BitMemberServiceImpl implements BitMemberService {

	@Autowired		// DI 
	private BitMemberDao bitMemberDao;	
	// BitMemberDao bitMemberDao = new BitMemberDaoImpl();

	@Override
	public int getId(MemberDto mem) {		
		return bitMemberDao.getId(mem);		
	}
	
	@Override
	public boolean addmember(MemberDto mem) throws Exception {		
		return bitMemberDao.addmember(mem);		
	}	

	@Override
	public MemberDto login(MemberDto mem) {		
		return bitMemberDao.login(mem);		
	}
	
	
	
}





