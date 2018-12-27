package domain;

import java.util.ArrayList;
import java.util.List;

public class Deck {
	private List<Card> cards;

	public static final int STANDARD_NUM_CARDS_IN_DECK = 52;

	public Deck() {
		this.cards = new ArrayList<Card>();
		addCards();
	}

	public List<Card> getCards() {
		return cards;
	}

	private void addCards() {
		for (Suit currentSuit : Suit.values()) {
			for (Rank currentRank : Rank.values()) {
				this.cards.add(new Card(currentRank, currentSuit));
			}
		}
	}

	public Card drawCard() {
		Integer deckSize = this.cards.size();
		Integer index = (int) Math.floor((Math.random() * deckSize));
		Card drawnCard = this.cards.get(index);
		cards.remove(drawnCard);
		return drawnCard;
	}

	public boolean hasCards() {
		return !this.cards.isEmpty();
	}

	public String toString() {
		String returnString = "";
		for (Card card : cards) {
			returnString += card + "\n";
		}
		return returnString;
	}
}
