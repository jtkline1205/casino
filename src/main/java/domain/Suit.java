package domain;

public enum Suit {
	HEARTS('h'), SPADES('s'), DIAMONDS('d'), CLUBS('c');

	private char label;

	Suit(char label) {
		this.label = label;
	}

	public char getLabel() {
		return this.label;
	}
}
