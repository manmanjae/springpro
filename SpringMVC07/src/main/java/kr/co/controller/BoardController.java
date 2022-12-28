package kr.co.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.entity.Board;
import kr.co.service.BoardService;

@Controller //POJO 클래스
@RequestMapping("/board/*")
public class BoardController {

	// 메서드가 있는 서비스를 오토와이어드 해준다.
	@Autowired
	BoardService boardService;
	
	@GetMapping("/list")
	public String getList(Model model) {
		List<Board> list = boardService.getList();
		// 모델에 list를 객체바인딩
		model.addAttribute("list", list);
		return "/board/list";
	}

//	게시물 등록 화면
	@GetMapping("/register")
	public String register() {
		
		return "/board/register";
	}
	
//	게시물 등록
	@PostMapping("/register")
	public String register(Board vo, RedirectAttributes rttr) { // 파라미터수집(vo)
		boardService.register(vo); //게시물 등록
		rttr.addFlashAttribute("result", vo.getIdx()); //일회성 세션 꺼내올때는 ${result} 모달창 띄우기 위해 사용
		return "redirect:/board/list";
	}
	
	@GetMapping("/get")
	public String get(@RequestParam("idx") int idx, Model model) {
		Board vo = boardService.get(idx);
		model.addAttribute("vo", vo);
		return "board/get";
	}
	
	@GetMapping("/modify")
	public String modify(@RequestParam("idx") int idx, Model model) {
		Board vo = boardService.get(idx);
		model.addAttribute("vo", vo);
		return "board/modify";
	}
	
	@PostMapping("/modify")
	public String modify(Board vo) {
		boardService.modify(vo);
		return "redirect:/board/list";
	}
	
	@GetMapping("/remove")
	public String remove(@RequestParam("idx") int idx) {
		boardService.remove(idx);
		return "redirect:/board/list";
	}
	
	@GetMapping("/reply")
		public String reply(int idx, Model model) {
			Board vo = boardService.get(idx);
			model.addAttribute("vo", vo);
			return "board/reply";
	}
	@PostMapping("/reply")
	public String reply(Board vo) {
		// 답글에 필요한 처리
		boardService.replyProcess(vo); //답글이 저장됨
		return "redirect:/board/list";
	}
}

