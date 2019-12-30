package org.bamboomy.c44;

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
		
		model.addAttribute("board", BoardController.getInstance().getBoard(gameHash));

		return "hello";
	}
}