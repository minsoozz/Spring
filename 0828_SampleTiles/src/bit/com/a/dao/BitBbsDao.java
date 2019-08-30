package bit.com.a.dao;

import java.util.List;

import bit.com.a.model.BbsDto;
import bit.com.a.model.BbsOrderDto; 

public interface BitBbsDao {
	public List<BbsDto> getbbslist(BbsOrderDto dto) throws Exception;

	public boolean writebbslist(BbsDto dto) throws Exception;

	public BbsDto bbsdetail(int seq) throws Exception;

	public int bbsdelete(int seq) throws Exception;

	public int bbsupdate(BbsDto dto) throws Exception;

	public int bbsanswer(int seq, BbsDto dto) throws Exception;

	public int getbbscount(BbsOrderDto dto) throws Exception;
}
