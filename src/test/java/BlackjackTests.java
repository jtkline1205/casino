import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import domain.Card;
import domain.Hand;
import domain.Rank;
import domain.Stack;

public class BlackjackTests {

	@Test
	public void stackTest() {
		new Stack(927.50);
	}

	@Test
	public void value4Tests() {
		List<Hand> value4Hands = new ArrayList<Hand>();
		value4Hands.add(new Hand(new Card(Rank.TWO), new Card(Rank.TWO)));

		for (Hand hand : value4Hands) {
			assertEquals(new Integer(4), hand.calculateValue());
		}
	}

	@Test
	public void value5Tests() {
		List<Hand> value5Hands = new ArrayList<Hand>();
		value5Hands.add(new Hand(new Card(Rank.THREE), new Card(Rank.TWO)));

		for (Hand hand : value5Hands) {
			assertEquals(new Integer(5), hand.calculateValue());
		}
	}

	@Test
	public void value6Tests() {
		List<Hand> value6Hands = new ArrayList<Hand>();
		value6Hands.add(new Hand(new Card(Rank.FOUR), new Card(Rank.TWO)));
		value6Hands.add(new Hand(new Card(Rank.THREE), new Card(Rank.THREE)));

		for (Hand hand : value6Hands) {
			assertEquals(new Integer(6), hand.calculateValue());
		}
	}

	@Test
	public void value7Tests() {
		List<Hand> value7Hands = new ArrayList<Hand>();
		value7Hands.add(new Hand(new Card(Rank.FIVE), new Card(Rank.TWO)));
		value7Hands.add(new Hand(new Card(Rank.FOUR), new Card(Rank.THREE)));

		for (Hand hand : value7Hands) {
			assertEquals(new Integer(7), hand.calculateValue());
		}
	}

	@Test
	public void value8Tests() {
		List<Hand> value8Hands = new ArrayList<Hand>();
		value8Hands.add(new Hand(new Card(Rank.SIX), new Card(Rank.TWO)));
		value8Hands.add(new Hand(new Card(Rank.FIVE), new Card(Rank.THREE)));
		value8Hands.add(new Hand(new Card(Rank.FOUR), new Card(Rank.FOUR)));

		for (Hand hand : value8Hands) {
			assertEquals(new Integer(8), hand.calculateValue());
		}
	}

	@Test
	public void value9Tests() {
		List<Hand> value9Hands = new ArrayList<Hand>();
		value9Hands.add(new Hand(new Card(Rank.SEVEN), new Card(Rank.TWO)));
		value9Hands.add(new Hand(new Card(Rank.SIX), new Card(Rank.THREE)));
		value9Hands.add(new Hand(new Card(Rank.FIVE), new Card(Rank.FOUR)));

		for (Hand hand : value9Hands) {
			assertEquals(new Integer(9), hand.calculateValue());
		}
	}

	@Test
	public void value10Tests() {
		List<Hand> value10Hands = new ArrayList<Hand>();
		value10Hands.add(new Hand(new Card(Rank.EIGHT), new Card(Rank.TWO)));
		value10Hands.add(new Hand(new Card(Rank.SEVEN), new Card(Rank.THREE)));
		value10Hands.add(new Hand(new Card(Rank.SIX), new Card(Rank.FOUR)));
		value10Hands.add(new Hand(new Card(Rank.FIVE), new Card(Rank.FIVE)));

		for (Hand hand : value10Hands) {
			assertEquals(new Integer(10), hand.calculateValue());
		}
	}

	@Test
	public void value11Tests() {
		List<Hand> value11Hands = new ArrayList<Hand>();
		value11Hands.add(new Hand(new Card(Rank.NINE), new Card(Rank.TWO)));
		value11Hands.add(new Hand(new Card(Rank.EIGHT), new Card(Rank.THREE)));
		value11Hands.add(new Hand(new Card(Rank.SEVEN), new Card(Rank.FOUR)));
		value11Hands.add(new Hand(new Card(Rank.SIX), new Card(Rank.FIVE)));

		for (Hand hand : value11Hands) {
			assertEquals(new Integer(11), hand.calculateValue());
		}
	}

