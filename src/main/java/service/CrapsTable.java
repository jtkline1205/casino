package service;

public class CrapsTable {

	private int[] fieldPayouts = new int[] { 2, 1, 1, 0, 0, 0, 0, 1, 1, 1, 2 };
	private int[] worldPayouts = new int[] { 30, 15, 0, 0, 0, 4, 0, 0, 0, 15, 30 };
	private int[] snakeEyesPayouts = new int[] { 30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	private int[] aceDeucePayouts = new int[] { 0, 15, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	private int[] anySevenPayouts = new int[] { 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0 };

	public CrapsTable() {

	}

	public int[] rollDice() {
		return new int[] { rollDie(), rollDie() };
	}

	private int rollDie() {
		return (int) (Math.floor(Math.random() * 6) + 1);
	}

	public int returnPropositionPayout(String betName, int diceSum, int bet) {
		if (betName.equals("Field")) {
			if (fieldPayouts[diceSum - 2] != 0) {
				return (fieldPayouts[diceSum - 2] * bet) + bet;
			}
		} else if (betName.equals("World")) {
			if (worldPayouts[diceSum - 2] != 0) {
				return (worldPayouts[diceSum - 2] * bet) + bet;
			}
		} else if (betName.equals("Snake Eyes")) {
			if (snakeEyesPayouts[diceSum - 2] != 0) {
				return (snakeEyesPayouts[diceSum - 2] * bet) + bet;
			}
		} else if (betName.equals("Ace Deuce")) {
			if (aceDeucePayouts[diceSum - 2] != 0) {
				return (aceDeucePayouts[diceSum - 2] * bet) + bet;
			}
		} else if (betName.equals("Any Seven")) {
			if (anySevenPayouts[diceSum - 2] != 0) {
				return (anySevenPayouts[diceSum - 2] * bet) + bet;
			}
		}
		return 0;
	}

	public int returnHardWayPayout(int[] roll, int hardSum, int bet) {
		if (roll[0] + roll[1] == hardSum) {
			if (roll[0] == hardSum / 2) {
				if (hardSum == 6 || hardSum == 8)
					return 10 * bet;
				return 8 * bet;
			} else {
				return 0;
			}
		} else if (roll[0] + roll[1] == 7) {
			return 0;
		}
		return bet;
	}

}
