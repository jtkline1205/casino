import org.junit.Assert.assertFalse;
import org.junit.Assert.assertTrue;

import org.junit.Test;

import domain.Deck;

class DeckTests {

	@Test
	def testDrawFullDeck() {
		val deck = new Deck();
		assertTrue(deck.hasCards());
//		for (int i = 0; i < Deck.STANDARD_NUM_CARDS_IN_DECK; i++) {
//			deck.drawCard();
//		}
//		assertFalse(deck.hasCards());
	}
}
