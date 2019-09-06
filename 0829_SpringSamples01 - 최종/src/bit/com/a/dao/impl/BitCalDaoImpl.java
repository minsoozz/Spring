package bit.com.a.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bit.com.a.dao.BitCalendarDao;
import bit.com.a.model.CalendarDto;
import bit.com.a.model.MemberDto;

@Repository
public class BitCalDaoImpl implements BitCalendarDao {

	@Autowired
	SqlSession sqlSession;
	
	String namespace = "Cal.";	
	
	@Override
	public List<CalendarDto> getCalendarList(String id, String String) {
		
		Map<String,Object> map = new HashMap<String, Object>();
		
		map.put("id", id);
		map.put("String", String);
		
		System.out.println("String : " + String);
		System.out.println("id : " + id);

		
		List<CalendarDto> list = sqlSession.selectList(namespace + "getCalendar", map);

		
		return list;
	}

}
