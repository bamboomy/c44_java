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

		model.addAttribute("board", BoardController.getInstance().getBoard(hash));

		Iterable<ColorsTaken> gamez = colorsTakenRepository.findAll();

		for (ColorsTaken ct: gamez) {

			System.out.println(ct.getJavaHash());
		}
		
		/*
		Iterable<Game> gamez = gameRepository.findAll();

		for (Game game : gamez) {

			System.out.println(game.getSentence());
		}
		
		System.out.println(gameRepository.findByHash("921164de9eec315810062a7753cf8a77").getSentence());
		*/

		return "negative";
	}
}