package org.bamboomy.c44.board.pieces;

import java.util.ArrayList;

import org.bamboomy.c44.board.Color;
import org.bamboomy.c44.board.Move;
import org.bamboomy.c44.board.Place;
import org.bamboomy.c44.board.Player;

public class Horse extends Piece {

	public Horse(Place place, int color, Player player) {
		super(place, color, player);
	}

	@Override
	public String getPieceName() {
		
		switch(Color.getBySeq(color)) {
		
		case RED:
			
			return "img/horse_red.png";
			
		case YELLOW:
			
			return "img/horse_yellow.png";
			
		case GREEN:
			
			return "img/horse_green.png";
			
		case BLUE:
			
			return "img/horse_blue.png";
		}

		throw new RuntimeException("invalid color in horse");
	}

	@Override
	public void setAttackablePlaces(boolean unused, boolean addMove) {
		
		attackableMoves = new ArrayList<Move>();

		if (currentPlace.getX() + 2 < 12) {

			if (currentPlace.getY() + 1 < 12) {

				Place otherPlace = currentPlace.getBoard().getPlacez()[currentPlace.getX() + 2][currentPlace.getY() + 1];

				if (otherPlace != null && otherPlace != currentPlace) {

					if (otherPlace.getPiece() != null) {

						if (otherPlace.getPiece().getColor() != color) {

							attackableMoves.add(new Move(currentPlace, otherPlace, this, addMove));
						}

					} else {

						attackableMoves.add(new Move(currentPlace, otherPlace, this, addMove));
					}
				}
			}

			if (currentPlace.getY() - 1 >= 0) {

				Place otherPlace = currentPlace.getBoard().getPlacez()[currentPlace.getX() + 2][currentPlace.getY() - 1];

				if (otherPlace != null && otherPlace != currentPlace) {

					if (otherPlace.getPiece() != null) {

						if (otherPlace.getPiece().getColor() != color) {

							attackableMoves.add(new Move(currentPlace, otherPlace, this, addMove));
						}

					} else {

						attackableMoves.add(new Move(currentPlace, otherPlace, this, addMove));
					}
				}
			}
		}

		if (currentPlace.getX() - 2 >= 0) {

			if (currentPlace.getY() + 1 < 12) {

				Place otherPlace = currentPlace.getBoard().getPlacez()[currentPlace.getX() - 2][currentPlace.getY() + 1];

				if (otherPlace != null && otherPlace != currentPlace) {

					if (otherPlace.getPiece() != null) {

						if (otherPlace.getPiece().getColor() != color) {

							attackableMoves.add(new Move(currentPlace, otherPlace, this, addMove));
						}

					} else {

						attackableMoves.add(new Move(currentPlace, otherPlace, this, addMove));
					}
				}
			}

			if (currentPlace.getY() - 1 >= 0) {

				Place otherPlace = currentPlace.getBoard().getPlacez()[currentPlace.getX() - 2][currentPlace.getY() - 1];

				if (otherPlace != null && otherPlace != currentPlace) {

					if (otherPlace.getPiece() != null) {

						if (otherPlace.getPiece().getColor() != color) {

							attackableMoves.add(new Move(currentPlace, otherPlace, this, addMove));
						}

					} else {

						attackableMoves.add(new Move(currentPlace, otherPlace, this, addMove));
					}
				}
			}
		}

		if (currentPlace.getY() + 2 < 12) {

			if (currentPlace.getX() + 1 < 12) {

				Place otherPlace = currentPlace.getBoard().getPlacez()[currentPlace.getX() + 1][currentPlace.getY() + 2];

				if (otherPlace != null && otherPlace != currentPlace) {

					if (otherPlace.getPiece() != null) {

						if (otherPlace.getPiece().getColor() != color) {

							attackableMoves.add(new Move(currentPlace, otherPlace, this, addMove));
						}

					} else {

						attackableMoves.add(new Move(currentPlace, otherPlace, this, addMove));
					}
				}
			}

			if (currentPlace.getX() - 1 >= 0) {

				Place otherPlace = currentPlace.getBoard().getPlacez()[currentPlace.getX() - 1][currentPlace.getY() + 2];

				if (otherPlace != null && otherPlace != currentPlace) {

					if (otherPlace.getPiece() != null) {

						if (otherPlace.getPiece().getColor() != color) {

							attackableMoves.add(new Move(currentPlace, otherPlace, this, addMove));
						}

					} else {

						attackableMoves.add(new Move(currentPlace, otherPlace, this, addMove));
					}
				}
			}
		}

		if (currentPlace.getY() - 2 >= 0) {

			if (currentPlace.getX() + 1 < 12) {

				Place otherPlace = currentPlace.getBoard().getPlacez()[currentPlace.getX() + 1][currentPlace.getY() - 2];

				if (otherPlace != null && otherPlace != currentPlace) {

					if (otherPlace.getPiece() != null) {

						if (otherPlace.getPiece().getColor() != color) {

							attackableMoves.add(new Move(currentPlace, otherPlace, this, addMove));
						}

					} else {

						attackableMoves.add(new Move(currentPlace, otherPlace, this, addMove));
					}
				}
			}

			if (currentPlace.getX() - 1 >= 0) {

				Place otherPlace = currentPlace.getBoard().getPlacez()[currentPlace.getX() - 1][currentPlace.getY() - 2];

				if (otherPlace != null && otherPlace != currentPlace) {

					if (otherPlace.getPiece() != null) {

						if (otherPlace.getPiece().getColor() != color) {

							attackableMoves.add(new Move(currentPlace, otherPlace, this, addMove));
						}

					} else {

						attackableMoves.add(new Move(currentPlace, otherPlace, this, addMove));
					}
				}
			}
		}
	}

	@Override
	public String getPieceIdentifier() {

		return HORSE;
	}
}
