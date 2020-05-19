package org.bamboomy.c44.board.pieces;

import java.util.ArrayList;

import org.bamboomy.c44.board.Color;
import org.bamboomy.c44.board.Move;
import org.bamboomy.c44.board.Place;
import org.bamboomy.c44.board.Player;

public class Bisshop extends Piece {

	public Bisshop(Place place, int color, Player player) {
		super(place, color, player);
	}

	@Override
	public String getPieceName() {

		switch (Color.getBySeq(color)) {

		case RED:

			return "/" + getCurrentPlace().getBoard().getTomcatPath() + "/img/bisshop_red.png";

		case YELLOW:

			return "/" + getCurrentPlace().getBoard().getTomcatPath() + "/img/bisshop_yellow.png";

		case GREEN:

			return "/" + getCurrentPlace().getBoard().getTomcatPath() + "/img/bisshop_green.png";

		case BLUE:

			return "/" + getCurrentPlace().getBoard().getTomcatPath() + "/img/bisshop_blue.png";
		}

		throw new RuntimeException("invalid color in bishop");
	}

	@Override
	public void setAttackablePlaces(boolean unused, boolean addMove) {

		attackableMoves = new ArrayList<Move>();

		int counter = 0;

		for (int i = currentPlace.getX(); i < 12; i++) {

			if (currentPlace.getY() - counter < 0) {

				break;
			}

			Place otherPlace = currentPlace.getBoard().getPlacez()[i][currentPlace.getY() - counter];

			if (otherPlace != null && otherPlace != currentPlace) {

				if (otherPlace.getPiece() != null) {

					if (otherPlace.getPiece().getColor() != color) {

						attackableMoves.add(new Move(currentPlace, otherPlace, this, addMove));
					}

					break;

				} else {

					attackableMoves.add(new Move(currentPlace, otherPlace, this, addMove));
				}
			} else if (otherPlace == null) {

				break;
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

						attackableMoves.add(new Move(currentPlace, otherPlace, this, addMove));
					}

					break;

				} else {

					attackableMoves.add(new Move(currentPlace, otherPlace, this, addMove));
				}
			} else if (otherPlace == null) {

				break;
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

						attackableMoves.add(new Move(currentPlace, otherPlace, this, addMove));
					}

					break;

				} else {

					attackableMoves.add(new Move(currentPlace, otherPlace, this, addMove));
				}
			} else if (otherPlace == null) {

				break;
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

						attackableMoves.add(new Move(currentPlace, otherPlace, this, addMove));
					}

					break;

				} else {

					attackableMoves.add(new Move(currentPlace, otherPlace, this, addMove));
				}
			} else if (otherPlace == null) {

				break;
			}

			counter++;
		}
	}

	@Override
	public String getPieceIdentifier() {

		return BISSHOP;
	}
}
