package kr.co.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.entity.Member;
import kr.co.service.BoardService;

@Controller
@RequestMapping("/login/*")
public class LoginController {

	@Autowired
	BoardService boardService;
	
	@RequestMapping("/loginProcess")
	public String login(Member vo, HttpSession session) {
		Member mvo = boardService.login(vo);
		if(mvo!=null) { // 로그인 성공
			session.setAttribute("mvo", mvo); // 객체바인딩 -> ${!empty mvo}
		}
		return "redirect:/board/list";
		
	}
	
	@RequestMapping("/logoutProcess")
	public String logout(HttpSession session) {
		session.invalidate(); // 세션 무효화 로그아웃 처리
		return "redirect:/board/list";
	}
}
