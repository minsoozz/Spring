package bit.com.a.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bit.com.a.dao.BitBbsDao;
import bit.com.a.model.BbsDto;
import bit.com.a.model.BbsParam;
import bit.com.a.service.BitBbsService;

@Service
public class BitBbsServiceImpl implements BitBbsService {

	@Autowired
	BitBbsDao bbsDao;

	@Override
	public List<BbsDto> getBbsList(BbsParam param) {		
		return bbsDao.getBbsList(param);
	}

	@Override
	public int getBbsCount(BbsParam param) {
		// TODO Auto-generated method stub
		return bbsDao.getBbsCount(param);
	}

	@Override
	public boolean writebbslist(BbsDto dto ) {
		// TODO Auto-generated method stub
		return bbsDao.bbsWrite(dto);
	}

	@Override
	public BbsDto bbsdetail(int seq) {
		// TODO Auto-generated method stub
		return bbsDao.bbsdetail(seq);
	}
	
}
