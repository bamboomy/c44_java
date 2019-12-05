package org.bamboomy.c44.board;

import org.bamboomy.c44.board.pieces.Piece;

import lombok.Getter;
import lombok.Setter;

public class Move {

	@Getter
	@Setter
	private Place from, to;
	
	@Getter
	@Setter
	private Piece piece; 
}
