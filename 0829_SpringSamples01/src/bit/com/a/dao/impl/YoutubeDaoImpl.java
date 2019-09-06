package bit.com.a.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bit.com.a.dao.YoutubeDao;
import bit.com.a.model.YoutubeSave;

@Repository
public class YoutubeDaoImpl implements YoutubeDao {

	@Autowired
	SqlSession sqlSession;

	String ns = "Youtube.";

	@Override
	public boolean writeYoutube(YoutubeSave ys) {
		int n = sqlSession.insert(ns + "writeYoutube", ys);
		return n > 0 ? true : false;
	}

	@Override
	public YoutubeSave getYoutube(YoutubeSave ys) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(ns + "getYoutube", ys);
	}

	@Override
	public List<YoutubeSave> getYoutubeList(YoutubeSave ys) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(ns + "getYoutubeList", ys);
	}

}
