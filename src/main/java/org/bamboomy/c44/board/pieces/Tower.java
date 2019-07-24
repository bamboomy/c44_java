package org.bamboomy.c44.board.pieces;

import java.util.ArrayList;

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

		} else if (color == Player.YELLOW) {

			return "../img/tower_yellow.png";

		} else if (color == Player.GREEN) {

			return "../img/tower_green.png";

		} else if (color == Player.BLUE) {

			return "../img/tower_blue.png";
		}

		throw new RuntimeException("invalid color in tower");
	}

	protected void setAttackablePlaces() {

		attackablePlaces = new ArrayList<Place>();

		for (int i = place.getX(); i < 12; i++) {

			Place otherPlace = place.getBoard().getPlacez()[i][place.getY()];

			if (otherPlace != null && otherPlace != place) {

				if (otherPlace.getPiece() != null) {

					if (otherPlace.getPiece().getColor() != color) {

						attackablePlaces.add(otherPlace);
					}

					break;

				} else {

					attackablePlaces.add(otherPlace);
				}
			}
		}

		for (int i = place.getY(); i < 12; i++) {

			Place otherPlace = place.getBoard().getPlacez()[place.getX()][i];

			if (otherPlace != null && otherPlace != place) {

				if (otherPlace.getPiece() != null) {

					if (otherPlace.getPiece().getColor() != color) {

						attackablePlaces.add(otherPlace);
					}

					break;

				} else {

					attackablePlaces.add(otherPlace);
				}
			}
		}

		for (int i = place.getX(); i >= 0; i--) {

			Place otherPlace = place.getBoard().getPlacez()[i][place.getY()];

			if (otherPlace != null && otherPlace != place) {

				if (otherPlace.getPiece() != null) {

					if (otherPlace.getPiece().getColor() != color) {

						attackablePlaces.add(otherPlace);
					}

					break;

				} else {

					attackablePlaces.add(otherPlace);
				}
			}
		}

		for (int i = place.getY(); i >= 0; i--) {

			Place otherPlace = place.getBoard().getPlacez()[place.getX()][i];

			if (otherPlace != null && otherPlace != place) {

				if (otherPlace.getPiece() != null) {

					if (otherPlace.getPiece().getColor() != color) {

						attackablePlaces.add(otherPlace);
					}

					break;

				} else {

					attackablePlaces.add(otherPlace);
				}
			}
		}

	}

	@Override
	public String getPieceIdentifier() {

		return TOWER;
	}
}
