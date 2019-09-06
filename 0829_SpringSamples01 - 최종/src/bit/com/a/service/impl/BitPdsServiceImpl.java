package bit.com.a.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bit.com.a.dao.BitPdsDao;
import bit.com.a.model.BbsParam;
import bit.com.a.model.PdsDto;
import bit.com.a.service.BitPdsService;

@Service
public class BitPdsServiceImpl implements BitPdsService {
	
	@Autowired
	BitPdsDao pdsDao;

	@Override
	public List<PdsDto> getPdsList(BbsParam param) {
		// TODO Auto-generated method stub
		return pdsDao.getPdsList(param);
	}

	@Override
	public boolean uploadPds(PdsDto dto) {
		// TODO Auto-generated method stub
		return pdsDao.uploadPds(dto);
	}

	@Override
	public PdsDto pdsdetail(int seq) {
		// TODO Auto-generated method stub
		return pdsDao.pdsdetail(seq);
	}

	@Override
	public boolean pdsupdate(PdsDto dto) {
		// TODO Auto-generated method stub
		return pdsDao.pdsupdate(dto);
	}

	@Override
	public boolean pdsdelete(int seq) {
		// TODO Auto-generated method stub
		return pdsDao.pdsdelete(seq);
	}

	@Override
	public String getfilename(int seq) {
		// TODO Auto-generated method stub
		return pdsDao.getfilename(seq);
	}

	@Override
	public boolean downcount(int seq) {
		// TODO Auto-generated method stub
		return pdsDao.downcount(seq);
	}

	@Override
	public boolean readcount(int seq) {
		return pdsDao.readcount(seq);
		
	}

	@Override
	public int getPdsCount(BbsParam param) {
		// TODO Auto-generated method stub
		return pdsDao.getPdsCount(param);
	}
	
	
 
}
