package bit.com.a.dao;

import bit.com.a.model.MemberDto;

public interface BitMemberDao {

	public int getId(MemberDto mem);
	
	public boolean addmember(MemberDto mem)throws Exception;
		
	public MemberDto login(MemberDto mem);
}
