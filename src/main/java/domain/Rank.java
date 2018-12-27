package domain;

public enum Rank {
	TWO(2, '2'), THREE(3, '3'), FOUR(4, '4'), FIVE(5, '5'), SIX(6, '6'), SEVEN(7, '7'), EIGHT(8, '8'), NINE(9,
			'9'), TEN(10, 'T'), JACK(10, 'J'), QUEEN(10, 'Q'), KING(10, 'K'), ACE(11, 'A');

	private Integer value;
	private char label;

	Rank(Integer value, char label) {
		this.value = value;
		this.label = label;
	}

	public Integer getValue() {
		return this.value;
	}

	public char getLabel() {
		return this.label;
	}

	public static Rank getRankFromInt(int value) {
		for (Rank r : Rank.values()) {
			if (value == r.getValue()) {
				return r;
			}
		}
		return null;
	}
}
