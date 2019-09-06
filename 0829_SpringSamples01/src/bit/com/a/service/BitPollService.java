package bit.com.a.service;

import java.util.List;

import bit.com.a.model.PollBean;
import bit.com.a.model.PollDto;
import bit.com.a.model.PollSubDto;
import bit.com.a.model.Voter;

public interface BitPollService {
	
	public List<PollDto> getPollAllList(String id);
	public void makePoll(PollBean pbean);
	
	public PollDto getPoll(PollDto poll);
	public List<PollSubDto> getPollSubList(PollDto poll);
	
	public void polling(Voter voter);

}
