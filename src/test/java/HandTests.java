import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import domain.Card;
import domain.Hand;
import domain.Rank;

public class HandTests {

	@Test
	public void hardTest() {
		List<Hand> hardHands = new ArrayList<Hand>();
		hardHands.add(new Hand(new Card(Rank.TEN), new Card(Rank.FOUR)));
		hardHands.add(new Hand(new Card(Rank.EIGHT), new Card(Rank.NINE)));
		hardHands.add(new Hand(new Card(Rank.TWO), new Card(Rank.KING)));
		hardHands.add(new Hand(new Card(Rank.QUEEN), new Card(Rank.QUEEN)));
		hardHands.add(new Hand(new Card(Rank.FOUR), new Card(Rank.JACK)));
		hardHands.add(new Hand(new Card(Rank.KING), new Card(Rank.FIVE)));

		for (Hand hand : hardHands) {
			assertFalse(hand.isSoft());
		}
	}

	@Test
	public void softTest() {
		List<Hand> softHands = new ArrayList<Hand>();
		softHands.add(new Hand(new Card(Rank.ACE), new Card(Rank.FOUR)));
		softHands.add(new Hand(new Card(Rank.TWO), new Card(Rank.ACE)));
		softHands.add(new Hand(new Card(Rank.ACE), new Card(Rank.ACE)));
		softHands.add(new Hand(new Card(Rank.THREE), new Card(Rank.ACE)));
		softHands.add(new Hand(new Card(Rank.ACE), new Card(Rank.KING)));
		softHands.add(new Hand(new Card(Rank.TEN), new Card(Rank.ACE)));

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
}
