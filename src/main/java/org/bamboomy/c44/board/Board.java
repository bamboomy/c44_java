package org.bamboomy.c44.board;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

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
	private String redName, greenName, blueName, yellowName, gameName, playerHash;

	private int turn = 2;

	/*
	 * @Getter private String[] timeArray = new String[4];
	 */

	private int[] timeArrayInt = new int[4];

	@Getter
	private int[] timeOutzIntz = new int[4];

	@Getter
	private String[] timeOutzArray = new String[4];

	private ArrayList<Integer> deadPlayers = new ArrayList<>();

	private ArrayList<Integer> deadNotifiedPlayers = new ArrayList<>();

	private ArrayList<Integer> deadGUINotifiedPlayers = new ArrayList<>();

	@Getter
	@Setter
	private boolean playerIsMoving = false;

	@Getter
	@Setter
	private boolean wouldBeCheck = false;

	@Getter
	private String hash;

	private ArrayList<Move> performedMoves;

	private long reference;

	@Getter
	private boolean playSound = false;

	@Getter
	private boolean[] timeOut = new boolean[4];

	@Getter
	private String resignText;

	private boolean[] resignRead = new boolean[4];

	@Getter
	private String[] nameArray = new String[4];

	@Getter
	private boolean finished;

	private int delta;

	@Getter
	private ReentrantLock lock = new ReentrantLock();

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

		playerz[0] = new Player(0, this, false);

		playerz[1] = new Player(1, this, false);

		playerz[2] = new Player(2, this, false);

		playerz[3] = new Player(3, this, false);

		currentPlayer = playerz[turn];

		this.hash = hash;

		initRedPlaces();

		initYellowPlaces();

		initBluePlaces();

		performedMoves = new ArrayList<>();

		resetTimer();

		// timeArray[2] = getCurrentTimeOfCurrentPlayer();

		for (int i = 0; i < 4; i++) {

			timeOutzIntz[i] = 2;

			resignRead[i] = true;
		}
	}

	private int getCurrentTimeOfCurrentPlayer() {

		long now = System.currentTimeMillis();

		// long millisUntilFinished = (reference + (2 * 60000) - now);

		long millisUntilFinished = delta + (reference + (3 * 60 * 1000) - now);

		playSound = false;

		if (((millisUntilFinished % 60000 / 1000) < 10 && ((millisUntilFinished / 60000) == 0))) {

			playSound = true;
		}

		if (millisUntilFinished <= 0) {

			timeOutzIntz[turn]--;

			timeOutzArray[turn] = timeOutzIntz[turn] + " timeouts left...";

			if (timeOutzIntz[turn] <= 0) {

				resignText = buildResignText();

				System.out.println(resignText);

				removeMe();

				checkFinish();

				for (int i = 0; i < 4; i++) {

					resignRead[i] = false;
				}

				return 0;
			}

			timeOut[turn] = true;

			forceMove();

			return 0;
		}

		return (int) millisUntilFinished;
	}

	private void checkFinish() {

		if (finished) {

			resignText = Player.getColorNamez()[playerz[turn].getColor()] + " has won the game :)";
		}
	}

	private String buildResignText() {

		String result = "";

		result += Player.getColorNamez()[playerz[turn].getColor()] + "(";

		result += nameArray[playerz[turn].getColor()];

		result += ") has resigned,\\n";

		if (deadPlayers.size() < 3) {

			result += (3 - deadPlayers.size()) + " players are left...";
		}

		return result;
	}

	private void forceMove() {

		playerz[turn].playRandomMove(performedMoves);
	}

	private void resetTimer() {

		reference = System.currentTimeMillis();
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

	public void click(String md5, String color) {

		System.out.println(md5);

		if ((currentPlayer.getColor() == Player.RED && !color.equalsIgnoreCase("red"))
				|| (currentPlayer.getColor() == Player.GREEN && !color.equalsIgnoreCase("green"))
				|| (currentPlayer.getColor() == Player.BLUE && !color.equalsIgnoreCase("blue"))
				|| (currentPlayer.getColor() == Player.YELLOW && !color.equalsIgnoreCase("yellow"))) {
			return;
		}

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

		timeArrayInt[turn] = Math.max((int) (delta + (reference + (3 * 60 * 1000) - System.currentTimeMillis())), 0);

		turn = (turn + 1) % 4;

		delta = timeArrayInt[turn];

		resetTimer();

		for (Integer i : deadPlayers) {

			System.out.print("dead:" + i);
		}

		countDead();

		if (finished) {

			return;
		}

		while (deadPlayers.contains(turn)) {

			turn = (turn + 1) % 4;
		}

		currentPlayer = playerz[turn];

		// currentPlayer.removeEnPassant();

		System.out.println("color: " + currentPlayer.getColor());

		if (currentPlayer.isRobot()) {

			currentPlayer.playRandomMove(performedMoves);
		}
	}

	private void countDead() {

		if (deadPlayers.size() >= 3) {

			System.out.println("Game ended...");

			finished = true;
		}
	}

	public void removeMe() {

		deadPlayers.add(turn);

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

	public Place[][] getRotatedPlacez(String color) {

		if (color.equalsIgnoreCase("red")) {

			return getRedPlacez();

		} else if (color.equalsIgnoreCase("yellow")) {

			return getYellozPlacez();

		} else if (color.equalsIgnoreCase("blue")) {

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

	public void updateTime() {

		timeArrayInt[turn] = getCurrentTimeOfCurrentPlayer();
	}

	public boolean isRenderingCurrentPlayer(String color) {

		if (playerz[turn].getColor() == Player.RED && color.equalsIgnoreCase("red")) {

			return true;

		} else if (playerz[turn].getColor() == Player.GREEN && color.equalsIgnoreCase("green")) {

			return true;

		} else if (playerz[turn].getColor() == Player.BLUE && color.equalsIgnoreCase("blue")) {

			return true;

		} else if (playerz[turn].getColor() == Player.YELLOW && color.equalsIgnoreCase("yellow")) {

			return true;
		}

		return false;
	}

	public boolean isTimeOut(String color) {

		boolean result = false;

		if (color.equalsIgnoreCase("red")) {

			result = timeOut[Player.RED];

			timeOut[Player.RED] = false;

		} else if (color.equalsIgnoreCase("green")) {

			result = timeOut[Player.GREEN];

			timeOut[Player.GREEN] = false;

		} else if (color.equalsIgnoreCase("blue")) {

			result = timeOut[Player.BLUE];

			timeOut[Player.BLUE] = false;

		} else if (color.equalsIgnoreCase("yellow")) {

			result = timeOut[Player.YELLOW];

			timeOut[Player.YELLOW] = false;
		}

		return result;
	}

	public boolean readResign(String color) {

		System.out.println("readin...");

		boolean result = true;

		if (color.equalsIgnoreCase("red")) {

			result = resignRead[Player.RED];

			resignRead[Player.RED] = true;

		} else if (color.equalsIgnoreCase("green")) {

			result = resignRead[Player.GREEN];

			resignRead[Player.GREEN] = true;

		} else if (color.equalsIgnoreCase("blue")) {

			result = resignRead[Player.BLUE];

			resignRead[Player.BLUE] = true;

		} else if (color.equalsIgnoreCase("yellow")) {

			result = resignRead[Player.YELLOW];

			resignRead[Player.YELLOW] = true;
		}

		System.out.println(result);

		return result;
	}

	public void syncNames() {

		nameArray[0] = redName;
		nameArray[1] = yellowName;
		nameArray[2] = greenName;
		nameArray[3] = blueName;
	}

	public boolean isDead() {

		return deadPlayers.contains(turn);
	}

	public boolean isNewDead() {

		boolean result = false;

		if (deadPlayers.contains(turn) && !deadNotifiedPlayers.contains(turn)) {

			deadNotifiedPlayers.add(turn);

			result = true;
		}

		return result;
	}

	public boolean isNewGUIDead() {

		boolean result = false;

		if (deadPlayers.contains(turn) && !deadGUINotifiedPlayers.contains(turn)) {

			deadGUINotifiedPlayers.add(turn);

			result = true;
		}

		return result;
	}

	public String getTimeArray(int index) {

		String result = String.format("%02d:%02d", timeArrayInt[index] / 60000, timeArrayInt[index] % 60000 / 1000);

		return result;
	}

	public void resign(String color) {

		int colorInt = 0;

		String[] colorNames = Player.getColorNamez();

		for (int i = 0; i < 4; i++) {

			if (colorNames[i].equalsIgnoreCase(color)) {

				colorInt = i;

				break;
			}
		}

		deadPlayers.add(colorInt);

		System.out.println(colorInt + " resigned...");

		countDead();

		checkFinish();

		for (int i = 0; i < 4; i++) {

			resignRead[i] = false;
		}
	}
}
