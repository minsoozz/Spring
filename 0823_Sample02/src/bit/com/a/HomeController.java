package bit.com.a;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

// @Component 자동적으로 생성을 하게 해준다..? 검색해보기 가끔 쓰인다..

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/* 그냥 이렇게 생각해서 사용도 가능하다 */
	@RequestMapping("/home")
	public ModelAndView home() {
		logger.info("HomeController home " + new Date());
//		System.out.println("HomeController home()");
		
		ModelAndView view = new ModelAndView();
	
		view.setViewName("/home"); // home.jsp로 가라
		
		return view;
	}
	
	/*외부에서는 value값을 보고 들어온다			GET 방식이다 */
	@RequestMapping(value="hello.do", method=RequestMethod.GET)
	public String hello(Model model) {	// 데이터를 보내주기 위해서?!
		logger.info("HelloController hello " + new Date());
		
		model.addAttribute("name", "홍길동"); // req.setAttribute와 동일하다
		
		return "hello";	// hello.jsp 로 가라 ! 가고싶은 파일의 파일명
						// 셋팅을 해 놓은 곳은 DispatcherServlet = Servlet-context
	
	}
	
}
