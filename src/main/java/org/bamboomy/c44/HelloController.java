package org.bamboomy.c44;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

	@GetMapping({ "/" })
	public String hello(Model model,
			@RequestParam(value = "id", required = false, defaultValue = "World") final String hash,
			@RequestParam(value = "playerHash", required = false, defaultValue = "World") final String playerHash) {

		model.addAttribute("board", BoardController.getInstance().getBoard(hash));
		model.addAttribute("playerHash", playerHash);

		return "hello";
	}
}