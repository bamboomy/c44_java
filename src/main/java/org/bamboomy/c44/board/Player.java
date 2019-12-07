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
import lombok.Setter;

public class Player {

	@Getter
	private int color;

	public static final int RED = 0, YELLOW = 1, GREEN = 2, BLUE = 3;

	@Getter
	private ArrayList<Piece> piecez = new ArrayList<Piece>();

	@Getter
	private Piece selectedPiece;

	@Getter
	private boolean robot;

	private Board board;

	private Piece king;

	// private EnPassant enPassant;

	private ArrayList<Piece> preventPieces = new ArrayList<>();

	@Setter
	@Getter
	private boolean chekcingCheck = false;

	private ArrayList<Piece> kingTakerz;

	// TODO: pat
	private boolean pat = false;

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
			piecez.add(new Pawn(board.getPlacez()[1][i + 2], color, 1, 0, this));
		}

		piecez.add(new Tower(board.getPlacez()[0][2], color, this));
		piecez.add(new Horse(board.getPlacez()[0][3], color, this));
		piecez.add(new Bisshop(board.getPlacez()[0][4], color, this));

		king = new King(board.getPlacez()[0][5], color, 1, 0, this);

		piecez.add(king);
		piecez.add(new Queen(board.getPlacez()[0][6], color, this));

		piecez.add(new Bisshop(board.getPlacez()[0][7], color, this));
		piecez.add(new Horse(board.getPlacez()[0][8], color, this));
		piecez.add(new Tower(board.getPlacez()[0][9], color, this));
	}

	private void initYellow(Board board) {

		for (int i = 0; i < 8; i++) {
			piecez.add(new Pawn(board.getPlacez()[i + 2][10], color, 0, -1, this));
		}

		piecez.add(new Tower(board.getPlacez()[2][11], color, this));
		piecez.add(new Horse(board.getPlacez()[3][11], color, this));
		piecez.add(new Bisshop(board.getPlacez()[4][11], color, this));

		king = new King(board.getPlacez()[5][11], color, 0, -1, this);

		piecez.add(king);
		piecez.add(new Queen(board.getPlacez()[6][11], color, this));

		piecez.add(new Bisshop(board.getPlacez()[7][11], color, this));
		piecez.add(new Horse(board.getPlacez()[8][11], color, this));
		piecez.add(new Tower(board.getPlacez()[9][11], color, this));
	}

	private void initGreen(Board board) {

		for (int i = 0; i < 8; i++) {
			piecez.add(new Pawn(board.getPlacez()[10][i + 2], color, -1, 0, this));
		}

		piecez.add(new Tower(board.getPlacez()[11][2], color, this));
		piecez.add(new Horse(board.getPlacez()[11][3], color, this));
		piecez.add(new Bisshop(board.getPlacez()[11][4], color, this));

		king = new King(board.getPlacez()[11][6], color, -1, 0, this);

		piecez.add(new Queen(board.getPlacez()[11][5], color, this));
		piecez.add(king);

		piecez.add(new Bisshop(board.getPlacez()[11][7], color, this));
		piecez.add(new Horse(board.getPlacez()[11][8], color, this));
		piecez.add(new Tower(board.getPlacez()[11][9], color, this));
	}

	private void initBlue(Board board) {

		for (int i = 0; i < 8; i++) {
			piecez.add(new Pawn(board.getPlacez()[i + 2][1], color, 0, 1, this));
		}

		piecez.add(new Tower(board.getPlacez()[2][0], color, this));
		piecez.add(new Horse(board.getPlacez()[3][0], color, this));
		piecez.add(new Bisshop(board.getPlacez()[4][0], color, this));

		king = new King(board.getPlacez()[6][0], color, 0, 1, this);

		piecez.add(new Queen(board.getPlacez()[5][0], color, this));
		piecez.add(king);

		piecez.add(new Bisshop(board.getPlacez()[7][0], color, this));
		piecez.add(new Horse(board.getPlacez()[8][0], color, this));
		piecez.add(new Tower(board.getPlacez()[9][0], color, this));
	}

	boolean click(String md5) {

		System.out.println("player");

		for (Piece piece : piecez) {

			if (md5.equalsIgnoreCase(piece.getMd5())) {

				System.out.println(piece.getPieceName());

				board.setPlayerIsMoving(true);

				piece.click();

				board.setWouldBeCheck(false);

				if (piece.checkWouldBeCheck()) {

					board.setWouldBeCheck(true);
				}

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

	public void generateRandomMove(ArrayList<Move> performedMoves) {

		ArrayList<Move> moves = new ArrayList<>();

		for (Piece piece : piecez) {

			if (piece.canMove()) {

				moves.addAll(piece.getAttackableMoves());
			}
		}

		/*
		 * ArrayList<Integer> selectedPlaces = new ArrayList<>();
		 * 
		 * int index = (int) (Math.random() * movable.size());
		 * 
		 * while (!movable.get(index).doRandomMove() && selectedPlaces.size() <
		 * movable.size()) {
		 * 
		 * selectedPlaces.add(index);
		 * 
		 * index = (int) (Math.random() * movable.size());
		 * 
		 * while (selectedPlaces.contains(index) && selectedPlaces.size() <
		 * movable.size()) {
		 * 
		 * index = (int) (Math.random() * movable.size()); } }
		 */

		if (moves.size() == 0) {

			die(true);

		} else {
			
			int index = (int) (Math.random() * moves.size());
			
			moves.get(index).execute(performedMoves);

			board.next();
		}
	}

	private void die(boolean pat) {

		this.pat = pat;

		board.removeMe();
	}

	public boolean checkCheck() {

		for (Player player : board.getPlayerz()) {

			if (player != this) {

				for (Piece piece : player.getPiecez()) {

					player.setChekcingCheck(true);

					if (piece.checkCheck(king)) {

						player.setChekcingCheck(false);

						return true;
					}

					player.setChekcingCheck(false);
				}
			}
		}

		return false;
	}

	public boolean canPrevent() {

		preventPieces = new ArrayList<>();

		boolean canPrevent = false;

		ArrayList<Piece> copyOfPiecez = new ArrayList<>();

		for (Piece piece : getPiecez()) {

			copyOfPiecez.add(piece);
		}

		for (Piece piece : copyOfPiecez) {

			boolean pieceCanPrevent = piece.canPrevent();

			canPrevent |= pieceCanPrevent;

			if (pieceCanPrevent) {

				preventPieces.add(piece);
			}
		}

		return canPrevent;
	}

	public void prevent(ArrayList<Move> performedMoves) {

		preventPieces.get((int) Math.random() * preventPieces.size()).prevent(performedMoves);
		
		board.next();
	}

	/*
	 * public void attachEnPassant(EnPassant enPassant) {
	 * 
	 * if (this.enPassant != null) {
	 * 
	 * throw new RuntimeException("en passant wasn't null"); }
	 * 
	 * this.enPassant = enPassant; }
	 * 
	 * public void removeEnPassant() {
	 * 
	 * if (enPassant != null) { enPassant.destroy(); } }
	 */

	public boolean canTakeKing() {

		kingTakerz = new ArrayList<>();

		for (Piece piece : piecez) {

			if (piece.canTakeKing()) {

				kingTakerz.add(piece);
			}
		}

		return kingTakerz.size() > 0;
	}

	public void takeAKing(ArrayList<Move> performedMoves) {

		kingTakerz.get((int) (kingTakerz.size() * Math.random())).takeKing(performedMoves);
		
		board.next();
	}

	public void kamikaze(ArrayList<Move> performedMoves) {

		ArrayList<Piece> movable = new ArrayList<Piece>();

		for (Piece piece : piecez) {

			if (piece.canMove()) {

				movable.add(piece);
			}
		}

		movable.get((int) (Math.random() * movable.size())).kamikaze(performedMoves);

		die(false);
		
		board.next();
	}

	public void setAttackedPlaces(boolean checkRocade) {

		for (Piece piece : piecez) {

			piece.setAttackablePlaces(checkRocade);

			ArrayList<Move> attackingMoves = piece.getAttackableMoves();

			for (Move move : attackingMoves) {

				move.getTo().setAttacked(true);
			}
		}
	}

	/*
	 * public void removeEnPassant(EnPassant enPassant) {
	 * 
	 * if (this.enPassant != enPassant) {
	 * 
	 * throw new RuntimeException("wrong enpassant"); }
	 * 
	 * enPassant = null; }
	 */
}
