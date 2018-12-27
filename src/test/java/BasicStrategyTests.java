import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import domain.Card;
import domain.Decision;
import domain.Hand;
import domain.Rank;
import service.DecisionService;

public class BasicStrategyTests {

	private Card[] weakDealerCards = new Card[] { new Card(Rank.TWO), new Card(Rank.THREE), new Card(Rank.FOUR),
			new Card(Rank.FIVE), new Card(Rank.SIX) };

	private Card[] strongDealerCards = new Card[] { new Card(Rank.SEVEN), new Card(Rank.EIGHT), new Card(Rank.NINE),
			new Card(Rank.TEN), new Card(Rank.ACE) };

	private Hand createHand(int value1, int value2) {
		return HandFactory.createHand(value1, value2);
	}

	private DecisionService decisionService = new DecisionService();

	@Test
	public void testStandOnStrongHandsVersusAllDealerCards() {
		List<Hand> strongPlayerHands = new ArrayList<Hand>();
		strongPlayerHands.add(createHand(7, 10));
		strongPlayerHands.add(createHand(8, 10));
		strongPlayerHands.add(createHand(9, 10));
		strongPlayerHands.add(createHand(10, 10));

		for (Hand strongPlayerHand : strongPlayerHands) {
			for (Card weakDealerCard : weakDealerCards) {
				assertEquals(Decision.STAND,
						decisionService.getBasicStrategyDecision(strongPlayerHand, weakDealerCard));
			}
			for (Card strongDealerCard : strongDealerCards) {
				assertEquals(Decision.STAND,
						decisionService.getBasicStrategyDecision(strongPlayerHand, strongDealerCard));
			}
		}
	}

	@Test
	public void testStandOnWeakHandsVersusWeakDealerCards() {
		List<Hand> weakPlayerHands = new ArrayList<Hand>();
		weakPlayerHands.add(createHand(3, 10));
		weakPlayerHands.add(createHand(4, 10));
		weakPlayerHands.add(createHand(5, 10));
		weakPlayerHands.add(createHand(6, 10));

		for (Hand weakPlayerHand : weakPlayerHands) {
			for (Card weakDealerCard : weakDealerCards) {
				assertEquals(Decision.STAND, decisionService.getBasicStrategyDecision(weakPlayerHand, weakDealerCard));
			}
		}
	}

	@Test
	public void testHitOnWeakHandsVersusStrongDealerCards() {
		List<Hand> weakPlayerHands = new ArrayList<Hand>();
		weakPlayerHands.add(createHand(3, 10));
		weakPlayerHands.add(createHand(4, 10));
		weakPlayerHands.add(createHand(5, 10));
		weakPlayerHands.add(createHand(6, 10));

		for (Hand weakPlayerHand : weakPlayerHands) {
			for (Card strongDealerCard : strongDealerCards) {
				assertEquals(Decision.HIT, decisionService.getBasicStrategyDecision(weakPlayerHand, strongDealerCard));
			}
		}
	}

	@Test
	public void testHitOnLowNonSplittableHandsVersusAllDealerCards() {
		List<Hand> lowNonSplittablePlayerHands = new ArrayList<Hand>();
		lowNonSplittablePlayerHands.add(createHand(2, 6));
		lowNonSplittablePlayerHands.add(createHand(2, 5));
		lowNonSplittablePlayerHands.add(createHand(2, 4));
		lowNonSplittablePlayerHands.add(createHand(2, 3));

		for (Hand lowNonSplittablePlayerHand : lowNonSplittablePlayerHands) {
			for (Card strongDealerCard : strongDealerCards) {
				assertEquals(Decision.HIT,
						decisionService.getBasicStrategyDecision(lowNonSplittablePlayerHand, strongDealerCard));
			}
			for (Card weakDealerCard : weakDealerCards) {
				assertEquals(Decision.HIT,
						decisionService.getBasicStrategyDecision(lowNonSplittablePlayerHand, weakDealerCard));
			}
		}
	}

	@Test
	public void testDoubleOnNineVersusWeakDealerCardsExceptTwo() {
		List<Hand> nineHands = new ArrayList<Hand>();
		nineHands.add(createHand(2, 7));
		nineHands.add(createHand(6, 3));

		for (Hand nineHand : nineHands) {
			for (Card weakDealerCard : weakDealerCards) {
				if (weakDealerCard.getRank().equals(Rank.TWO)) {
					assertEquals(Decision.HIT, decisionService.getBasicStrategyDecision(nineHand, weakDealerCard));
				} else {
					assertEquals(Decision.DOUBLE, decisionService.getBasicStrategyDecision(nineHand, weakDealerCard));
				}
			}
		}
	}

