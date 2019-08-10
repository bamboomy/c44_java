package org.bamboomy.c44;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

import org.bamboomy.c44.board.Board;
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

		return "hello";
	}
}