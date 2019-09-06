package bit.com.a.login;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import bit.com.a.model.MemberDto;
import bit.com.a.service.BitMemberService;

@Controller
public class BitMemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(BitMemberController.class);
	
	@Autowired private BitMemberService bitMemService;
	
	@RequestMapping(value = "login.do", method=RequestMethod.GET)
	public String login() {		
		logger.info("BitMemberController login " + new Date());	
		return "login.tiles";
	}	
	
	@RequestMapping(value = "regi.do", method=RequestMethod.GET)
	public String regi() {
		logger.info("BitMemberController regi " + new Date());
		return "regi.tiles";
	}
	
	@ResponseBody
	@RequestMapping(value = "getId.do", method=RequestMethod.POST)
	public String getId(MemberDto mem) {
		logger.info("BitMemberController getId " + new Date());
		
		int count = bitMemService.getId(mem);
		
		String msg = "";
		if(count > 0) {
			msg = "YES";
		}else {
			msg = "NO";
		}
		return msg;
	}
	
	@RequestMapping(value = "regiAf.do", method=RequestMethod.POST)
	public String regiAf(MemberDto mem)throws Exception {
		logger.info("BitMemberController regiAf " + new Date());
		
		boolean b = bitMemService.addmember(mem);
		if(b) {
			return "redirect:/login.do";	
		}else {
			return "redirect:/regi.do";
		}		
	}
	
	@RequestMapping(value = "loginAf.do", method=RequestMethod.POST)
	public String loginAf(MemberDto dto, HttpServletRequest req) {
		logger.info("BitMemberController loginAf " + new Date());
		
		MemberDto login = bitMemService.login(dto);
		
		if(login != null && !login.getId().equals("")) {
			logger.info("BitMemberController loginAf SUC " + new Date());
			// session 저장
			req.getSession().setAttribute("login", login);
			req.getSession().setMaxInactiveInterval(60 * 60 * 2);

			return "redirect:/bbslist.do";			
		}else {	
			logger.info("BitMemberController loginAf Fail " + new Date());			
			return "redirect:/login.do";
		}		
		
	}
}







