package org.bamboomy.c44;

import org.bamboomy.c44.board.Board;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/piece")
public class PieceController {
	
	private Board board = BoardController.getInstance().getBoard();

	@RequestMapping(value = "/{md5}", method = RequestMethod.GET)
	public synchronized String getData(@PathVariable("md5") String md5) {
		
		System.out.println(md5);
		
		board.click(md5);
		
		return "succezzz";
	}

}
