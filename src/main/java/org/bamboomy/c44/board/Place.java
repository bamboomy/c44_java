package org.bamboomy.c44.board;

import org.bamboomy.c44.board.pieces.Piece;

import lombok.Getter;
import lombok.Setter;

public class Place {

	@Getter
	private final int color;

	public static int BLACK = 0, WHITE = 1;

	@Getter
	@Setter
	private Piece piece;

	public Place(int color) {

		if (color != BLACK && color != WHITE) {

			throw new RuntimeException("invalid color:" + color);
		}

		this.color = color;
	}

	public String getCssName() {

		if (color == BLACK) {

			return "piece_on_black";

		} else {

			return "piece_on_white";
		}
	}

	public boolean hasPiece() {

		return piece != null;
	}
}
