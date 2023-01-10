package kr.co.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.entity.Board;
import kr.co.entity.Criteria;
import kr.co.entity.PageMaker;
import kr.co.service.BoardService;

@Controller //POJO 클래스
@RequestMapping("/board/*")
public class BoardController {

	// 메서드가 있는 서비스를 오토와이어드 해준다.
	@Autowired
	BoardService boardService;
	
	@RequestMapping("/list")
	public String getList(Criteria cri, Model model) { // type, keyword
		List<Board> list = boardService.getList(cri);
		// 모델에 list를 객체바인딩
		model.addAttribute("list", list);
		
		// 페이징 처리에 필요한 부분
		PageMaker pageMaker=new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(boardService.totalCount(cri)); // 전체페이지의 수를 구해야한다.
		model.addAttribute("pageMaker", pageMaker);
		
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
	public String get(@RequestParam("idx") int idx, Model model,@ModelAttribute("cri") Criteria cri) {
		Board vo = boardService.get(idx);
		model.addAttribute("vo", vo);
		return "board/get";
	}
	
	@GetMapping("/modify")
	public String modify(@RequestParam("idx") int idx, Model model,@ModelAttribute("cri") Criteria cri) {
		Board vo = boardService.get(idx);
		model.addAttribute("vo", vo);
		return "board/modify";
	}
	
	@PostMapping("/modify")
	public String modify(Board vo, Criteria cri , RedirectAttributes rttr) {
		boardService.modify(vo);
		rttr.addAttribute("page",cri.getPage());
		rttr.addAttribute("perPageNum",cri.getPerPageNum());
		rttr.addAttribute("type",cri.getType());
		rttr.addAttribute("keyword",cri.getKeyword());
		return "redirect:/board/list"; // ?page=2&perPageNum=5
	}
	
	@GetMapping("/remove")
	public String remove(@RequestParam("idx") int idx, Criteria cri, RedirectAttributes rttr) {
		boardService.remove(idx);
		rttr.addAttribute("page",cri.getPage());
		rttr.addAttribute("perPageNum",cri.getPerPageNum());
		rttr.addAttribute("type",cri.getType());
		rttr.addAttribute("keyword",cri.getKeyword());
		return "redirect:/board/list";
	}
	
	@GetMapping("/reply")
		public String reply(int idx, Model model, @ModelAttribute("cri") Criteria cri) {
			Board vo = boardService.get(idx);
			model.addAttribute("vo", vo);
			return "board/reply";
	}
	@PostMapping("/reply")
	public String reply(Board vo, Criteria cri, RedirectAttributes rttr) {
		// 답글에 필요한 처리
		boardService.replyProcess(vo); //답글이 저장됨
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("type",cri.getType());
		rttr.addAttribute("keyword",cri.getKeyword());
		
		return "redirect:/board/list";
	}
}