	@Test
	public void testDoubleOnTenVersusAllCardsExceptTenAndAce() {
		List<Hand> tenHands = new ArrayList<Hand>();
		tenHands.add(createHand(2, 8));
		tenHands.add(createHand(7, 3));

		for (Hand tenHand : tenHands) {
			for (Card weakDealerCard : weakDealerCards) {
				assertEquals(Decision.DOUBLE, decisionService.getBasicStrategyDecision(tenHand, weakDealerCard));
			}
			for (Card strongDealerCard : strongDealerCards) {
				if (strongDealerCard.getRank().equals(Rank.TEN) || strongDealerCard.getRank().equals(Rank.ACE)) {
					assertEquals(Decision.HIT, decisionService.getBasicStrategyDecision(tenHand, strongDealerCard));
				} else {
					assertEquals(Decision.DOUBLE, decisionService.getBasicStrategyDecision(tenHand, strongDealerCard));
				}
			}

		}
	}

	@Test
	public void testDoubleOnElevenVersusAllCardsExceptAce() {
		List<Hand> elevenHands = new ArrayList<Hand>();
		elevenHands.add(createHand(2, 9));

		for (Hand elevenHand : elevenHands) {
			for (Card weakDealerCard : weakDealerCards) {
				assertEquals(Decision.DOUBLE, decisionService.getBasicStrategyDecision(elevenHand, weakDealerCard));
			}
			for (Card strongDealerCard : strongDealerCards) {
				if (strongDealerCard.getRank().equals(Rank.ACE)) {
					assertEquals(Decision.HIT, decisionService.getBasicStrategyDecision(elevenHand, strongDealerCard));
				} else {
					assertEquals(Decision.DOUBLE,
							decisionService.getBasicStrategyDecision(elevenHand, strongDealerCard));
				}
			}
		}
	}

	@Test
	public void testDecisionsOnTwelveVersusAllCards() {
		List<Hand> twelveHands = new ArrayList<Hand>();
		twelveHands.add(createHand(2, 10));
		twelveHands.add(createHand(5, 7));

		for (Hand twelveHand : twelveHands) {
			for (Card weakDealerCard : weakDealerCards) {
				if (weakDealerCard.getRank().equals(Rank.TWO) || weakDealerCard.getRank().equals(Rank.THREE)) {
					assertEquals(Decision.HIT, decisionService.getBasicStrategyDecision(twelveHand, weakDealerCard));
				} else {
					assertEquals(Decision.STAND, decisionService.getBasicStrategyDecision(twelveHand, weakDealerCard));
				}
			}
			for (Card strongDealerCard : strongDealerCards) {
				assertEquals(Decision.HIT, decisionService.getBasicStrategyDecision(twelveHand, strongDealerCard));
			}
		}
	}

	@Test
	public void testHitOnNineVersusStrongDealerCardOrTwo() {
		List<Hand> nineHands = new ArrayList<Hand>();
		nineHands.add(createHand(2, 7));
		nineHands.add(createHand(5, 4));

		for (Hand nineHand : nineHands) {
			for (Card strongDealerCard : strongDealerCards) {
				assertEquals(Decision.HIT, decisionService.getBasicStrategyDecision(nineHand, strongDealerCard));
			}
			assertEquals(Decision.HIT, decisionService.getBasicStrategyDecision(nineHand, new Card(Rank.TWO)));
		}
	}

	@Test
	public void testSplitOnSplittableHandsVersusWeakDealerCards() {
		List<Hand> splittableHands = new ArrayList<Hand>();
		splittableHands.add(createHand(11, 11));
		splittableHands.add(createHand(2, 2));
		splittableHands.add(createHand(3, 3));
		splittableHands.add(createHand(6, 6));
		splittableHands.add(createHand(7, 7));
		splittableHands.add(createHand(8, 8));
		splittableHands.add(createHand(9, 9));

		for (Hand splittableHand : splittableHands) {
			for (Card weakDealerCard : weakDealerCards) {
				assertEquals(Decision.SPLIT, decisionService.getBasicStrategyDecision(splittableHand, weakDealerCard));
			}
		}
	}

	@Test
	public void testSplitOnSplittableHandsVersusStrongDealerCards() {
		List<Hand> splittableHands = new ArrayList<Hand>();
		splittableHands.add(createHand(11, 11));
		splittableHands.add(createHand(8, 8));

		for (Hand splittableHand : splittableHands) {
			for (Card strongDealerCard : strongDealerCards) {
				assertEquals(Decision.SPLIT,
						decisionService.getBasicStrategyDecision(splittableHand, strongDealerCard));
			}
		}
	}

}
