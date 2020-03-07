package org.bamboomy.c44.board.pieces;

import java.util.ArrayList;

import org.bamboomy.c44.board.Color;
import org.bamboomy.c44.board.Move;
import org.bamboomy.c44.board.Place;
import org.bamboomy.c44.board.Player;

public class Queen extends Piece {

	public Queen(Place place, int color, Player player) {
		super(place, color, player);
	}

	@Override
	public String getPieceName() {

		switch(Color.getBySeq(color)) {
		
		case RED:
			
			return "img/queen_red.png";
			
		case YELLOW:
			
			return "img/queen_yellow.png";
			
		case GREEN:
			
			return "img/queen_green.png";
			
		case BLUE:
			
			return "img/queen_blue.png";
		}

		throw new RuntimeException("invalid color in queen");
	}

	@Override
	public void setAttackablePlaces(boolean unused, boolean addMove) {

		int counter = 0;

		attackableMoves = new ArrayList<Move>();

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

		for (int i = currentPlace.getX(); i < 12; i++) {

			Place otherPlace = currentPlace.getBoard().getPlacez()[i][currentPlace.getY()];

			if (otherPlace != null && otherPlace != currentPlace) {

				if (otherPlace.getPiece() != null) {

					if (otherPlace.getPiece().getColor() != color) {

						attackableMoves.add(new Move(currentPlace, otherPlace, this, addMove));
					}

					break;

				} else {

					attackableMoves.add(new Move(currentPlace, otherPlace, this, addMove));
				}
			}
		}

		for (int i = currentPlace.getY(); i < 12; i++) {

			Place otherPlace = currentPlace.getBoard().getPlacez()[currentPlace.getX()][i];

			if (otherPlace != null && otherPlace != currentPlace) {

				if (otherPlace.getPiece() != null) {

					if (otherPlace.getPiece().getColor() != color) {

						attackableMoves.add(new Move(currentPlace, otherPlace, this, addMove));
					}

					break;

				} else {

					attackableMoves.add(new Move(currentPlace, otherPlace, this, addMove));
				}
			}
		}

		for (int i = currentPlace.getX(); i >= 0; i--) {

			Place otherPlace = currentPlace.getBoard().getPlacez()[i][currentPlace.getY()];

			if (otherPlace != null && otherPlace != currentPlace) {

				if (otherPlace.getPiece() != null) {

					if (otherPlace.getPiece().getColor() != color) {

						attackableMoves.add(new Move(currentPlace, otherPlace, this, addMove));
					}

					break;

				} else {

					attackableMoves.add(new Move(currentPlace, otherPlace, this, addMove));
				}
			}
		}

		for (int i = currentPlace.getY(); i >= 0; i--) {

			Place otherPlace = currentPlace.getBoard().getPlacez()[currentPlace.getX()][i];

			if (otherPlace != null && otherPlace != currentPlace) {

				if (otherPlace.getPiece() != null) {

					if (otherPlace.getPiece().getColor() != color) {

						attackableMoves.add(new Move(currentPlace, otherPlace, this, addMove));
					}

					break;

				} else {

					attackableMoves.add(new Move(currentPlace, otherPlace, this, addMove));
				}
			}
		}
	}

	@Override
	public String getPieceIdentifier() {

		return QUEEN;
	}
}
