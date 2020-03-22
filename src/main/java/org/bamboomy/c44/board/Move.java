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

	protected ArrayList<Move> performedMoves;

	protected boolean rememberMoved;

	public Move(Place from, Place to, Piece piece, boolean addMove) {

		this.from = from;
		this.to = to;

		this.piece = piece;

		if (addMove) {

			to.addMove(this);
		}
	}

	public void execute(ArrayList<Move> performedMoves) {

		if (to.getPiece() != null) {

			takenPiece = to.getPiece();
			to.remove(to.getPiece());

			takenPiece.getPlayer().getPiecez().remove(takenPiece.getPlayer().getPiecez().indexOf(takenPiece));
		}

		from.remove(piece);

		to.setPiece(piece);

		piece.setCurrentPlace(to);

		if (performedMoves != null) {

			performedMoves.add(this);

			this.performedMoves = performedMoves;
		}

		rememberMoved = piece.isMoved();

		piece.setMoved(true);
	}

	public void rollBack() {

		if (takenPiece != null) {

			to.remove(piece);

			to.setPiece(takenPiece);

			takenPiece.getPlayer().getPiecez().add(takenPiece);

			takenPiece.setCurrentPlace(to);

		} else {

			to.remove(piece);
		}

		from.setPiece(piece);

		piece.setCurrentPlace(from);

		if (performedMoves != null) {

			performedMoves.remove(performedMoves.indexOf(this));
		}

		piece.setMoved(rememberMoved);
	}
	
	void show(Color color) {
		
		to.setBorder(true, color);
		from.setBorder(true, color);
	}

	void hide() {

		to.setBorder(false);
		from.setBorder(false);
	}
}
