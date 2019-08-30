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
	public String bbslist() throws Exception {
		logger.info("테스트 1");
		// 전체 게시글의 수
		int listcount = bitBbsService.getbbslistcount(dto);		
		logger.info("테스트 2");

		BbsOrderDto dto2 = new BbsOrderDto(cond, keyword, pageNum, listcount);
		logger.info("테스트 3");
		
		//검색조건에 해당하는 게시글의 수
		dto2.setNav(bitBbsService.getbbslistcount(dto2));
		logger.info("테스트 4");

				
		return "bbslist.tiles";
	}


}
