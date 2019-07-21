package org.bamboomy.c44.board.pieces;

import org.bamboomy.c44.board.Place;
import org.bamboomy.c44.board.Player;

public class Tower extends Piece{
	
	public Tower(Place place, int color) {
		super(place, color);
	}

	@Override
	public String getPieceName() {

		if(color == Player.RED) {
			
			return "../img/tower_red.png";
		}
		
		return "../img/tower.png";
	}

}
