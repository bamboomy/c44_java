package org.bamboomy.c44.board;

import lombok.Getter;

public class Place {

	@Getter
	private final int color;

	public static int BLACK = 0, WHITE = 1;

	public Place(int color) {

		if (color != BLACK && color != WHITE) {

			throw new RuntimeException("invalid color:" + color);
		}

		this.color = color;
	}

	public String getImageName() {

		if (color == BLACK) {

			return "../img/black.png";

		} else {

			return "../img/white.png";
		}
	}
}
