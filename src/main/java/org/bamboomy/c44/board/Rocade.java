package org.bamboomy.c44.board;

import java.util.ArrayList;

import org.bamboomy.c44.board.pieces.Piece;
import org.bamboomy.c44.board.pieces.Tower;

import lombok.Getter;
import lombok.Setter;

public class Rocade extends Move {

	@Getter
	@Setter
	private Tower tower;

	private Place towerToPlace, towerFromPlace;

	public Rocade(Place from, Place to, Piece piece, Tower tower, Place towerToPlace, Place towerFromPlace,
			boolean addMove) {

		super(from, to, piece, addMove);

		this.tower = tower;
		this.towerToPlace = towerToPlace;
		this.towerFromPlace = towerFromPlace;

		isRocade = true;
	}

	@Override
	public void execute(ArrayList<Move> performedMoves) {

		if (to.getPiece() != null) {

			takenPiece = to.getPiece();
			to.remove(to.getPiece());

			takenPiece.getPlayer().getPiecez().remove(takenPiece.getPlayer().getPiecez().indexOf(takenPiece));
		}

		from.remove(piece);

		to.setPiece(piece);

		piece.setCurrentPlace(to);

		towerToPlace.setPiece(tower);

		tower.setCurrentPlace(towerToPlace);
		
		towerFromPlace.remove(tower);

		if (performedMoves != null) {

			performedMoves.add(this);

			this.performedMoves = performedMoves;
		}
	}

	@Override
	public void rollBack() {

		if (takenPiece != null) {

			to.remove(piece);

			to.setPiece(takenPiece);

			takenPiece.getPlayer().getPiecez().add(takenPiece);

		} else {

			to.remove(piece);
		}

		from.setPiece(piece);

		piece.setCurrentPlace(from);

		towerFromPlace.setPiece(tower);
		
		towerToPlace.remove(tower);

		tower.setCurrentPlace(towerFromPlace);

		if (performedMoves != null) {

			performedMoves.remove(performedMoves.indexOf(this));
		}
	}

}
