package bit.com.a.controller;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bit.com.a.model.CalendarDto;
import bit.com.a.model.MemberDto;
import bit.com.a.service.BitCalendarService;
import bit.com.a.service.impl.BitCalendarServiceImpl;

@Controller
public class BitCalendarController {

	@Autowired
	BitCalendarService calService;

	@RequestMapping(value = "#", method = RequestMethod.GET)
	public String Calendar(Model model) throws Exception {

		return "calendar.tiles";
	}
	
	// 내 일정 보는거 같음
	
	@RequestMapping(value="Calendar.do" , method = RequestMethod.GET)
	public String getCalendarList(Model model, HttpSession session, CalendarDto dto) throws Exception {
		
		MemberDto mem = (MemberDto) session.getAttribute("login");
		
		Calendar cal = Calendar.getInstance();
		
		cal.set(Calendar.DATE, 1);
		String syear = dto.getYear()+"";
		String smonth = dto.getMonth()+"";
		
		int year = cal.get(Calendar.YEAR);
 
		int month = cal.get(Calendar.MONTH) + 1;
 
		if(month < 1){
		   month = 12;
		   year--;
		   
		}
		if(month > 12){
		   month = 1;
		   year++;
		}
		cal.set(year, month - 1, 1);  //연월일 셋팅 완료
		// 요일 
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

		model.addAttribute("cal", cal);
		model.addAttribute("year", year);
		model.addAttribute("month",month);
		
		List<CalendarDto> list = calService.getCalendarList(mem.getId(), year + two(month+""));
		
		model.addAttribute("CalList",list);
		
		
		
		return "calendar.tiles";
		
	}
	
	public String two(String msg){
	
		return msg.trim().length() < 2?"0"+msg:msg.trim();
		
	}
	
	public String makeTable(int year, int month, int day, List<CalendarDto> list){
		   String str = "";
		   // 20190731
		   String dates = (year +"") + two(month+"") + two(day+"");   //0붙여주는 함수 위에꺼 씀
		   
		   str += "<table>";
		   str +="<col width='98'>";
		   
		   for(CalendarDto dto : list){
		      if(dto.getRdate().substring(0, 8).equals(dates)){
		         str += "<tr bgcolor='green'>";
		         str += "<td>";
		         
		         str += "<a href='caldetail.jsp?seq=" + dto.getSeq() + "'>";
		         
		         str += "<font style='font-size:8;color:red'>";
		         
		         str += dot3(dto.getTitle());
		         
		         
		         str += "</font>";
		         
		         
		         str += "</a>";
		         
		         str += "</td>";
		         str += "</tr>";
		      	System.out.println(dto.getRdate().substring(0, 8));
		      }
		      
		   }
		   
		   
		   str += "</table>";
		   
		   return str;
		   
		   
		}
	
	public String dot3(String msg){
		   String str = "";
		   if(msg.length() >= 10){
		      str = msg.substring(0, 10);
		      str += "...";
		   }else{
		      str = msg.trim();
		   }
		   return str;
		   
		   
		}

}
