package bit.com.a.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bit.com.a.dao.YoutubeDao;
import bit.com.a.model.YoutubeSave;
import bit.com.a.service.YoutubeService;

@Service
public class YoutubeServiceImpl implements YoutubeService {

	@Autowired
	YoutubeDao youtubeDao;

	@Override
	public boolean writeYoutube(YoutubeSave ys) {
		// TODO Auto-generated method stub
		return youtubeDao.writeYoutube(ys);
	}

	@Override
	public YoutubeSave getYoutube(YoutubeSave ys) {
		// TODO Auto-generated method stub
		return youtubeDao.getYoutube(ys);
	}

	@Override
	public List<YoutubeSave> getYoutubeList(YoutubeSave ys) {
		// TODO Auto-generated method stub
		return youtubeDao.getYoutubeList(ys);
	}
	
}
