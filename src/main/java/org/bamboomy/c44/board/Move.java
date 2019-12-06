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

	protected Piece takenPiece;

	@Getter
	protected boolean isRocade = false;

	public Move(Place from, Place to, Piece piece) {

		this.from = from;
		this.to = to;

		this.piece = piece;

		to.addMove(this);
	}

	public void execute() {

		if (to.getPiece() != null) {

			takenPiece = to.getPiece();
			to.remove(to.getPiece());
		}

		from.remove(piece);

		to.setPiece(piece);
	}

	public void rollBack() {
		// TODO Auto-generated method stub

	}
}
