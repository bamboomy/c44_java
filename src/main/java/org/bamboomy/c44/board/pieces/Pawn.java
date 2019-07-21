package org.bamboomy.c44.board.pieces;

import java.util.ArrayList;

import org.bamboomy.c44.board.Place;
import org.bamboomy.c44.board.Player;

public class Pawn extends Piece {

	private int xDelta, yDelta;

	private boolean neverMoved = true;

	public Pawn(Place place, int color, int xDelta, int yDelta) {
		super(place, color);

		this.xDelta = xDelta;
		this.yDelta = yDelta;
	}

	@Override
	public String getPieceName() {

		if (color == Player.RED) {

			return "../img/pawn_red.png";

		} else if (color == Player.YELLOW) {

			return "../img/pawn_yellow.png";

		} else if (color == Player.GREEN) {

			return "../img/pawn_green.png";

		} else if (color == Player.BLUE) {

			return "../img/pawn_blue.png";
		}

		throw new RuntimeException("invalid color in pawn");
	}

	@Override
	protected void setAttackablePlaces() {

		attackablePlaces = new ArrayList<Place>();

		Place otherPlace = place.getBoard().getPlacez()[place.getX() + xDelta][place.getY() + yDelta];

		if (otherPlace != null && !otherPlace.hasPiece()) {

			attackablePlaces.add(otherPlace);

			if (neverMoved) {

				otherPlace = place.getBoard().getPlacez()[place.getX() + (xDelta * 2)][place.getY() + (yDelta * 2)];

				if (otherPlace != null && !otherPlace.hasPiece()) {

					attackablePlaces.add(otherPlace);
				}
			}
		}
	}
}
