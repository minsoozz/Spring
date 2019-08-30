package bit.com.a.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bit.com.a.dao.BitCalendarDao;
import bit.com.a.model.CalendarDto;
import bit.com.a.service.BitBbsService;
import bit.com.a.service.BitCalendarService;

@Service
public class BitCalendarServiceImpl implements BitCalendarService {

	@Autowired
	BitCalendarDao Caldao;

	@Override
	public List<CalendarDto> getCalendarList(String id, String string) {
		// TODO Auto-generated method stub
		return Caldao.getCalendarList(id,string);
	}

}
