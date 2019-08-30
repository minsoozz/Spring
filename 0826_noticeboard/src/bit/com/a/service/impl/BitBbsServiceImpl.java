package bit.com.a.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bit.com.a.dao.BitBbsDao;
import bit.com.a.model.BbsDto;
import bit.com.a.model.BbsOrderDto; 
import bit.com.a.service.BitBbsService;

@Service
public class BitBbsServiceImpl implements BitBbsService {
	
	@Autowired	// DI:(의존성) , 알아서 동적할당!?!?!
	private BitBbsDao bitBbsDao; // 인스턴스
	
	
	@Override
	public List<BbsDto> getbbslist(BbsOrderDto dto) throws Exception {
		// TODO Auto-generated method stub
		return bitBbsDao.getbbslist(dto);
	}

	@Override
	public boolean writebbslist(BbsDto dto) throws Exception {
		// TODO Auto-generated method stub
		return bitBbsDao.writebbslist(dto);
	}

	@Override
	public BbsDto bbsdetail(int seq) throws Exception {
		return bitBbsDao.bbsdetail(seq);
	}

	@Override
	public int bbsdelete(int seq) throws Exception {
		// TODO Auto-generated method stub
		return bitBbsDao.bbsdelete(seq);
	}

	@Override
	public int bbsupdate(BbsDto dto) throws Exception {
		// TODO Auto-generated method stub
		return bitBbsDao.bbsupdate(dto);
	}

	@Override
	public int bbsanswer(int seq, BbsDto dto) throws Exception {
		// TODO Auto-generated method stub
		return bitBbsDao.bbsanswer(seq,dto);
	}

	@Override
	public int getbbslistcount(BbsOrderDto dto) throws Exception {
		// TODO Auto-generated method stub
		return bitBbsDao.getbbscount(dto);
	}
	
	
}
