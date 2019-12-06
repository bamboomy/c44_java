package org.bamboomy.c44.board;

import java.util.ArrayList;

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

	private ArrayList<Move> performedMoves;

	public Move(Place from, Place to, Piece piece) {

		this.from = from;
		this.to = to;

		this.piece = piece;

		to.addMove(this);
	}

	public void execute(ArrayList<Move> performedMoves) {

		if (to.getPiece() != null) {

			takenPiece = to.getPiece();
			to.remove(to.getPiece());
		}

		from.remove(piece);

		to.setPiece(piece);

		if (performedMoves != null) {

			performedMoves.add(this);

			this.performedMoves = performedMoves;
		}
	}

	public void rollBack() {

		if (takenPiece != null) {

			to.remove(piece);

			to.setPiece(takenPiece);

		} else {

			to.remove(piece);
		}

		from.setPiece(piece);

		if (performedMoves != null) {

			performedMoves.remove(performedMoves.indexOf(this));
		}
	}
}
