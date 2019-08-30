package bit.com.a;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import bit.com.a.model.MyClass;
import bit.com.a.model.MyData;
 

/*컨트롤러 주석문을 먼저 작성하자 !*/
@Controller
public class HelloController {
	
	private static final Logger logger = LoggerFactory.getLogger(HelloController.class);
	/* 1 */
	@RequestMapping(value="hello", method=RequestMethod.GET)
	public String hello(Model model) {
		logger.info("hellocontroller에 도착했습니다 " + new Date());
		
		MyClass cls = new MyClass();
		
		cls.setNumber(1001);
		cls.setName("민수");
		
		model.addAttribute("mycls",cls);
		
		return "hello";
	}
	
	
	/* 2 */
	@RequestMapping(value="inputData", method= {RequestMethod.GET,RequestMethod.POST})
	public String inputData(Model model, MyClass mycls) throws Exception {
		logger.info("hellocontroller inputData()" + new Date());
		logger.info(mycls.toString());
		System.out.println(mycls.getName());
		System.out.println(mycls.getNumber());
		
		model.addAttribute("mycls", mycls);
		
		return "hello";
	}
	
	
	/* 3 */
	@RequestMapping(value="move", method= {RequestMethod.GET,RequestMethod.POST})
		public String move() {
			logger.info("hello controller move() " + new Date());
		
//			return "hello";
		  return "redirect:/hello"; // controller 에서 controller 로 이동  	
		/* return "forward:/hello"; // controller 에서 controller에서 데이터를 가지고 이동*/	
	}
	
	
	/* 4 */
	@ResponseBody /* Ajax 사용을 위하여 적어주어야한다 */
		@RequestMapping(value="idcheck",
						produces="applecation/String; charset=UTF-8",
						method= {RequestMethod.GET,RequestMethod.POST})
		public String idcheck(String id) {
		
		logger.info("hello controller idcheck " + new Date());
		logger.info("id : " + id);
		
		String str = "ㅎ";
		
		return str;
	}
	
	
	/* 5 */
	@ResponseBody
		@RequestMapping(value = "account", method=RequestMethod.POST)
		public Map<String, Object> account(MyData my) {
		logger.info("account() "  + new Date());		
		logger.info(my.toString());
		
		// 넘길 데이터 준비 
		Map<String,Object> rmap = new HashMap<String, Object>();
		
		rmap.put("msg", "메시지 입니다");
		rmap.put("data", "민수");
		
		return rmap;
	}
	
	/* 6 */
	@ResponseBody
	@RequestMapping(value = "updateUser", method=RequestMethod.POST)
							/* 맵으로 받을때는 매개변수에 이렇게 @RequestBody 명시를 해주어야한다 */
	public Map<String,Object> updateUser(@RequestBody Map<String,Object> map){ 
		logger.info("updateUser() " + new Date());
		
		logger.info(map.get("name")+"");
		logger.info(map.get("tel")+"");
		logger.info(map.get("email")+"");
		logger.info(map.get("birth")+"");
		
		Map<String,Object> rmap = new HashMap<String, Object>();
		
		rmap.put("msg", "데이터 입니다");
		rmap.put("data", "일민수");
		
		return rmap;
	}
	
	
	
	
	
	
	
	
	
}
