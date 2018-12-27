import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import domain.Deck;

public class DeckTests {

	@Test
	public void testDrawFullDeck() {
		Deck deck = new Deck();
		assertTrue(deck.hasCards());
		for (int i = 0; i < Deck.STANDARD_NUM_CARDS_IN_DECK; i++) {
			deck.drawCard();
		}
		assertFalse(deck.hasCards());
	}
}
