package scala

import domain.{Card, Hand}
import org.junit.Assert.assertFalse
import org.junit.Test;

class HandTests {

	def createHand(value1: Int, value2: Int): Hand = new Hand(new Card(value1), new Card(value2))

	@Test
	def hardTest() {
		val hardHands = List(
			createHand(10, 4),
			createHand(8, 9),
			createHand(2, 10),
			createHand(10, 10),
			createHand(4, 10),
			createHand(10, 5)
		);

		assertFalse(hardHands(0).isSoft)

//		for (Hand hand : hardHands) {
//			assertFalse(hand.isSoft());
//		}
	}

//	@Test
//	public void softTest() {
//		List<Hand> softHands = new ArrayList<Hand>();
//		softHands.add(createHand(11, 4));
//		softHands.add(createHand(2, 11));
//		softHands.add(createHand(11, 11));
//		softHands.add(createHand(3, 11));
//		softHands.add(createHand(11, 10));
//		softHands.add(createHand(10, 11));
//
//		for (Hand hand : softHands) {
//			assertTrue(hand.isSoft());
//		}
//	}
//
//	@Test
//	public void testIsBlackjack() {
//		List<Hand> blackjackHands = new ArrayList<Hand>();
//		blackjackHands.add(createHand(11, 10));
//
//		for (Hand hand : blackjackHands) {
//			assertTrue(hand.isBlackjack());
//		}
//
//		List<Hand> nonBlackjackHands = new ArrayList<Hand>();
//		nonBlackjackHands.add(createHand(4, 10));
//		nonBlackjackHands.add(createHand(2, 2));
//		nonBlackjackHands.add(createHand(11, 7));
//
//		for (Hand hand : nonBlackjackHands) {
//			assertFalse(hand.isBlackjack());
//		}
//
//	}
//
//	@Test
//	public void value4Tests() {
//		List<Hand> value4Hands = new ArrayList<Hand>();
//		value4Hands.add(createHand(2, 2));
//
//		for (Hand hand : value4Hands) {
//			assertEquals(new Integer(4), hand.calculateValue());
//		}
//	}
//
//	@Test
//	public void value5Tests() {
//		List<Hand> value5Hands = new ArrayList<Hand>();
//		value5Hands.add(createHand(3, 2));
//
//		for (Hand hand : value5Hands) {
//			assertEquals(new Integer(5), hand.calculateValue());
//		}
//	}
//
//	@Test
//	public void value6Tests() {
//		List<Hand> value6Hands = new ArrayList<Hand>();
//		value6Hands.add(createHand(4, 2));
//		value6Hands.add(createHand(3, 3));
//
//		for (Hand hand : value6Hands) {
//			assertEquals(new Integer(6), hand.calculateValue());
//		}
//	}
//
//	@Test
//	public void value7Tests() {
//		List<Hand> value7Hands = new ArrayList<Hand>();
//		value7Hands.add(createHand(5, 2));
//		value7Hands.add(createHand(4, 3));
//
//		for (Hand hand : value7Hands) {
//			assertEquals(new Integer(7), hand.calculateValue());
//		}
//	}
//
//	@Test
//	public void value8Tests() {
//		List<Hand> value8Hands = new ArrayList<Hand>();
//		value8Hands.add(createHand(6, 2));
//		value8Hands.add(createHand(5, 3));
//		value8Hands.add(createHand(4, 4));
//
//		for (Hand hand : value8Hands) {
//			assertEquals(new Integer(8), hand.calculateValue());
//		}
//	}
//
//	@Test
//	public void value9Tests() {
//		List<Hand> value9Hands = new ArrayList<Hand>();
//		value9Hands.add(createHand(7, 2));
//		value9Hands.add(createHand(6, 3));
//		value9Hands.add(createHand(5, 4));
//
//		for (Hand hand : value9Hands) {
//			assertEquals(new Integer(9), hand.calculateValue());
//		}
//	}
//
//	@Test
//	public void value10Tests() {
//		List<Hand> value10Hands = new ArrayList<Hand>();
//		value10Hands.add(createHand(8, 2));
//		value10Hands.add(createHand(7, 3));
//		value10Hands.add(createHand(6, 4));
//		value10Hands.add(createHand(5, 5));
//
//		for (Hand hand : value10Hands) {
//			assertEquals(new Integer(10), hand.calculateValue());
//		}
//	}
//
//	@Test
//	public void value11Tests() {
//		List<Hand> value11Hands = new ArrayList<Hand>();
//		value11Hands.add(createHand(9, 2));
//		value11Hands.add(createHand(8, 3));
//		value11Hands.add(createHand(7, 4));
//		value11Hands.add(createHand(6, 5));
//
//		for (Hand hand : value11Hands) {
//			assertEquals(new Integer(11), hand.calculateValue());
//		}
//	}
//
//	@Test
//	public void value12Tests() {
//		List<Hand> value12Hands = new ArrayList<Hand>();
//		value12Hands.add(createHand(11, 11));
//		value12Hands.add(createHand(10, 2));
//		value12Hands.add(createHand(9, 3));
//		value12Hands.add(createHand(8, 4));
//		value12Hands.add(createHand(7, 5));
//		value12Hands.add(createHand(6, 6));
//
//		for (Hand hand : value12Hands) {
//			assertEquals(new Integer(12), hand.calculateValue());
//		}
//
//	}
//
//	@Test
//	public void value13Tests() {
//		List<Hand> value13Hands = new ArrayList<Hand>();
//		value13Hands.add(createHand(11, 2));
//		value13Hands.add(createHand(10, 3));
//		value13Hands.add(createHand(9, 4));
//		value13Hands.add(createHand(8, 5));
//		value13Hands.add(createHand(7, 6));
//
//		for (Hand hand : value13Hands) {
//			assertEquals(new Integer(13), hand.calculateValue());
//		}
//	}
//
//	@Test
//	public void value14Tests() {
//		List<Hand> value14Hands = new ArrayList<Hand>();
//		value14Hands.add(createHand(11, 3));
//		value14Hands.add(createHand(10, 4));
//		value14Hands.add(createHand(9, 5));
//		value14Hands.add(createHand(8, 6));
//		value14Hands.add(createHand(7, 7));
//
//		for (Hand hand : value14Hands) {
//			assertEquals(new Integer(14), hand.calculateValue());
//		}
//	}
//
//	@Test
//	public void value15Tests() {
//		List<Hand> value15Hands = new ArrayList<Hand>();
//		value15Hands.add(createHand(11, 4));
//		value15Hands.add(createHand(10, 5));
//		value15Hands.add(createHand(9, 6));
//		value15Hands.add(createHand(8, 7));
//
//		for (Hand hand : value15Hands) {
//			assertEquals(new Integer(15), hand.calculateValue());
//		}
//	}
//
//	@Test
//	public void value16Tests() {
//		List<Hand> value16Hands = new ArrayList<Hand>();
//		value16Hands.add(createHand(11, 5));
//		value16Hands.add(createHand(10, 6));
//		value16Hands.add(createHand(9, 7));
//		value16Hands.add(createHand(8, 8));
//
//		for (Hand hand : value16Hands) {
//			assertEquals(new Integer(16), hand.calculateValue());
//		}
//	}
//
//	@Test
//	public void value17Tests() {
//		List<Hand> value17Hands = new ArrayList<Hand>();
//		value17Hands.add(createHand(11, 6));
//		value17Hands.add(createHand(10, 7));
//		value17Hands.add(createHand(9, 8));
//
//		for (Hand hand : value17Hands) {
//			assertEquals(new Integer(17), hand.calculateValue());
//		}
//	}
//
//	@Test
//	public void value18Tests() {
//		List<Hand> value18Hands = new ArrayList<Hand>();
//		value18Hands.add(createHand(11, 7));
//		value18Hands.add(createHand(10, 8));
//		value18Hands.add(createHand(9, 9));
//
//		for (Hand hand : value18Hands) {
//			assertEquals(new Integer(18), hand.calculateValue());
//		}
//	}
//
//	@Test
//	public void value19Tests() {
//		List<Hand> value19Hands = new ArrayList<Hand>();
//		value19Hands.add(createHand(11, 8));
//		value19Hands.add(createHand(10, 9));
//
//		for (Hand hand : value19Hands) {
//			assertEquals(new Integer(19), hand.calculateValue());
//		}
//	}
//
//	@Test
//	public void value20Tests() {
//		List<Hand> value20Hands = new ArrayList<Hand>();
//		value20Hands.add(createHand(11, 9));
//		value20Hands.add(createHand(10, 10));
//
//		for (Hand hand : value20Hands) {
//			assertEquals(new Integer(20), hand.calculateValue());
//		}
//	}
}
