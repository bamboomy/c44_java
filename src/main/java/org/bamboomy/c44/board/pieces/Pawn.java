package org.bamboomy.c44.board.pieces;

import java.util.ArrayList;

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

		attackablePlaces = new ArrayList<Place>();

		Place otherPlace = place.getBoard().getPlacez()[place.getX() + xDelta][place.getY() + yDelta];

		if (otherPlace != null && !otherPlace.hasPiece()) {

			attackablePlaces.add(otherPlace);

			if (neverMoved) {

				Place enPassantPlace = otherPlace;

				otherPlace = place.getBoard().getPlacez()[place.getX() + (xDelta * 2)][place.getY() + (yDelta * 2)];

				if (otherPlace != null && !otherPlace.hasPiece()) {

					attackablePlaces.add(otherPlace);

					if (!place.getBoard().isCheckingCheck()) {

						EnPassant enPassant = new EnPassant(this, otherPlace, enPassantPlace);

						enPassantPlace.attachEnPassant(enPassant);
						
						setEnPassant(enPassant);

						place.getBoard().getCurrentPlayer().attachEnPassant(enPassant);
					}
				}
			}
		}

		if (xDelta != 0 && place.getY() + 1 < 12) {

			otherPlace = place.getBoard().getPlacez()[place.getX() + xDelta][place.getY() + 1];

			if (otherPlace != null && otherPlace.getPiece() != null && otherPlace.getPiece().getColor() != color) {

				attackablePlaces.add(otherPlace);
			}
		}

		if (xDelta != 0 && place.getY() - 1 >= 0) {

			otherPlace = place.getBoard().getPlacez()[place.getX() + xDelta][place.getY() - 1];

			if (otherPlace != null && otherPlace.getPiece() != null && otherPlace.getPiece().getColor() != color) {

				attackablePlaces.add(otherPlace);
			}
		}

		if (yDelta != 0 && place.getX() + 1 < 12) {

			otherPlace = place.getBoard().getPlacez()[place.getX() + 1][place.getY() + yDelta];

			if (otherPlace != null && otherPlace.getPiece() != null && otherPlace.getPiece().getColor() != color) {

				attackablePlaces.add(otherPlace);
			}
		}

		if (yDelta != 0 && place.getX() - 1 >= 0) {

			otherPlace = place.getBoard().getPlacez()[place.getX() - 1][place.getY() + yDelta];

			if (otherPlace != null && otherPlace.getPiece() != null && otherPlace.getPiece().getColor() != color) {

				attackablePlaces.add(otherPlace);
			}
		}

		// em passant

		if (place.getBoard().isCheckingCheck()) {

			return;
		}

		if (xDelta != 0 && place.getY() + 1 < 12) {

			otherPlace = place.getBoard().getPlacez()[place.getX() + xDelta][place.getY() + 1];

			if (otherPlace != null && otherPlace.getEnPassant() != null
					&& otherPlace.getEnPassant().getTargetPawn().getColor() != color) {

				System.out.println("en passant detected");
				
				otherPlace.setEnPassantActivated(true);

				attackablePlaces.add(otherPlace);
			}
		}

		if (xDelta != 0 && place.getY() - 1 >= 0) {

			otherPlace = place.getBoard().getPlacez()[place.getX() + xDelta][place.getY() - 1];

			if (otherPlace != null && otherPlace.getEnPassant() != null
					&& otherPlace.getEnPassant().getTargetPawn().getColor() != color) {

				System.out.println("en passant detected");
				
				otherPlace.setEnPassantActivated(true);

				attackablePlaces.add(otherPlace);
			}
		}

		if (yDelta != 0 && place.getX() + 1 < 12) {

			otherPlace = place.getBoard().getPlacez()[place.getX() + 1][place.getY() + yDelta];

			if (otherPlace != null && otherPlace.getEnPassant() != null
					&& otherPlace.getEnPassant().getTargetPawn().getColor() != color) {

				System.out.println("en passant detected");
				
				otherPlace.setEnPassantActivated(true);

				attackablePlaces.add(otherPlace);
			}
		}

		if (yDelta != 0 && place.getX() - 1 >= 0) {

			otherPlace = place.getBoard().getPlacez()[place.getX() - 1][place.getY() + yDelta];

			if (otherPlace != null && otherPlace.getEnPassant() != null
					&& otherPlace.getEnPassant().getTargetPawn().getColor() != color) {

				System.out.println("en passant detected");
				
				otherPlace.setEnPassantActivated(true);

				attackablePlaces.add(otherPlace);
			}
		}
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
		
		oldPlace.restoreEnpassant();

		unselect();
	}

	@Override
	public String getPieceIdentifier() {

		return PAWN;
	}
}
