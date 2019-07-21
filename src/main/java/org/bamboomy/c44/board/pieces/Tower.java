package org.bamboomy.c44.board.pieces;

import org.bamboomy.c44.board.Place;
import org.bamboomy.c44.board.Player;

public class Tower extends Piece {

	public Tower(Place place, int color) {
		super(place, color);
	}

	@Override
	public String getPieceName() {

		if (color == Player.RED) {

			return "../img/tower_red.png";
		}

		return "../img/tower.png";
	}

	@Override
	public void click() {

		if (!selected) {

			for (int i = 0; i < 12; i++) {

				Place otherPlace = place.getBoard().getPlacez()[i][place.getY()];

				if (otherPlace != null && otherPlace != place) {

					otherPlace.attack(color);
				}
			}

			for (int i = 0; i < 12; i++) {

				Place otherPlace = place.getBoard().getPlacez()[place.getX()][i];

				if (otherPlace != null && otherPlace != place) {

					otherPlace.attack(color);
				}
			}

			selected = true;

		} else {

			selected = false;
		}
	}

}
