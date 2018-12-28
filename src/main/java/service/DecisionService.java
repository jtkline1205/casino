package service;

import domain.Card;
import domain.Decision;
import domain.Hand;
import domain.Rank;

public class DecisionService {

	public Decision getBasicStrategyDecisionForPair(Hand pair, Card dealerUpCard) {
		Integer dealerUpCardValue = dealerUpCard.getRank().getValue();

		Rank rank = pair.getFirstCard().getRank();
		switch (rank) {
		case ACE:
		case EIGHT:
			return Decision.SPLIT;
		case TWO:
		case THREE:
			if (dealerUpCardValue < 8)
				return Decision.SPLIT;
			return Decision.HIT;
		case FOUR:
			if (dealerUpCardValue == 5 || dealerUpCardValue == 6)
				return Decision.SPLIT;
			return Decision.HIT;
		case SIX:
			if (dealerUpCardValue < 7)
				return Decision.SPLIT;
			return Decision.HIT;
		case SEVEN:
			if (dealerUpCardValue < 8)
				return Decision.SPLIT;
			return Decision.HIT;
		default:
			if (dealerUpCardValue == 7 || dealerUpCardValue == 10 || dealerUpCardValue == 11)
				return Decision.STAND;
			return Decision.SPLIT;

		}
	}

	public Decision getBasicStrategyDecisionForSoftHand(Hand softHand, Card dealerUpCard) {
		Integer handValue = softHand.calculateValue();
		Integer dealerUpCardValue = dealerUpCard.getRank().getValue();

		switch (handValue) {
		case 18:
			switch (dealerUpCardValue) {
			case 2:
			case 7:
			case 8:
				return Decision.STAND;
			default:
				if (dealerUpCardValue > 2 && dealerUpCardValue < 7)
					if (softHand.isTwoCards())
						return Decision.DOUBLE;
				return Decision.HIT;
			}
		case 17:
			if (dealerUpCardValue > 2 && dealerUpCardValue < 7) {
				if (softHand.isTwoCards())
					return Decision.DOUBLE;
			}
			return Decision.HIT;
		case 16:
		case 15:
			if (dealerUpCardValue > 3 && dealerUpCardValue < 7)
				if (softHand.isTwoCards())
					return Decision.DOUBLE;
			return Decision.HIT;
		case 14:
		case 13:
			if (dealerUpCardValue > 4 && dealerUpCardValue < 7)
				if (softHand.isTwoCards())
					return Decision.DOUBLE;
			return Decision.HIT;
		default:
			if (handValue > 18)
				return Decision.STAND;
			else
				return Decision.HIT;
		}
	}

	public Decision getBasicStrategyDecisionForHardHand(Hand hardHand, Card dealerUpCard) {
		Integer handValue = hardHand.calculateValue();
		Integer dealerUpCardValue = dealerUpCard.getRank().getValue();

		if (dealerUpCardValue < 7) {
			switch (handValue) {
			case 9:
				if (dealerUpCardValue == 2)
					return Decision.HIT;
				return (hardHand.isTwoCards() ? Decision.DOUBLE : Decision.HIT);
			case 10:
			case 11:
				return (hardHand.isTwoCards() ? Decision.DOUBLE : Decision.HIT);
			case 12:
				if (dealerUpCardValue == 2 || dealerUpCardValue == 3)
					return Decision.HIT;
				return Decision.STAND;
			default:
				if (handValue < 9)
					return Decision.HIT;
				return Decision.STAND;
			}
		} else {
			if (handValue < 17) {
				if (handValue == 10 || handValue == 11) {
					if (dealerUpCardValue < handValue) {
						return (hardHand.isTwoCards() ? Decision.DOUBLE : Decision.HIT);
					}
					return Decision.HIT;
				}
				return Decision.HIT;
			}
			return Decision.STAND;
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
