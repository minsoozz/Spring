package bit.com.a.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import bit.com.a.model.BbsDto;
import bit.com.a.model.BbsParam;
import bit.com.a.service.BitBbsService;

@Controller
public class BitBbsController {

	private static final Logger logger = LoggerFactory.getLogger(BitBbsController.class);
	
	@Autowired
	BitBbsService bbsService;
	
	@RequestMapping(value = "bbslist.do",  method = { RequestMethod.GET, RequestMethod.POST })
	public String bbslist(Model model, BbsParam param) {		
		logger.info("BitBbsController bbslist " + new Date());
		
		model.addAttribute("doc_title", "글목록");
		
		// 글의 총 수 
		int totalRecordCount = bbsService.getBbsCount(param);
	 
		
		
		// pageNumber 취득
		int sn = param.getPageNumber(); // 0 , 1, 2
		int start = sn * param.getRecordCountPerPage() + 1; // 0 -> 1 , 1 - > 11		1   11
		int end = (sn + 1) * param.getRecordCountPerPage(); // 0 - > 10, 1 - > 20		10  20
		
		param.setStart(start);
		param.setEnd(end);
		
		List<BbsDto> bbslist = bbsService.getBbsList(param);			
		
		
		
		model.addAttribute("bbslist", bbslist);
		
		model.addAttribute("s_category",param.getS_category());
		model.addAttribute("s_keyword",param.getS_keyword());
		
		model.addAttribute("pageNumber",sn); // 현재 페이지 넘버
		model.addAttribute("pageCountPerScreen",10);
		model.addAttribute("recordCountPerPage",param.getRecordCountPerPage());
		model.addAttribute("totalRecordCount", totalRecordCount);
		
		return "bbslist.tiles";
	}
	
	@RequestMapping(value = "bbswrite.do", method = RequestMethod.GET)
	public String writebbs(Model model) throws Exception {
		
		return "bbswrite.tiles";
	}
	
	@RequestMapping(value = "bbswriteAf.do", method = RequestMethod.GET)
	public String bbswriteAf(BbsDto dto, Model model) throws Exception {
		boolean b = false;
		
		b = bbsService.writebbslist(dto);

		logger.info("bbswriteAf done!!!!!!!!");
		
		if (b) {
			
			return "redirect:/bbslist.do";

		} else {
			
			return "forward:/bbswrite.do";
		}
	}
	
	@RequestMapping(value = "bbsdetail.do", method = RequestMethod.GET)
	public String bbsdetail(int seq, Model model) throws Exception {
		System.out.println("bbsdetail.do seq : " + seq);
		BbsDto dto = bbsService.bbsdetail(seq);
		
		model.addAttribute("dto", dto);
		System.out.println("controller dto : "  + dto.toString());
		
		return "bbsdetail.tiles";
	}
	
	@RequestMapping(value = "chatting.do", method = RequestMethod.GET)
	public String chatting() throws Exception{
		
		return "cahtting.tiles"; 
	}
	

	
}












