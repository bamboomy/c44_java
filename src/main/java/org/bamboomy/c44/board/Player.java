package org.bamboomy.c44.board;

import java.util.ArrayList;

import org.bamboomy.c44.board.pieces.Bisshop;
import org.bamboomy.c44.board.pieces.Horse;
import org.bamboomy.c44.board.pieces.King;
import org.bamboomy.c44.board.pieces.Pawn;
import org.bamboomy.c44.board.pieces.Piece;
import org.bamboomy.c44.board.pieces.Queen;
import org.bamboomy.c44.board.pieces.Tower;

import lombok.Getter;

public class Player {

	private int color;

	public static final int RED = 0, YELLOW = 1, GREEN = 2, BLUE = 3;

	@Getter
	private ArrayList<Piece> piecez = new ArrayList<Piece>();

	@Getter
	private Piece selectedPiece;

	@Getter
	private boolean robot;

	private Board board;

	private Place enPassant = null;

	private Piece king;

	public Player(int color, Board board, boolean isRobot) {

		if (color != RED && color != BLUE && color != GREEN && color != YELLOW) {

			throw new RuntimeException("invalid color: " + color);
		}

		this.color = color;

		if (color == RED) {

			initRed(board);

		} else if (color == YELLOW) {

			initYellow(board);

		} else if (color == GREEN) {

			initGreen(board);

		} else if (color == BLUE) {

			initBlue(board);
		}

		robot = isRobot;

		this.board = board;
	}

	private void initRed(Board board) {

		for (int i = 0; i < 8; i++) {
			piecez.add(new Pawn(board.getPlacez()[1][i + 2], color, 1, 0));
		}

		piecez.add(new Tower(board.getPlacez()[0][2], color));
		piecez.add(new Horse(board.getPlacez()[0][3], color));
		piecez.add(new Bisshop(board.getPlacez()[0][4], color));

		king = new King(board.getPlacez()[0][5], color);

		piecez.add(king);
		piecez.add(new Queen(board.getPlacez()[0][6], color));

		piecez.add(new Bisshop(board.getPlacez()[0][7], color));
		piecez.add(new Horse(board.getPlacez()[0][8], color));
		piecez.add(new Tower(board.getPlacez()[0][9], color));
	}

	private void initYellow(Board board) {

		for (int i = 0; i < 8; i++) {
			piecez.add(new Pawn(board.getPlacez()[i + 2][10], color, 0, -1));
		}

		piecez.add(new Tower(board.getPlacez()[2][11], color));
		piecez.add(new Horse(board.getPlacez()[3][11], color));
		piecez.add(new Bisshop(board.getPlacez()[4][11], color));

		king = new King(board.getPlacez()[5][11], color);

		piecez.add(king);
		piecez.add(new Queen(board.getPlacez()[6][11], color));

		piecez.add(new Bisshop(board.getPlacez()[7][11], color));
		piecez.add(new Horse(board.getPlacez()[8][11], color));
		piecez.add(new Tower(board.getPlacez()[9][11], color));
	}

	private void initGreen(Board board) {

		for (int i = 0; i < 8; i++) {
			piecez.add(new Pawn(board.getPlacez()[10][i + 2], color, -1, 0));
		}

		piecez.add(new Tower(board.getPlacez()[11][2], color));
		piecez.add(new Horse(board.getPlacez()[11][3], color));
		piecez.add(new Bisshop(board.getPlacez()[11][4], color));

		king = new King(board.getPlacez()[11][6], color);

		piecez.add(new Queen(board.getPlacez()[11][5], color));
		piecez.add(king);

		piecez.add(new Bisshop(board.getPlacez()[11][7], color));
		piecez.add(new Horse(board.getPlacez()[11][8], color));
		piecez.add(new Tower(board.getPlacez()[11][9], color));
	}

	private void initBlue(Board board) {

		for (int i = 0; i < 8; i++) {
			piecez.add(new Pawn(board.getPlacez()[i + 2][1], color, 0, 1));
		}

		piecez.add(new Tower(board.getPlacez()[2][0], color));
		piecez.add(new Horse(board.getPlacez()[3][0], color));
		piecez.add(new Bisshop(board.getPlacez()[4][0], color));

		king = new King(board.getPlacez()[6][0], color);

		piecez.add(new Queen(board.getPlacez()[5][0], color));
		piecez.add(king);

		piecez.add(new Bisshop(board.getPlacez()[7][0], color));
		piecez.add(new Horse(board.getPlacez()[8][0], color));
		piecez.add(new Tower(board.getPlacez()[9][0], color));

	}

	boolean click(String md5) {

		for (Piece piece : piecez) {

			if (md5.equalsIgnoreCase(piece.getMd5())) {

				System.out.println(piece.getPieceName());

				piece.click();

				return true;
			}
		}

		return false;
	}

	public void setSelected(Piece piece) {

		if (selectedPiece != null) {

			selectedPiece.unselect();
		}

		selectedPiece = piece;
	}

	public void generateRandomMove() {

		unsetEnPassant();

		ArrayList<Piece> movable = new ArrayList<Piece>();

		for (Piece piece : piecez) {

			if (piece.canMove()) {

				movable.add(piece);
			}
		}

		movable.get((int) (Math.random() * movable.size())).doRandomMove();
	}

	public void setEnPassant(Place otherPlace, Pawn pawn) {

		System.out.println("en passant set");

		enPassant = otherPlace;

		enPassant.setEnPassant(true);

		enPassant.getEnPassantPieces().add(pawn);
	}

	public void unsetEnPassant() {

		System.out.println("enpassant unset (high)");

		if (enPassant != null) {

			enPassant.setEnPassant(false);
		}

		enPassant = null;
	}

	public boolean checkCheck() {

		for (Player player : board.getPlayerz()) {

			if (player != this) {

				for (Piece piece : player.getPiecez()) {

					if (piece.checkCheck(king)) {
						
						return true;
					}
				}
			}
		}

		return false;
	}
}
