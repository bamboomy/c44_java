package org.bamboomy.c44.board;

public class Dubious extends Player {

	private Player[] others = new Player[3];

	private int turn = 0;

	public Dubious(int color, Board board, boolean isRobot, Player[] others) {

		this(color, board, isRobot);

		this.others = others;
	}

	private Dubious(int color, Board board, boolean isRobot) {
		super(color, board, isRobot);
	}

	public String getCurrent() {

		return Player.getColorNamez()[others[turn].getColor()];
	}

	public void next() {

		turn++;

		turn %= 3;
	}
}
