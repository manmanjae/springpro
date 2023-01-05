package kr.co.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.entity.Board;
import kr.co.entity.Criteria;
import kr.co.entity.Member;

@Mapper
public interface BoardMapper {
	public List<Board> getList(Criteria cri);
	public void insert(Board vo);
	public void insertSelectKey(Board vo);
	public Member login(Member vo);
	public Board read(int idx);
	public void delete(int idx);
	public void update(Board vo);
	public void replySeqUpdate(Board parent);
	public void replyInsert(Board vo);
	public int totalCount();
}
