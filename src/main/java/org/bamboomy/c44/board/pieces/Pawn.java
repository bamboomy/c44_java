package org.bamboomy.c44.board.pieces;

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
	public void click() {

		if (!selected) {

			place.getBoard().getPlayerz()[color].setSelected(this);

			Place otherPlace = place.getBoard().getPlacez()[place.getX() + xDelta][place.getY() + yDelta];

			if (otherPlace != null && !otherPlace.hasPiece()) {

				otherPlace.attack(color);

				if (neverMoved) {

					otherPlace = place.getBoard().getPlacez()[place.getX() + (xDelta * 2)][place.getY() + (yDelta * 2)];

					if (otherPlace != null && !otherPlace.hasPiece()) {

						otherPlace.attack(color);
					}
				}
			}

			selected = true;

		} else {

			unselect();
		}
	}

	@Override
	public void unselect() {

		Place otherPlace = place.getBoard().getPlacez()[place.getX() + xDelta][place.getY() + yDelta];

		if (otherPlace != null && !otherPlace.hasPiece()) {

			otherPlace.stopAttack();

			otherPlace = place.getBoard().getPlacez()[place.getX() + (xDelta * 2)][place.getY() + (yDelta * 2)];

			if (otherPlace != null && !otherPlace.hasPiece()) {

				otherPlace.stopAttack();
			}
		}

		selected = false;
	}
}
