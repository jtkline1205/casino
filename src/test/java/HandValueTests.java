import domain.Card;
import domain.Hand;
import domain.Rank;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class HandValueTests {
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
}
