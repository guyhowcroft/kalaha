package com.guyhowcroft.kalaha.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guyhowcroft.kalaha.controller.game.Board;

@Controller
public class GameController {
	
	Board board = new Board();
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@PostMapping("/newGame")
	public @ResponseBody Board newGame() {
		board = new Board();
		board.initialiseBoard();
		return board;
	}
	
	@PostMapping("/turn")
	public @ResponseBody Boolean makeTurn(@RequestParam("position") String position) {
		return board.makeTurn(Integer.valueOf(position));
	}
	
	@GetMapping("/getBoard")
	public @ResponseBody Board getBoard(){
		return board;
	}
	
}
