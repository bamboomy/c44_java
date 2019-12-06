package org.bamboomy.c44.board;

import org.bamboomy.c44.board.pieces.Piece;

import lombok.Getter;
import lombok.Setter;

public class Move {

	@Getter
	@Setter
	protected Place from, to;

	@Getter
	@Setter
	protected Piece piece;

	public Move(Place from, Place to, Piece piece) {

		this.from = from;
		this.to = to;

		this.piece = piece;
	}

	public void execute() {
		// TODO Auto-generated method stub
		
	}

	public void rollBack() {
		// TODO Auto-generated method stub
		
	}
}
