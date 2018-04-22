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

	public String getBasicStrategyDecisionForPair(Card dealerUpCard) {
		Integer dealerUpCardValue = dealerUpCard.getRank().getValue();

		Rank rank = this.cards.get(0).getRank();
		if (rank.equals(Rank.ACE) || rank.equals(Rank.EIGHT)) {
			// Always split Aces and 8s
			return "Split";
		} else if (rank.equals(Rank.TWO) || rank.equals(Rank.THREE)) {
			// Split 2s and 3s against up to a 7
			if (dealerUpCardValue < 8) {
				return "Split";
			}
			return "Hit";
		} else if (rank.equals(Rank.FOUR)) {
			// Only split 4s against a 5 or 6
			if (dealerUpCardValue == 5 || dealerUpCardValue == 6) {
				return "Split";
			} else {
				return "Hit";
			}
		} else if (rank.equals(Rank.SIX)) {
			// Split 6s against up to a 6
			if (dealerUpCardValue < 7) {
				return "Split";
			} else {
				return "Hit";
			}
		} else if (rank.equals(Rank.SEVEN)) {
			// Split 7s against up to a 7
			if (dealerUpCardValue < 8) {
				return "Split";
			} else {
				return "Hit";
			}
		} else {
			// Split 9s against all but a 7, 10 or Ace
			if (dealerUpCardValue == 7 || dealerUpCardValue == 10 || dealerUpCardValue == 11) {
				return "Stand";
			} else {
				return "Split";
			}
		}
	}

	public String getBasicStrategyDecisionForSoftHand(Card dealerUpCard) {
		Integer handValue = this.calculateValue();
		Integer dealerUpCardValue = dealerUpCard.getRank().getValue();
		if (handValue > 18) {
			return "Stand";
		} else if (handValue == 18) {
			if (dealerUpCardValue == 2 || dealerUpCardValue == 7 || dealerUpCardValue == 8) {
				return "Stand";
			} else if (dealerUpCardValue > 2 && dealerUpCardValue < 7) {
				if (this.isTwoCards())
					return "Double";
				else
					return "Hit";
			} else {
				return "Hit";
			}
		} else if (handValue == 17) {
			if (dealerUpCardValue > 2 && dealerUpCardValue < 7) {
				if (this.isTwoCards())
					return "Double";
				else
					return "Hit";
			} else {
				return "Hit";
			}
		} else if (handValue == 16 || handValue == 15) {
			if (dealerUpCardValue > 3 && dealerUpCardValue < 7) {
				if (this.isTwoCards())
					return "Double";
				else
					return "Hit";
			} else {
				return "Hit";
			}
		} else if (handValue == 14 || handValue == 13) {
			if (dealerUpCardValue > 4 && dealerUpCardValue < 7) {
				if (this.isTwoCards())
					return "Double";
				else
					return "Hit";
			} else {
				return "Hit";
			}
		} else {
			return "Hit";
		}
	}

	public String getBasicStrategyDecisionForHardHand(Card dealerUpCard) {
		Integer dealerUpCardValue = dealerUpCard.getRank().getValue();
		if (dealerUpCardValue < 7) {
			// Weak dealer card
			if (this.calculateValue() < 9) {
				return "Hit";
			} else if (this.calculateValue() == 9) {
				if (dealerUpCardValue == 2) {
					return "Hit";
				} else {
					if (this.isTwoCards())
						return "Double";
					else
						return "Hit";
				}
			} else if (this.calculateValue() == 10 || this.calculateValue() == 11) {
				if (this.isTwoCards())
					return "Double";
				else
					return "Hit";
			} else if (this.calculateValue() == 12) {
				if (dealerUpCardValue == 2 || dealerUpCardValue == 3) {
					return "Hit";
				} else {
					return "Stand";
				}
			} else {
				return "Stand";
			}
		} else {
			// Strong dealer card
			if (this.calculateValue() < 17) {
				if (this.calculateValue() == 10 || this.calculateValue() == 11) {
					if (dealerUpCardValue < this.calculateValue()) {
						if (this.isTwoCards())
							return "Double";
						else
							return "Hit";
					} else {
						return "Hit";
					}
				} else {
					return "Hit";
				}
			} else {
				return "Stand";
			}
		}
	}

	// Returns basic strategy decision for this hand against the given
	// dealerUpCard.
	public String getBasicStrategyDecision(Card dealerUpCard) {
		if (this.isPair() && this.calculateValue() != 10 && this.calculateValue() != 20) {
			return getBasicStrategyDecisionForPair(dealerUpCard);
		} else {
			if (this.isSoft()) {
				return getBasicStrategyDecisionForSoftHand(dealerUpCard);
			} else {
				return getBasicStrategyDecisionForHardHand(dealerUpCard);
			}
		}
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
