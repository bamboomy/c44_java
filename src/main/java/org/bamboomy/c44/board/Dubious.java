package org.bamboomy.c44.board;

import java.util.ArrayList;

public class Dubious extends Player {

	private Player[] others = new Player[3];

	private int turn = 0;

	private String[] timeOutzArray = new String[3];

	private int[] timeOutzIntz = new int[3];

	private int[] timeArrayInt = new int[3];

	private ArrayList<Integer> timeOutz = new ArrayList<Integer>();

	public Dubious(int color, Board board, boolean isRobot, Player[] others) {

		this(color, board, isRobot);

		this.others = others;
	}

	private Dubious(int color, Board board, boolean isRobot) {
		super(color, board, isRobot);
	}

	public String getCurrent() {

		return Color.getBySeq(turn).getName();
	}

	public int getCurrentNumber() {

		return others[turn].getColor().getSeq();
	}

	public String getCurrentName() {

		return others[turn].getColorsTaken().getName();
	}

	public void next() {

		turn++;

		turn %= 3;

		int counter = 0;

		while (timeOutz.contains(turn) && counter < 3) {

			turn++;

			turn %= 3;

			counter++;
		}
	}

	public void storeTimeOut(int timeOutzIntzValue, String timeOutzArrayValue, int timeArrayIntValue) {

		timeOutzIntz[turn] = timeOutzIntzValue;

		timeOutzArray[turn] = timeOutzArrayValue;

		timeArrayInt[turn] = timeArrayIntValue;
	}

	public int getCurrentTimeOutzIntz() {

		return timeOutzIntz[turn];
	}

	public String getCurrentTimeOutzArray() {

		return timeOutzArray[turn];
	}

	public int getCurrentTimeArrayInt() {

		return timeArrayInt[turn];
	}

	public boolean removeCurrent() {

		timeOutz.add(turn);

		return timeOutz.size() >= 3;
	}
}
