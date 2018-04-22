package domain;

import java.util.ArrayList;
import java.util.List;

public class Stack {

	private List<Chip> chips;

	public Stack(List<Chip> chips) {
		this.chips = chips;
	}

	public Stack(Double doubleValue) {
		int numberOfOrangeChips = (int) (doubleValue / 500.00);
		double leftOver = doubleValue % 500.00;
		int numberOfBlackChips = (int) (leftOver / 100.00);
		leftOver = leftOver % 100.00;
		int numberOfGreenChips = (int) (leftOver / 25.00);
		leftOver = leftOver % 25.00;
		int numberOfRedChips = (int) (leftOver / 5.00);
		leftOver = leftOver % 5.00;
		int numberOfBlueChips = (int) (leftOver / 2.50);
		leftOver = leftOver % 2.50;
		int numberOfWhiteChips = (int) (leftOver / 1.00);

		this.chips = new ArrayList<Chip>();

		for (int i = 0; i < numberOfOrangeChips; i++) {
			Chip chip = new Chip(Denomination.FIVE_HUNDRED);
			this.chips.add(chip);
		}

		for (int i = 0; i < numberOfBlackChips; i++) {
			Chip chip = new Chip(Denomination.HUNDRED);
			this.chips.add(chip);
		}

		for (int i = 0; i < numberOfGreenChips; i++) {
			Chip chip = new Chip(Denomination.TWENTY_FIVE);
			this.chips.add(chip);
		}

		for (int i = 0; i < numberOfRedChips; i++) {
			Chip chip = new Chip(Denomination.FIVE);
			this.chips.add(chip);
		}

		for (int i = 0; i < numberOfBlueChips; i++) {
			Chip chip = new Chip(Denomination.TWO_FIFTY);
			this.chips.add(chip);
		}

		for (int i = 0; i < numberOfWhiteChips; i++) {
			Chip chip = new Chip(Denomination.ONE);
			this.chips.add(chip);
		}
	}

	public List<Chip> getChips() {
		return chips;
	}

	public void setChips(List<Chip> chips) {
		this.chips = chips;
	}

}
