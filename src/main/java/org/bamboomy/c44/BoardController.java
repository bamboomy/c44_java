package org.bamboomy.c44;

import java.util.Collections;
import java.util.Iterator;
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

	synchronized Board getBoard(String hash) {

		Board result = GAMEZ.get(hash);

		if (GAMEZ.get(hash) == null) {

			result = new Board(hash);

			GAMEZ.put(hash, result);
		}

		return result;
	}

	public Board getBoardByPlayerHash(String hash) {

		Iterator<Board> boardz = GAMEZ.values().iterator();

		while(boardz.hasNext()) {
			
			Board board = boardz.next();

			if (board.getColorsTaken(hash) != null) {

				return board;
			}
		}

		return null;
	}
}
