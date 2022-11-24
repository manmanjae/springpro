package kr.board.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.board.entity.Board;

@Controller
public class Boardcontroller {

	// /boardList.do
	//HandlerMapping
	@RequestMapping("/boardList.do")
	public String boardList(Model model) {
		
		Board VO = new Board();
		VO.setIdx(1);
		VO.setTitle("게시판실습");
		VO.setContent("게시판실습");
		VO.setWriter("김민재");
		VO.setIndate("2022-11-22");
		VO.setCount(0);
		
		List<Board> list = new ArrayList<Board>();
		list.add(VO);
		list.add(VO);
		list.add(VO);
		model.addAttribute("list" , list);
		return "boardList" ; // /WEB-INF/views/~.jsp (servlet-context) -> forward
	}
	
}
