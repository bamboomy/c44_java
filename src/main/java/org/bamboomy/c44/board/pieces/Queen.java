package org.bamboomy.c44.board.pieces;

import java.util.ArrayList;

import org.bamboomy.c44.board.Place;
import org.bamboomy.c44.board.Player;

public class Queen extends Piece {

	public Queen(Place place, int color) {
		super(place, color);
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
		
		for (int i = 0; i < 12; i++) {

			Place otherPlace = place.getBoard().getPlacez()[i][place.getY()];

			if (otherPlace != null && otherPlace != place) {

				otherPlace.stopAttack();
			}
		}

		for (int i = 0; i < 12; i++) {

			Place otherPlace = place.getBoard().getPlacez()[place.getX()][i];

			if (otherPlace != null && otherPlace != place) {

				otherPlace.stopAttack();
			}
		}

		selected = false;
	}

	@Override
	public boolean canMove() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void setAttackablePlaces() {

		int counter = 0;
		
		attackablePlaces = new ArrayList<Place>();
		
		for (int i = place.getX(); i < 12; i++) {

			if (place.getY() - counter < 0) {

				break;
			}

			Place otherPlace = place.getBoard().getPlacez()[i][place.getY() - counter];

			if (otherPlace != null && otherPlace != place) {

				if (otherPlace.getPiece() != null) {

					if (otherPlace.getPiece().getColor() != color) {

						attackablePlaces.add(otherPlace);
					}

					break;

				} else {

					attackablePlaces.add(otherPlace);
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

						attackablePlaces.add(otherPlace);
					}

					break;

				} else {

					attackablePlaces.add(otherPlace);
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

						attackablePlaces.add(otherPlace);
					}

					break;

				} else {

					attackablePlaces.add(otherPlace);
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

						attackablePlaces.add(otherPlace);
					}

					break;

				} else {

					attackablePlaces.add(otherPlace);
				}
			}

			counter++;
		}

		for (int i = place.getX(); i < 12; i++) {

			Place otherPlace = place.getBoard().getPlacez()[i][place.getY()];

			if (otherPlace != null && otherPlace != place) {

				if (otherPlace.getPiece() != null) {

					if (otherPlace.getPiece().getColor() != color) {

						attackablePlaces.add(otherPlace);
					}
					
					break;

				} else {

					attackablePlaces.add(otherPlace);
				}
			}
		}

		for (int i = place.getY(); i < 12; i++) {

			Place otherPlace = place.getBoard().getPlacez()[place.getX()][i];

			if (otherPlace != null && otherPlace != place) {

				if (otherPlace.getPiece() != null) {

					if (otherPlace.getPiece().getColor() != color) {

						attackablePlaces.add(otherPlace);
					}
					
					break;

				} else {

					attackablePlaces.add(otherPlace);
				}
			}
		}

		for (int i = place.getX(); i >= 0; i--) {

			Place otherPlace = place.getBoard().getPlacez()[i][place.getY()];

			if (otherPlace != null && otherPlace != place) {

				if (otherPlace.getPiece() != null) {

					if (otherPlace.getPiece().getColor() != color) {

						attackablePlaces.add(otherPlace);
					}
					
					break;

				} else {

					attackablePlaces.add(otherPlace);
				}
			}
		}

		for (int i = place.getY(); i >= 0; i--) {

			Place otherPlace = place.getBoard().getPlacez()[place.getX()][i];

			if (otherPlace != null && otherPlace != place) {

				if (otherPlace.getPiece() != null) {

					if (otherPlace.getPiece().getColor() != color) {

						attackablePlaces.add(otherPlace);
					}
					
					break;

				} else {

					attackablePlaces.add(otherPlace);
				}
			}
		}
	}
}
