package org.bamboomy.c44.board.pieces;

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
		
		} else if(color == Player.YELLOW) {
			
			return "../img/bisshop_yellow.png";
		}

		return "../img/bisshop.png";
	}

	@Override
	public void click() {

		if (!selected) {

			int counter = 0;

			place.getBoard().getPlayerz()[color].setSelected(this);

			for (int i = place.getX(); i < 12; i++) {

				if (place.getY() - counter < 0) {

					break;
				}

				Place otherPlace = place.getBoard().getPlacez()[i][place.getY() - counter];

				if (otherPlace != null && otherPlace != place) {

					if (otherPlace.getPiece() != null) {

						if (otherPlace.getPiece().getColor() != color) {

							otherPlace.attack(color);
						}

						break;

					} else {

						otherPlace.attack(color);
					}
				}

				counter++;
			}

			counter = 0;

			for (int i = place.getY(); i < 12; i++) {

				if (place.getX() + counter > 11) {

					break;
				}

				Place otherPlace = place.getBoard().getPlacez()[place.getX() + counter][i];

				if (otherPlace != null && otherPlace != place) {

					if (otherPlace.getPiece() != null) {

						if (otherPlace.getPiece().getColor() != color) {

							otherPlace.attack(color);
						}

						break;

					} else {

						otherPlace.attack(color);
					}
				}

				counter++;
			}

			counter = 0;

			for (int i = place.getX(); i >= 0; i--) {

				if (place.getY() + counter > 11) {

					break;
				}

				Place otherPlace = place.getBoard().getPlacez()[i][place.getY() + counter];

				if (otherPlace != null && otherPlace != place) {

					if (otherPlace.getPiece() != null) {

						if (otherPlace.getPiece().getColor() != color) {

							otherPlace.attack(color);
						}

						break;

					} else {

						otherPlace.attack(color);
					}
				}

				counter++;
			}

			counter = 0;

			for (int i = place.getY(); i >= 0; i--) {

				if (place.getX() - counter < 0) {

					break;
				}

				Place otherPlace = place.getBoard().getPlacez()[place.getX() - counter][i];

				if (otherPlace != null && otherPlace != place) {

					if (otherPlace.getPiece() != null) {

						if (otherPlace.getPiece().getColor() != color) {

							otherPlace.attack(color);
						}

						break;

					} else {

						otherPlace.attack(color);
					}
				}

				counter++;
			}

			selected = true;

		} else {

			unselect();
		}
	}

	@Override
	public void unselect() {

		int counter = 0;

		for (int i = place.getX(); i < 12; i++) {

			if (place.getY() - counter < 0) {

				break;
			}

			Place otherPlace = place.getBoard().getPlacez()[i][place.getY() - counter];

			if (otherPlace != null && otherPlace != place) {

				otherPlace.stopAttack();
			}

			counter++;
		}

		counter = 0;

		for (int i = place.getY(); i < 12; i++) {

			if (place.getX() + counter > 11) {

				break;
			}

			Place otherPlace = place.getBoard().getPlacez()[place.getX() + counter][i];

			if (otherPlace != null && otherPlace != place) {

				otherPlace.stopAttack();
			}

			counter++;
		}

		counter = 0;

		for (int i = place.getX(); i >= 0; i--) {

			if (place.getY() + counter > 11) {

				break;
			}

			Place otherPlace = place.getBoard().getPlacez()[i][place.getY() + counter];

			if (otherPlace != null && otherPlace != place) {

				otherPlace.stopAttack();
			}

			counter++;
		}

		counter = 0;

		for (int i = place.getY(); i >= 0; i--) {

			if (place.getX() - counter < 0) {

				break;
			}

			Place otherPlace = place.getBoard().getPlacez()[place.getX() - counter][i];

			if (otherPlace != null && otherPlace != place) {

				otherPlace.stopAttack();
			}

			counter++;
		}

		selected = false;
	}
}
