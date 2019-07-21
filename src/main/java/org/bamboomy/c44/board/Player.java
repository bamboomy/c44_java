package org.bamboomy.c44.board;

import java.util.ArrayList;

import org.bamboomy.c44.board.pieces.Pawn;
import org.bamboomy.c44.board.pieces.Piece;
import org.bamboomy.c44.board.pieces.Tower;

public class Player {

	private int color;

	public static final int RED = 0, BLUE = 1, GREEN = 2, YELLOW = 3;

	private ArrayList<Piece> piecez = new ArrayList<Piece>();

	public Player(int color, Board board) {

		if (color != RED && color != BLUE && color != GREEN && color != YELLOW) {

			throw new RuntimeException("invalid color: " + color);
		}

		this.color = color;

		if (color == RED) {

			initRed(board);
		}
	}

	private void initRed(Board board) {

		/*
		for (int i = 0; i < 8; i++) {
			piecez.add(new Pawn(board.getPlacez()[1][i + 2], color));
		}
		*/
		
		piecez.add(new Tower(board.getPlacez()[0][2], color));
		
		piecez.add(new Tower(board.getPlacez()[0][9], color));
	}
}
