package bit.com.a.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bit.com.a.dao.BitPollDao;
import bit.com.a.model.PollBean;
import bit.com.a.model.PollDto;
import bit.com.a.model.PollSubDto;
import bit.com.a.model.Voter;
import bit.com.a.service.BitPollService;

@Service
public class BitPollServiceImpl implements BitPollService {

	@Autowired
	BitPollDao pollDao;

	@Override
	public List<PollDto> getPollAllList(String id) {

		// 1. 모든 투표 목록을 가져온다
		List<PollDto> list = pollDao.getPollAllList();

		// 2. 투표가 가능한지 정리해서 넘겨줄 리스트
		List<PollDto> plist = new ArrayList<PollDto>();

		for (PollDto poll : list) {
			int pollid = poll.getPollid(); // 투표번호
			System.out.println("pollid : "  + pollid);
			/* 두번째는 아무거나 첫번째랑 세번째가 중요하다 */
			int count = pollDao.isVote(new Voter(pollid, -1, id));
			
			System.out.println("count : " + count);
			
			if (count == 1) { // 투표했음
				poll.setVote(true);
			} else { // 투표 안했음
				poll.setVote(false);
			}
			plist.add(poll);
		}
		for (int i = 0; i < plist.size(); i++) {
			System.out.println(plist.get(i));
		}
		return plist;
	}

	@Override
	public void makePoll(PollBean pbean) {
		// 투표 항목
		PollDto poll = new PollDto(pbean.getId(),
				pbean.getQuestion(),
				pbean.getsdate(),
				pbean.getEdate(),
				pbean.getItemcount(),
				0);
		
		pollDao.makePoll(poll);

		// 보기
		String answer[] = pbean.getPollnum();
		
		for (int i = 0; i < pbean.getItemcount(); i++) {
			PollSubDto pollsub = new PollSubDto();
//			pollsub.setPollid(poll.getPollid());
			System.out.println(pollsub.getAnswer());
			pollsub.setAnswer(answer[i]);
			pollDao.makePollSub(pollsub);
			
		}
	}

	@Override
	public PollDto getPoll(PollDto poll) {
		// TODO Auto-generated method stub
		return pollDao.getPoll(poll);
	}

	@Override
	public List<PollSubDto> getPollSubList(PollDto poll) {
		// TODO Auto-generated method stub
		return pollDao.getPollSubList(poll);
	}

	@Override
	public void polling(Voter voter) {
		pollDao.pollingVoter(voter);
		pollDao.pollingPoll(voter);
		pollDao.pollingSub(voter);
	}
	
	
}
