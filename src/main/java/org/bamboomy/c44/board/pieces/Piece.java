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

	public static final String BISSHOP = "FCE41CFA81F3035C5A36C2DB0F04FF55";
	public static final String HORSE = "955B55F2455675B715F50F14821C250D";
	public static final String KING = "49357FE6D3EC4D9658996CDF440223E0";
	public static final String PAWN = "50DE8CAA507BA8E8953CEEEC9570F88D";
	public static final String QUEEN = "31C81D236FFFD16904FDAC93C9E68512";
	public static final String TOWER = "6797E8118F1CF36B220C8AB05B8A6ADD";

	protected boolean selected = false;

	protected ArrayList<Place> attackablePlaces = new ArrayList<Place>();

	protected ArrayList<Place> preventPlacez = new ArrayList<>();

	public Piece(Place place, int color) {

		this.place = place;

		place.setPiece(this);

		this.color = color;

		recalculateHash();
	}

	private void recalculateHash() {

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

	public abstract String getPieceIdentifier();

	public void click() {

		System.out.println(getPieceName());

		recalculateHash();

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

	public boolean doRandomMove() {

		click();

		ArrayList<Integer> moves = new ArrayList<>();

		int index = (int) (Math.random() * attackablePlaces.size());

		attackablePlaces.get(index).click(true);

		while (place.getBoard().getCurrentPlayer().checkCheck() && moves.size() < attackablePlaces.size()) {

			attackablePlaces.get(index).rollBack();

			moves.add(index);

			index = (int) (Math.random() * attackablePlaces.size());

			while (moves.contains(index) && moves.size() < attackablePlaces.size()) {

				index = (int) (Math.random() * attackablePlaces.size());
			}

			click();

			attackablePlaces.get(index).click(true);
		}

		if (place.getBoard().getCurrentPlayer().checkCheck()) {

			attackablePlaces.get(index).rollBack();

		} else {

			attackablePlaces.get(index).commit();
		}

		return moves.size() < attackablePlaces.size();
	}

	public boolean checkCheck(Piece king) {

		recalculateHash();

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

			if (!place.getBoard().getCurrentPlayer().checkCheck()) {

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

	public void prevent() {

		click();

		preventPlacez.get((int) Math.random() * preventPlacez.size()).click(false);
	}

	public boolean checkCheck() {

		ArrayList<Place> filtered = new ArrayList<>();

		ArrayList<Place> unsetList = new ArrayList<>();

		for (Place place : attackablePlaces) {

			place.click(true);

			if (!place.getBoard().getCurrentPlayer().checkCheck()) {

				filtered.add(place);

			} else {

				unsetList.add(place);
			}

			Piece selectedPiece = place.getPiece();

			place.rollBack();

			selectedPiece.click();
		}

		for (Place place : unsetList) {

			place.stopAttack();
		}

		attackablePlaces = filtered;

		return filtered.size() == 0;
	}
}
