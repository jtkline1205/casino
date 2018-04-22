package domain;

public class Chip {

	private Denomination denomination;

	public Chip(Denomination denomination) {
		this.denomination = denomination;
	}

	public Denomination getDenomination() {
		return denomination;
	}

	public void setDenomination(Denomination denomination) {
		this.denomination = denomination;
	}

	public String toString() {
		return denomination.toString();
	}

	public String getResourceName() {
		return denomination.getColor() + "chip";
	}

}
