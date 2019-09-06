package bit.com.a.dao.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bit.com.a.dao.BitPdsDao;
import bit.com.a.model.BbsParam;
import bit.com.a.model.PdsDto;

@Repository
public class BitPdsDaoImpl implements BitPdsDao {

	@Autowired
	SqlSessionTemplate sqlSession;
	
	String ns = "Pds.";

	@Override
	public List<PdsDto> getPdsList(BbsParam param) {
		
		return sqlSession.selectList(ns + "getPdslist" , param);
	}
	
	@Override
	public int getPdsCount(BbsParam param) {
		
		return sqlSession.selectOne(ns + "getPdsCount" , param);
	}
	
	
	
	public boolean uploadPds(PdsDto dto) {
		int n = sqlSession.insert(ns + "uploadPds" , dto);
		
		return n>0?true:false;
	}

	@Override
	public PdsDto pdsdetail(int seq) {
		
		return  (PdsDto)sqlSession.selectOne(ns + "pdsdetail", seq);
	}

	@Override
	public boolean pdsupdate(PdsDto dto) {
		
		int n = sqlSession.update(ns + "pdsupdate", dto);
		
		return n>0?true:false;
	}

	@Override
	public boolean pdsdelete(int seq) {

		int n = sqlSession.delete(ns + "pdsdelete" , seq);
		
		return n>0?true:false;
	}

	@Override
	public String getfilename(int seq) {
		
		String str = sqlSession.selectOne(ns + "filename",seq);
		
		return str;
	}

	@Override
	public boolean downcount(int seq) {
		
		int n = sqlSession.update(ns+"downcount", seq);
		
		return  n > 0 ? true : false;
	}

	@Override
	public boolean readcount(int seq) {
		
		int n = sqlSession.update(ns + "readcount", seq);
		
		return n > 0 ? true : false;
	}
	
	
	
}
