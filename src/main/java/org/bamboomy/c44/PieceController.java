package org.bamboomy.c44;

import org.bamboomy.c44.board.Board;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/peace")
public class PieceController {

	@RequestMapping(value = "/{md5}/{hash}", method = RequestMethod.GET)
	public synchronized String getData(@PathVariable("md5") String md5, @PathVariable("hash") String hash) {

		System.out.println(md5);

		System.out.println(hash);

		Board board = BoardController.getInstance().getBoard(hash);

		board.click(md5);

		return "succezzz";
	}
}
