package org.bamboomy.c44.board.pieces;

import org.bamboomy.c44.board.Board;
import org.bamboomy.c44.board.Place;

public class Roccade {

	private Place towerPlace;
	private Piece tower;

	public Roccade(Place towerPlace, Piece tower) {

		this.tower = tower;
		this.towerPlace = towerPlace;
	}

	public void perform(Board board, boolean noNext, Place currentPlace) {

		/*
		 * in case we need to rollback
		 * 
		 * Piece selectedPiece = board.getCurrentPlayer().getSelectedPiece();
		 * 
		 * Place oldPlace = board.getCurrentPlayer().getSelectedPiece().getPlace();
		 */

		board.getCurrentPlayer().getSelectedPiece().moveTo(currentPlace);

		tower.moveTo(towerPlace);

		if (!noNext) {

			currentPlace.commit();
			
			towerPlace.commit();

			board.next();
		}

	}

}
