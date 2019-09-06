package bit.com.a.dao;

import java.util.List;

import bit.com.a.model.YoutubeSave;

public interface YoutubeDao {

	public boolean writeYoutube(YoutubeSave ys);

	public YoutubeSave getYoutube(YoutubeSave ys);

	public List<YoutubeSave> getYoutubeList(YoutubeSave ys);

}
