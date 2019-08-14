package org.bamboomy.c44.board.pieces;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.xml.bind.DatatypeConverter;

import org.bamboomy.c44.board.Place;

import lombok.Getter;
import lombok.Setter;

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

	protected ArrayList<Place> preventPlacezOfPiece = new ArrayList<>();

	@Setter
	@Getter
	private boolean rocaded = false;

	protected boolean neverMoved = true;

	@Setter
	protected EnPassant enPassant;

	private ArrayList<Place> kingPlacez;

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

			place.getBoard().getCurrentPlayer().setSelected(this);

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

		if (otherPlace.getEnPassant() != null) {

			otherPlace.backupEnpassant();
		}

		unselect();

		place.remove(this);

		otherPlace.setPiece(this);

		place = otherPlace;

		return false;
	}

	public boolean uncheckedMoveTo(Place otherPlace) {

		unselect();

		otherPlace.setPiece(this);

		place = otherPlace;

		return false;
	}

	public boolean canMove() {

		setAttackablePlaces();

		if (enPassant != null) {

			enPassant.destroy();
		}

		return attackablePlaces.size() > 0;
	}

	public boolean doRandomMove() {

		unselect();

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

			unselect();

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

		preventPlacezOfPiece = new ArrayList<>();

		boolean canPrevent = false;

		for (Place place : attackablePlaces) {

			unselect();

			click();

			place.click(true);

			if (!place.getBoard().getCurrentPlayer().checkCheck()) {

				preventPlacezOfPiece.add(place);

				canPrevent = true;
			}

			place.rollBack();
		}

		return canPrevent;
	}

	public void rollBackMoveTo(Place oldPlace, boolean unused) {

		place.remove(this);

		oldPlace.setPiece(this);

		place = oldPlace;

		unselect();
	}

	public void prevent() {

		unselect();

		click();

		preventPlacezOfPiece.get((int) Math.random() * preventPlacezOfPiece.size()).click(false);
	}

	public boolean checkWouldBeCheck() {

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

		return filtered.size() == 0 && unsetList.size() != 0;
	}

	public String getMd5WithBoard() {

		return md5 + "/" + place.getBoard().getHash();
	}

	public boolean canTakeKing() {

		kingPlacez = new ArrayList<>();

		if (canMove()) {

			for (Place place : attackablePlaces) {

				if (place.getPiece() != null && place.getPiece().getPieceIdentifier().equalsIgnoreCase(KING)) {

					kingPlacez.add(place);
				}
			}

			return kingPlacez.size() > 0;

		} else {

			return false;
		}
	}

	public void takeKing() {

		click();

		kingPlacez.get((int) (Math.random() * kingPlacez.size())).click(false);
	}

	public void kamikaze() {

		unselect();

		click();

		attackablePlaces.get((int) (Math.random() * attackablePlaces.size())).click(true);
	}
}
