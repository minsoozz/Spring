package bit.com.a.dao;

import java.util.List;

import bit.com.a.model.BbsDto;
import bit.com.a.model.BbsParam;

public interface BitBbsDao {

	public List<BbsDto> getBbsList(BbsParam param); 
		
	public int getBbsCount(BbsParam param);

	public boolean bbsWrite(BbsDto dto);

	public BbsDto bbsdetail(int seq);
}
