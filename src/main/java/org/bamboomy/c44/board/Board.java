package org.bamboomy.c44.board;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

public class Board {

	@Getter
	private Place[][] placez = new Place[12][12];

	@Getter
	private Place[][] redPlacez = new Place[12][12];

	@Getter
	private Place[][] yellozPlacez = new Place[12][12];

	@Getter
	private Place[][] bluePlacez = new Place[12][12];

	@Getter
	private Player[] playerz = new Player[4];

	@Getter
	private Player currentPlayer;

	@Getter
	@Setter
	private String redName, greenName, blueName, yellowName, gameName, playerHash, viewColor;

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

	@Getter
	private String hash;

	private ArrayList<Move> performedMoves;

	public Board(String hash) {

		System.out.println("board created...");

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

		playerz[0] = new Player(0, this, true);

		playerz[1] = new Player(1, this, true);

		playerz[2] = new Player(2, this, false);

		playerz[3] = new Player(3, this, true);

		currentPlayer = playerz[turn];

		this.hash = hash;

		initRedPlaces();

		initYellowPlaces();

		initBluePlaces();

		performedMoves = new ArrayList<>();
	}

	private void initRedPlaces() {

		for (int i = 0; i < 12; i++) {

			for (int j = 0; j < 12; j++) {

				redPlacez[i][j] = placez[11 - i][11 - j];
			}
		}
	}

	private void initYellowPlaces() {

		for (int i = 0; i < 12; i++) {

			for (int j = 0; j < 12; j++) {

				yellozPlacez[i][j] = placez[11 - j][i];
			}
		}
	}

	private void initBluePlaces() {

		for (int i = 0; i < 12; i++) {

			for (int j = 0; j < 12; j++) {

				bluePlacez[i][j] = placez[j][11 - i];
			}
		}
	}

	public void click(String md5) {

		System.out.println(md5);

		if (!currentPlayer.click(md5)) {

			System.out.println("not piece");

			for (Place[] row : placez) {

				for (Place place : row) {

					if (place != null && place.getMd5() != null && place.getMd5().equalsIgnoreCase(md5)) {

						place.click(performedMoves);
					}
				}
			}
		}
	}

	public void next() {

		playerIsMoving = false;

		turn = (turn + 1) % 4;

		for (Integer i : deadPlayers) {

			System.out.print("dead:" + i);
		}

		if (deadPlayers.size() >= 3) {

			System.out.println("Game ended...");

			return;
		}

		while (deadPlayers.contains(turn)) {

			turn = (turn + 1) % 4;
		}

		currentPlayer = playerz[turn];

		// currentPlayer.removeEnPassant();

		System.out.println("color: " + currentPlayer.getColor());

		if (currentPlayer.isRobot()) {

			if (currentPlayer.checkCheck()) {

				if (currentPlayer.canPrevent()) {

					currentPlayer.prevent(performedMoves);

				} else {

					currentPlayer.kamikaze(performedMoves);
				}

			} else {

				if (currentPlayer.canTakeKing()) {

					currentPlayer.takeAKing(performedMoves);

				} else {

					currentPlayer.generateRandomMove(performedMoves);
				}
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

	public 	Place[][] getRotatedPlacez() {

		if (viewColor.equalsIgnoreCase("red")) {

			return getRedPlacez();

		} else if (viewColor.equalsIgnoreCase("yellow")) {

			return getYellozPlacez();

		} else if (viewColor.equalsIgnoreCase("blue")) {

			return getBluePlacez();
		}

		return placez;
	}

	public void setAttackedPlaces(int color) {

		reset();

		for (Player player : playerz) {

			if (player.getColor() != color) {

				player.setAttackedPlaces(false);
			}
		}
	}

	private void reset() {

		for (Place[] row : placez) {

			for (Place place : row) {

				if (place != null) {

					place.setAttacked(false);
				}
			}
		}
	}
}
