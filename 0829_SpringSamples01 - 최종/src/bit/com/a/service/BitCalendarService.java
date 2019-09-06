package bit.com.a.service;

import java.util.List;

import bit.com.a.model.CalendarDto;

public interface BitCalendarService {

	public List<CalendarDto> getCalendarList(String id, String string);
}
