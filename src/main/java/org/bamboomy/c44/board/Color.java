package org.bamboomy.c44.board;

import lombok.Getter;

public enum Color {

	GREEN(0, "Green", "green"), BLUE(1, "Blue", "blue"), RED(2, "Red", "red"), YELLOW(3, "Yellow", "brown");

	@Getter
	private final int seq;

	@Getter
	private final String name;

	@Getter
	private final String cssColor;

	Color(int seq, String name, String cssColor) {
		this.seq = seq;
		this.name = name;
		this.cssColor = cssColor;
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
