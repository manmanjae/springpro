package kr.board.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Boardcontroller {

	@RequestMapping("/")
	public String main() {
		return "main";
	}

}
