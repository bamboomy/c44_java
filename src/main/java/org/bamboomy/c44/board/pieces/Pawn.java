package org.bamboomy.c44.board.pieces;

import org.bamboomy.c44.board.Place;
import org.bamboomy.c44.board.Player;

public class Pawn extends Piece {

	public Pawn(Place place, int color) {
		super(place, color);
	}

	@Override
	public String getPieceName() {

		if(color == Player.RED) {
			
			return "../img/pawn_red.png";
		}
		
		return "../img/pawn.png";
	}
}
