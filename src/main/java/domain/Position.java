package domain;

public enum Position {
	BUTTON('B'), SMALL_BLIND('S'), UTG('U'), UTG1('V'), UTG2('W'), LO_JACK('L'), HI_JACK('H'), CUT_OFF('C');

	private char value;

	Position(char value) {
		this.value = value;
	}

	public char getValue() {
		return this.value;
	}

	public static Position fromValue(char value) {
		for (Position p : Position.values()) {
			if (p.getValue() == value)
				return p;
		}
		return null;
	}

	public static void printPositions() {
		for (Position p : Position.values()) {
			System.out.println(p.name() + ": " + p.getValue());
		}
	}
}
