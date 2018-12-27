package service;

import domain.Card;
import domain.Decision;
import domain.Hand;
import domain.Rank;

public class DecisionService {

	public Decision getBasicStrategyDecisionForPair(Hand pair, Card dealerUpCard) {
		Integer dealerUpCardValue = dealerUpCard.getRank().getValue();

		Rank rank = pair.getCards().get(0).getRank();
		if (rank.equals(Rank.ACE) || rank.equals(Rank.EIGHT)) {
			// Always split Aces and 8s
			return Decision.SPLIT;
		} else if (rank.equals(Rank.TWO) || rank.equals(Rank.THREE)) {
			// Split 2s and 3s against up to a 7
			if (dealerUpCardValue < 8) {
				return Decision.SPLIT;
			}
			return Decision.HIT;
		} else if (rank.equals(Rank.FOUR)) {
			// Only split 4s against a 5 or 6
			if (dealerUpCardValue == 5 || dealerUpCardValue == 6) {
				return Decision.SPLIT;
			} else {
				return Decision.HIT;
			}
		} else if (rank.equals(Rank.SIX)) {
			// Split 6s against up to a 6
			if (dealerUpCardValue < 7) {
				return Decision.SPLIT;
			} else {
				return Decision.HIT;
			}
		} else if (rank.equals(Rank.SEVEN)) {
			// Split 7s against up to a 7
			if (dealerUpCardValue < 8) {
				return Decision.SPLIT;
			} else {
				return Decision.HIT;
			}
		} else {
			// Split 9s against all but a 7, 10 or Ace
			if (dealerUpCardValue == 7 || dealerUpCardValue == 10 || dealerUpCardValue == 11) {
				return Decision.STAND;
			} else {
				return Decision.SPLIT;
			}
		}
	}

	public Decision getBasicStrategyDecisionForSoftHand(Hand softHand, Card dealerUpCard) {
		Integer handValue = softHand.calculateValue();
		Integer dealerUpCardValue = dealerUpCard.getRank().getValue();
		if (handValue > 18) {
			return Decision.STAND;
		} else if (handValue == 18) {
			if (dealerUpCardValue == 2 || dealerUpCardValue == 7 || dealerUpCardValue == 8) {
				return Decision.STAND;
			} else if (dealerUpCardValue > 2 && dealerUpCardValue < 7) {
				if (softHand.isTwoCards())
					return Decision.DOUBLE;
				else
					return Decision.HIT;
			} else {
				return Decision.HIT;
			}
		} else if (handValue == 17) {
			if (dealerUpCardValue > 2 && dealerUpCardValue < 7) {
				if (softHand.isTwoCards())
					return Decision.DOUBLE;
				else
					return Decision.HIT;
			} else {
				return Decision.HIT;
			}
		} else if (handValue == 16 || handValue == 15) {
			if (dealerUpCardValue > 3 && dealerUpCardValue < 7) {
				if (softHand.isTwoCards())
					return Decision.DOUBLE;
				else
					return Decision.HIT;
			} else {
				return Decision.HIT;
			}
		} else if (handValue == 14 || handValue == 13) {
			if (dealerUpCardValue > 4 && dealerUpCardValue < 7) {
				if (softHand.isTwoCards())
					return Decision.DOUBLE;
				else
					return Decision.HIT;
			} else {
				return Decision.HIT;
			}
		} else {
			return Decision.HIT;
		}
	}

	public Decision getBasicStrategyDecisionForHardHand(Hand hardHand, Card dealerUpCard) {
		Integer dealerUpCardValue = dealerUpCard.getRank().getValue();
		if (dealerUpCardValue < 7) {
			// Weak dealer card
			if (hardHand.calculateValue() < 9) {
				return Decision.HIT;
			} else if (hardHand.calculateValue() == 9) {
				if (dealerUpCardValue == 2) {
					return Decision.HIT;
				} else {
					if (hardHand.isTwoCards())
						return Decision.DOUBLE;
					else
						return Decision.HIT;
				}
			} else if (hardHand.calculateValue() == 10 || hardHand.calculateValue() == 11) {
				if (hardHand.isTwoCards())
					return Decision.DOUBLE;
				else
					return Decision.HIT;
			} else if (hardHand.calculateValue() == 12) {
				if (dealerUpCardValue == 2 || dealerUpCardValue == 3) {
					return Decision.HIT;
				} else {
					return Decision.STAND;
				}
			} else {
				return Decision.STAND;
			}
		} else {
			// Strong dealer card
			if (hardHand.calculateValue() < 17) {
				if (hardHand.calculateValue() == 10 || hardHand.calculateValue() == 11) {
					if (dealerUpCardValue < hardHand.calculateValue()) {
						if (hardHand.isTwoCards())
							return Decision.DOUBLE;
						else
							return Decision.HIT;
					} else {
						return Decision.HIT;
					}
				} else {
					return Decision.HIT;
				}
			} else {
				return Decision.STAND;
			}
		}
	}

	// Returns basic strategy decision for this hand against the given
	// dealerUpCard.
	public Decision getBasicStrategyDecision(Hand hand, Card dealerUpCard) {
		if (hand.isPair() && hand.calculateValue() != 10 && hand.calculateValue() != 20) {
			return getBasicStrategyDecisionForPair(hand, dealerUpCard);
		} else {
			if (hand.isSoft()) {
				return getBasicStrategyDecisionForSoftHand(hand, dealerUpCard);
			} else {
				return getBasicStrategyDecisionForHardHand(hand, dealerUpCard);
			}
		}
	}

}
