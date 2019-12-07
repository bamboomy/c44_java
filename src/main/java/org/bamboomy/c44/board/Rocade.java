package org.bamboomy.c44.board;

import org.bamboomy.c44.board.pieces.Piece;
import org.bamboomy.c44.board.pieces.Tower;

import lombok.Getter;
import lombok.Setter;

public class Rocade extends Move {

	@Getter
	@Setter
	private Tower tower;

	private Place towerToPlace;

	public Rocade(Place from, Place to, Piece piece, Tower tower, Place towerToPlace, boolean addMove) {

		super(from, to, piece, addMove);

		this.tower = tower;
		this.towerToPlace = towerToPlace;

		isRocade = true;
	}
}
