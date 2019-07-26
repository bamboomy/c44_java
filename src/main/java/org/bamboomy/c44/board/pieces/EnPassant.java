package org.bamboomy.c44.board.pieces;

import org.bamboomy.c44.board.Board;
import org.bamboomy.c44.board.Place;

public class EnPassant {

	private Pawn targetPawn;
	private Place toBeRemovedPawnPlace, parent;

	public EnPassant(Pawn targetPawn, Place toBeRemovedPawnPlace, Place parent) {

		this.targetPawn = targetPawn;
		this.toBeRemovedPawnPlace = toBeRemovedPawnPlace;
		
		this.parent = parent;
	}

	public void perform(Board board, boolean noNext, Place currentPlace) {

		board.getCurrentPlayer().getSelectedPiece().moveTo(currentPlace);

		toBeRemovedPawnPlace.remove(targetPawn);

		if (!noNext) {

			currentPlace.commit();

			toBeRemovedPawnPlace.commit();

			board.next();
		}
	}

	public void destroy() {

		parent.removeEnpassant();
	}
}
