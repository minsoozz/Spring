package bit.com.a.dao;

import java.util.List;

import bit.com.a.model.PollDto;
import bit.com.a.model.PollSubDto;
import bit.com.a.model.Voter;

public interface BitPollDao {
	
	public List<PollDto> getPollAllList();
	public int isVote(Voter voter);
	
	// 투표만들기
	public void makePoll(PollDto poll);
	public void makePollSub(PollSubDto pollsub);
	
	// 투표하기
	public PollDto getPoll(PollDto poll);
	public List<PollSubDto> getPollSubList(PollDto poll);
	
	// 투표
	public void pollingVoter(Voter voter);
	public void pollingPoll(Voter voter);
	public void pollingSub(Voter voter);
}
