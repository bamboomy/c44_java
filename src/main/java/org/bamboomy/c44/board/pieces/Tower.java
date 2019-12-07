package org.bamboomy.c44.board.pieces;

import java.util.ArrayList;

import org.bamboomy.c44.board.Move;
import org.bamboomy.c44.board.Place;
import org.bamboomy.c44.board.Player;

public class Tower extends Piece {

	public Tower(Place place, int color, Player player) {
		super(place, color, player);
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

	public void setAttackablePlaces(boolean unused) {

		attackableMoves = new ArrayList<Move>();

		for (int i = currentPlace.getX(); i < 12; i++) {

			Place otherPlace = currentPlace.getBoard().getPlacez()[i][currentPlace.getY()];

			if (otherPlace != null && otherPlace != currentPlace) {

				if (otherPlace.getPiece() != null) {

					if (otherPlace.getPiece().getColor() != color) {

						attackableMoves.add(new Move(currentPlace, otherPlace, this));
					}

					break;

				} else {

					attackableMoves.add(new Move(currentPlace, otherPlace, this));
				}
			}
		}

		for (int i = currentPlace.getY(); i < 12; i++) {

			Place otherPlace = currentPlace.getBoard().getPlacez()[currentPlace.getX()][i];

			if (otherPlace != null && otherPlace != currentPlace) {

				if (otherPlace.getPiece() != null) {

					if (otherPlace.getPiece().getColor() != color) {

						attackableMoves.add(new Move(currentPlace, otherPlace, this));
					}

					break;

				} else {

					attackableMoves.add(new Move(currentPlace, otherPlace, this));
				}
			}
		}

		for (int i = currentPlace.getX(); i >= 0; i--) {

			Place otherPlace = currentPlace.getBoard().getPlacez()[i][currentPlace.getY()];

			if (otherPlace != null && otherPlace != currentPlace) {

				if (otherPlace.getPiece() != null) {

					if (otherPlace.getPiece().getColor() != color) {

						attackableMoves.add(new Move(currentPlace, otherPlace, this));
					}

					break;

				} else {

					attackableMoves.add(new Move(currentPlace, otherPlace, this));
				}
			}
		}

		for (int i = currentPlace.getY(); i >= 0; i--) {

			Place otherPlace = currentPlace.getBoard().getPlacez()[currentPlace.getX()][i];

			if (otherPlace != null && otherPlace != currentPlace) {

				if (otherPlace.getPiece() != null) {

					if (otherPlace.getPiece().getColor() != color) {

						attackableMoves.add(new Move(currentPlace, otherPlace, this));
					}

					break;

				} else {

					attackableMoves.add(new Move(currentPlace, otherPlace, this));
				}
			}
		}

	}

	@Override
	public String getPieceIdentifier() {

		return TOWER;
	}
}
