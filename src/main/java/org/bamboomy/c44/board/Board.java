package org.bamboomy.c44.board;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

import org.bamboomy.c44.ColorsTaken;

import lombok.Getter;
import lombok.Setter;

public class Board {

	public static String staticProfile = "";

	public static String staticPiecePath = "/peace/";

	public static String staticTomcatPath = "tomcat";

	@Getter
	private String piecePath = staticPiecePath;

	@Getter
	private String tomcatPath = staticTomcatPath;

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
	private long[] timestamps = new long[4];

	@Getter
	@Setter
	private String redName, greenName, blueName, yellowName, gameName, playerHash;

	private boolean[] beginTurn = new boolean[4];

	private int turn = 2;

	private int[] timeArrayInt = new int[4];

	@Getter
	private int[] timeOutzIntz = new int[4];

	@Getter
	private String[] timeOutzArray = new String[4];

	private ArrayList<Integer> deadPlayers = new ArrayList<>();

	private ArrayList<Integer> recordedDeadPlayers = new ArrayList<>();

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

	@Getter
	private ReentrantLock lock = new ReentrantLock();

	private boolean botSet = false;

	@Getter
	private boolean dubiousSet = false;

	private String[] places = new String[4];

	private String[] playerPlaces = new String[4];

	private int placesCounter = 0;

	private String dubiousColor = null;

	@Getter
	private Dubious dubious = null;

	private int dubiousTurn = -1;

	@Getter
	private String profile;

	@Getter
	private String statusMessages = "";

	private boolean usersSet = false;

	private ColorsTaken[] colorsTaken = new ColorsTaken[4];

	private static String[] colorStrings = { "red", "brown", "green", "blue" };

	public Board(String hash) {

		profile = staticProfile;

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

			timeOutzIntz[i] = 3;

			resignRead[i] = true;

			beginTurn[i] = false;

			timestamps[i] = -1;
		}

		places[0] = "4th";
		places[1] = "3rd";
		places[2] = "2nd";
		places[3] = "1st";

