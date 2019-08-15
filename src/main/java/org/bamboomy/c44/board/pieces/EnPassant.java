package org.bamboomy.c44.board.pieces;

import org.bamboomy.c44.board.Board;
import org.bamboomy.c44.board.Place;
import org.bamboomy.c44.board.Player;

import lombok.Getter;

public class EnPassant {

	@Getter
	private Pawn targetPawn;
	private Place toBeRemovedPawnPlace, parent;
	private Player player;

	public EnPassant(Pawn targetPawn, Place toBeRemovedPawnPlace, Place parent, Player player) {

		this.targetPawn = targetPawn;
		this.toBeRemovedPawnPlace = toBeRemovedPawnPlace;

		this.parent = parent;
		
		this.player = player;
	}

	public void perform(Board board, boolean noNext, Place enPassantPlace) {

		board.getCurrentPlayer().getSelectedPiece().moveTo(enPassantPlace);

		toBeRemovedPawnPlace.remove(targetPawn);

		if (!noNext) {

			enPassantPlace.commit();

			toBeRemovedPawnPlace.commit();

			board.next();
		}
	}

	public void destroy() {

		//parent.removeEnpassant();
		//targetPawn.setEnPassant(null);
		//parent.setEnPassantActivated(false);
		
		//player.removeEnPassant(this);

		System.out.println("enPassant destroyed...");
	}
}
