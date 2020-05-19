package org.bamboomy.c44.board.pieces;

import java.util.ArrayList;

import org.bamboomy.c44.board.Color;
import org.bamboomy.c44.board.Move;
import org.bamboomy.c44.board.Place;
import org.bamboomy.c44.board.Player;

public class Tower extends Piece {

	public Tower(Place place, int color, Player player) {
		super(place, color, player);
	}

	@Override
	public String getPieceName() {
		
		switch(Color.getBySeq(color)) {
		
		case RED:
			
			return "/" + getCurrentPlace().getBoard().getTomcatPath() + "/img/tower_red.png";
			
		case YELLOW:
			
			return "/" + getCurrentPlace().getBoard().getTomcatPath() + "/img/tower_yellow.png";
			
		case GREEN:
			
			return "/" + getCurrentPlace().getBoard().getTomcatPath() + "/img/tower_green.png";
			
		case BLUE:
			
			return "/" + getCurrentPlace().getBoard().getTomcatPath() + "/img/tower_blue.png";
		}

		throw new RuntimeException("invalid color in tower");
	}

	public void setAttackablePlaces(boolean unused, boolean addMove) {

		attackableMoves = new ArrayList<Move>();

		for (int i = currentPlace.getX(); i < 12; i++) {

			Place otherPlace = currentPlace.getBoard().getPlacez()[i][currentPlace.getY()];

			if (otherPlace != null && otherPlace != currentPlace) {

				if (otherPlace.getPiece() != null) {

					if (otherPlace.getPiece().getColor() != color) {

						attackableMoves.add(new Move(currentPlace, otherPlace, this, addMove));
					}

					break;

				} else {

					attackableMoves.add(new Move(currentPlace, otherPlace, this, addMove));
				}
			}
		}

		for (int i = currentPlace.getY(); i < 12; i++) {

			Place otherPlace = currentPlace.getBoard().getPlacez()[currentPlace.getX()][i];

			if (otherPlace != null && otherPlace != currentPlace) {

				if (otherPlace.getPiece() != null) {

					if (otherPlace.getPiece().getColor() != color) {

						attackableMoves.add(new Move(currentPlace, otherPlace, this, addMove));
					}

					break;

				} else {

					attackableMoves.add(new Move(currentPlace, otherPlace, this, addMove));
				}
			}
		}

		for (int i = currentPlace.getX(); i >= 0; i--) {

			Place otherPlace = currentPlace.getBoard().getPlacez()[i][currentPlace.getY()];

			if (otherPlace != null && otherPlace != currentPlace) {

				if (otherPlace.getPiece() != null) {

					if (otherPlace.getPiece().getColor() != color) {

						attackableMoves.add(new Move(currentPlace, otherPlace, this, addMove));
					}

					break;

				} else {

					attackableMoves.add(new Move(currentPlace, otherPlace, this, addMove));
				}
			}
		}

		for (int i = currentPlace.getY(); i >= 0; i--) {

			Place otherPlace = currentPlace.getBoard().getPlacez()[currentPlace.getX()][i];

			if (otherPlace != null && otherPlace != currentPlace) {

				if (otherPlace.getPiece() != null) {

					if (otherPlace.getPiece().getColor() != color) {

						attackableMoves.add(new Move(currentPlace, otherPlace, this, addMove));
					}

					break;

				} else {

					attackableMoves.add(new Move(currentPlace, otherPlace, this, addMove));
				}
			}
		}

	}

	@Override
	public String getPieceIdentifier() {

		return TOWER;
	}
}
