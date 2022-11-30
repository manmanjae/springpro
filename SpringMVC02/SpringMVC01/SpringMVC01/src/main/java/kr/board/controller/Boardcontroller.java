package kr.board.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.board.entity.Board;
import kr.board.mapper.BoardMapper;

@Controller
public class Boardcontroller {

	// /boardList.do
	// 자동 주입 특정객체를 필요할때 찾아주는 의존성 주입
	@Autowired
	private BoardMapper mapper;
	//HandlerMapping
	@RequestMapping("/boardList.do")
	public String boardList(Model model) {
		
		List<Board> list = mapper.getLists();
		model.addAttribute("list" , list);
		return "boardList" ; // /WEB-INF/views/~.jsp (servlet-context) -> forward
	}
	
}
