package bit.com.a.service;

import bit.com.a.model.MemberDto;

public interface BitMemberService {

	public int getId(MemberDto mem);
	
	public boolean addmember(MemberDto mem) throws Exception;
	
	
	
	public MemberDto login(MemberDto mem);
}
