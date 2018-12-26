import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import domain.Card;
import domain.Decision;
import domain.Hand;
import domain.Rank;

public class BasicStrategyTests {

	private Card[] weakDealerCards = new Card[] { new Card(Rank.TWO), new Card(Rank.THREE), new Card(Rank.FOUR),
			new Card(Rank.FIVE), new Card(Rank.SIX) };

	private Card[] strongDealerCards = new Card[] { new Card(Rank.SEVEN), new Card(Rank.EIGHT), new Card(Rank.NINE),
			new Card(Rank.TEN), new Card(Rank.ACE) };

	@Test
	public void testStandOnStrongHandsVersusAllDealerCards() {
		List<Hand> strongPlayerHands = new ArrayList<Hand>();
		strongPlayerHands.add(new Hand(new Card(Rank.SEVEN), new Card(Rank.JACK)));
		strongPlayerHands.add(new Hand(new Card(Rank.EIGHT), new Card(Rank.JACK)));
		strongPlayerHands.add(new Hand(new Card(Rank.NINE), new Card(Rank.JACK)));
		strongPlayerHands.add(new Hand(new Card(Rank.JACK), new Card(Rank.JACK)));

		for (Hand strongPlayerHand : strongPlayerHands) {
			for (Card weakDealerCard : weakDealerCards) {
				assertEquals(Decision.STAND, strongPlayerHand.getBasicStrategyDecision(weakDealerCard));
			}
			for (Card strongDealerCard : strongDealerCards) {
				assertEquals(Decision.STAND, strongPlayerHand.getBasicStrategyDecision(strongDealerCard));
			}
		}
	}

	@Test
	public void testStandOnWeakHandsVersusWeakDealerCards() {
		List<Hand> weakPlayerHands = new ArrayList<Hand>();
		weakPlayerHands.add(new Hand(new Card(Rank.THREE), new Card(Rank.JACK)));
		weakPlayerHands.add(new Hand(new Card(Rank.FOUR), new Card(Rank.JACK)));
		weakPlayerHands.add(new Hand(new Card(Rank.FIVE), new Card(Rank.JACK)));
		weakPlayerHands.add(new Hand(new Card(Rank.SIX), new Card(Rank.JACK)));

		for (Hand weakPlayerHand : weakPlayerHands) {
			for (Card weakDealerCard : weakDealerCards) {
				assertEquals(Decision.STAND, weakPlayerHand.getBasicStrategyDecision(weakDealerCard));
			}
		}
	}

	@Test
	public void testHitOnWeakHandsVersusStrongDealerCards() {
		List<Hand> weakPlayerHands = new ArrayList<Hand>();
		weakPlayerHands.add(new Hand(new Card(Rank.THREE), new Card(Rank.JACK)));
		weakPlayerHands.add(new Hand(new Card(Rank.FOUR), new Card(Rank.JACK)));
		weakPlayerHands.add(new Hand(new Card(Rank.FIVE), new Card(Rank.JACK)));
		weakPlayerHands.add(new Hand(new Card(Rank.SIX), new Card(Rank.JACK)));

		for (Hand weakPlayerHand : weakPlayerHands) {
			for (Card strongDealerCard : strongDealerCards) {
				assertEquals(Decision.HIT, weakPlayerHand.getBasicStrategyDecision(strongDealerCard));
			}
		}
	}

	@Test
	public void testHitOnLowNonSplittableHandsVersusAllDealerCards() {
		List<Hand> lowNonSplittablePlayerHands = new ArrayList<Hand>();
		lowNonSplittablePlayerHands.add(new Hand(new Card(Rank.TWO), new Card(Rank.SIX)));
		lowNonSplittablePlayerHands.add(new Hand(new Card(Rank.TWO), new Card(Rank.FIVE)));
		lowNonSplittablePlayerHands.add(new Hand(new Card(Rank.TWO), new Card(Rank.FOUR)));
		lowNonSplittablePlayerHands.add(new Hand(new Card(Rank.TWO), new Card(Rank.THREE)));

		for (Hand lowNonSplittablePlayerHand : lowNonSplittablePlayerHands) {
			for (Card strongDealerCard : strongDealerCards) {
				assertEquals(Decision.HIT, lowNonSplittablePlayerHand.getBasicStrategyDecision(strongDealerCard));
			}
			for (Card weakDealerCard : weakDealerCards) {
				assertEquals(Decision.HIT, lowNonSplittablePlayerHand.getBasicStrategyDecision(weakDealerCard));
			}
		}
	}

	@Test
	public void testDoubleOnNineVersusWeakDealerCardsExceptTwo() {
		List<Hand> nineHands = new ArrayList<Hand>();
		nineHands.add(new Hand(new Card(Rank.TWO), new Card(Rank.SEVEN)));
		nineHands.add(new Hand(new Card(Rank.SIX), new Card(Rank.THREE)));

		for (Hand nineHand : nineHands) {
			for (Card weakDealerCard : weakDealerCards) {
				if (weakDealerCard.getRank().equals(Rank.TWO)) {
					assertEquals(Decision.HIT, nineHand.getBasicStrategyDecision(weakDealerCard));
				} else {
					assertEquals(Decision.DOUBLE, nineHand.getBasicStrategyDecision(weakDealerCard));
				}
			}
		}
	}

