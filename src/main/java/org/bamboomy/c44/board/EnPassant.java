package org.bamboomy.c44.board;

import java.util.ArrayList;

import org.bamboomy.c44.board.pieces.Piece;

import lombok.Getter;

public class EnPassant extends Move {

	@Getter
	private ArrayList<Piece> enemies;

	public EnPassant(Place from, Place to, Piece piece, boolean addMove) {
		
		super(from, to, piece, addMove);
	}
}
