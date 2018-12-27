package domain;

import java.util.ArrayList;
import java.util.List;

public class Stack {

	private List<Chip> chips;

	public Stack(List<Chip> chips) {
		this.chips = chips;
	}

	public Stack(Double remainingValue) {
		this.chips = new ArrayList<Chip>();
		remainingValue = addChips(Denomination.FIVE_HUNDRED, remainingValue);
		remainingValue = addChips(Denomination.HUNDRED, remainingValue);
		remainingValue = addChips(Denomination.TWENTY_FIVE, remainingValue);
		remainingValue = addChips(Denomination.FIVE, remainingValue);
		if (remainingValue % 1 == 0.50) {
			remainingValue = addChips(Denomination.TWO_FIFTY, remainingValue);
		}
		remainingValue = addChips(Denomination.ONE, remainingValue);
	}

	private Double addChips(Denomination d, Double doubleValue) {
		int numberOfChips = (int) (doubleValue / d.getValue());
		double leftOver = doubleValue % d.getValue();
		for (int i = 0; i < numberOfChips; i++) {
			Chip chip = new Chip(d);
			this.chips.add(chip);
		}
		return leftOver;
	}

	public List<Chip> getChips() {
		return chips;
	}

	public void setChips(List<Chip> chips) {
		this.chips = chips;
	}

	public int countTypeOfChip(Denomination d) {
		int count = 0;
		for (Chip chip : this.chips) {
			if (chip.getDenomination().equals(d)) {
				count++;
			}
		}
		return count;
	}

}
