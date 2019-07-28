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

			attackablePlaces
					.add(place.getBoard().getPlacez()[place.getX() + (yDelta * 2)][place.getY() - (xDelta * 2)]);

		} else if (place.getBoard().getPlacez()[place.getX() + (yDelta * 2)][place.getY() - (xDelta * 2)] != null) {

			place.getBoard().getPlacez()[place.getX() + (yDelta * 2)][place.getY() - (xDelta * 2)].removeRocade();
		}

		if (canRocadeLeft()) {

			attackablePlaces
					.add(place.getBoard().getPlacez()[place.getX() - (yDelta * 2)][place.getY() + (xDelta * 2)]);

		} else if (place.getBoard().getPlacez()[place.getX() - (yDelta * 2)][place.getY() + (xDelta * 2)] != null) {

			place.getBoard().getPlacez()[place.getX() - (yDelta * 2)][place.getY() + (xDelta * 2)].removeRocade();
		}
	}

	private boolean canRocadeRight() {

		if (place.getBoard().isCheckingCheck() || isRocaded() || !neverMoved) {

			return false;
		}

		if (!place.getBoard().getCurrentPlayer().checkCheck()
				&& place.getBoard().getPlacez()[place.getX() + yDelta][place.getY() - xDelta].getPiece() == null
				&& place.getBoard().getPlacez()[place.getX() + (yDelta * 2)][place.getY() - (xDelta * 2)]
						.getPiece() == null
				&& place.getBoard().getPlacez()[place.getX() + (yDelta * 3)][place.getY() - (xDelta * 3)]
						.getPiece() != null
				&& place.getBoard().getPlacez()[place.getX() + (yDelta * 3)][place.getY() - (xDelta * 3)].getPiece()
						.getPieceIdentifier().equalsIgnoreCase(Piece.TOWER)) {

			boolean result = true;

			result &= moveAndRollBack(place.getBoard().getPlacez()[place.getX() + yDelta][place.getY() - xDelta]);

			if (!result) {

				return false;
			}

			result &= moveAndRollBack(
					place.getBoard().getPlacez()[place.getX() + (yDelta * 2)][place.getY() - (xDelta * 2)]);

			if (!result) {

				return false;
			}

			Place towerPlace = place.getBoard().getPlacez()[place.getX() + (yDelta * 3)][place.getY() - (xDelta * 3)];

			Piece tower = towerPlace.getPiece();

			towerPlace.remove(tower);

			result &= moveAndRollBack(towerPlace);

			tower.uncheckedMoveTo(towerPlace);

			if (!result) {

				return false;
			}

			place.getBoard().getPlacez()[place.getX() + (yDelta * 2)][place.getY() - (xDelta * 2)].attachRocade(
					new Roccade(place.getBoard().getPlacez()[place.getX() + yDelta][place.getY() - xDelta], tower));

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

	private boolean canRocadeLeft() {

		if (place.getBoard().isCheckingCheck() || isRocaded() || !neverMoved) {

			return false;
		}

		if (!place.getBoard().getCurrentPlayer().checkCheck()
				&& place.getBoard().getPlacez()[place.getX() - yDelta][place.getY() + xDelta].getPiece() == null
				&& place.getBoard().getPlacez()[place.getX() - (yDelta * 2)][place.getY() + (xDelta * 2)]
						.getPiece() == null
				&& place.getBoard().getPlacez()[place.getX() - (yDelta * 3)][place.getY() + (xDelta * 3)]
						.getPiece() == null
				&& place.getBoard().getPlacez()[place.getX() - (yDelta * 4)][place.getY() + (xDelta * 4)]
						.getPiece() != null
				&& place.getBoard().getPlacez()[place.getX() - (yDelta * 4)][place.getY() + (xDelta * 4)].getPiece()
						.getPieceIdentifier().equalsIgnoreCase(Piece.TOWER)) {

			boolean result = true;

			result &= moveAndRollBack(place.getBoard().getPlacez()[place.getX() - yDelta][place.getY() + xDelta]);

			if (!result) {

				return false;
			}

			result &= moveAndRollBack(
					place.getBoard().getPlacez()[place.getX() - (yDelta * 2)][place.getY() + (xDelta * 2)]);

			if (!result) {

				return false;
			}

			result &= moveAndRollBack(
					place.getBoard().getPlacez()[place.getX() - (yDelta * 3)][place.getY() + (xDelta * 3)]);

			if (!result) {

				return false;
			}

			Place towerPlace = place.getBoard().getPlacez()[place.getX() - (yDelta * 4)][place.getY() + (xDelta * 4)];

			Piece tower = towerPlace.getPiece();

			towerPlace.remove(tower);

			result &= moveAndRollBack(towerPlace);

			tower.uncheckedMoveTo(towerPlace);

			if (!result) {

				return false;
			}

			place.getBoard().getPlacez()[place.getX() - (yDelta * 2)][place.getY() + (xDelta * 2)].attachRocade(
					new Roccade(place.getBoard().getPlacez()[place.getX() - yDelta][place.getY() + xDelta], tower));

			return true;
		}

		return false;
	}

	@Override
	public boolean moveTo(Place otherPlace) {

		boolean rememberedNeverMoved = neverMoved;

		neverMoved = false;

		super.moveTo(otherPlace);

		return rememberedNeverMoved;
	}
	
	@Override
	public void rollBackMoveTo(Place oldPlace, boolean neverMoved) {

		place.remove(this);

		oldPlace.setPiece(this);

		place = oldPlace;

		this.neverMoved = neverMoved;

		unselect();
	}
}
