package bit.com.a.service;

import java.util.List;

import bit.com.a.model.BbsDto;
import bit.com.a.model.BbsParam;

public interface BitBbsService {

	public List<BbsDto> getBbsList(BbsParam param);
	
	public int getBbsCount(BbsParam param);

	public boolean writebbslist(BbsDto dto);

	public BbsDto bbsdetail(int seq);
	
}
