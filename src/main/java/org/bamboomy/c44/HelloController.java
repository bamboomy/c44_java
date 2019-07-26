package org.bamboomy.c44;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

	@GetMapping({ "/" })
	public String hello(Model model,
			@RequestParam(value = "id", required = false, defaultValue = "World") final String hash) {

		System.out.println(hash);

		model.addAttribute("board", BoardController.getInstance().getBoard(hash));

		return "hello";
	}
}