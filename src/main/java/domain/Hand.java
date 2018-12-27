package domain;

import java.util.ArrayList;
import java.util.List;

public class Hand {
	private List<Card> cards;
	private Double bet;

	public Hand() {
		this.cards = new ArrayList<Card>();
	}

	public Hand(Card card1, Card card2) {
		this.cards = new ArrayList<Card>();
		this.addCard(card1);
		this.addCard(card2);
		this.bet = 0.0;
	}

	public Hand(Card card1, Double bet) {
		this.cards = new ArrayList<Card>();
		this.addCard(card1);
		this.bet = bet;
	}

	public Hand(Card card1, Card card2, Double bet) {
		this.cards = new ArrayList<Card>();
		this.addCard(card1);
		this.addCard(card2);
		this.bet = bet;
	}

	public List<Card> getCards() {
		return this.cards;
	}

	public Double getBet() {
		return this.bet;
	}

	public void setBet(Double bet) {
		this.bet = bet;
	}

	public void addCard(Card card) {
		this.cards.add(card);
	}

	public void doubleBet() {
		this.bet = this.bet * 2;
	}

	public Integer calculateValue() {
		Integer returnValue = 0;
		Integer acesAs11s = 0;
		for (Card card : this.cards) {
			returnValue += card.getRank().getValue();
			if (card.getRank().equals(Rank.ACE)) {
				acesAs11s++;
			}
		}
		while (returnValue > 21 && acesAs11s > 0) {
			returnValue -= 10;
			acesAs11s--;
		}
		return returnValue;
	}

	public boolean isPair() {
		if (this.isTwoCards()) {
			return cards.get(0).getRank().equals(cards.get(1).getRank());
		}
		return false;
	}

	public boolean isSoft() {
		Integer returnValue = 0;
		Integer acesAs11s = 0;
		for (Card card : this.cards) {
			returnValue += card.getRank().getValue();
			if (card.getRank().equals(Rank.ACE)) {
				acesAs11s++;
			}
		}
		while (returnValue > 21 && acesAs11s > 0) {
			returnValue -= 10;
			acesAs11s--;
		}
		return (acesAs11s != 0);
	}

	public boolean isBlackjack() {
		if (!this.isTwoCards()) {
			return false;
		}
		Card dealerUpCard = this.cards.get(0);
		Card dealerHoleCard = this.cards.get(1);
		if (dealerUpCard.getRank().equals(Rank.ACE)) {
			if (dealerHoleCard.getRank().getValue().equals(10)) {
				return true;
			}
		}

		if (dealerUpCard.getRank().getValue().equals(10)) {
			if (dealerHoleCard.getRank().equals(Rank.ACE)) {
				return true;
			}
		}
		return false;
	}

	public boolean isBust() {
		if (this.calculateValue() > 21) {
			return true;
		}
		return false;
	}

	public boolean isTwoCards() {
		return this.cards.size() == 2;
	}

	public String toString() {
		String returnString = "";
		Integer handValue = calculateValue();
		for (Card card : this.cards) {
			returnString += card + ", ";
		}
		returnString = returnString.substring(0, returnString.length() - 2);
		if (handValue == 21 && this.isTwoCards()) {
			returnString += " (Blackjack)";
		} else {
			returnString += " (" + (this.isSoft() ? "Soft " : "Hard ") + handValue + ")";
		}
		return returnString;
	}
}
