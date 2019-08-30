package bit.com.a.dao;

import java.util.List;

import bit.com.a.model.BbsDto;
import bit.com.a.model.MemberDto;

public interface BitMemberDao {
	
	public boolean addMember(MemberDto mem) throws Exception;

	public int idcheck(String id) throws Exception;

	public int logincheck(MemberDto dto) throws Exception;

	

}
