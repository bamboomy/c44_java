package org.bamboomy.c44.board.pieces;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.xml.bind.DatatypeConverter;

import org.bamboomy.c44.board.Move;
import org.bamboomy.c44.board.Place;

import lombok.Getter;
import lombok.Setter;

public abstract class Piece {

	@Getter
	protected Place currentPlace;

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

	@Getter
	protected ArrayList<Move> attackableMoves = new ArrayList<>();

	protected ArrayList<Place> preventPlacezOfPiece = new ArrayList<>();

	@Setter
	@Getter
	private boolean rocaded = false;

	protected boolean neverMoved = true, rememberNeverMoved = true;

	/*
	 * @Setter protected EnPassant enPassant;
	 */

	private ArrayList<Place> kingPlacez;

	public Piece(Place place, int color) {

		this.currentPlace = place;

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

			currentPlace.getBoard().getCurrentPlayer().setSelected(this);

			setAttackablePlaces(true);

			for (Move move : attackableMoves) {

				move.getTo().attack(color);
			}

			selected = true;

		} else {

			unselect();
		}
	}

	public abstract void setAttackablePlaces(boolean checkRocade);

	public void unselect() {

		for (Move move : attackableMoves) {

			move.getTo().stopAttack();
		}

		selected = false;
	}

	/*
	 * public void moveTo(Place otherPlace) {
	 * 
	 * /* if (otherPlace.getEnPassant() != null) {
	 * 
	 * otherPlace.backupEnpassant(); }
	 * 
	 * unselect();
	 * 
	 * currentPlace.remove(this);
	 * 
	 * otherPlace.setPiece(this);
	 * 
	 * currentPlace = otherPlace;
	 * 
	 * rememberNeverMoved = neverMoved;
	 * 
	 * neverMoved = false; }
	 */

	/*
	 * public boolean uncheckedMoveTo(Place otherPlace) {
	 * 
	 * unselect();
	 * 
	 * otherPlace.setPiece(this);
	 * 
	 * currentPlace = otherPlace;
	 * 
	 * return false; }
	 */

	public boolean canMove() {

		setAttackablePlaces(true);

		/*
		 * if (enPassant != null) {
		 * 
		 * enPassant.destroy(); }
		 */

		return attackableMoves.size() > 0;
	}

	/*
	 * public boolean doRandomMove() {
	 * 
	 * unselect();
	 * 
	 * click();
	 * 
	 * ArrayList<Integer> moves = new ArrayList<>();
	 * 
	 * int index = (int) (Math.random() * attackableMoves.size());
	 * 
	 * attackableMoves.get(index).click(true);
	 * 
	 * while (currentPlace.getBoard().getCurrentPlayer().checkCheck() &&
	 * moves.size() < attackableMoves.size()) {
	 * 
	 * attackableMoves.get(index).rollBack();
	 * 
	 * moves.add(index);
	 * 
	 * index = (int) (Math.random() * attackableMoves.size());
	 * 
	 * while (moves.contains(index) && moves.size() < attackableMoves.size()) {
	 * 
	 * index = (int) (Math.random() * attackableMoves.size()); }
	 * 
	 * unselect();
	 * 
	 * click();
	 * 
	 * attackableMoves.get(index).click(true); }
	 * 
	 * if (currentPlace.getBoard().getCurrentPlayer().checkCheck()) {
	 * 
	 * attackableMoves.get(index).rollBack();
	 * 
	 * } else {
	 * 
	 * attackableMoves.get(index).commit(); }
	 * 
	 * return moves.size() < attackableMoves.size(); }
	 */

	public boolean checkCheck(Piece king) {

		recalculateHash();

		setAttackablePlaces(false);

		for (Move move : attackableMoves) {

			if (move.getTo().equals(king.getCurrentPlace())) {

				return true;
			}
		}

		return false;
	}

	public boolean canPrevent() {

		setAttackablePlaces(true);

		preventPlacezOfPiece = new ArrayList<>();

		boolean canPrevent = false;

		for (Move move : attackableMoves) {

			// TODO: untested code

			/*
			 * unselect();
			 * 
			 * click();
			 * 
			 * move.getTo().click(true);
			 */

			move.execute();

			if (!move.getTo().getBoard().getCurrentPlayer().checkCheck()) {

				preventPlacezOfPiece.add(move.getTo());

				canPrevent = true;
			}

			move.rollBack();
		}

		return canPrevent;
	}

	/*
	 * public void rollBackMoveTo(Place oldPlace) {
	 * 
	 * currentPlace.remove(this);
	 * 
	 * oldPlace.setPiece(this);
	 * 
	 * currentPlace = oldPlace;
	 * 
	 * unselect(); }
	 */

	public void prevent() {

		unselect();

		click();

		preventPlacezOfPiece.get((int) Math.random() * preventPlacezOfPiece.size()).click(false);
	}

	public boolean checkWouldBeCheck() {

		ArrayList<Move> filtered = new ArrayList<>();

		ArrayList<Move> unsetList = new ArrayList<>();

		for (Move move : attackableMoves) {

			if (move.isRocade()) {

				continue;
			}

			// place.click(true);

			move.execute();

			if (!move.getTo().getBoard().getCurrentPlayer().checkCheck()) {

				filtered.add(move);

			} else {

				unsetList.add(move);
			}

			move.rollBack();

			/*
			 * Piece selectedPiece = place.getPiece();
			 * 
			 * place.rollBack();
			 * 
			 * selectedPiece.unselect();
			 * 
			 * selectedPiece.click();
			 */
		}

		for (Move move : unsetList) {

			move.getTo().stopAttack();
		}

		attackableMoves = filtered;

		return filtered.size() == 0 && unsetList.size() != 0;
	}

	public String getMd5WithBoard() {

		return md5 + "/" + currentPlace.getBoard().getHash();
	}

	public boolean canTakeKing() {

		kingPlacez = new ArrayList<>();

		if (canMove()) {

			for (Place place : attackableMoves) {

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

		unselect();

		click();

		kingPlacez.get((int) (Math.random() * kingPlacez.size())).click(false);
	}

	public void kamikaze() {

		unselect();

		click();

		attackableMoves.get((int) (Math.random() * attackableMoves.size())).click(true);
	}
}
