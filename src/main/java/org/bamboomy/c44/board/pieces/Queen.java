package org.bamboomy.c44.board.pieces;

import java.util.ArrayList;

import org.bamboomy.c44.board.Move;
import org.bamboomy.c44.board.Place;
import org.bamboomy.c44.board.Player;

public class Queen extends Piece {

	public Queen(Place place, int color, Player player) {
		super(place, color, player);
	}

	@Override
	public String getPieceName() {

		if (color == Player.RED) {

			return "../img/queen_red.png";

		} else if (color == Player.YELLOW) {

			return "../img/queen_yellow.png";

		} else if (color == Player.GREEN) {

			return "../img/queen_green.png";

		} else if (color == Player.BLUE) {

			return "../img/queen_blue.png";
		}

		throw new RuntimeException("invalid color in queen");
	}

	@Override
	public void setAttackablePlaces(boolean unused) {

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

						attackableMoves.add(new Move(currentPlace, otherPlace, this));
					}

					break;

				} else {

					attackableMoves.add(new Move(currentPlace, otherPlace, this));
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

						attackableMoves.add(new Move(currentPlace, otherPlace, this));
					}

					break;

				} else {

					attackableMoves.add(new Move(currentPlace, otherPlace, this));
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

						attackableMoves.add(new Move(currentPlace, otherPlace, this));
					}

					break;

				} else {

					attackableMoves.add(new Move(currentPlace, otherPlace, this));
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

						attackableMoves.add(new Move(currentPlace, otherPlace, this));
					}

					break;

				} else {

					attackableMoves.add(new Move(currentPlace, otherPlace, this));
				}
			}

			counter++;
		}

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

		return QUEEN;
	}
}
