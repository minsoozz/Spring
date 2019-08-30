package bit.com.a.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import bit.com.a.login.BitMemberController;
import bit.com.a.model.BbsDto;
import bit.com.a.model.BbsOrderDto;
 
import bit.com.a.service.BitBbsService;

@Controller
public class BitBbsController {

	@Autowired
	private BitBbsService bitBbsService;

	private static final Logger logger = LoggerFactory.getLogger(BitMemberController.class);

	@RequestMapping(value = "getBoardList.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String getbbslist(Model model,BbsOrderDto dto,
			 @RequestParam(required = false, defaultValue = "4") int cond
			,@RequestParam(required = false, defaultValue = "") String keyword
			,@RequestParam(required = false, defaultValue = "1") int pageNum
			) throws Exception {
		logger.info("테스트 1");
		// 전체 게시글의 수
		int listcount = bitBbsService.getbbslistcount(dto);		
		logger.info("테스트 2");

		BbsOrderDto dto2 = new BbsOrderDto(cond, keyword, pageNum, listcount);
		logger.info("테스트 3");
		
		//검색조건에 해당하는 게시글의 수
		dto2.setNav(bitBbsService.getbbslistcount(dto2));
		logger.info("테스트 4");
		
		model.addAttribute("bbslist", bitBbsService.getbbslist(dto2) );		
		logger.info("테스트 5");

		model.addAttribute("paging",dto2);
		logger.info("테스트 6");
				
		return "bbslist.tiles";
	}

	@RequestMapping(value = "bbswrite.do", method = RequestMethod.GET)
	public String writebbs(Model model) throws Exception {
		
		return "bbswrite.tiles";
	}

	
	@RequestMapping(value = "bbswriteAf.do", method = RequestMethod.GET)
	public String bbswriteAf(BbsDto dto, Model model) throws Exception {
		dto.setWdate("1");
		System.out.println("writeAf dto : " + dto.toString());
		boolean b = false;
		b = bitBbsService.writebbslist(dto);

		logger.info("bbswriteAf done!!!!!!!!");
		
		if (b) {
			
			return "redirect:/getBoardList.do";

		} else {
			
			return "forward:/bbswrite.do";
		}
	}

	@RequestMapping(value = "bbsdetail.do", method = RequestMethod.GET)
	public String bbsdetail(int seq, Model model) throws Exception {

		BbsDto dto = bitBbsService.bbsdetail(seq);
		model.addAttribute("dto", dto);

		return "bbsdetail.do";
	}
	
	@RequestMapping(value = "deletebbs.do", method = RequestMethod.GET)
	public String bbsdetail(int seq) throws Exception {
		
		int count = 0;
		count = bitBbsService.bbsdelete(seq);
		
		if(count>0) {
		}
		else {
		}
		
		return "redirect:/getBoardList.do"; 
	}
	
	@RequestMapping(value="updatebbs.do", method = RequestMethod.GET)
	public String updatebbs(int seq,Model model) throws Exception {
		
		BbsDto dto =  bitBbsService.bbsdetail(seq);
		
		model.addAttribute("dto", dto);
		
		return "bbsupdate.do";
	}
	
	@RequestMapping(value="updateAf.do", method = RequestMethod.GET)
	public String updateAf(BbsDto dto) throws Exception {
		int count = 0;
		count = bitBbsService.bbsupdate(dto);
		
		return "redirect:/getBoardList.do";
	}
	
	@RequestMapping(value="answerbbs.do", method = RequestMethod.GET)
	public String answerbbs(int seq,Model model) throws Exception {

			model.addAttribute("seq",seq);
			
			return "answerbbs.do";
	}
	
	@RequestMapping(value="answerwriteAf.do", method = RequestMethod.POST)
	public String answerwriteAf(int seq, BbsDto dto) throws Exception {
		int count = 0;
		
		count = bitBbsService.bbsanswer(seq,dto);		
		if(count>0) {
			logger.info("답글 성공 " + new Date());
		} else {
			logger.info("답글 실패" + new Date());
		}
		return "redirect:/getBoardList.do";
	}
}
