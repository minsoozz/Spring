package bit.com.a.login;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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


	@Autowired private BitMemberService bitMemberService;


	@RequestMapping("login.do")
	public String login(Model model) {
		
		logger.info("BitMemberController login " + new Date());

		return "login.tiles";
	}
	
	@RequestMapping(value="account.do", method=RequestMethod.GET)
	public String account() {
		logger.info("BitMemberController account " + new Date());
		
		return "account.tiles";
	}
		@ResponseBody
	@RequestMapping(value="idcheck.do", method=RequestMethod.POST)
	public String idcheck(MemberDto mem)  throws Exception {
		logger.info("BitMemberController idcheck " + new Date());
		String id = mem.getId();
		String msg = "";
		int count = bitMemberService.idcheck(id);
		if(count > 0) {
			msg = "YES";
		} else {
			msg = "NO";
		}
		return msg;
	}
		
	@RequestMapping(value="accountAf.do", method=RequestMethod.POST)
	public String accountAf(MemberDto mem) throws Exception {
		logger.info("BitMemberController accountAf " + new Date());
		
		boolean b = bitMemberService.addMember(mem);
	
		if(b) {
			return "redirect:/login.do";
		} else {
			return "redirect:/account.do";
		}
		
	
	}
	@ResponseBody
	@RequestMapping(value="loginAf.do", method=RequestMethod.POST)
	public  Map<String, Object> loginAf (MemberDto mem, HttpSession session, HttpServletRequest req) throws Exception {
		logger.info("BitMemberController loginAf " + new Date());
		int count = 0;
		Map<String, Object> map = new HashMap<String, Object>();

		count = bitMemberService.logincheck(mem);
		System.out.println("컨트롤러에서 count : " + count);
		if (count > 0) {

			map.put("logincheck", count);
			
			// 세션 부분의 로그인 이 없어 졌을 때 로그인으로 다시 보내주어야 한다
			session.setAttribute("login", mem); 
			req.getSession().setMaxInactiveInterval(50000);
		} else {

			map.put("logincheck", count);

		}
		return map;
	}
}
