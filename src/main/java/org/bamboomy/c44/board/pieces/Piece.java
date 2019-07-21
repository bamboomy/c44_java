package org.bamboomy.c44.board.pieces;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

import org.bamboomy.c44.board.Place;

import lombok.Getter;

public abstract class Piece {

	private Place place;

	protected int color;

	@Getter
	private String md5;

	public Piece(Place place, int color) {

		this.place = place;

		place.setPiece(this);

		this.color = color;

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

	public abstract String getPieceName();

}
