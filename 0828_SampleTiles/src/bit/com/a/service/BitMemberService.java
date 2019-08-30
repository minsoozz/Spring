package bit.com.a.service;

import java.util.List;
 
import bit.com.a.model.MemberDto;

public interface BitMemberService {
	public boolean addMember(MemberDto mem) throws Exception;

	public int idcheck(String id) throws Exception;

	public int logincheck(MemberDto dto) throws Exception;

}
