package bit.com.a.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bit.com.a.model.MemberDto;
import bit.com.a.model.PollBean;
import bit.com.a.model.PollDto;
import bit.com.a.model.PollSubDto;
import bit.com.a.model.Voter;
import bit.com.a.service.BitPollService;

@Controller
public class BitPollController {

	@Autowired
	BitPollService pollService;

	@RequestMapping(value = "polllist.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String polllist(Model model, HttpServletRequest req) {
		model.addAttribute("doc_title", "투표 목록");

		String id = ((MemberDto) req.getSession().getAttribute("login")).getId();

		// list 투표를 했는지 체크
		List<PollDto> list = pollService.getPollAllList(id);
		model.addAttribute("plists", list);

		return "polllist.tiles";
	}

	@RequestMapping(value = "pollmake.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String pollmake(Model model) {
		model.addAttribute("doc_title", "투표 만들기");
		
		return "pollmake.tiles";
	}
	
	@RequestMapping(value = "pollmakeAf.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String pollmakeAf(PollBean pbean) {
		System.out.println(pbean.toString());
		
		pollService.makePoll(pbean);
		
		return "redirect:/polllist.do";
	}
	
	@RequestMapping(value = "polldetail.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String polldetail(PollDto poll,Model model) {
		model.addAttribute("doc_title", "투표내용");
		
		PollDto dto = pollService.getPoll(poll); // 질문
		List<PollSubDto> list = pollService.getPollSubList(poll);
		
		System.out.println(dto.toString());
		System.out.println(list.toString());
		
		model.addAttribute("poll", dto);
		model.addAttribute("pollsublist", list);
		
	
		return "polldetail.tiles";
	}
	
	@RequestMapping(value = "polling.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String polling(Voter voter,Model model) {
		
		
		pollService.polling(voter);
	
	   return "redirect:/polllist.do";
	}
	
	@RequestMapping(value = "pollresult.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String pollresult(PollDto poll,Model model) {
		model.addAttribute("doc_title", "투표결과");
		
		PollDto dto = pollService.getPoll(poll);
		List<PollSubDto> list = pollService.getPollSubList(poll);
		
		model.addAttribute("poll", dto);
		model.addAttribute("pollsublist", list);
	
		return "pollresult.tiles";
	}
}
