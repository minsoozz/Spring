package bit.com.a.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bit.com.a.model.MemberDto;
import bit.com.a.service.BitMemberService;

@Controller
public class BitMemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(BitMemberController.class);
	
	@Autowired
	private BitMemberService bitMemberService;

	@RequestMapping("/login")
	public String login(Model model) {
		logger.info("loginController !! " + new Date());

		
		return "login";
	}
	
	@RequestMapping("/account")
	public String account(Model model) {
		logger.info("loginController !! " + new Date());
		
		return "account";
	}

	/* req 세션을 접근하기 위한 용도 , moedl 짐 싸기 위한 용도 */
	@RequestMapping(value = "/accountAf", method = RequestMethod.POST)
	public String accountAf(MemberDto dto, Model model, HttpServletRequest req ) throws Exception {
		
		System.out.println("accountAf로 도착 ! "); 
		logger.info(dto.toString());
		
		boolean b = bitMemberService.addMember(dto);
		if(b) {
			logger.info("회원가입 되었습니다  " + new Date());
			return "login";
		}
		return "account";
		
	}
}
