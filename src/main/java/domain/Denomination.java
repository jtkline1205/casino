package domain;

public enum Denomination {
	ONE(1.00, "white"), TWO_FIFTY(2.50, "blue"), FIVE(5.00, "red"), TWENTY_FIVE(25.00, "green"), HUNDRED(100.00,
			"black"), FIVE_HUNDRED(500.00, "orange");

	private Double value;
	private String color;

	Denomination(Double value, String color) {
		this.value = value;
		this.color = color;
	}

	public Double getValue() {
		return this.value;
	}

	public String getColor() {
		return this.color;
	}
}
