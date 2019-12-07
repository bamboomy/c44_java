package org.bamboomy.c44.board.pieces;

import java.util.ArrayList;

import org.bamboomy.c44.board.Move;
import org.bamboomy.c44.board.Place;
import org.bamboomy.c44.board.Player;
import org.bamboomy.c44.board.Rocade;

public class King extends Piece {

	private int xDelta, yDelta;

	public King(Place place, int color, int xDeltq, int yDelta, Player player) {
		super(place, color, player);

		this.xDelta = xDeltq;
		this.yDelta = yDelta;
	}

	private Rocade rocade;

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
	public void setAttackablePlaces(boolean checkRocade) {

		attackableMoves = new ArrayList<Move>();

		int k = currentPlace.getX() + 1;
		int l = currentPlace.getY() + 1;

		if (k < 12 && l < 12) {

			Place otherPlace = currentPlace.getBoard().getPlacez()[k][l];

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

		k = currentPlace.getX();

		if (k < 12 && l < 12) {

			Place otherPlace = currentPlace.getBoard().getPlacez()[k][l];

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

		k = currentPlace.getX() - 1;

		if (k >= 0 && l < 12) {

			Place otherPlace = currentPlace.getBoard().getPlacez()[k][l];

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

		k = currentPlace.getX() + 1;
		l = currentPlace.getY();

		if (k < 12 && l < 12) {

			Place otherPlace = currentPlace.getBoard().getPlacez()[k][l];

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

		k = currentPlace.getX() - 1;

		if (k >= 0 && l < 12) {

			Place otherPlace = currentPlace.getBoard().getPlacez()[k][l];

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

		k = currentPlace.getX() + 1;
		l = currentPlace.getY() - 1;

		if (k < 12 && l >= 0) {

			Place otherPlace = currentPlace.getBoard().getPlacez()[k][l];

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

		k = currentPlace.getX();

		if (k < 12 && l >= 0) {

			Place otherPlace = currentPlace.getBoard().getPlacez()[k][l];

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

		k = currentPlace.getX() - 1;

		if (k >= 0 && l >= 0) {

			Place otherPlace = currentPlace.getBoard().getPlacez()[k][l];

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

		if (!checkRocade) {

			return;
		}

		if (canRocadeRight()) {

			attackableMoves.add(rocade);
		}

		if (canRocadeLeft()) {

			attackableMoves.add(rocade);
		}
	}

	private boolean canRocadeRight() {

		if (currentPlace.getBoard().isCheckingCheck() || isRocaded() || !neverMoved) {

			return false;
		}

		if (!currentPlace.getBoard().getCurrentPlayer().checkCheck()
				&& currentPlace.getBoard().getPlacez()[currentPlace.getX() + yDelta][currentPlace.getY() - xDelta]
						.getPiece() == null
				&& currentPlace.getBoard().getPlacez()[currentPlace.getX() + (yDelta * 2)][currentPlace.getY()
						- (xDelta * 2)].getPiece() == null
				&& currentPlace.getBoard().getPlacez()[currentPlace.getX() + (yDelta * 3)][currentPlace.getY()
						- (xDelta * 3)].getPiece() != null
				&& currentPlace.getBoard().getPlacez()[currentPlace.getX() + (yDelta * 3)][currentPlace.getY()
						- (xDelta * 3)].getPiece().getPieceIdentifier().equalsIgnoreCase(Piece.TOWER)) {

			ArrayList<Place> placesToBeChecked = new ArrayList<>();

			placesToBeChecked.add(
					currentPlace.getBoard().getPlacez()[currentPlace.getX() + yDelta][currentPlace.getY() - xDelta]);
			placesToBeChecked
					.add(currentPlace.getBoard().getPlacez()[currentPlace.getX() + (yDelta * 2)][currentPlace.getY()
							- (xDelta * 2)]);
			placesToBeChecked
					.add(currentPlace.getBoard().getPlacez()[currentPlace.getX() + (yDelta * 3)][currentPlace.getY()
							- (xDelta * 3)]);

			currentPlace.getBoard().setAttackedPlaces(color);

			for (Place toBeCheckedPlace : placesToBeChecked) {

				if (toBeCheckedPlace.isAttacked()) {

					return false;
				}
			}

			rocade = new Rocade(currentPlace, currentPlace
					.getBoard().getPlacez()[currentPlace.getX() + (yDelta * 2)][currentPlace.getY() - (xDelta * 2)],
					this,
					(Tower) currentPlace.getBoard().getPlacez()[currentPlace.getX() + (yDelta * 3)][currentPlace.getY()
							- (xDelta * 3)].getPiece(),
					currentPlace.getBoard().getPlacez()[currentPlace.getX() + yDelta][currentPlace.getY() - xDelta]);

			return true;

		} else {

			return false;
		}

		/*
		 * boolean result = true;
		 * 
		 * result &=
		 * moveAndRollBack(currentPlace.getBoard().getPlacez()[currentPlace.getX() +
		 * yDelta][currentPlace.getY() - xDelta]);
		 * 
		 * if (!result) {
		 * 
		 * return false; }
		 * 
		 * result &= moveAndRollBack(
		 * currentPlace.getBoard().getPlacez()[currentPlace.getX() + (yDelta *
		 * 2)][currentPlace.getY() - (xDelta * 2)]);
		 * 
		 * if (!result) {
		 * 
		 * return false; }
		 * 
		 * Place towerPlace = currentPlace.getBoard().getPlacez()[currentPlace.getX() +
		 * (yDelta * 3)][currentPlace.getY() - (xDelta * 3)];
		 * 
		 * Piece tower = towerPlace.getPiece();
		 * 
		 * towerPlace.remove(tower);
		 * 
		 * result &= moveAndRollBack(towerPlace);
		 * 
		 * tower.uncheckedMoveTo(towerPlace);
		 * 
		 * if (!result) {
		 * 
		 * return false; }
		 * 
		 * currentPlace.getBoard().getPlacez()[currentPlace.getX() + (yDelta *
		 * 2)][currentPlace.getY() - (xDelta * 2)].attachRocade( new
		 * Roccade(currentPlace.getBoard().getPlacez()[currentPlace.getX() +
		 * yDelta][currentPlace.getY() - xDelta], tower));
		 * 
		 * return true;
		 */
	}

	/*
	 * private boolean moveAndRollBack(Place place) {
	 * 
	 * boolean result = true;
	 * 
	 * Place oldPlace = this.currentPlace;
	 * 
	 * moveTo(place);
	 * 
	 * result &= !place.getBoard().getCurrentPlayer().checkCheck();
	 * 
	 * rollBackMoveTo(oldPlace);
	 * 
	 * return result; }
	 */

	@Override
	public String getPieceIdentifier() {

		return KING;
	}

	private boolean canRocadeLeft() {

		if (currentPlace.getBoard().isCheckingCheck() || isRocaded() || !neverMoved) {

			return false;
		}

		if (!currentPlace.getBoard().getCurrentPlayer().checkCheck()
				&& currentPlace.getBoard().getPlacez()[currentPlace.getX() - yDelta][currentPlace.getY() + xDelta]
						.getPiece() == null
				&& currentPlace.getBoard().getPlacez()[currentPlace.getX() - (yDelta * 2)][currentPlace.getY()
						+ (xDelta * 2)].getPiece() == null
				&& currentPlace.getBoard().getPlacez()[currentPlace.getX() - (yDelta * 3)][currentPlace.getY()
						+ (xDelta * 3)].getPiece() == null
				&& currentPlace.getBoard().getPlacez()[currentPlace.getX() - (yDelta * 4)][currentPlace.getY()
						+ (xDelta * 4)].getPiece() != null
				&& currentPlace.getBoard().getPlacez()[currentPlace.getX() - (yDelta * 4)][currentPlace.getY()
						+ (xDelta * 4)].getPiece().getPieceIdentifier().equalsIgnoreCase(Piece.TOWER)) {

			ArrayList<Place> placesToBeChecked = new ArrayList<>();

			placesToBeChecked.add(
					currentPlace.getBoard().getPlacez()[currentPlace.getX() - yDelta][currentPlace.getY() + xDelta]);
			placesToBeChecked
					.add(currentPlace.getBoard().getPlacez()[currentPlace.getX() - (yDelta * 2)][currentPlace.getY()
							+ (xDelta * 2)]);
			placesToBeChecked
					.add(currentPlace.getBoard().getPlacez()[currentPlace.getX() - (yDelta * 3)][currentPlace.getY()
							+ (xDelta * 3)]);
			placesToBeChecked
					.add(currentPlace.getBoard().getPlacez()[currentPlace.getX() - (yDelta * 4)][currentPlace.getY()
							+ (xDelta * 4)]);

			currentPlace.getBoard().setAttackedPlaces(color);

			for (Place toBeCheckedPlace : placesToBeChecked) {

				if (toBeCheckedPlace.isAttacked()) {

					return false;
				}
			}

			rocade = new Rocade(currentPlace, currentPlace
					.getBoard().getPlacez()[currentPlace.getX() - (yDelta * 2)][currentPlace.getY() + (xDelta * 2)],
					this,
					(Tower) currentPlace.getBoard().getPlacez()[currentPlace.getX() - (yDelta * 3)][currentPlace.getY()
							+ (xDelta * 3)].getPiece(),
					currentPlace.getBoard().getPlacez()[currentPlace.getX() - yDelta][currentPlace.getY() + xDelta]);

			return true;

		} else {

			return false;
		}

		/*
		 * boolean result = true;
		 * 
		 * result &= moveAndRollBack(
		 * currentPlace.getBoard().getPlacez()[currentPlace.getX() -
		 * yDelta][currentPlace.getY() + xDelta]);
		 * 
		 * if (!result) {
		 * 
		 * return false; }
		 * 
		 * result &= moveAndRollBack(
		 * currentPlace.getBoard().getPlacez()[currentPlace.getX() - (yDelta *
		 * 2)][currentPlace.getY() + (xDelta * 2)]);
		 * 
		 * if (!result) {
		 * 
		 * return false; }
		 * 
		 * result &= moveAndRollBack(
		 * currentPlace.getBoard().getPlacez()[currentPlace.getX() - (yDelta *
		 * 3)][currentPlace.getY() + (xDelta * 3)]);
		 * 
		 * if (!result) {
		 * 
		 * return false; }
		 * 
		 * Place towerPlace = currentPlace.getBoard().getPlacez()[currentPlace.getX() -
		 * (yDelta * 4)][currentPlace .getY() + (xDelta * 4)];
		 * 
		 * Piece tower = towerPlace.getPiece();
		 * 
		 * towerPlace.remove(tower);
		 * 
		 * result &= moveAndRollBack(towerPlace);
		 * 
		 * tower.uncheckedMoveTo(towerPlace);
		 * 
		 * if (!result) {
		 * 
		 * return false; }
		 * 
		 * currentPlace.getBoard().getPlacez()[currentPlace.getX() - (yDelta *
		 * 2)][currentPlace.getY() + (xDelta * 2)] .attachRocade(new Roccade(
		 * currentPlace.getBoard().getPlacez()[currentPlace.getX() -
		 * yDelta][currentPlace.getY() + xDelta], tower));
		 * 
		 * return true; }
		 * 
		 * return false;
		 */
	}

	/*
	 * 
	 * @Override public void rollBackMoveTo(Place oldPlace) {
	 * 
	 * currentPlace.remove(this);
	 * 
	 * oldPlace.setPiece(this);
	 * 
	 * currentPlace = oldPlace;
	 * 
	 * neverMoved = rememberNeverMoved;
	 * 
	 * unselect(); }
	 */
}
