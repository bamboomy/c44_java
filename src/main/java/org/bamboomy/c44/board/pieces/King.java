package org.bamboomy.c44.board.pieces;

import java.util.ArrayList;

import org.bamboomy.c44.board.Place;
import org.bamboomy.c44.board.Player;

public class King extends Piece {

	private int xDelta, yDelta;

	public King(Place place, int color, int xDeltq, int yDelta) {
		super(place, color);

		this.xDelta = xDeltq;
		this.yDelta = yDelta;
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
	protected void setAttackablePlaces() {

		attackablePlaces = new ArrayList<Place>();

		int k = place.getX() + 1;
		int l = place.getY() + 1;

		if (k < 12 && l < 12) {

			Place otherPlace = place.getBoard().getPlacez()[k][l];

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

		k = place.getX();

		if (k < 12 && l < 12) {

			Place otherPlace = place.getBoard().getPlacez()[k][l];

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

		k = place.getX() - 1;

		if (k >= 0 && l < 12) {

			Place otherPlace = place.getBoard().getPlacez()[k][l];

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

		k = place.getX() + 1;
		l = place.getY();

		if (k < 12 && l < 12) {

			Place otherPlace = place.getBoard().getPlacez()[k][l];

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

		k = place.getX() - 1;

		if (k >= 0 && l < 12) {

			Place otherPlace = place.getBoard().getPlacez()[k][l];

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

		k = place.getX() + 1;
		l = place.getY() - 1;

		if (k < 12 && l >= 0) {

			Place otherPlace = place.getBoard().getPlacez()[k][l];

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

		k = place.getX();

		if (k < 12 && l >= 0) {

			Place otherPlace = place.getBoard().getPlacez()[k][l];

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

		k = place.getX() - 1;

		if (k >= 0 && l >= 0) {

			Place otherPlace = place.getBoard().getPlacez()[k][l];

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

		if (canRocadeRight()) {

			attackablePlaces.add(place.getBoard().getPlacez()[place.getX()][place.getY() - (xDelta * 2)]);
		}
	}

	private boolean canRocadeRight() {

		for (Player player : place.getBoard().getPlayerz()) {

			if (player.isChekcingCheck()) {

				return false;
			}
		}

		if (yDelta == 0 && !place.getBoard().getCurrentPlayer().checkCheck()
				&& place.getBoard().getPlacez()[place.getX()][place.getY() - xDelta].getPiece() == null
				&& place.getBoard().getPlacez()[place.getX()][place.getY() - (xDelta * 2)].getPiece() == null
				&& place.getBoard().getPlacez()[place.getX()][place.getY() - (xDelta * 3)].getPiece().getPieceName()
						.equalsIgnoreCase("tower")) {

			boolean result = true;

			result &= moveAndRollBack(place.getBoard().getPlacez()[place.getX()][place.getY() - xDelta]);

			if (!result) {

				return false;
			}

			result &= moveAndRollBack(place.getBoard().getPlacez()[place.getX()][place.getY() - (xDelta * 2)]);

			if (!result) {

				return false;
			}

			return true;
		}

		return false;
	}

	private boolean moveAndRollBack(Place place) {

		boolean result = true;

		Place oldPlace = this.place;

		moveTo(place);

		result &= !place.getBoard().getCurrentPlayer().checkCheck();

		rollBackMoveTo(oldPlace, true);

		return result;
	}

	@Override
	public String getPieceIdentifier() {

		return KING;
	}
}