	@Test
	public void value12Tests() {
		List<Hand> value12Hands = new ArrayList<Hand>();
		value12Hands.add(new Hand(new Card(Rank.ACE), new Card(Rank.ACE)));
		value12Hands.add(new Hand(new Card(Rank.TEN), new Card(Rank.TWO)));
		value12Hands.add(new Hand(new Card(Rank.JACK), new Card(Rank.TWO)));
		value12Hands.add(new Hand(new Card(Rank.QUEEN), new Card(Rank.TWO)));
		value12Hands.add(new Hand(new Card(Rank.KING), new Card(Rank.TWO)));
		value12Hands.add(new Hand(new Card(Rank.NINE), new Card(Rank.THREE)));
		value12Hands.add(new Hand(new Card(Rank.EIGHT), new Card(Rank.FOUR)));
		value12Hands.add(new Hand(new Card(Rank.SEVEN), new Card(Rank.FIVE)));
		value12Hands.add(new Hand(new Card(Rank.SIX), new Card(Rank.SIX)));

		for (Hand hand : value12Hands) {
			assertEquals(new Integer(12), hand.calculateValue());
		}

	}

	@Test
	public void value13Tests() {
		List<Hand> value13Hands = new ArrayList<Hand>();
		value13Hands.add(new Hand(new Card(Rank.ACE), new Card(Rank.TWO)));
		value13Hands.add(new Hand(new Card(Rank.TEN), new Card(Rank.THREE)));
		value13Hands.add(new Hand(new Card(Rank.JACK), new Card(Rank.THREE)));
		value13Hands.add(new Hand(new Card(Rank.QUEEN), new Card(Rank.THREE)));
		value13Hands.add(new Hand(new Card(Rank.KING), new Card(Rank.THREE)));
		value13Hands.add(new Hand(new Card(Rank.NINE), new Card(Rank.FOUR)));
		value13Hands.add(new Hand(new Card(Rank.EIGHT), new Card(Rank.FIVE)));
		value13Hands.add(new Hand(new Card(Rank.SEVEN), new Card(Rank.SIX)));

		for (Hand hand : value13Hands) {
			assertEquals(new Integer(13), hand.calculateValue());
		}
	}

	@Test
	public void value14Tests() {
		List<Hand> value14Hands = new ArrayList<Hand>();
		value14Hands.add(new Hand(new Card(Rank.ACE), new Card(Rank.THREE)));
		value14Hands.add(new Hand(new Card(Rank.TEN), new Card(Rank.FOUR)));
		value14Hands.add(new Hand(new Card(Rank.JACK), new Card(Rank.FOUR)));
		value14Hands.add(new Hand(new Card(Rank.QUEEN), new Card(Rank.FOUR)));
		value14Hands.add(new Hand(new Card(Rank.KING), new Card(Rank.FOUR)));
		value14Hands.add(new Hand(new Card(Rank.NINE), new Card(Rank.FIVE)));
		value14Hands.add(new Hand(new Card(Rank.EIGHT), new Card(Rank.SIX)));
		value14Hands.add(new Hand(new Card(Rank.SEVEN), new Card(Rank.SEVEN)));

		for (Hand hand : value14Hands) {
			assertEquals(new Integer(14), hand.calculateValue());
		}
	}

	@Test
	public void value15Tests() {
		List<Hand> value15Hands = new ArrayList<Hand>();
		value15Hands.add(new Hand(new Card(Rank.ACE), new Card(Rank.FOUR)));
		value15Hands.add(new Hand(new Card(Rank.TEN), new Card(Rank.FIVE)));
		value15Hands.add(new Hand(new Card(Rank.JACK), new Card(Rank.FIVE)));
		value15Hands.add(new Hand(new Card(Rank.QUEEN), new Card(Rank.FIVE)));
		value15Hands.add(new Hand(new Card(Rank.KING), new Card(Rank.FIVE)));
		value15Hands.add(new Hand(new Card(Rank.NINE), new Card(Rank.SIX)));
		value15Hands.add(new Hand(new Card(Rank.EIGHT), new Card(Rank.SEVEN)));

		for (Hand hand : value15Hands) {
			assertEquals(new Integer(15), hand.calculateValue());
		}
	}

