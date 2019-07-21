package org.bamboomy.c44.board;

import lombok.Getter;

public class Board {

	@Getter
	private Place[][] placez = new Place[12][12];

	@Getter
	private Player[] playerz = new Player[4];

	@Getter
	private Player currentPlayer;

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

		playerz[0] = new Player(0, this);

		currentPlayer = playerz[0];
		
		playerz[1] = new Player(1, this);
		
		playerz[2] = new Player(2, this);
		
		playerz[3] = new Player(3, this);
	}

	public void click(String md5) {

		boolean piece = playerz[0].click(md5);

		if (!piece) {

			for (Place[] row : placez) {

				for (Place place : row) {

					if (place != null && place.getMd5() != null && place.getMd5().equalsIgnoreCase(md5)) {

						place.click();
					}
				}
			}
		}
	}
}
