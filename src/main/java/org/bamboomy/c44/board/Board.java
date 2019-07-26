package org.bamboomy.c44.board;

import java.util.ArrayList;

import org.eclipse.jdt.internal.compiler.ast.ReturnStatement;

import lombok.Getter;
import lombok.Setter;

public class Board {

	@Getter
	private Place[][] placez = new Place[12][12];

	@Getter
	private Player[] playerz = new Player[4];

	@Getter
	private Player currentPlayer;

	private int turn = 2;

	private ArrayList<Integer> deadPlayers = new ArrayList<>();

	@Getter
	@Setter
	private boolean playerIsMoving = false;

	@Getter
	@Setter
	private boolean wouldBeCheck = false;

	private int max = 100;

	private int counter = 0;

	public Board() {

		int color = 0;

		for (int i = 0; i < 12; i++) {

			placez[i] = new Place[12];

			if (i < 2 || i > 9) {

				for (int j = 2; j < 10; j++) {

					placez[i][j] = new Place(color, this, i, j);

					color = 1 - color;
				}

				color = 1 - color;

			} else {

				for (int j = 0; j < 12; j++) {

					placez[i][j] = new Place(color, this, i, j);

					color = 1 - color;
				}

				color = 1 - color;
			}
		}

		playerz[0] = new Player(0, this, false);

		playerz[1] = new Player(1, this, false);

		playerz[2] = new Player(2, this, false);

		playerz[3] = new Player(3, this, false);

		currentPlayer = playerz[turn];
	}

	public void click(String md5) {

		boolean piece = currentPlayer.click(md5);

		if (!piece) {

			for (Place[] row : placez) {

				for (Place place : row) {

					if (place != null && place.getMd5() != null && place.getMd5().equalsIgnoreCase(md5)) {

						place.click(false);

						place.commit();
					}
				}
			}
		}
	}

	public void next() {

		playerIsMoving = false;

		turn = (turn + 1) % 4;

		if (deadPlayers.size() >= 3) {

			System.out.println("Game ended...");

			return;
		}

		while (deadPlayers.contains(turn)) {

			turn = (turn + 1) % 4;
		}

		currentPlayer = playerz[turn];
		
		currentPlayer.removeEnPassant();

		System.out.println("color: " + currentPlayer.getColor());

		if (currentPlayer.isRobot()) {

			if (currentPlayer.checkCheck()) {

				if (currentPlayer.canPrevent()) {

					currentPlayer.prevent();

				} else {

					currentPlayer.generateRandomMove();
				}

			} else {

				currentPlayer.generateRandomMove();
			}
		}
	}

	public void removeMe() {

		deadPlayers.add(turn);

		next();
	}

	public void generateMoves() {

		counter = 0;

		next();
	}

	public boolean isCheckingCheck() {

		for (Player player : playerz) {

			if (player.isChekcingCheck()) {

				return true;
			}
		}

		return false;
	}
}
