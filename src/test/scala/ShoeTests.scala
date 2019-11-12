import org.junit.Assert.assertEquals;

import org.junit.Test;

import domain.Deck;
import domain.Shoe;

class ShoeTests {

	@Test
	def drawFromShoe() {
		val numDecks: Int = 4;
		val shoe = new Shoe(numDecks);
		assertEquals(numDecks, shoe.getDecks().size());
		assertEquals(numDecks * Deck.STANDARD_NUM_CARDS_IN_DECK, shoe.getNumberOfCardsInShoe());
		shoe.drawCard();
		assertEquals(numDecks * Deck.STANDARD_NUM_CARDS_IN_DECK - 1, shoe.getNumberOfCardsInShoe());
	}
}
