package bit.com.a.dao;

import java.util.List;
import java.util.Map;

import bit.com.a.model.BbsParam;
import bit.com.a.model.PdsDto;

public interface BitPdsDao {
	
	public List<PdsDto> getPdsList(BbsParam param);

	public boolean uploadPds(PdsDto dto);

	public PdsDto pdsdetail(int seq);

	public boolean pdsupdate(PdsDto dto);

	public boolean pdsdelete(int seq);

	public String getfilename(int seq);

	public boolean downcount(int seq);
	
	public boolean readcount(int seq);

	public int getPdsCount(BbsParam param);
}