	@Test
	public void value16Tests() {
		List<Hand> value16Hands = new ArrayList<Hand>();
		value16Hands.add(new Hand(new Card(Rank.ACE), new Card(Rank.FIVE)));
		value16Hands.add(new Hand(new Card(Rank.TEN), new Card(Rank.SIX)));
		value16Hands.add(new Hand(new Card(Rank.JACK), new Card(Rank.SIX)));
		value16Hands.add(new Hand(new Card(Rank.QUEEN), new Card(Rank.SIX)));
		value16Hands.add(new Hand(new Card(Rank.KING), new Card(Rank.SIX)));
		value16Hands.add(new Hand(new Card(Rank.NINE), new Card(Rank.SEVEN)));
		value16Hands.add(new Hand(new Card(Rank.EIGHT), new Card(Rank.EIGHT)));

		for (Hand hand : value16Hands) {
			assertEquals(new Integer(16), hand.calculateValue());
		}
	}

	@Test
	public void value17Tests() {
		List<Hand> value17Hands = new ArrayList<Hand>();
		value17Hands.add(new Hand(new Card(Rank.ACE), new Card(Rank.SIX)));
		value17Hands.add(new Hand(new Card(Rank.TEN), new Card(Rank.SEVEN)));
		value17Hands.add(new Hand(new Card(Rank.JACK), new Card(Rank.SEVEN)));
		value17Hands.add(new Hand(new Card(Rank.QUEEN), new Card(Rank.SEVEN)));
		value17Hands.add(new Hand(new Card(Rank.KING), new Card(Rank.SEVEN)));
		value17Hands.add(new Hand(new Card(Rank.NINE), new Card(Rank.EIGHT)));

		for (Hand hand : value17Hands) {
			assertEquals(new Integer(17), hand.calculateValue());
		}
	}

	@Test
	public void value18Tests() {
		List<Hand> value18Hands = new ArrayList<Hand>();
		value18Hands.add(new Hand(new Card(Rank.ACE), new Card(Rank.SEVEN)));
		value18Hands.add(new Hand(new Card(Rank.TEN), new Card(Rank.EIGHT)));
		value18Hands.add(new Hand(new Card(Rank.JACK), new Card(Rank.EIGHT)));
		value18Hands.add(new Hand(new Card(Rank.QUEEN), new Card(Rank.EIGHT)));
		value18Hands.add(new Hand(new Card(Rank.KING), new Card(Rank.EIGHT)));
		value18Hands.add(new Hand(new Card(Rank.NINE), new Card(Rank.NINE)));

		for (Hand hand : value18Hands) {
			assertEquals(new Integer(18), hand.calculateValue());
		}
	}

	@Test
	public void value19Tests() {
		List<Hand> value19Hands = new ArrayList<Hand>();
		value19Hands.add(new Hand(new Card(Rank.ACE), new Card(Rank.EIGHT)));
		value19Hands.add(new Hand(new Card(Rank.TEN), new Card(Rank.NINE)));
		value19Hands.add(new Hand(new Card(Rank.JACK), new Card(Rank.NINE)));
		value19Hands.add(new Hand(new Card(Rank.QUEEN), new Card(Rank.NINE)));
		value19Hands.add(new Hand(new Card(Rank.KING), new Card(Rank.NINE)));

		for (Hand hand : value19Hands) {
			assertEquals(new Integer(19), hand.calculateValue());
		}
	}

	@Test
	public void value20Tests() {
		List<Hand> value20Hands = new ArrayList<Hand>();
		value20Hands.add(new Hand(new Card(Rank.ACE), new Card(Rank.NINE)));
		value20Hands.add(new Hand(new Card(Rank.TEN), new Card(Rank.TEN)));
		value20Hands.add(new Hand(new Card(Rank.JACK), new Card(Rank.TEN)));
		value20Hands.add(new Hand(new Card(Rank.QUEEN), new Card(Rank.TEN)));
		value20Hands.add(new Hand(new Card(Rank.KING), new Card(Rank.TEN)));
		value20Hands.add(new Hand(new Card(Rank.JACK), new Card(Rank.JACK)));
		value20Hands.add(new Hand(new Card(Rank.QUEEN), new Card(Rank.JACK)));
		value20Hands.add(new Hand(new Card(Rank.KING), new Card(Rank.JACK)));
		value20Hands.add(new Hand(new Card(Rank.QUEEN), new Card(Rank.QUEEN)));
		value20Hands.add(new Hand(new Card(Rank.KING), new Card(Rank.QUEEN)));
		value20Hands.add(new Hand(new Card(Rank.KING), new Card(Rank.KING)));

		for (Hand hand : value20Hands) {
			assertEquals(new Integer(20), hand.calculateValue());
		}
	}