	@Test
	public void testDoubleOnTenVersusAllCardsExceptTenAndAce() {
		List<Hand> tenHands = new ArrayList<Hand>();
		tenHands.add(new Hand(new Card(Rank.TWO), new Card(Rank.EIGHT)));
		tenHands.add(new Hand(new Card(Rank.SEVEN), new Card(Rank.THREE)));

		for (Hand tenHand : tenHands) {
			for (Card weakDealerCard : weakDealerCards) {
				assertEquals(Decision.DOUBLE, tenHand.getBasicStrategyDecision(weakDealerCard));
			}
			for (Card strongDealerCard : strongDealerCards) {
				if (strongDealerCard.getRank().equals(Rank.TEN) || strongDealerCard.getRank().equals(Rank.ACE)) {
					assertEquals(Decision.HIT, tenHand.getBasicStrategyDecision(strongDealerCard));
				} else {
					assertEquals(Decision.DOUBLE, tenHand.getBasicStrategyDecision(strongDealerCard));
				}
			}

		}
	}

	@Test
	public void testDoubleOnElevenVersusAllCardsExceptAce() {
		List<Hand> elevenHands = new ArrayList<Hand>();
		elevenHands.add(new Hand(new Card(Rank.TWO), new Card(Rank.NINE)));

		for (Hand elevenHand : elevenHands) {
			for (Card weakDealerCard : weakDealerCards) {
				assertEquals(Decision.DOUBLE, elevenHand.getBasicStrategyDecision(weakDealerCard));
			}
			for (Card strongDealerCard : strongDealerCards) {
				if (strongDealerCard.getRank().equals(Rank.ACE)) {
					assertEquals(Decision.HIT, elevenHand.getBasicStrategyDecision(strongDealerCard));
				} else {
					assertEquals(Decision.DOUBLE, elevenHand.getBasicStrategyDecision(strongDealerCard));
				}
			}
		}
	}

	@Test
	public void testDecisionsOnTwelveVersusAllCards() {
		List<Hand> twelveHands = new ArrayList<Hand>();
		twelveHands.add(new Hand(new Card(Rank.TWO), new Card(Rank.JACK)));
		twelveHands.add(new Hand(new Card(Rank.FIVE), new Card(Rank.SEVEN)));

		for (Hand twelveHand : twelveHands) {
			for (Card weakDealerCard : weakDealerCards) {
				if (weakDealerCard.getRank().equals(Rank.TWO) || weakDealerCard.getRank().equals(Rank.THREE)) {
					assertEquals(Decision.HIT, twelveHand.getBasicStrategyDecision(weakDealerCard));
				} else {
					assertEquals(Decision.STAND, twelveHand.getBasicStrategyDecision(weakDealerCard));
				}
			}
			for (Card strongDealerCard : strongDealerCards) {
				assertEquals(Decision.HIT, twelveHand.getBasicStrategyDecision(strongDealerCard));
			}
		}
	}

	@Test
	public void testHitOnNineVersusStrongDealerCardOrTwo() {
		List<Hand> nineHands = new ArrayList<Hand>();
		nineHands.add(new Hand(new Card(Rank.TWO), new Card(Rank.SEVEN)));
		nineHands.add(new Hand(new Card(Rank.FIVE), new Card(Rank.FOUR)));

		for (Hand nineHand : nineHands) {
			for (Card strongDealerCard : strongDealerCards) {
				assertEquals(Decision.HIT, nineHand.getBasicStrategyDecision(strongDealerCard));
			}
			assertEquals(Decision.HIT, nineHand.getBasicStrategyDecision(new Card(Rank.TWO)));
		}
	}

	@Test
	public void testSplitOnSplittableHandsVersusWeakDealerCards() {
		List<Hand> splittableHands = new ArrayList<Hand>();
		splittableHands.add(new Hand(new Card(Rank.ACE), new Card(Rank.ACE)));
		splittableHands.add(new Hand(new Card(Rank.TWO), new Card(Rank.TWO)));
		splittableHands.add(new Hand(new Card(Rank.THREE), new Card(Rank.THREE)));
		splittableHands.add(new Hand(new Card(Rank.SIX), new Card(Rank.SIX)));
		splittableHands.add(new Hand(new Card(Rank.SEVEN), new Card(Rank.SEVEN)));
		splittableHands.add(new Hand(new Card(Rank.EIGHT), new Card(Rank.EIGHT)));
		splittableHands.add(new Hand(new Card(Rank.NINE), new Card(Rank.NINE)));

		for (Hand splittableHand : splittableHands) {
			for (Card weakDealerCard : weakDealerCards) {
				assertEquals(Decision.SPLIT, splittableHand.getBasicStrategyDecision(weakDealerCard));
			}
		}
	}

	@Test
	public void testSplitOnSplittableHandsVersusStrongDealerCards() {
		List<Hand> splittableHands = new ArrayList<Hand>();
		splittableHands.add(new Hand(new Card(Rank.ACE), new Card(Rank.ACE)));
		splittableHands.add(new Hand(new Card(Rank.EIGHT), new Card(Rank.EIGHT)));

		for (Hand splittableHand : splittableHands) {
			for (Card strongDealerCard : strongDealerCards) {
				assertEquals(Decision.SPLIT, splittableHand.getBasicStrategyDecision(strongDealerCard));
			}
		}
	}

}
