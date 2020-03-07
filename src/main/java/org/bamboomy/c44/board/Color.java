package org.bamboomy.c44.board;

import lombok.Getter;

public enum Color {

	GREEN(0, "Green"), BLUE(1, "Blue"), RED(2, "Red"), YELLOW(3, "Yellow");

	@Getter
	private final int seq;

	@Getter
	private final String name;

	Color(int seq, String name) {
		this.seq = seq;
		this.name = name;
	}

	public static Color getByName(String name) {

		for (Color p : Color.values()) {

			if (p.name.equalsIgnoreCase(name)) {

				return p;
			}
		}

		return null;
	}

	public static Color getBySeq(int seq) {

		for (Color p : Color.values()) {

			if (p.seq == seq) {

				return p;
			}
		}

		return null;
	}

}
