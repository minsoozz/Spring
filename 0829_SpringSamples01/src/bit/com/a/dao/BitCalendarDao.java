package bit.com.a.dao;

import java.util.List;

import bit.com.a.model.CalendarDto;

public interface BitCalendarDao {
	
	public List<CalendarDto> getCalendarList(String id, String string);
}
