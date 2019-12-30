package org.bamboomy.c44;

import org.bamboomy.c44.board.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

	@Autowired 
	private GameRepository gameRepository;

	@Autowired 
	private ColorsTakenRepository colorsTakenRepository;

	@GetMapping({ "/" })
	public String hello(Model model,
			@RequestParam(value = "id", required = true, defaultValue = "World") final String hash) {

		System.out.println(hash);

		ColorsTaken user = colorsTakenRepository.findByHash(hash);

		if(user == null) {
			
			return "negative";	
		}
		
		String gameHash = user.getGame();
		
		Board board = BoardController.getInstance().getBoard(gameHash);
		
		if(user.getColor().equalsIgnoreCase("red")) {
			
			board .setRedName(user.getName());
		} else if(user.getColor().equalsIgnoreCase("green")) {
			
			board .setGreenName(user.getName());
		}else  if(user.getColor().equalsIgnoreCase("blue")) {
			
			board .setBlueName(user.getName());
		}else if(user.getColor().equalsIgnoreCase("yellow")) {
			
			board .setYellowName(user.getName());
		} 
		
		Game game = gameRepository.findByHash(gameHash);
		
		board.setGameName(game.getSentence());
		
		model.addAttribute("board", board);

		return "hello";
	}
}