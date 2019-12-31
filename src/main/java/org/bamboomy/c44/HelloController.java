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
		
		Iterable<ColorsTaken> userIterable = colorsTakenRepository.findByGameHash(gameHash);
		
		for(ColorsTaken userColor: userIterable) {
			
			if(userColor.getColor().equalsIgnoreCase("red")) {
				
				if(userColor.getColor().equalsIgnoreCase(user.getColor())) {
					
					board .setRedName("You");	
					
				}else {
					
					board .setRedName(userColor.getName());	
				}

			} else if(userColor.getColor().equalsIgnoreCase("green")) {
				
				if(userColor.getColor().equalsIgnoreCase(user.getColor())) {
					
					board .setGreenName("You");	
					
				}else {
					
					board .setGreenName(userColor.getName());	
				}

			}else  if(userColor.getColor().equalsIgnoreCase("blue")) {
				
				if(userColor.getColor().equalsIgnoreCase(user.getColor())) {
					
					board .setBlueName("You");	
					
				}else {
					
					board .setBlueName(userColor.getName());	
				}

			}else if(userColor.getColor().equalsIgnoreCase("yellow")) {
				
				if(userColor.getColor().equalsIgnoreCase(user.getColor())) {
					
					board .setYellowName("You");	
					
				}else {
					
					board .setYellowName(userColor.getName());	
				}
			} 
		}
		
		
		Game game = gameRepository.findByHash(gameHash);
		
		board.setGameName(game.getSentence());
		
		board.setPlayerHash(hash);
		
		model.addAttribute("board", board);

		return "hello";
	}

	@GetMapping({ "/board/" })
	public synchronized String board(Model model,
			@RequestParam(value = "id", required = true, defaultValue = "World") final String hash) {

		System.out.println(hash);

		ColorsTaken user = colorsTakenRepository.findByHash(hash);

		if(user == null) {
			
			return "negative";	
		}
		
		String gameHash = user.getGame();
		
		Board board = BoardController.getInstance().getBoard(gameHash);
		
		board.setViewColor(user.getColor());

		model.addAttribute("board", board);

		return "board";
	}
}