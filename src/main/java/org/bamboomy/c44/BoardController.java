package org.bamboomy.c44;

import org.bamboomy.c44.board.Board;

import lombok.Getter;

public class BoardController {

	private static BoardController single_instance = null;

	@Getter
	private Board board = new Board();

	private BoardController() {
	}

	public static synchronized BoardController getInstance() {

		if (single_instance == null) {
			single_instance = new BoardController();
		}

		return single_instance;
	}
}
