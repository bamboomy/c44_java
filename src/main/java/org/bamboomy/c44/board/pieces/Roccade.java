package org.bamboomy.c44.board.pieces;

import org.bamboomy.c44.board.Board;
import org.bamboomy.c44.board.Place;

public class Roccade {

	private Place towerPlace;
	private Piece tower;

	public Roccade(Place towerPlace, Piece tower) {

		this.tower = tower;
		this.towerPlace = towerPlace;

		System.out.println("roccade created");
	}

	public void perform(Board board, boolean noNext, Place currentPlace) {

		board.getCurrentPlayer().getSelectedPiece().moveTo(currentPlace);

		board.getCurrentPlayer().getSelectedPiece().setRocaded(true);

		tower.moveTo(towerPlace);

		if (!noNext) {

			currentPlace.commit();

			towerPlace.commit();

			board.next();
		}

	}

}