	@Test
	public void hardTest() {
		List<Hand> hardHands = new ArrayList<Hand>();

		Hand hardHand1 = new Hand();
		hardHand1.addCard(new Card(Rank.TEN));
		hardHand1.addCard(new Card(Rank.FOUR));
		hardHands.add(hardHand1);

		Hand hardHand2 = new Hand();
		hardHand2.addCard(new Card(Rank.EIGHT));
		hardHand2.addCard(new Card(Rank.NINE));
		hardHands.add(hardHand2);

		Hand hardHand3 = new Hand();
		hardHand3.addCard(new Card(Rank.TWO));
		hardHand3.addCard(new Card(Rank.KING));
		hardHands.add(hardHand3);

		Hand hardHand4 = new Hand();
		hardHand4.addCard(new Card(Rank.QUEEN));
		hardHand4.addCard(new Card(Rank.QUEEN));
		hardHands.add(hardHand4);

		Hand hardHand5 = new Hand();
		hardHand5.addCard(new Card(Rank.FOUR));
		hardHand5.addCard(new Card(Rank.JACK));
		hardHands.add(hardHand5);

		Hand hardHand6 = new Hand();
		hardHand6.addCard(new Card(Rank.KING));
		hardHand6.addCard(new Card(Rank.FIVE));
		hardHands.add(hardHand6);

		for (Hand hand : hardHands) {
			assertFalse(hand.isSoft());
		}
	}

	@Test
	public void softTest() {
		List<Hand> softHands = new ArrayList<Hand>();

		Hand softHand1 = new Hand();
		softHand1.addCard(new Card(Rank.ACE));
		softHand1.addCard(new Card(Rank.FOUR));
		softHands.add(softHand1);

		Hand softHand2 = new Hand();
		softHand2.addCard(new Card(Rank.TWO));
		softHand2.addCard(new Card(Rank.ACE));
		softHands.add(softHand2);

		Hand softHand3 = new Hand();
		softHand3.addCard(new Card(Rank.ACE));
		softHand3.addCard(new Card(Rank.ACE));
		softHands.add(softHand3);

		Hand softHand4 = new Hand();
		softHand4.addCard(new Card(Rank.THREE));
		softHand4.addCard(new Card(Rank.ACE));
		softHands.add(softHand4);

		Hand softHand5 = new Hand();
		softHand5.addCard(new Card(Rank.ACE));
		softHand5.addCard(new Card(Rank.KING));
		softHands.add(softHand5);

		Hand softHand6 = new Hand();
		softHand6.addCard(new Card(Rank.TEN));
		softHand6.addCard(new Card(Rank.ACE));
		softHands.add(softHand6);

		for (Hand hand : softHands) {
			assertTrue(hand.isSoft());
		}
	}

	@Test
	public void testIsBlackjack() {
		List<Hand> blackjackHands = new ArrayList<Hand>();
		blackjackHands.add(new Hand(new Card(Rank.ACE), new Card(Rank.KING)));
		blackjackHands.add(new Hand(new Card(Rank.ACE), new Card(Rank.QUEEN)));
		blackjackHands.add(new Hand(new Card(Rank.ACE), new Card(Rank.JACK)));
		blackjackHands.add(new Hand(new Card(Rank.ACE), new Card(Rank.TEN)));

		for (Hand hand : blackjackHands) {
			assertTrue(hand.isBlackjack());
		}

		List<Hand> nonBlackjackHands = new ArrayList<Hand>();
		nonBlackjackHands.add(new Hand(new Card(Rank.FOUR), new Card(Rank.KING)));
		nonBlackjackHands.add(new Hand(new Card(Rank.TWO), new Card(Rank.TWO)));
		nonBlackjackHands.add(new Hand(new Card(Rank.ACE), new Card(Rank.SEVEN)));

		for (Hand hand : nonBlackjackHands) {
			assertFalse(hand.isBlackjack());
		}

	}

