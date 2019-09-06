package bit.com.a.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bit.com.a.dao.BitPollDao;
import bit.com.a.model.PollDto;
import bit.com.a.model.PollSubDto;
import bit.com.a.model.Voter;

@Repository
public class BitPollDaoImpl implements BitPollDao {

	@Autowired
	SqlSession sqlSession;

	String ns = "Poll.";

	@Override
	public List<PollDto> getPollAllList() {
		// TODO Auto-generated method stub
		return sqlSession.selectList(ns + "getPollAllList");
	}

	@Override
	public int isVote(Voter voter) {

		return sqlSession.selectOne(ns + "isVote", voter);
	}

	@Override
	public void makePoll(PollDto poll) {
		sqlSession.insert(ns + "makePoll", poll);

	}

	@Override
	public void makePollSub(PollSubDto pollsub) {
		sqlSession.insert(ns + "makePollSub", pollsub);

	}

	@Override
	public PollDto getPoll(PollDto poll) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(ns + "getPoll", poll);
	}

	@Override
	public List<PollSubDto> getPollSubList(PollDto poll) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(ns + "getPollSubList", poll);
	}

	@Override
	public void pollingVoter(Voter voter) {
		// TODO Auto-generated method stub
		sqlSession.insert(ns + "pollingVote", voter);
	}

	@Override
	public void pollingPoll(Voter voter) {
		sqlSession.update(ns+"pollingPoll",voter);
		
	}

	@Override
	public void pollingSub(Voter voter) {
		sqlSession.update(ns+"pollingSub",voter);
	}
	
}
