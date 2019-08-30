package bit.com.a.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bit.com.a.model.BbsDto;
import bit.com.a.model.BbsOrderDto; 

@Repository // == 저장소 DaoImpl에서 DB에 접근한다
public class BitBbsDaoImpl implements bit.com.a.dao.BitBbsDao {

	@Autowired
	SqlSession sqlSession;

	String namespace = "Bbs.";

	@Override
	public List<BbsDto> getbbslist(BbsOrderDto dto) {

		List<BbsDto> list = sqlSession.selectList(namespace + "getbbslist", dto);

		return list;
	}

	@Override
	public int getbbscount(BbsOrderDto dto) throws Exception {
		int count = sqlSession.selectOne(namespace + "getbbscount", dto);
		return count;
	}

	@Override
	public boolean writebbslist(BbsDto dto) {
		int count = 0;
		boolean b = false;
		
		count = sqlSession.insert(namespace + "writebbs", dto);


		return count > 0 ? true : false;
	}

	@Override
	public BbsDto bbsdetail(int seq) {

		BbsDto dto = (BbsDto) sqlSession.selectOne(namespace + "view", seq);
		return dto;
	}

	@Override
	public int bbsdelete(int seq) {
		int count = 0;

		count = sqlSession.delete(namespace + "delete", seq);

		return count;
	}

	@Override
	public int bbsupdate(BbsDto dto) throws Exception {
		int count = 0;

		count = sqlSession.update(namespace + "update", dto);

		return count;
	}

	@Override
	public int bbsanswer(int seq, BbsDto dto) throws Exception {
		Map map = new HashMap();
		int count = 0;

		map.put("seq", seq);
		map.put("id", dto.getId());
		map.put("title", dto.getTitle());
		map.put("content", dto.getContent());

		sqlSession.update(namespace + "answer", seq);

		count = sqlSession.insert(namespace + "bbsanswerinsert", map);

		return count;
	}

}
