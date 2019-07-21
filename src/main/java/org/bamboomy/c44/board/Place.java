package org.bamboomy.c44.board;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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

		if (attackingColor == Player.RED) {

			blackString = "piece_on_black_red";
			whiteString = "piece_on_white_red";
		}
	}

	public void stopAttack() {

		blackString = "piece_on_black";
		whiteString = "piece_on_white";
	}
}
