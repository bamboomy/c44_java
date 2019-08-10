package org.bamboomy.c44;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import org.bamboomy.c44.board.Board;

public class BoardController {

	private static BoardController single_instance = null;

	static final Map<String, Board> GAMEZ;

	private static final int MAX_ENTRIES = 20000;

	static {

		Map<String, Board> innerCache = new LinkedHashMap<String, Board>(MAX_ENTRIES + 1, .75F, true) {

			private static final long serialVersionUID = 1L;

			// This method is called just after a new entry has been added
			public boolean removeEldestEntry(Map.Entry eldest) {

				System.out.println("cache removal?");

				boolean remove = size() > MAX_ENTRIES;

				return remove;
			}
		};

		GAMEZ = (Map<String, Board>) Collections.synchronizedMap(innerCache);
	}

	private BoardController() {
	}

	public static synchronized BoardController getInstance() {

		if (single_instance == null) {
			single_instance = new BoardController();
		}

		return single_instance;
	}

	Board getBoard(String hash) {

		return GAMEZ.get(hash);
	}

	public void createBoard(String md5, String playerHash, String player2Hash, String player3Hash, String player4Hash) {

		GAMEZ.put(md5, new Board(md5, playerHash, player2Hash, player3Hash, player4Hash));
	}
}
