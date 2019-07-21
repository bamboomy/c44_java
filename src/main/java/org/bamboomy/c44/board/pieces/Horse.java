package org.bamboomy.c44.board.pieces;

import java.util.ArrayList;

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
	protected void setAttackablePlaces() {
		
		attackablePlaces = new ArrayList<Place>();

		if (place.getX() + 2 < 12) {

			if (place.getY() + 1 < 12) {

				Place otherPlace = place.getBoard().getPlacez()[place.getX() + 2][place.getY() + 1];

				if (otherPlace != null && otherPlace != place) {

					if (otherPlace.getPiece() != null) {

						if (otherPlace.getPiece().getColor() != color) {

							attackablePlaces.add(otherPlace);
						}

					} else {

						attackablePlaces.add(otherPlace);
					}
				}
			}

			if (place.getY() - 1 >= 0) {

				Place otherPlace = place.getBoard().getPlacez()[place.getX() + 2][place.getY() - 1];

				if (otherPlace != null && otherPlace != place) {

					if (otherPlace.getPiece() != null) {

						if (otherPlace.getPiece().getColor() != color) {

							attackablePlaces.add(otherPlace);
						}

					} else {

						attackablePlaces.add(otherPlace);
					}
				}
			}
		}

		if (place.getX() - 2 >= 0) {

			if (place.getY() + 1 < 12) {

				Place otherPlace = place.getBoard().getPlacez()[place.getX() - 2][place.getY() + 1];

				if (otherPlace != null && otherPlace != place) {

					if (otherPlace.getPiece() != null) {

						if (otherPlace.getPiece().getColor() != color) {

							attackablePlaces.add(otherPlace);
						}

					} else {

						attackablePlaces.add(otherPlace);
					}
				}
			}

			if (place.getY() - 1 >= 0) {

				Place otherPlace = place.getBoard().getPlacez()[place.getX() - 2][place.getY() - 1];

				if (otherPlace != null && otherPlace != place) {

					if (otherPlace.getPiece() != null) {

						if (otherPlace.getPiece().getColor() != color) {

							attackablePlaces.add(otherPlace);
						}

					} else {

						attackablePlaces.add(otherPlace);
					}
				}
			}
		}

		if (place.getY() + 2 < 12) {

			if (place.getX() + 1 < 12) {

				Place otherPlace = place.getBoard().getPlacez()[place.getX() + 1][place.getY() + 2];

				if (otherPlace != null && otherPlace != place) {

					if (otherPlace.getPiece() != null) {

						if (otherPlace.getPiece().getColor() != color) {

							attackablePlaces.add(otherPlace);
						}

					} else {

						attackablePlaces.add(otherPlace);
					}
				}
			}

			if (place.getX() - 1 >= 0) {

				Place otherPlace = place.getBoard().getPlacez()[place.getX() - 1][place.getY() + 2];

				if (otherPlace != null && otherPlace != place) {

					if (otherPlace.getPiece() != null) {

						if (otherPlace.getPiece().getColor() != color) {

							attackablePlaces.add(otherPlace);
						}

					} else {

						attackablePlaces.add(otherPlace);
					}
				}
			}
		}

		if (place.getY() - 2 >= 0) {

			if (place.getX() + 1 < 12) {

				Place otherPlace = place.getBoard().getPlacez()[place.getX() + 1][place.getY() - 2];

				if (otherPlace != null && otherPlace != place) {

					if (otherPlace.getPiece() != null) {

						if (otherPlace.getPiece().getColor() != color) {

							attackablePlaces.add(otherPlace);
						}

					} else {

						attackablePlaces.add(otherPlace);
					}
				}
			}

			if (place.getX() - 1 >= 0) {

				Place otherPlace = place.getBoard().getPlacez()[place.getX() - 1][place.getY() - 2];

				if (otherPlace != null && otherPlace != place) {

					if (otherPlace.getPiece() != null) {

						if (otherPlace.getPiece().getColor() != color) {

							attackablePlaces.add(otherPlace);
						}

					} else {

						attackablePlaces.add(otherPlace);
					}
				}
			}
		}
	}
}