	@Test
	public void testGetBasicStrategyDecisionForHardHands() {
		List<Hand> standHands1 = new ArrayList<Hand>();
		standHands1.add(new Hand(new Card(Rank.SEVEN), new Card(Rank.JACK)));
		standHands1.add(new Hand(new Card(Rank.EIGHT), new Card(Rank.JACK)));
		standHands1.add(new Hand(new Card(Rank.NINE), new Card(Rank.JACK)));
		standHands1.add(new Hand(new Card(Rank.JACK), new Card(Rank.JACK)));

		for (Hand playerHand : standHands1) {
			String decision = playerHand.getBasicStrategyDecision(new Card(Rank.TWO));
			assertEquals("Stand", decision);
			decision = playerHand.getBasicStrategyDecision(new Card(Rank.THREE));
			assertEquals("Stand", decision);
			decision = playerHand.getBasicStrategyDecision(new Card(Rank.FOUR));
			assertEquals("Stand", decision);
			decision = playerHand.getBasicStrategyDecision(new Card(Rank.FIVE));
			assertEquals("Stand", decision);
			decision = playerHand.getBasicStrategyDecision(new Card(Rank.SIX));
			assertEquals("Stand", decision);
			decision = playerHand.getBasicStrategyDecision(new Card(Rank.SEVEN));
			assertEquals("Stand", decision);
			decision = playerHand.getBasicStrategyDecision(new Card(Rank.EIGHT));
			assertEquals("Stand", decision);
			decision = playerHand.getBasicStrategyDecision(new Card(Rank.NINE));
			assertEquals("Stand", decision);
			decision = playerHand.getBasicStrategyDecision(new Card(Rank.TEN));
			assertEquals("Stand", decision);
			decision = playerHand.getBasicStrategyDecision(new Card(Rank.ACE));
			assertEquals("Stand", decision);
		}

		List<Hand> standHands2 = new ArrayList<Hand>();
		standHands2.add(new Hand(new Card(Rank.THREE), new Card(Rank.JACK)));
		standHands2.add(new Hand(new Card(Rank.FOUR), new Card(Rank.JACK)));
		standHands2.add(new Hand(new Card(Rank.FIVE), new Card(Rank.JACK)));
		standHands2.add(new Hand(new Card(Rank.SIX), new Card(Rank.JACK)));

		for (Hand playerHand : standHands2) {
			String decision = playerHand.getBasicStrategyDecision(new Card(Rank.TWO));
			assertEquals("Stand", decision);
			decision = playerHand.getBasicStrategyDecision(new Card(Rank.THREE));
			assertEquals("Stand", decision);
			decision = playerHand.getBasicStrategyDecision(new Card(Rank.FOUR));
			assertEquals("Stand", decision);
			decision = playerHand.getBasicStrategyDecision(new Card(Rank.FIVE));
			assertEquals("Stand", decision);
			decision = playerHand.getBasicStrategyDecision(new Card(Rank.SIX));
			assertEquals("Stand", decision);
		}

		List<Hand> standHands3 = new ArrayList<Hand>();
		standHands3.add(new Hand(new Card(Rank.TWO), new Card(Rank.JACK)));

		for (Hand playerHand : standHands3) {
			String decision = playerHand.getBasicStrategyDecision(new Card(Rank.FOUR));
			assertEquals("Stand", decision);
			decision = playerHand.getBasicStrategyDecision(new Card(Rank.FIVE));
			assertEquals("Stand", decision);
			decision = playerHand.getBasicStrategyDecision(new Card(Rank.SIX));
			assertEquals("Stand", decision);
		}

		List<Hand> hitHands1 = new ArrayList<Hand>();
		hitHands1.add(new Hand(new Card(Rank.THREE), new Card(Rank.JACK)));
		hitHands1.add(new Hand(new Card(Rank.FOUR), new Card(Rank.JACK)));
		hitHands1.add(new Hand(new Card(Rank.FIVE), new Card(Rank.JACK)));
		hitHands1.add(new Hand(new Card(Rank.SIX), new Card(Rank.JACK)));

		for (Hand playerHand : hitHands1) {
			String decision = playerHand.getBasicStrategyDecision(new Card(Rank.SEVEN));
			assertEquals("Hit", decision);
			decision = playerHand.getBasicStrategyDecision(new Card(Rank.EIGHT));
			assertEquals("Hit", decision);
			decision = playerHand.getBasicStrategyDecision(new Card(Rank.NINE));
			assertEquals("Hit", decision);
			decision = playerHand.getBasicStrategyDecision(new Card(Rank.TEN));
			assertEquals("Hit", decision);
			decision = playerHand.getBasicStrategyDecision(new Card(Rank.ACE));
			assertEquals("Hit", decision);
		}

		List<Hand> hitHands2 = new ArrayList<Hand>();
		hitHands2.add(new Hand(new Card(Rank.TWO), new Card(Rank.JACK)));

		for (Hand playerHand : hitHands2) {
			String decision = playerHand.getBasicStrategyDecision(new Card(Rank.TWO));
			assertEquals("Hit", decision);
			decision = playerHand.getBasicStrategyDecision(new Card(Rank.THREE));
			assertEquals("Hit", decision);
			decision = playerHand.getBasicStrategyDecision(new Card(Rank.SEVEN));
			assertEquals("Hit", decision);
			decision = playerHand.getBasicStrategyDecision(new Card(Rank.EIGHT));
			assertEquals("Hit", decision);
			decision = playerHand.getBasicStrategyDecision(new Card(Rank.NINE));
			assertEquals("Hit", decision);
			decision = playerHand.getBasicStrategyDecision(new Card(Rank.TEN));
			assertEquals("Hit", decision);
			decision = playerHand.getBasicStrategyDecision(new Card(Rank.ACE));
			assertEquals("Hit", decision);
		}

		List<Hand> hitHands3 = new ArrayList<Hand>();
		hitHands3.add(new Hand(new Card(Rank.TWO), new Card(Rank.SEVEN)));

		for (Hand playerHand : hitHands3) {
			String decision = playerHand.getBasicStrategyDecision(new Card(Rank.TWO));
			assertEquals("Hit", decision);
			decision = playerHand.getBasicStrategyDecision(new Card(Rank.SEVEN));
			assertEquals("Hit", decision);
			decision = playerHand.getBasicStrategyDecision(new Card(Rank.EIGHT));
			assertEquals("Hit", decision);
			decision = playerHand.getBasicStrategyDecision(new Card(Rank.NINE));
			assertEquals("Hit", decision);
			decision = playerHand.getBasicStrategyDecision(new Card(Rank.TEN));
			assertEquals("Hit", decision);
			decision = playerHand.getBasicStrategyDecision(new Card(Rank.ACE));
			assertEquals("Hit", decision);
		}

		List<Hand> hitHands4 = new ArrayList<Hand>();
		hitHands4.add(new Hand(new Card(Rank.TWO), new Card(Rank.SIX)));
		hitHands4.add(new Hand(new Card(Rank.TWO), new Card(Rank.FIVE)));
		hitHands4.add(new Hand(new Card(Rank.TWO), new Card(Rank.FOUR)));
		hitHands4.add(new Hand(new Card(Rank.TWO), new Card(Rank.THREE)));

		for (Hand playerHand : hitHands4) {
			String decision = playerHand.getBasicStrategyDecision(new Card(Rank.TWO));
			assertEquals("Hit", decision);
			decision = playerHand.getBasicStrategyDecision(new Card(Rank.THREE));
			assertEquals("Hit", decision);
			decision = playerHand.getBasicStrategyDecision(new Card(Rank.FOUR));
			assertEquals("Hit", decision);
			decision = playerHand.getBasicStrategyDecision(new Card(Rank.FIVE));
			assertEquals("Hit", decision);
			decision = playerHand.getBasicStrategyDecision(new Card(Rank.SIX));
			assertEquals("Hit", decision);
			decision = playerHand.getBasicStrategyDecision(new Card(Rank.SEVEN));
			assertEquals("Hit", decision);
			decision = playerHand.getBasicStrategyDecision(new Card(Rank.EIGHT));
			assertEquals("Hit", decision);
			decision = playerHand.getBasicStrategyDecision(new Card(Rank.NINE));
			assertEquals("Hit", decision);
			decision = playerHand.getBasicStrategyDecision(new Card(Rank.TEN));
			assertEquals("Hit", decision);
			decision = playerHand.getBasicStrategyDecision(new Card(Rank.ACE));
			assertEquals("Hit", decision);
		}

		List<Hand> doubleHands1 = new ArrayList<Hand>();
		doubleHands1.add(new Hand(new Card(Rank.TWO), new Card(Rank.NINE)));

		for (Hand playerHand : doubleHands1) {
			String decision = playerHand.getBasicStrategyDecision(new Card(Rank.TWO));
			assertEquals("Double", decision);
			decision = playerHand.getBasicStrategyDecision(new Card(Rank.THREE));
			assertEquals("Double", decision);
			decision = playerHand.getBasicStrategyDecision(new Card(Rank.FOUR));
			assertEquals("Double", decision);
			decision = playerHand.getBasicStrategyDecision(new Card(Rank.FIVE));
			assertEquals("Double", decision);
			decision = playerHand.getBasicStrategyDecision(new Card(Rank.SIX));
			assertEquals("Double", decision);
			decision = playerHand.getBasicStrategyDecision(new Card(Rank.SEVEN));
			assertEquals("Double", decision);
			decision = playerHand.getBasicStrategyDecision(new Card(Rank.EIGHT));
			assertEquals("Double", decision);
			decision = playerHand.getBasicStrategyDecision(new Card(Rank.NINE));
			assertEquals("Double", decision);
			decision = playerHand.getBasicStrategyDecision(new Card(Rank.TEN));
			assertEquals("Double", decision);
			// decision = playerHand.getBasicStrategyDecision(new
			// Card(Rank.ACE));
			// assertEquals("Double", decision);
		}

		List<Hand> doubleHands2 = new ArrayList<Hand>();
		doubleHands2.add(new Hand(new Card(Rank.TWO), new Card(Rank.EIGHT)));

		for (Hand playerHand : doubleHands2) {
			String decision = playerHand.getBasicStrategyDecision(new Card(Rank.TWO));
			assertEquals("Double", decision);
			decision = playerHand.getBasicStrategyDecision(new Card(Rank.THREE));
			assertEquals("Double", decision);
			decision = playerHand.getBasicStrategyDecision(new Card(Rank.FOUR));
			assertEquals("Double", decision);
			decision = playerHand.getBasicStrategyDecision(new Card(Rank.FIVE));
			assertEquals("Double", decision);
			decision = playerHand.getBasicStrategyDecision(new Card(Rank.SIX));
			assertEquals("Double", decision);
			decision = playerHand.getBasicStrategyDecision(new Card(Rank.SEVEN));
			assertEquals("Double", decision);
			decision = playerHand.getBasicStrategyDecision(new Card(Rank.EIGHT));
			assertEquals("Double", decision);
			decision = playerHand.getBasicStrategyDecision(new Card(Rank.NINE));
			assertEquals("Double", decision);
		}

		List<Hand> doubleHands3 = new ArrayList<Hand>();
		doubleHands3.add(new Hand(new Card(Rank.TWO), new Card(Rank.SEVEN)));

		for (Hand playerHand : doubleHands3) {
			String decision = playerHand.getBasicStrategyDecision(new Card(Rank.THREE));
			assertEquals("Double", decision);
			decision = playerHand.getBasicStrategyDecision(new Card(Rank.FOUR));
			assertEquals("Double", decision);
			decision = playerHand.getBasicStrategyDecision(new Card(Rank.FIVE));
			assertEquals("Double", decision);
			decision = playerHand.getBasicStrategyDecision(new Card(Rank.SIX));
			assertEquals("Double", decision);
		}

	}
}
