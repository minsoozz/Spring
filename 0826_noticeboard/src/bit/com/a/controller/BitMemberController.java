package bit.com.a.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.RuntimeErrorException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import bit.com.a.model.BbsDto;
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
	public String accountAf(MemberDto dto, Model model, HttpServletRequest req) throws Exception {

		boolean b = bitMemberService.addMember(dto);
		if (b) {
			logger.info("회원가입 되었습니다  " + new Date());
			return "login";
		}
		return "account";

	}

	@RequestMapping(value = "idcheck", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> idcheck(@RequestBody String id) throws Exception {

		System.out.println("idcheck id : " + id);
		int count = 0;

		count = bitMemberService.idcheck(id);
		System.out.println("count : " + count);
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("check", count);

		return map;
	}

	@RequestMapping(value = "logincheck", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> logincheck(MemberDto dto, HttpSession session, HttpServletRequest req) throws Exception {
		System.out.println("logincheck 도착");
		Map<String, Object> map = new HashMap<String, Object>();

		int count = 0;

		count = bitMemberService.logincheck(dto);
		System.out.println("컨트롤러에서 count : " + count);
		if (count > 0) {

			map.put("logincheck", count);
			
			// 세션 부분의 로그인 이 없어 졌을 때 로그인으로 다시 보내주어야 한다
			req.getSession().setAttribute("login", dto); 
			req.getSession().setMaxInactiveInterval(5);
		} else {

			map.put("logincheck", count);

		}
		return map;
	}

}
