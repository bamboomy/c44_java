package org.bamboomy.c44.board.pieces;

import java.util.ArrayList;

import org.bamboomy.c44.board.Move;
import org.bamboomy.c44.board.Place;
import org.bamboomy.c44.board.Player;

public class Pawn extends Piece {

	private int xDelta, yDelta;

	public Pawn(Place place, int color, int xDelta, int yDelta) {
		super(place, color);

		this.xDelta = xDelta;
		this.yDelta = yDelta;
	}

	@Override
	public String getPieceName() {

		if (color == Player.RED) {

			return "../img/pawn_red.png";

		} else if (color == Player.YELLOW) {

			return "../img/pawn_yellow.png";

		} else if (color == Player.GREEN) {

			return "../img/pawn_green.png";

		} else if (color == Player.BLUE) {

			return "../img/pawn_blue.png";
		}

		throw new RuntimeException("invalid color in pawn");
	}

	@Override
	protected void setAttackablePlaces() {

		attackablePlaces = new ArrayList<Move>();

		Place otherPlace = currentPlace.getBoard().getPlacez()[currentPlace.getX() + xDelta][currentPlace.getY() + yDelta];

		if (otherPlace != null && !otherPlace.hasPiece()) {

			attackablePlaces.add(new Move(currentPlace, otherPlace, this));

			if (neverMoved) {

				// Place enPassantPlace = otherPlace;

				otherPlace = currentPlace.getBoard().getPlacez()[currentPlace.getX() + (xDelta * 2)][currentPlace.getY() + (yDelta * 2)];

				if (otherPlace != null && !otherPlace.hasPiece()) {

					attackablePlaces.add(new Move(currentPlace, otherPlace, this));

					/*
					 * if (!place.getBoard().isCheckingCheck()) {
					 * 
					 * EnPassant enPassant = new EnPassant(this, otherPlace, enPassantPlace,
					 * place.getBoard().getCurrentPlayer());
					 * 
					 * enPassantPlace.attachEnPassant(enPassant);
					 * 
					 * setEnPassant(enPassant);
					 * 
					 * place.getBoard().getCurrentPlayer().attachEnPassant(enPassant); }
					 */
				}
			}
		}

		if (xDelta != 0 && currentPlace.getY() + 1 < 12) {

			otherPlace = currentPlace.getBoard().getPlacez()[currentPlace.getX() + xDelta][currentPlace.getY() + 1];

			if (otherPlace != null && otherPlace.getPiece() != null && otherPlace.getPiece().getColor() != color) {

				attackablePlaces.add(new Move(currentPlace, otherPlace, this));
			}
		}

		if (xDelta != 0 && currentPlace.getY() - 1 >= 0) {

			otherPlace = currentPlace.getBoard().getPlacez()[currentPlace.getX() + xDelta][currentPlace.getY() - 1];

			if (otherPlace != null && otherPlace.getPiece() != null && otherPlace.getPiece().getColor() != color) {

				attackablePlaces.add(new Move(currentPlace, otherPlace, this));
			}
		}

		if (yDelta != 0 && currentPlace.getX() + 1 < 12) {

			otherPlace = currentPlace.getBoard().getPlacez()[currentPlace.getX() + 1][currentPlace.getY() + yDelta];

			if (otherPlace != null && otherPlace.getPiece() != null && otherPlace.getPiece().getColor() != color) {

				attackablePlaces.add(new Move(currentPlace, otherPlace, this));
			}
		}

		if (yDelta != 0 && currentPlace.getX() - 1 >= 0) {

			otherPlace = currentPlace.getBoard().getPlacez()[currentPlace.getX() - 1][currentPlace.getY() + yDelta];

			if (otherPlace != null && otherPlace.getPiece() != null && otherPlace.getPiece().getColor() != color) {

				attackablePlaces.add(new Move(currentPlace, otherPlace, this));
			}
		}

		// em passant -> it doesn't work: we abandon it (for now)... (we're still in
		// poc...)

		/*
		 * 
		 * if (xDelta != 0 && place.getY() + 1 < 12) {
		 * 
		 * otherPlace = place.getBoard().getPlacez()[place.getX() + xDelta][place.getY()
		 * + 1];
		 * 
		 * if (otherPlace != null && otherPlace.getEnPassant() != null &&
		 * otherPlace.getEnPassant().getTargetPawn().getColor() != color) {
		 * 
		 * System.out.println("en passant detected");
		 * 
		 * otherPlace.setEnPassantActivated(true);
		 * 
		 * attackablePlaces.add(otherPlace); } }
		 * 
		 * if (xDelta != 0 && place.getY() - 1 >= 0) {
		 * 
		 * otherPlace = place.getBoard().getPlacez()[place.getX() + xDelta][place.getY()
		 * - 1];
		 * 
		 * if (otherPlace != null && otherPlace.getEnPassant() != null &&
		 * otherPlace.getEnPassant().getTargetPawn().getColor() != color) {
		 * 
		 * System.out.println("en passant detected");
		 * 
		 * otherPlace.setEnPassantActivated(true);
		 * 
		 * attackablePlaces.add(otherPlace); } }
		 * 
		 * if (yDelta != 0 && place.getX() + 1 < 12) {
		 * 
		 * otherPlace = place.getBoard().getPlacez()[place.getX() + 1][place.getY() +
		 * yDelta];
		 * 
		 * if (otherPlace != null && otherPlace.getEnPassant() != null &&
		 * otherPlace.getEnPassant().getTargetPawn().getColor() != color) {
		 * 
		 * System.out.println("en passant detected");
		 * 
		 * otherPlace.setEnPassantActivated(true);
		 * 
		 * attackablePlaces.add(otherPlace); } }
		 * 
		 * if (yDelta != 0 && place.getX() - 1 >= 0) {
		 * 
		 * otherPlace = place.getBoard().getPlacez()[place.getX() - 1][place.getY() +
		 * yDelta];
		 * 
		 * if (otherPlace != null && otherPlace.getEnPassant() != null &&
		 * otherPlace.getEnPassant().getTargetPawn().getColor() != color) {
		 * 
		 * System.out.println("en passant detected");
		 * 
		 * otherPlace.setEnPassantActivated(true);
		 * 
		 * attackablePlaces.add(otherPlace); } }
		 * 
		 */
	}

	@Override
	public void rollBackMoveTo(Place oldPlace) {

		/*
		 * if (enPassant != null) {
		 * 
		 * enPassant.destroy(); }
		 */

		currentPlace.remove(this);

		oldPlace.setPiece(this);

		currentPlace = oldPlace;

		neverMoved = rememberNeverMoved;

		// oldPlace.restoreEnpassant();

		unselect();
	}

	@Override
	public String getPieceIdentifier() {

		return PAWN;
	}
}
