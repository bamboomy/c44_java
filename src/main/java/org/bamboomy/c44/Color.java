package org.bamboomy.c44;

import lombok.Getter;

public enum Color {

	GREEN("Green", 0), BLUE("Blue", 1), RED("Red", 2), YELLOW("Yellow", 3);

	@Getter
	private final String name;

	@Getter
	private final int number;

	Color(String name, int number) {

		this.name = name;
		this.number = number;
	}

	public static Color getFromName(String name) {

		for (Color myVar : Color.values()) {
			if (myVar.name.equalsIgnoreCase(name)) {
				return myVar;
			}
		}

		return null;
	}

	public static Color getFromInt(int number) {

		for (Color myVar : Color.values()) {
			if (myVar.number == number) {
				return myVar;
			}
		}

		return null;
	}
}
