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
}
