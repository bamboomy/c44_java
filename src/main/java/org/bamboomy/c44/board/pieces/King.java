package org.bamboomy.c44.board.pieces;

import org.bamboomy.c44.board.Place;
import org.bamboomy.c44.board.Player;

public class King extends Piece {

	public King(Place place, int color) {
		super(place, color);
	}

	@Override
	public String getPieceName() {

		if (color == Player.RED) {

			return "../img/king_red.png";

		} else if (color == Player.YELLOW) {

			return "../img/king_yellow.png";

		} else if (color == Player.GREEN) {

			return "../img/king_green.png";

		} else if (color == Player.BLUE) {

			return "../img/king_blue.png";
		}

		throw new RuntimeException("invalid color in king");
	}

	@Override
	public void click() {

		if (!selected) {

			place.getBoard().getPlayerz()[color].setSelected(this);

			int k = place.getX() + 1;
			int l = place.getY() + 1;

			if (k < 12 && l < 12) {

				Place otherPlace = place.getBoard().getPlacez()[k][l];

				if (otherPlace != null && otherPlace != place) {

					if (otherPlace.getPiece() != null) {

						if (otherPlace.getPiece().getColor() != color) {

							otherPlace.attack(color);
						}

					} else {

						otherPlace.attack(color);
					}
				}
			}

			k = place.getX();

			if (k < 12 && l < 12) {

				Place otherPlace = place.getBoard().getPlacez()[k][l];

				if (otherPlace != null && otherPlace != place) {

					if (otherPlace.getPiece() != null) {

						if (otherPlace.getPiece().getColor() != color) {

							otherPlace.attack(color);
						}

					} else {

						otherPlace.attack(color);
					}
				}
			}

			k = place.getX() - 1;

			if (k >= 0 && l < 12) {

				Place otherPlace = place.getBoard().getPlacez()[k][l];

				if (otherPlace != null && otherPlace != place) {

					if (otherPlace.getPiece() != null) {

						if (otherPlace.getPiece().getColor() != color) {

							otherPlace.attack(color);
						}

					} else {

						otherPlace.attack(color);
					}
				}
			}

			k = place.getX() + 1;
			l = place.getY();

			if (k < 12 && l < 12) {

				Place otherPlace = place.getBoard().getPlacez()[k][l];

				if (otherPlace != null && otherPlace != place) {

					if (otherPlace.getPiece() != null) {

						if (otherPlace.getPiece().getColor() != color) {

							otherPlace.attack(color);
						}

					} else {

						otherPlace.attack(color);
					}
				}
			}

			k = place.getX() - 1;

			if (k >= 0 && l < 12) {

				Place otherPlace = place.getBoard().getPlacez()[k][l];

				if (otherPlace != null && otherPlace != place) {

					if (otherPlace.getPiece() != null) {

						if (otherPlace.getPiece().getColor() != color) {

							otherPlace.attack(color);
						}

					} else {

						otherPlace.attack(color);
					}
				}
			}

			k = place.getX() + 1;
			l = place.getY() - 1;

			if (k < 12 && l >= 0) {

				Place otherPlace = place.getBoard().getPlacez()[k][l];

				if (otherPlace != null && otherPlace != place) {

					if (otherPlace.getPiece() != null) {

						if (otherPlace.getPiece().getColor() != color) {

							otherPlace.attack(color);
						}

					} else {

						otherPlace.attack(color);
					}
				}
			}

			k = place.getX();

			if (k < 12 && l >= 0) {

				Place otherPlace = place.getBoard().getPlacez()[k][l];

				if (otherPlace != null && otherPlace != place) {

					if (otherPlace.getPiece() != null) {

						if (otherPlace.getPiece().getColor() != color) {

							otherPlace.attack(color);
						}

					} else {

						otherPlace.attack(color);
					}
				}
			}

			k = place.getX() - 1;

			if (k >= 0 && l >= 0) {

				Place otherPlace = place.getBoard().getPlacez()[k][l];

				if (otherPlace != null && otherPlace != place) {

					if (otherPlace.getPiece() != null) {

						if (otherPlace.getPiece().getColor() != color) {

							otherPlace.attack(color);
						}

					} else {

						otherPlace.attack(color);
					}
				}
			}

			selected = true;

		} else {

			unselect();
		}

	}

	@Override
	public void unselect() {

		int k = place.getX() + 1;
		int l = place.getY() + 1;

		if (k < 12 && l < 12) {

			Place otherPlace = place.getBoard().getPlacez()[k][l];

			if (otherPlace != null && otherPlace != place) {

				otherPlace.stopAttack();
			}
		}

		k = place.getX();

		if (k < 12 && l < 12) {

			Place otherPlace = place.getBoard().getPlacez()[k][l];

			if (otherPlace != null && otherPlace != place) {

				otherPlace.stopAttack();
			}
		}

		k = place.getX() - 1;

		if (k >= 0 && l < 12) {

			Place otherPlace = place.getBoard().getPlacez()[k][l];

			if (otherPlace != null && otherPlace != place) {

				otherPlace.stopAttack();
			}
		}

		k = place.getX() + 1;
		l = place.getY();

		if (k < 12 && l < 12) {

			Place otherPlace = place.getBoard().getPlacez()[k][l];

			if (otherPlace != null && otherPlace != place) {

				otherPlace.stopAttack();
			}
		}

		k = place.getX() - 1;

		if (k >= 0 && l < 12) {

			Place otherPlace = place.getBoard().getPlacez()[k][l];

			if (otherPlace != null && otherPlace != place) {

				otherPlace.stopAttack();
			}
		}

		k = place.getX() + 1;
		l = place.getY() - 1;

		if (k < 12 && l >= 0) {

			Place otherPlace = place.getBoard().getPlacez()[k][l];

			if (otherPlace != null && otherPlace != place) {

				otherPlace.stopAttack();
			}
		}

		k = place.getX();

		if (k < 12 && l >= 0) {

			Place otherPlace = place.getBoard().getPlacez()[k][l];

			if (otherPlace != null && otherPlace != place) {

				otherPlace.stopAttack();
			}
		}

		k = place.getX() - 1;

		if (k >= 0 && l >= 0) {

			Place otherPlace = place.getBoard().getPlacez()[k][l];

			if (otherPlace != null && otherPlace != place) {

				otherPlace.stopAttack();
			}
		}

		selected = false;
	}

}
