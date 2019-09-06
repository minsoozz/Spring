package bit.com.a.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bit.com.a.dao.BitBbsDao;
import bit.com.a.model.BbsDto;
import bit.com.a.model.BbsParam;

@Repository
public class BitBbsDaoImpl implements BitBbsDao {

	@Autowired
	SqlSession sqlSession;

	String ns = "Bbs.";

	@Override
	public List<BbsDto> getBbsList(BbsParam param) {
		return sqlSession.selectList(ns + "getBbsList", param);
	}

	@Override
	public int getBbsCount(BbsParam param) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(ns + "getBbsCount", param);
	}

	@Override
	public boolean bbsWrite(BbsDto dto) {
		int count = 0;
		boolean b = false;

		count = sqlSession.insert(ns + "writebbs", dto);

		return count > 0 ? true : false;

	}

	@Override
	public BbsDto bbsdetail(int seq) {
		System.out.println("bbsdetail dao seq : " + seq);
		BbsDto dto = (BbsDto) sqlSession.selectOne(ns + "view", seq);
		
		System.out.println(dto.toString());
		return dto;
		 
	}
	
}
