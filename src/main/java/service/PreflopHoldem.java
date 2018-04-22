package service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import domain.Card;
import domain.Deck;
import domain.Position;
import domain.Rank;

public class PreflopHoldem {

	private static Position[] POSITIONS = { Position.BUTTON, Position.SMALL_BLIND, Position.UTG, Position.UTG1,
			Position.UTG2, Position.LO_JACK, Position.HI_JACK, Position.CUT_OFF };
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		Set<String> handsToRaiseSet = initSet();
		Map<Position, Integer> positionCorrectMap = initMap();
		Map<Position, Integer> positionTotalMap = initMap();
		int hands = promptForIntInput("How many hands?");

		Position position = POSITIONS[(int) (Math.random() * POSITIONS.length)];
		char useOnePosition = promptForCharInput("Work with one position only? (y/n)");
		if (useOnePosition == 'y') {
			char choosePosition = promptForCharInput("Choose position yourself? (y/n)");
			if (choosePosition == 'y') {
				Position.printPositions();
				position = Position.fromValue(promptForCharInput("Which position?"));
			} else {
				position = POSITIONS[(int) (Math.random() * POSITIONS.length)];
			}
		}

		for (int i = 0; i < hands; i++) {
			Deck deck = new Deck();
			Card card1 = deck.drawCard();
			Card card2 = deck.drawCard();
			Rank largerRank = card1.getRank();
			Rank smallerRank = card2.getRank();
			String cardString = card1.toString() + card2.toString();
			if (card2.getRank().getValue() > card1.getRank().getValue()) {
				largerRank = card2.getRank();
				smallerRank = card1.getRank();
				cardString = card2.toString() + card1.toString();
			}
			boolean suited = (card1.getSuit().equals(card2.getSuit()));
			if (useOnePosition == 'n') {
				position = POSITIONS[(int) (Math.random() * POSITIONS.length)];
			}
			String key = "" + position.getValue() + largerRank.getLabel() + smallerRank.getLabel()
					+ (suited ? 'S' : 'O');

			char answer = promptForCharInput(position.name() + ": " + cardString + "   Raise preflop? (y/n) ");
			boolean shouldRaise = handsToRaiseSet.contains(key);
			positionTotalMap.put(position, positionTotalMap.get(position) + 1);
			if ((answer == 'y') && shouldRaise || (answer == 'n' && !shouldRaise)) {
				System.out.println("Correct.");
				positionCorrectMap.put(position, positionCorrectMap.get(position) + 1);
			} else {
				System.out.println("Incorrect.");
			}
		}

		printStatistics(positionCorrectMap, positionTotalMap);
	}

	private static Map<Position, Integer> initMap() {
		Map<Position, Integer> map = new HashMap<Position, Integer>();
		for (Position p : Position.values()) {
			map.put(p, 0);
		}
		return map;
	}

	private static void printStatistics(Map<Position, Integer> positionCorrectMap,
			Map<Position, Integer> positionTotalMap) {
		for (Position p : Position.values()) {
			int correct = positionCorrectMap.get(p);
			int total = positionTotalMap.get(p);
			double percentCorrect = 0.0;
			if (total != 0) {
				percentCorrect = (double) ((double) correct / (double) total) * 100;
			}
			System.out.println(p.name() + ": " + correct + "/" + total + "   " + percentCorrect + "%");
		}
	}

	private static Set<String> initSet() throws IOException {
		Set<String> handsToRaiseSet = new HashSet<String>();
		String filePath = constructFilePath();
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String sCurrentLine;
		while ((sCurrentLine = br.readLine()) != null) {
			handsToRaiseSet.add(sCurrentLine);
		}
		br.close();
		return handsToRaiseSet;
	}

	private static String constructFilePath() {
		return "src/main/resources/PreflopRaiseChart";
	}

	private static int promptForIntInput(String prompt) {
		System.out.print(prompt + " ");
		return scanner.nextInt();
	}

	private static char promptForCharInput(String prompt) {
		System.out.print(prompt + " ");
		return scanner.next().charAt(0);
	}
}
