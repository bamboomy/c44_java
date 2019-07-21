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
		
		} else if(color == Player.YELLOW) {
			
			return "../img/pawn_yellow.png";

		} else if (color == Player.GREEN) {

			return "../img/pawn_green.png";

		} else if (color == Player.BLUE) {

			return "../img/pawn_blue.png";
		}

		throw new RuntimeException("invalid color in pawn");
	}

	@Override
	public void click() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unselect() {
		// TODO Auto-generated method stub
		
	}
}
