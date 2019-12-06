package org.bamboomy.c44.board.pieces;

import java.util.ArrayList;

import org.bamboomy.c44.board.Move;
import org.bamboomy.c44.board.Place;
import org.bamboomy.c44.board.Player;

public class Horse extends Piece {

	public Horse(Place place, int color) {
		super(place, color);
	}

	@Override
	public String getPieceName() {

		if (color == Player.RED) {

			return "../img/horse_red.png";

		} else if (color == Player.YELLOW) {

			return "../img/horse_yellow.png";

		} else if (color == Player.GREEN) {

			return "../img/horse_green.png";

		} else if (color == Player.BLUE) {

			return "../img/horse_blue.png";
		}

		throw new RuntimeException("invalid color in horse");
	}

	@Override
	public void setAttackablePlaces(boolean unused) {
		
		attackableMoves = new ArrayList<Move>();

		if (currentPlace.getX() + 2 < 12) {

			if (currentPlace.getY() + 1 < 12) {

				Place otherPlace = currentPlace.getBoard().getPlacez()[currentPlace.getX() + 2][currentPlace.getY() + 1];

				if (otherPlace != null && otherPlace != currentPlace) {

					if (otherPlace.getPiece() != null) {

						if (otherPlace.getPiece().getColor() != color) {

							attackableMoves.add(new Move(currentPlace, otherPlace, this));
						}

					} else {

						attackableMoves.add(new Move(currentPlace, otherPlace, this));
					}
				}
			}

			if (currentPlace.getY() - 1 >= 0) {

				Place otherPlace = currentPlace.getBoard().getPlacez()[currentPlace.getX() + 2][currentPlace.getY() - 1];

				if (otherPlace != null && otherPlace != currentPlace) {

					if (otherPlace.getPiece() != null) {

						if (otherPlace.getPiece().getColor() != color) {

							attackableMoves.add(new Move(currentPlace, otherPlace, this));
						}

					} else {

						attackableMoves.add(new Move(currentPlace, otherPlace, this));
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

							attackableMoves.add(new Move(currentPlace, otherPlace, this));
						}

					} else {

						attackableMoves.add(new Move(currentPlace, otherPlace, this));
					}
				}
			}

			if (currentPlace.getY() - 1 >= 0) {

				Place otherPlace = currentPlace.getBoard().getPlacez()[currentPlace.getX() - 2][currentPlace.getY() - 1];

				if (otherPlace != null && otherPlace != currentPlace) {

					if (otherPlace.getPiece() != null) {

						if (otherPlace.getPiece().getColor() != color) {

							attackableMoves.add(new Move(currentPlace, otherPlace, this));
						}

					} else {

						attackableMoves.add(new Move(currentPlace, otherPlace, this));
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

							attackableMoves.add(new Move(currentPlace, otherPlace, this));
						}

					} else {

						attackableMoves.add(new Move(currentPlace, otherPlace, this));
					}
				}
			}

			if (currentPlace.getX() - 1 >= 0) {

				Place otherPlace = currentPlace.getBoard().getPlacez()[currentPlace.getX() - 1][currentPlace.getY() + 2];

				if (otherPlace != null && otherPlace != currentPlace) {

					if (otherPlace.getPiece() != null) {

						if (otherPlace.getPiece().getColor() != color) {

							attackableMoves.add(new Move(currentPlace, otherPlace, this));
						}

					} else {

						attackableMoves.add(new Move(currentPlace, otherPlace, this));
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

							attackableMoves.add(new Move(currentPlace, otherPlace, this));
						}

					} else {

						attackableMoves.add(new Move(currentPlace, otherPlace, this));
					}
				}
			}

			if (currentPlace.getX() - 1 >= 0) {

				Place otherPlace = currentPlace.getBoard().getPlacez()[currentPlace.getX() - 1][currentPlace.getY() - 2];

				if (otherPlace != null && otherPlace != currentPlace) {

					if (otherPlace.getPiece() != null) {

						if (otherPlace.getPiece().getColor() != color) {

							attackableMoves.add(new Move(currentPlace, otherPlace, this));
						}

					} else {

						attackableMoves.add(new Move(currentPlace, otherPlace, this));
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
