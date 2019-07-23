package org.bamboomy.c44.board.pieces;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.xml.bind.DatatypeConverter;

import org.bamboomy.c44.board.Place;

import lombok.Getter;

public abstract class Piece {

	@Getter
	protected Place place;

	@Getter
	protected int color;

	@Getter
	private String md5;

	protected boolean selected = false;

	protected ArrayList<Place> attackablePlaces = new ArrayList<Place>();
	
	protected ArrayList<Place> preventPlacez = new ArrayList<>();

	public Piece(Place place, int color) {

		this.place = place;

		place.setPiece(this);

		this.color = color;

		String time = System.currentTimeMillis() + "6";

		time += (Math.random() * 777);

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

	public void click() {
		
		System.out.println(getPieceName());

		if (!selected) {

			place.getBoard().getPlayerz()[color].setSelected(this);

			setAttackablePlaces();

			for (Place place : attackablePlaces) {

				place.attack(color);
			}

			selected = true;

		} else {

			unselect();
		}
	}

	protected abstract void setAttackablePlaces();

	public void unselect() {

		for (Place place : attackablePlaces) {

			place.stopAttack();
		}

		selected = false;
	}

	public boolean moveTo(Place otherPlace) {

		unselect();

		place.remove(this);

		otherPlace.setPiece(this);

		place = otherPlace;
		
		return false;
	}

	public boolean canMove() {

		setAttackablePlaces();

		return attackablePlaces.size() > 0;
	}

	public void doRandomMove() {

		click();

		attackablePlaces.get((int) (Math.random() * attackablePlaces.size())).click(false);
	}

	public boolean checkCheck(Piece king) {

		setAttackablePlaces();

		for (Place place : attackablePlaces) {

			if (place.equals(king.getPlace())) {

				return true;
			}
		}

		return false;
	}

	public boolean canPrevent() {

		setAttackablePlaces();
		
		preventPlacez = new ArrayList<>();
		
		boolean result = false;

		for (Place place : attackablePlaces) {
			
			click();

			place.click(true);
			
			if(!place.getBoard().getCurrentPlayer().checkCheck()) {
				
				preventPlacez.add(place);
				
				result = true;
			}
			
			place.rollBack();
		}

		return result;
	}

	public void rollBackMoveTo(Place oldPlace, boolean unused) {

		place.remove(this);
		
		oldPlace.setPiece(this);
		
		place = oldPlace;	
		
		unselect();
	}
}
