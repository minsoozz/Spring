package bit.com.a.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import bit.com.a.model.MemberDto;
import bit.com.a.model.Youtube;
import bit.com.a.model.YoutubeSave;
import bit.com.a.service.YoutubeService;
import bit.com.a.util.YoutubeParser;

@Controller
public class YoutubeController {

	@Autowired
	private YoutubeParser YoutubeParser; // 생성이 되면서 불러진다 @Component

	@Autowired
	private YoutubeService youtubeService;

	@RequestMapping(value = "yutube.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String yutube(String s_keyword, Model model) {
		model.addAttribute("doc_title", "Youtube");

		if (s_keyword != null && !s_keyword.equals("")) {

			ArrayList<Youtube> getTitles = YoutubeParser.getTitles(s_keyword);
			model.addAttribute("yulist", getTitles);
			model.addAttribute("s_keyword", s_keyword);

		} else {

		}

		return "yutube.tiles";
	}

	@ResponseBody
	@RequestMapping(value = "youtubesave.do", method = { RequestMethod.GET, RequestMethod.POST })
	public YoutubeSave youtubesave(YoutubeSave you) {

		// DB 추가
		System.out.println("1: " + you.toString());
		youtubeService.writeYoutube(you);
		System.out.println("2: " + you.toString());
		// 들어간 데이터를 보기 위해서 다시 받는다
		YoutubeSave ysave = youtubeService.getYoutube(you);
		System.out.println("3: " + you.toString());
		// success , false

		return ysave;
	}

	@RequestMapping(value = "youtubelist.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String youtubelist(HttpServletRequest req, Model model) {
		model.addAttribute("doc_title", "Youtube 목록");

		String id = ((MemberDto) req.getSession().getAttribute("login")).getId();

		YoutubeSave you = new YoutubeSave();
		you.setId(id);

		List<YoutubeSave> getTitles = youtubeService.getYoutubeList(you);

		model.addAttribute("youlist", getTitles);
		
		return "youtubelist.tiles";
	}

}
