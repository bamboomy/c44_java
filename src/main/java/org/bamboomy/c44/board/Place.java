package org.bamboomy.c44.board;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

import org.bamboomy.c44.board.pieces.Piece;
import org.bamboomy.c44.board.pieces.Roccade;

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
	private boolean attacked = false;

	/*
	 * @Getter private EnPassant enPassant; // private ArrayList<EnPassant>
	 * enPassantz;
	 */

	private Piece takenPiece = null, selectedPiece = null;

	private Place oldPlace = null;

	@Getter
	private Roccade roccade;

	/*
	 * @Setter
	 * 
	 * @Getter private boolean enPassantActivated = false;
	 */

	// private EnPassant backupEnpassant = null;

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

		String time = System.currentTimeMillis() + "6";

		time += (Math.random() * 999);

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

		attacked = true;

		if (attackingColor == Player.RED) {

			blackString = "piece_on_black_red";
			whiteString = "piece_on_white_red";

		} else if (attackingColor == Player.GREEN) {

			blackString = "piece_on_black_green";
			whiteString = "piece_on_white_green";

		} else if (attackingColor == Player.YELLOW) {

			blackString = "piece_on_black_yellow";
			whiteString = "piece_on_white_yellow";

		} else if (attackingColor == Player.BLUE) {

			blackString = "piece_on_black_blue";
			whiteString = "piece_on_white_blue";
		}
	}

	public void stopAttack() {

		attacked = false;

		blackString = "piece_on_black";
		whiteString = "piece_on_white";
	}

	public void click(boolean noNext) {

		System.out.println(getX() + ", " + getY() + ": " + getCssName());

		selectedPiece = board.getCurrentPlayer().getSelectedPiece();

		oldPlace = board.getCurrentPlayer().getSelectedPiece().getPlace();
		
		if (!getBoard().isCheckingCheck() && roccade != null) {

			Roccade aboutToPerform = roccade;

			roccade = null;

			aboutToPerform.perform(board, noNext, this);

			return;
		}

		/*
		 * if (!getBoard().isCheckingCheck() && enPassant != null && enPassantActivated)
		 * {
		 * 
		 * EnPassant aboutToPerform = enPassant;
		 * 
		 * enPassant = null;
		 * 
		 * aboutToPerform.perform(board, noNext, this);
		 * 
		 * return; }
		 */

		if (piece != null) {

			takenPiece = piece;

			board.getPlayerz()[takenPiece.getColor()].getPiecez().remove(takenPiece);
		}

		board.getCurrentPlayer().getSelectedPiece().moveTo(this);

		if (!noNext) {

			commit();

			board.next();
		}
	}

	public void remove(Piece oldPiece) {

		if (oldPiece != piece) {

			if (piece != null) {

				throw new RuntimeException("this piece wasn't here :-( -> " + oldPiece.getPlace().getX() + ", "
						+ oldPiece.getPlace().getY() + " == " + x + ", " + y + " != " + piece.getPlace().getX() + ", "
						+ piece.getPlace().getY());

			} else {

				throw new RuntimeException("this piece wasn't here :-( -> " + oldPiece.getPlace().getX() + ", "
						+ oldPiece.getPlace().getY() + " == " + x + ", " + y);
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

	public void rollBack() {

		if (takenPiece != null) {

			board.getPlayerz()[takenPiece.getColor()].getPiecez().add(takenPiece);
		}

		selectedPiece.rollBackMoveTo(oldPlace);

		piece = takenPiece;

		commit();
	}

	public void commit() {

		takenPiece = null;
		oldPlace = null;
		selectedPiece = null;
	}

	public void attachRocade(Roccade roccade) {

		this.roccade = roccade;
	}

	public void removeRocade() {

		roccade = null;
	}

	/*
	 * public void removeEnpassant() {
	 * 
	 * enPassant = null; }
	 */

	public String getMd5WithBoard() {

		return md5 + "/" + board.getHash();
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
