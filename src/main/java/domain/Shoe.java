package domain;

import java.util.ArrayList;
import java.util.List;

public class Shoe {
	private List<Deck> decks;

	public Shoe() {
		this.decks = new ArrayList<Deck>();
	}

	public Shoe(Integer numberOfDecks) {
		this.decks = new ArrayList<Deck>();
		for (int i = 0; i < numberOfDecks; i++) {
			this.decks.add(new Deck());
		}
	}

	public List<Deck> getDecks() {
		return this.decks;
	}

	public Card drawCard() {
		int numberOfDecks = decks.size();
		Deck chosenDeck = decks.get((int) Math.floor(Math.random() * numberOfDecks));
		while (!chosenDeck.hasCards()) {
			chosenDeck = decks.get((int) Math.floor(Math.random() * numberOfDecks));
		}
		Card drawnCard = chosenDeck.drawCard();
		return drawnCard;
	}

	public int getNumberOfCardsInShoe() {
		int returnInteger = 0;
		for (Deck deck : decks) {
			returnInteger += deck.getCards().size();
		}
		return returnInteger;
	}
}
