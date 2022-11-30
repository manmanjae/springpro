package kr.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.board.entity.Board;
import kr.board.mapper.BoardMapper;

@Controller
public class Boardcontroller {

	// boardList.do
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
	@GetMapping("/boardForm.do")
	public String boardForm() {
		return "boardForm";
	}
	@PostMapping("/boardInsert.do")
	public String boardInsert(Board vo) { //파라미터 수집(Board)
		mapper.boardInsert(vo);
		return "redirect:/boardList.do";
	}
	@GetMapping("/boardContent.do")
	public String boardContent(@RequestParam("idx") int idx, Model model) {
		Board vo=mapper.boardContent(idx);
		
		mapper.boardCount(idx);
		model.addAttribute("vo", vo);
		
		return "boardContent";
	}
	@GetMapping("/boardDelete.do/{idx}")
	public String boardDelete(@PathVariable("idx") int idx) {
		mapper.boardDelete(idx);
		return "redirect:/boardList.do";
	}
	@GetMapping("/boardUpdateForm.do/{idx}")
	public String boardUpdateForm(@PathVariable("idx") int idx, Model model) {
		Board vo=mapper.boardContent(idx);
		model.addAttribute("vo" , vo);
		return "boardUpdate";
	}
	@PostMapping("boardUpdate.do")
	public String boardUpdate(Board vo) {
		mapper.boardUpdate(vo);
		return "redirect:/boardList.do";
		
	}
}