		beginTurn[turn] = true;
	}

	private int getCurrentTimeOfCurrentPlayer() {

		long millisUntilFinished = (reference + (2 * 60 * 1000) - System.currentTimeMillis());

		if (millisUntilFinished <= 0) {

			timeOutzIntz[turn]--;

			timeOutzArray[turn] = timeOutzIntz[turn] + " timeouts left...";

			if (timeOutzIntz[turn] <= 0) {

				removeMe();

				resignText = buildResignText(turn);

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

	private String buildResignText(int player) {

		String result = "";

		result += Player.getColorNamez()[playerz[player].getColor()] + " has resigned,\\n";

		// TODO: refactor so the name (and not 'You') is used when building resign text
		// -> 1 possible tactic: build resigntext upon read...

		// result += nameArray[playerz[player].getColor()];
		// result += ")

		if (deadPlayers.size() < 3) {

			result += (4 - deadPlayers.size()) + " players are left...";
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

		timeArrayInt[turn] = 0;

		if (playerz[turn] == dubious) {

			dubious.storeTimeOut(timeOutzIntz[turn], timeOutzArray[turn], timeArrayInt[turn]);

			dubious.next();

			timeOutzIntz[turn] = dubious.getCurrentTimeOutzIntz();

			timeOutzArray[turn] = dubious.getCurrentTimeOutzArray();

			timeArrayInt[turn] = dubious.getCurrentTimeArrayInt();
		}

		turn = (turn + 1) % 4;

		beginTurn[turn] = true;

		if (playerz[turn] == dubious) {

			beginTurn[dubious.getCurrentNumber()] = true;
		}

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

		checkState();
	}

	private void countDead() {

		if (deadPlayers.size() >= 3) {

			System.out.println("Game ended...");

			finished = true;
		}
	}

	private void checkState() {

		if (getCurrentPlayer().checkCheck()) {

			statusMessages += "<span style='color:" + colorStrings[turn] + ";'>";

			statusMessages += colorsTaken[turn].getName()
					+ "</span><span style='color: red;'> is in check!</span><br/>";

			if (getCurrentPlayer().canPrevent()) {

				statusMessages += "<span style='color: green;'>...but can prevent... (for now)</span><br/>";

			} else {

				statusMessages += "<span style='color: red;'>and can't prevent :-(</span><br/>";
			}
		}
	}

	public void removeMe() {

		if (currentPlayer == dubious && dubious.removeCurrent()) {

			remove(turn);

		} else {

			remove(turn);
		}
	}

	public void removeMeWithoutDubiousCheck() {

		remove(turn);
	}

	public void remove(int number) {

		deadPlayers.add(number);

		playerPlaces[number] = places[placesCounter++];
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

		if (deadPlayers.contains(turn) && !recordedDeadPlayers.contains(turn)) {

			recordedDeadPlayers.add(turn);

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

		if (turn == dubiousTurn && dubious.getCurrent().equalsIgnoreCase(color)) {

			if (dubious.removeCurrent()) {

				remove(colorInt);
			}

			next();

		} else {

			remove(colorInt);
		}

		System.out.println(colorInt + " resigned...");

		resignText = buildResignText(colorInt);

		countDead();

		checkFinish();

		for (int i = 0; i < 4; i++) {

			resignRead[i] = false;
		}
	}

	public void setRandom(int color) {

		if (botSet) {

			return;
		}

		playerz[color] = new Player(color, this, true);

		if (playerz[turn].isRobot()) {

			playerz[turn].playRandomMove(performedMoves);
		}

		botSet = true;
	}

	public String getPlayerString(int number) {

		String result;

		if (playerPlaces[number] != null) {

			result = playerPlaces[number];

		} else {

			result = getTimeArray(number);

			if (timeOutzArray[number] != null) {

				result += " <span class='red'>" + timeOutzArray[number] + "</span>";
			}
		}

		return result;
	}

	public void setDubious(int color) {

		if (dubiousSet) {

			return;
		}

		Player[] others = new Player[3];

		int counter = 2;

		int index = 0;

		for (int i = 0; i < 4; i++) {

			if (color != counter) {

				others[index++] = playerz[counter];
			}

			counter++;

			counter %= 4;
		}

		playerz[color] = new Dubious(color, this, false, others);

		dubiousColor = Player.getColorNamez()[color];

		dubious = (Dubious) playerz[color];

		dubiousTurn = color;

		dubiousSet = true;
	}

	public ColorsTaken getUserColor(ColorsTaken user, Iterable<ColorsTaken> iterable) {

		if (playerz[turn] == dubious) {

			ColorsTaken dubiousTaken = null;

			for (ColorsTaken color : iterable) {

				if (color.getColor().equalsIgnoreCase(dubiousColor)) {

					dubiousTaken = color;
				}
			}

			if (dubious.getCurrent().equalsIgnoreCase(user.getColor())) {

				return dubiousTaken;
			}
		}

		return user;
	}

	public boolean readSound(String color) {

		System.out.println("readin...");

		boolean result = false;

		if (color.equalsIgnoreCase("red")) {

			result = beginTurn[Player.RED];

			beginTurn[Player.RED] = false;

		} else if (color.equalsIgnoreCase("green")) {

			result = beginTurn[Player.GREEN];

			beginTurn[Player.GREEN] = false;

		} else if (color.equalsIgnoreCase("blue")) {

			result = beginTurn[Player.BLUE];

			beginTurn[Player.BLUE] = false;

		} else if (color.equalsIgnoreCase("yellow")) {

			result = beginTurn[Player.YELLOW];

			beginTurn[Player.YELLOW] = false;
		}

		System.out.println(result);

		return result;
	}

	public void setColorsTaken(Iterable<ColorsTaken> userIterable) {

		if (usersSet) {

			return;
		}

		for (ColorsTaken userColor : userIterable) {

			if (userColor.getColor().equalsIgnoreCase("red")) {

				colorsTaken[0] = userColor;

			} else if (userColor.getColor().equalsIgnoreCase("yellow")) {

				colorsTaken[1] = userColor;

			} else if (userColor.getColor().equalsIgnoreCase("green")) {

				colorsTaken[2] = userColor;

			} else if (userColor.getColor().equalsIgnoreCase("blue")) {

				colorsTaken[3] = userColor;
			}
		}

		usersSet = true;
	}
}
