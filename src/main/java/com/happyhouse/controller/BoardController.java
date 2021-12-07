package com.happyhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.happyhouse.model.Board;
import com.happyhouse.model.dto.BoardParameterDto;
import com.happyhouse.model.service.board.BoardService;

@Controller
@RequestMapping("/board2")
public class BoardController {
	
	@Autowired
	private BoardService boardService;

	@GetMapping("/list")
	public String list(Model model, BoardParameterDto parameter) throws Exception {
		List<Board> list = boardService.listBoard(parameter);
		model.addAttribute("list", list);
		return "board/list";
	}
	
	
	
	
}
