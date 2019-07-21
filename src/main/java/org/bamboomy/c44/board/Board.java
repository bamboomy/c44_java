package org.bamboomy.c44.board;

import lombok.Getter;

public class Board {

	@Getter
	private Place[][] placez = new Place[12][12];

	private Player[] playerz = new Player[4];

	public Board() {

		int color = 0;

		for (int i = 0; i < 12; i++) {

			placez[i] = new Place[12];

			if (i < 2 || i > 9) {

				for (int j = 2; j < 10; j++) {

					placez[i][j] = new Place(color);

					color = 1 - color;
				}

				color = 1 - color;

			} else {

				for (int j = 0; j < 12; j++) {

					placez[i][j] = new Place(color);

					color = 1 - color;
				}

				color = 1 - color;
			}
		}

		playerz[0] = new Player(0, this);
	}

	public void click(String md5) {

		playerz[0].click(md5);
	}
}
