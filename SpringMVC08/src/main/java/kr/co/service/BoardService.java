package kr.co.service;

import java.util.List;

import kr.co.entity.Board;
import kr.co.entity.Criteria;
import kr.co.entity.Member;

//ServiceImpl을 바라본다.
public interface BoardService {

	public List<Board> getList(Criteria cri);
	public Member login(Member vo);
	public void register(Board vo);
	public Board get(int idx);
	public void remove(int idx);
	public void modify(Board vo);
	public void replyProcess(Board vo);
	public int totalCount();
}
