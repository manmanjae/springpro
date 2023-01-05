package kr.co.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.entity.Board;
import kr.co.entity.Criteria;
import kr.co.entity.Member;
import kr.co.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	BoardMapper boardMapper;
	
	@Override
	public List<Board> getList(Criteria cri) {
		// 반영할 로직이 있으면 서비스 레이어에서 해준다. 컨트롤러에서 작성하던 메서드 내용을 서비스 레이어에서 작성한다. 3-Tier
		List<Board> list = boardMapper.getList(cri);
		
		return list;
	}

	// 로그인 로직
	@Override
	public Member login(Member vo) {
		Member mvo = boardMapper.login(vo);
		return mvo;
	}

	// 글 등록 로직
	@Override
	public void register(Board vo) {
		boardMapper.insertSelectKey(vo);
		
	}

	// 상세보기 로직
	@Override
	public Board get(int idx) {
		Board vo = boardMapper.read(idx);
		return vo;
	}

	//삭제하기
	@Override
	public void remove(int idx) {
		boardMapper.delete(idx);
		
	}

	//수정하기
	@Override
	public void modify(Board vo) {
		boardMapper.update(vo);
		
	}

	@Override
	public void replyProcess(Board vo) {
		// 답글만들기
		// 1. 원글(부모)의 정보를 가져오기(vo -> idx)
		Board parent = boardMapper.read(vo.getIdx());
		// 2. 원글의 boardGroup의 값을-> 답글정보에 저장하기
		vo.setBoardGroup(parent.getBoardGroup());
		// 3. 원글의 sequence의 값을 +1해서 답글정보에 저장
		vo.setBoardSequence(parent.getBoardSequence()+1);
		// 4. 원글의 level의 값을 +1해서 답글정보에 저장
		vo.setBoardLevel(parent.getBoardLevel()+1);
		// 5. 원글의 같은 boardGroup에 있는 글 중에서 부모글의 sequence보다 큰 값들을 모두 1씩 업데이트하기
		boardMapper.replySeqUpdate(parent);
		// 6. 답글을 db에 insert
		boardMapper.replyInsert(vo);
	}

	@Override
	public int totalCount() {
		int vo = boardMapper.totalCount();
		return vo;
	}


}
