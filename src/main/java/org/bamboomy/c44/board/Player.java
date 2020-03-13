package org.bamboomy.c44.board;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

import org.bamboomy.c44.ColorsTaken;
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
	private Color color;

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

	private ReentrantLock lock = new ReentrantLock();

	@Getter
	@Setter
	private long timestamp = -1;

	@Getter
	private ColorsTaken colorsTaken;

	@Setter
	@Getter
	private boolean dead;

	public Player(int color, Board board, boolean isRobot) {

		if (Color.getBySeq(color) == null) {

			throw new RuntimeException("invalid color: " + color);
		}

		this.color = Color.getBySeq(color);

		switch (this.color) {

		case RED:
			initRed(board);
			break;

		case YELLOW:
			initYellow(board);
			break;

		case GREEN:
			initGreen(board);
			break;

		case BLUE:
			initBlue(board);
			break;
		}

		robot = isRobot;

		this.board = board;
	}

	private void initRed(Board board) {

		for (int i = 0; i < 8; i++) {

			// endgame debug

			/*
			 * if (i >= 2 && i <= 4) {
			 * 
			 * piecez.add(new Pawn(board.getPlacez()[1][i + 2], color.getSeq(), 1, 0,
			 * this)); }
			 */

			piecez.add(new Pawn(board.getPlacez()[1][i + 2], color.getSeq(), 1, 0, this));

			// en passant debug

			/*
			 * if (i > 4) { piecez.add(new Pawn(board.getPlacez()[1][i + 2], color.getSeq(),
			 * 1, 0, this)); }
			 */
		}

		piecez.add(new Tower(board.getPlacez()[0][2], color.getSeq(), this));
		piecez.add(new Horse(board.getPlacez()[0][3], color.getSeq(), this));
		piecez.add(new Bisshop(board.getPlacez()[0][4], color.getSeq(), this));

		king = new King(board.getPlacez()[0][5], color.getSeq(), 1, 0, this);

		piecez.add(king);
		piecez.add(new Queen(board.getPlacez()[0][6], color.getSeq(), this));

		piecez.add(new Bisshop(board.getPlacez()[0][7], color.getSeq(), this));
		piecez.add(new Horse(board.getPlacez()[0][8], color.getSeq(), this));
		piecez.add(new Tower(board.getPlacez()[0][9], color.getSeq(), this));
	}

	private void initYellow(Board board) {

		for (int i = 0; i < 8; i++) {

			piecez.add(new Pawn(board.getPlacez()[i + 2][10], color.getSeq(), 0, -1, this));

			// endgame debug

			/*
			 * 
			 * if (i >= 3 && i <= 4) {
			 * 
			 * piecez.add(new Pawn(board.getPlacez()[i + 2][10], color.getSeq(), 0, -1,
			 * this)); }
			 */

			// en passant debug
			/*
			 * 
			 * if (i < 4) {
			 * 
			 * piecez.add(new Pawn(board.getPlacez()[i + 2][10], color.getSeq(), 0, -1,
			 * this)); }
			 * 
			 */
		}

		piecez.add(new Tower(board.getPlacez()[2][11], color.getSeq(), this));
		piecez.add(new Horse(board.getPlacez()[3][11], color.getSeq(), this));
		piecez.add(new Bisshop(board.getPlacez()[4][11], color.getSeq(), this));

		king = new King(board.getPlacez()[5][11], color.getSeq(), 0, -1, this);

		piecez.add(king);
		piecez.add(new Queen(board.getPlacez()[6][11], color.getSeq(), this));

		piecez.add(new Bisshop(board.getPlacez()[7][11], color.getSeq(), this));
		piecez.add(new Horse(board.getPlacez()[8][11], color.getSeq(), this));
		piecez.add(new Tower(board.getPlacez()[9][11], color.getSeq(), this));
	}

	private void initGreen(Board board) {

		for (int i = 0; i < 8; i++) {

			// endgame debug

			/*
			 * if (i == 5 || i == 3) {
			 * 
			 * piecez.add(new Pawn(board.getPlacez()[10][i + 2], color.getSeq(), -1, 0,
			 * this)); }
			 */

			piecez.add(new Pawn(board.getPlacez()[10][i + 2], color.getSeq(), -1, 0, this));

			// en passant debug

			/*
			 * 
			 * if (i < 3) {
			 * 
			 * piecez.add(new Pawn(board.getPlacez()[10][i + 2], color.getSeq(), -1, 0,
			 * this)); }
			 */
		}

		piecez.add(new Tower(board.getPlacez()[11][2], color.getSeq(), this));
		piecez.add(new Horse(board.getPlacez()[11][3], color.getSeq(), this));
		piecez.add(new Bisshop(board.getPlacez()[11][4], color.getSeq(), this));

		king = new King(board.getPlacez()[11][6], color.getSeq(), -1, 0, this);

		piecez.add(new Queen(board.getPlacez()[11][5], color.getSeq(), this));
		piecez.add(king);

		piecez.add(new Bisshop(board.getPlacez()[11][7], color.getSeq(), this));
		piecez.add(new Horse(board.getPlacez()[11][8], color.getSeq(), this));
		piecez.add(new Tower(board.getPlacez()[11][9], color.getSeq(), this));
	}

	private void initBlue(Board board) {

		for (int i = 0; i < 8; i++) {

			// endgame debug

			/*
			 * 
			 * if (i == 5 || i == 4) {
			 * 
			 * piecez.add(new Pawn(board.getPlacez()[i + 2][1], color.getSeq(), 0, 1,
			 * this)); }
			 */

			piecez.add(new Pawn(board.getPlacez()[i + 2][1], color.getSeq(), 0, 1, this));

			// en passant debug

			/*
			 * if (i > 4) {
			 * 
			 * piecez.add(new Pawn(board.getPlacez()[i + 2][1], color.getSeq(), 0, 1,
			 * this)); }
			 */

		}

		piecez.add(new Tower(board.getPlacez()[2][0], color.getSeq(), this));
		piecez.add(new Horse(board.getPlacez()[3][0], color.getSeq(), this));
		piecez.add(new Bisshop(board.getPlacez()[4][0], color.getSeq(), this));

		king = new King(board.getPlacez()[6][0], color.getSeq(), 0, 1, this);

		piecez.add(new Queen(board.getPlacez()[5][0], color.getSeq(), this));
		piecez.add(king);

		piecez.add(new Bisshop(board.getPlacez()[7][0], color.getSeq(), this));
		piecez.add(new Horse(board.getPlacez()[8][0], color.getSeq(), this));
		piecez.add(new Tower(board.getPlacez()[9][0], color.getSeq(), this));
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

		ArrayList<Integer> selectedPlaces = new ArrayList<>();

		int index = (int) (Math.random() * moves.size());

		boolean check = true;

		while (check && selectedPlaces.size() < moves.size()) {

			selectedPlaces.add(index);

			moves.get(index).execute(null);

			check = checkCheck();

			moves.get(index).rollBack();

			while (check && selectedPlaces.contains(index) && selectedPlaces.size() < moves.size()) {

				index = (int) (Math.random() * moves.size());
			}
		}

		if (selectedPlaces.size() == moves.size()) {

			die(true);

		} else {

			moves.get(index).execute(performedMoves);

			board.next();
		}
	}

	private void die(boolean pat) {

		this.pat = pat;

		board.removeMeWithoutDubiousCheck();
	}

	public boolean checkCheck() {

		lock.lock();

		for (Player player : board.getPlayerz()) {

			if (player != this && !player.isDead()) {

				for (Piece piece : player.getPiecez()) {

					player.setChekcingCheck(true);

					if (piece.checkCheck(king)) {

						player.setChekcingCheck(false);

						lock.unlock();

						return true;
					}

					player.setChekcingCheck(false);
				}
			}
		}

		lock.unlock();

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
	}

	public void setAttackedPlaces(boolean checkRocade) {

		for (Piece piece : piecez) {

			piece.setAttackablePlaces(checkRocade, false);

			ArrayList<Move> attackingMoves = piece.getAttackableMoves();

			for (Move move : attackingMoves) {

				move.getTo().setAttacked(true);
			}
		}
	}

	public void playRandomMove(ArrayList<Move> performedMoves) {

		if (checkCheck()) {

			if (canPrevent()) {

				prevent(performedMoves);

			} else {

				kamikaze(performedMoves);
			}

		} else {

			if (canTakeKing()) {

				takeAKing(performedMoves);

			} else {

				generateRandomMove(performedMoves);
			}
		}
	}

	public void setColorTaken(ColorsTaken user) {

		if (colorsTaken == null) {

			colorsTaken = user;
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
