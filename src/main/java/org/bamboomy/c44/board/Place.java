package org.bamboomy.c44.board;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;

import javax.xml.bind.DatatypeConverter;

import org.bamboomy.c44.board.pieces.Piece;

import lombok.Getter;
import lombok.Setter;

public class Place {

	@Getter
	private final int color;

	public static int BLACK = 0, WHITE = 1;

	@Getter
	@Setter
	private Piece piece;

	@Getter
	private Board board;

	@Getter
	private int x, y;

	private String blackString = "piece_on_black", whiteString = "piece_on_white";

	@Getter
	private String md5;

	@Getter
	private boolean visuallyAttacked = false;

	@Getter
	@Setter
	private boolean attacked = false;

	@Getter
	private Move move;

	/*
	 * @Getter private EnPassant enPassant; // private ArrayList<EnPassant>
	 * enPassantz;
	 */

	/*
	 * private Piece takenPiece = null, selectedPiece = null;
	 * 
	 * private Place oldPlace = null;
	 * 
	 * /*
	 * 
	 * @Getter private Roccade roccade;
	 */

	/*
	 * @Setter
	 * 
	 * @Getter private boolean enPassantActivated = false;
	 */

	// private EnPassant backupEnpassant = null;
	
	private SecureRandom secureRandom = new SecureRandom("49357FE6D3EC4D9658996CDF440223E0".getBytes());

	public Place(int color, Board board, int i, int j) {

		if (color != BLACK && color != WHITE) {

			throw new RuntimeException("invalid color:" + color);
		}

		this.color = color;

		this.board = board;

		x = i;

		y = j;
	}

	private void calculateHash() {

		String time = System.currentTimeMillis() + "002154025";

		time += (secureRandom.nextDouble() * 63524187);

		try {

			byte[] bytesOfMessage = time.getBytes("UTF-8");

			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] thedigest = md.digest(bytesOfMessage);

			md5 = DatatypeConverter.printHexBinary(thedigest).toUpperCase();

		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();

			throw new RuntimeException(e);

		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();

			throw new RuntimeException(e);
		}
	}

	public String getCssName() {

		if (color == BLACK) {

			return blackString;

		} else {

			return whiteString;
		}
	}

	public boolean hasPiece() {

		return piece != null;
	}

	public void attack(int attackingColor) {

		calculateHash();

		visuallyAttacked = true;

		switch (Color.getBySeq(attackingColor)) {

		case RED:

			blackString = "piece_on_black_red";
			whiteString = "piece_on_white_red";

			break;

		case YELLOW:

			blackString = "piece_on_black_yellow";
			whiteString = "piece_on_white_yellow";

			break;

		case GREEN:

			blackString = "piece_on_black_green";
			whiteString = "piece_on_white_green";

			break;

		case BLUE:

			blackString = "piece_on_black_blue";
			whiteString = "piece_on_white_blue";

			break;
		}
	}

	public void stopAttack() {

		visuallyAttacked = false;

		blackString = "piece_on_black";
		whiteString = "piece_on_white";
	}

	public void click(ArrayList<Move> performedMoves) {

		System.out.println(getX() + ", " + getY() + ": " + getCssName());

		/*
		 * selectedPiece = board.getCurrentPlayer().getSelectedPiece();
		 * 
		 * oldPlace = board.getCurrentPlayer().getSelectedPiece().getCurrentPlace();
		 * 
		 * if (!getBoard().isCheckingCheck() && roccade != null) {
		 * 
		 * Roccade aboutToPerform = roccade;
		 * 
		 * roccade = null;
		 * 
		 * aboutToPerform.perform(board, noNext, this);
		 * 
		 * return; }
		 * 
		 * /* if (!getBoard().isCheckingCheck() && enPassant != null &&
		 * enPassantActivated) {
		 * 
		 * EnPassant aboutToPerform = enPassant;
		 * 
		 * enPassant = null;
		 * 
		 * aboutToPerform.perform(board, noNext, this);
		 * 
		 * return; }
		 */

		/*
		 * if (piece != null) {
		 * 
		 * takenPiece = piece;
		 * 
		 * board.getPlayerz()[takenPiece.getColor()].getPiecez().remove(takenPiece); }
		 * 
		 * board.getCurrentPlayer().getSelectedPiece().moveTo(this);
		 * 
		 * if (!noNext) {
		 * 
		 * commit();
		 * 
		 * 
		 * }
		 */

		if (move instanceof Promotion) {

			board.promote(move);

		} else {

			move.execute(performedMoves);

			move.getPiece().unselect();

			move.getPiece().setMoved(true);

			board.next();
		}
	}

	public void remove(Piece oldPiece) {

		if (oldPiece != piece) {

			if (piece != null) {

				throw new RuntimeException("this piece wasn't here :-( -> " + oldPiece.getCurrentPlace().getX() + ", "
						+ oldPiece.getCurrentPlace().getY() + " == " + x + ", " + y + " != "
						+ piece.getCurrentPlace().getX() + ", " + piece.getCurrentPlace().getY() + "=>"
						+ oldPiece.getPieceIdentifier());

			} else {

				throw new RuntimeException("this piece wasn't here :-( -> " + oldPiece.getCurrentPlace().getX() + ", "
						+ oldPiece.getCurrentPlace().getY() + " == " + x + ", " + y + "=>"
						+ oldPiece.getPieceIdentifier());
			}
		}

		piece = null;
	}

	/*
	 * public void attachEnPassant(EnPassant enPassant) {
	 * 
	 * // enPassantz.add(enPassant);
	 * 
	 * this.enPassant = enPassant; }
	 */

	/*
	 * public void rollBack() {
	 * 
	 * if (takenPiece != null) {
	 * 
	 * board.getPlayerz()[takenPiece.getColor()].getPiecez().add(takenPiece); }
	 * 
	 * selectedPiece.rollBackMoveTo(oldPlace);
	 * 
	 * piece = takenPiece;
	 * 
	 * commit(); }
	 * 
	 * public void commit() {
	 * 
	 * takenPiece = null; oldPlace = null; selectedPiece = null; }
	 * 
	 * public void attachRocade(Roccade roccade) {
	 * 
	 * this.roccade = roccade; }
	 * 
	 * public void removeRocade() {
	 * 
	 * roccade = null; }
	 */

	/*
	 * public void removeEnpassant() {
	 * 
	 * enPassant = null; }
	 */

	public String getMd5WithBoard() {

		return md5 + "/" + board.getHash();
	}

	public void addMove(Move move) {

		this.move = move;
	}

	/*
	 * public void backupEnpassant() {
	 * 
	 * backupEnpassant = enPassant;
	 * 
	 * enPassant = null; }
	 */

	/*
	 * public void restoreEnpassant() {
	 * 
	 * enPassant = backupEnpassant; }
	 */
}
