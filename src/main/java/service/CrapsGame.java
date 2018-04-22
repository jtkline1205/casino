package service;

import java.text.NumberFormat;
import java.util.Locale;

public class CrapsGame {

	private int minBet;
	private int numRolls;
	private CrapsTable crapsTable;

	public CrapsGame() {
		this.minBet = 1;
		this.numRolls = 100000000;
		this.crapsTable = new CrapsTable();
	}

	public static void main(String[] args) {
		System.out.println("Welcome to Craps.");
		CrapsGame crapsGame = new CrapsGame();
		crapsGame.playHardWayBets();
		// crapsGame.playPropositionBets();
	}

	private void playHardWayBets() {
		System.out.println("Playing the Hard Way Bets.");
		playHardWayBet(4);
		playHardWayBet(6);
		playHardWayBet(8);
		playHardWayBet(10);
	}

	private void playHardWayBet(int hardSum) {
		System.out.println("Playing the hard " + hardSum + " over "
				+ NumberFormat.getNumberInstance(Locale.US).format(numRolls) + " rolls with bets of " + minBet);
		int bankroll = 0;
		// int[] results = new int[11];

		for (int i = 0; i < numRolls; i++) {
			bankroll -= minBet;
			int[] roll = crapsTable.rollDice();
			int payout = crapsTable.returnHardWayPayout(roll, hardSum, minBet);
			bankroll += payout;
		}

		// printResults(results);

		double houseEdge = -(((double) bankroll / ((double) numRolls * 1)) * 100);
		System.out.println("Hard " + hardSum + " winnings: " + bankroll + " (house edge of " + houseEdge + "%)");
	}

	@SuppressWarnings("unused")
	private void playPropositionBets() {
		System.out.println("Playing the Proposition Bets.");
		playAPropositionBet("Field", 1);
		playAPropositionBet("World", 5);
		playAPropositionBet("Snake Eyes", 1);
		playAPropositionBet("Ace Deuce", 1);
		playAPropositionBet("Any Seven", 1);
	}

	private void playAPropositionBet(String betName, int betsPerRoll) {
		System.out.println("Playing the " + betName + " over "
				+ NumberFormat.getNumberInstance(Locale.US).format(numRolls) + " rolls with bets of " + minBet);
		int bankroll = 0;
		int[] results = new int[11];

		for (int i = 0; i < numRolls; i++) {
			int[] roll = crapsTable.rollDice();
			int diceSum = roll[0] + roll[1];
			results[diceSum - 2]++;
			bankroll -= minBet * betsPerRoll;
			bankroll += crapsTable.returnPropositionPayout(betName, diceSum, minBet);
		}

		// printResults(results);

		double houseEdge = -(((double) bankroll / ((double) numRolls * betsPerRoll)) * 100);
		System.out.println(betName + " winnings: " + bankroll + " (house edge of " + houseEdge + "%)");
	}

	@SuppressWarnings("unused")
	private void printResults(int[] resultsArray) {
		for (int i = 0; i < resultsArray.length; i++) {
			int rollSum = i + 2;
			int numOfSums = resultsArray[i];
			double percentage = (((double) numOfSums / (double) numRolls) * 100);
			System.out.println(rollSum + ": " + numOfSums + " (" + percentage + "%)");
		}
	}

}
