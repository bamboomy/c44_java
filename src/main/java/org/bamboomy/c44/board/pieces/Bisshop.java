package org.bamboomy.c44.board.pieces;

import java.util.ArrayList;

import org.bamboomy.c44.board.Move;
import org.bamboomy.c44.board.Place;
import org.bamboomy.c44.board.Player;

public class Bisshop extends Piece {

	public Bisshop(Place place, int color) {
		super(place, color);
	}

	@Override
	public String getPieceName() {

		if (color == Player.RED) {

			return "../img/bisshop_red.png";

		} else if (color == Player.YELLOW) {

			return "../img/bisshop_yellow.png";

		} else if (color == Player.GREEN) {

			return "../img/bisshop_green.png";

		} else if (color == Player.BLUE) {

			return "../img/bisshop_blue.png";
		}

		throw new RuntimeException("invalid color in bishop");
	}

	@Override
	protected void setAttackablePlaces() {

		attackablePlaces = new ArrayList<Move>();

		int counter = 0;

		for (int i = currentPlace.getX(); i < 12; i++) {

			if (currentPlace.getY() - counter < 0) {

				break;
			}

			Place otherPlace = currentPlace.getBoard().getPlacez()[i][currentPlace.getY() - counter];

			if (otherPlace != null && otherPlace != currentPlace) {

				if (otherPlace.getPiece() != null) {

					if (otherPlace.getPiece().getColor() != color) {

						attackablePlaces.add(new Move(currentPlace, otherPlace, this));
					}

					break;

				} else {

					attackablePlaces.add(new Move(currentPlace, otherPlace, this));
				}
			}

			counter++;
		}

		counter = 0;

		for (int i = currentPlace.getY(); i < 12; i++) {

			if (currentPlace.getX() + counter > 11) {

				break;
			}

			Place otherPlace = currentPlace.getBoard().getPlacez()[currentPlace.getX() + counter][i];

			if (otherPlace != null && otherPlace != currentPlace) {

				if (otherPlace.getPiece() != null) {

					if (otherPlace.getPiece().getColor() != color) {

						attackablePlaces.add(new Move(currentPlace, otherPlace, this));
					}

					break;

				} else {

					attackablePlaces.add(new Move(currentPlace, otherPlace, this));
				}
			}

			counter++;
		}

		counter = 0;

		for (int i = currentPlace.getX(); i >= 0; i--) {

			if (currentPlace.getY() + counter > 11) {

				break;
			}

			Place otherPlace = currentPlace.getBoard().getPlacez()[i][currentPlace.getY() + counter];

			if (otherPlace != null && otherPlace != currentPlace) {

				if (otherPlace.getPiece() != null) {

					if (otherPlace.getPiece().getColor() != color) {

						attackablePlaces.add(new Move(currentPlace, otherPlace, this));
					}

					break;

				} else {

					attackablePlaces.add(new Move(currentPlace, otherPlace, this));
				}
			}

			counter++;
		}

		counter = 0;

		for (int i = currentPlace.getY(); i >= 0; i--) {

			if (currentPlace.getX() - counter < 0) {

				break;
			}

			Place otherPlace = currentPlace.getBoard().getPlacez()[currentPlace.getX() - counter][i];

			if (otherPlace != null && otherPlace != currentPlace) {

				if (otherPlace.getPiece() != null) {

					if (otherPlace.getPiece().getColor() != color) {

						attackablePlaces.add(new Move(currentPlace, otherPlace, this));
					}

					break;

				} else {

					attackablePlaces.add(new Move(currentPlace, otherPlace, this));
				}
			}

			counter++;
		}
	}

	@Override
	public String getPieceIdentifier() {

		return BISSHOP;
	}
}
