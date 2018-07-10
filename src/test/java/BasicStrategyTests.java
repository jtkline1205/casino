import domain.Card;
import domain.Decision;
import domain.Hand;
import domain.Rank;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BasicStrategyTests {
    @Test
    public void testGetBasicStrategyDecisionForHardHands() {
        List<Hand> standHands1 = new ArrayList<Hand>();
        standHands1.add(new Hand(new Card(Rank.SEVEN), new Card(Rank.JACK)));
        standHands1.add(new Hand(new Card(Rank.EIGHT), new Card(Rank.JACK)));
        standHands1.add(new Hand(new Card(Rank.NINE), new Card(Rank.JACK)));
        standHands1.add(new Hand(new Card(Rank.JACK), new Card(Rank.JACK)));

        for (Hand playerHand : standHands1) {
            Decision decision = playerHand.getBasicStrategyDecision(new Card(Rank.TWO));
            assertEquals(Decision.STAND, decision);
            decision = playerHand.getBasicStrategyDecision(new Card(Rank.THREE));
            assertEquals(Decision.STAND, decision);
            decision = playerHand.getBasicStrategyDecision(new Card(Rank.FOUR));
            assertEquals(Decision.STAND, decision);
            decision = playerHand.getBasicStrategyDecision(new Card(Rank.FIVE));
            assertEquals(Decision.STAND, decision);
            decision = playerHand.getBasicStrategyDecision(new Card(Rank.SIX));
            assertEquals(Decision.STAND, decision);
            decision = playerHand.getBasicStrategyDecision(new Card(Rank.SEVEN));
            assertEquals(Decision.STAND, decision);
            decision = playerHand.getBasicStrategyDecision(new Card(Rank.EIGHT));
            assertEquals(Decision.STAND, decision);
            decision = playerHand.getBasicStrategyDecision(new Card(Rank.NINE));
            assertEquals(Decision.STAND, decision);
            decision = playerHand.getBasicStrategyDecision(new Card(Rank.TEN));
            assertEquals(Decision.STAND, decision);
            decision = playerHand.getBasicStrategyDecision(new Card(Rank.ACE));
            assertEquals(Decision.STAND, decision);
        }

        List<Hand> standHands2 = new ArrayList<Hand>();
        standHands2.add(new Hand(new Card(Rank.THREE), new Card(Rank.JACK)));
        standHands2.add(new Hand(new Card(Rank.FOUR), new Card(Rank.JACK)));
        standHands2.add(new Hand(new Card(Rank.FIVE), new Card(Rank.JACK)));
        standHands2.add(new Hand(new Card(Rank.SIX), new Card(Rank.JACK)));

        for (Hand playerHand : standHands2) {
            Decision decision = playerHand.getBasicStrategyDecision(new Card(Rank.TWO));
            assertEquals(Decision.STAND, decision);
            decision = playerHand.getBasicStrategyDecision(new Card(Rank.THREE));
            assertEquals(Decision.STAND, decision);
            decision = playerHand.getBasicStrategyDecision(new Card(Rank.FOUR));
            assertEquals(Decision.STAND, decision);
            decision = playerHand.getBasicStrategyDecision(new Card(Rank.FIVE));
            assertEquals(Decision.STAND, decision);
            decision = playerHand.getBasicStrategyDecision(new Card(Rank.SIX));
            assertEquals(Decision.STAND, decision);
        }

        List<Hand> standHands3 = new ArrayList<Hand>();
        standHands3.add(new Hand(new Card(Rank.TWO), new Card(Rank.JACK)));

        for (Hand playerHand : standHands3) {
            Decision decision = playerHand.getBasicStrategyDecision(new Card(Rank.FOUR));
            assertEquals(Decision.STAND, decision);
            decision = playerHand.getBasicStrategyDecision(new Card(Rank.FIVE));
            assertEquals(Decision.STAND, decision);
            decision = playerHand.getBasicStrategyDecision(new Card(Rank.SIX));
            assertEquals(Decision.STAND, decision);
        }

        List<Hand> hitHands1 = new ArrayList<Hand>();
        hitHands1.add(new Hand(new Card(Rank.THREE), new Card(Rank.JACK)));
        hitHands1.add(new Hand(new Card(Rank.FOUR), new Card(Rank.JACK)));
        hitHands1.add(new Hand(new Card(Rank.FIVE), new Card(Rank.JACK)));
        hitHands1.add(new Hand(new Card(Rank.SIX), new Card(Rank.JACK)));

        for (Hand playerHand : hitHands1) {
            Decision decision = playerHand.getBasicStrategyDecision(new Card(Rank.SEVEN));
            assertEquals(Decision.HIT, decision);
            decision = playerHand.getBasicStrategyDecision(new Card(Rank.EIGHT));
            assertEquals(Decision.HIT, decision);
            decision = playerHand.getBasicStrategyDecision(new Card(Rank.NINE));
            assertEquals(Decision.HIT, decision);
            decision = playerHand.getBasicStrategyDecision(new Card(Rank.TEN));
            assertEquals(Decision.HIT, decision);
            decision = playerHand.getBasicStrategyDecision(new Card(Rank.ACE));
            assertEquals(Decision.HIT, decision);
        }

        List<Hand> hitHands2 = new ArrayList<Hand>();
        hitHands2.add(new Hand(new Card(Rank.TWO), new Card(Rank.JACK)));

        for (Hand playerHand : hitHands2) {
            Decision decision = playerHand.getBasicStrategyDecision(new Card(Rank.TWO));
            assertEquals(Decision.HIT, decision);
            decision = playerHand.getBasicStrategyDecision(new Card(Rank.THREE));
            assertEquals(Decision.HIT, decision);
            decision = playerHand.getBasicStrategyDecision(new Card(Rank.SEVEN));
            assertEquals(Decision.HIT, decision);
            decision = playerHand.getBasicStrategyDecision(new Card(Rank.EIGHT));
            assertEquals(Decision.HIT, decision);
            decision = playerHand.getBasicStrategyDecision(new Card(Rank.NINE));
            assertEquals(Decision.HIT, decision);
            decision = playerHand.getBasicStrategyDecision(new Card(Rank.TEN));
            assertEquals(Decision.HIT, decision);
            decision = playerHand.getBasicStrategyDecision(new Card(Rank.ACE));
            assertEquals(Decision.HIT, decision);
        }

        List<Hand> hitHands3 = new ArrayList<Hand>();
        hitHands3.add(new Hand(new Card(Rank.TWO), new Card(Rank.SEVEN)));

        for (Hand playerHand : hitHands3) {
            Decision decision = playerHand.getBasicStrategyDecision(new Card(Rank.TWO));
            assertEquals(Decision.HIT, decision);
            decision = playerHand.getBasicStrategyDecision(new Card(Rank.SEVEN));
            assertEquals(Decision.HIT, decision);
            decision = playerHand.getBasicStrategyDecision(new Card(Rank.EIGHT));
            assertEquals(Decision.HIT, decision);
            decision = playerHand.getBasicStrategyDecision(new Card(Rank.NINE));
            assertEquals(Decision.HIT, decision);
            decision = playerHand.getBasicStrategyDecision(new Card(Rank.TEN));
            assertEquals(Decision.HIT, decision);
            decision = playerHand.getBasicStrategyDecision(new Card(Rank.ACE));
            assertEquals(Decision.HIT, decision);
        }

        List<Hand> hitHands4 = new ArrayList<Hand>();
        hitHands4.add(new Hand(new Card(Rank.TWO), new Card(Rank.SIX)));
        hitHands4.add(new Hand(new Card(Rank.TWO), new Card(Rank.FIVE)));
        hitHands4.add(new Hand(new Card(Rank.TWO), new Card(Rank.FOUR)));
        hitHands4.add(new Hand(new Card(Rank.TWO), new Card(Rank.THREE)));

        for (Hand playerHand : hitHands4) {
            Decision decision = playerHand.getBasicStrategyDecision(new Card(Rank.TWO));
            assertEquals(Decision.HIT, decision);
            decision = playerHand.getBasicStrategyDecision(new Card(Rank.THREE));
            assertEquals(Decision.HIT, decision);
            decision = playerHand.getBasicStrategyDecision(new Card(Rank.FOUR));
            assertEquals(Decision.HIT, decision);
            decision = playerHand.getBasicStrategyDecision(new Card(Rank.FIVE));
            assertEquals(Decision.HIT, decision);
            decision = playerHand.getBasicStrategyDecision(new Card(Rank.SIX));
            assertEquals(Decision.HIT, decision);
            decision = playerHand.getBasicStrategyDecision(new Card(Rank.SEVEN));
            assertEquals(Decision.HIT, decision);
            decision = playerHand.getBasicStrategyDecision(new Card(Rank.EIGHT));
            assertEquals(Decision.HIT, decision);
            decision = playerHand.getBasicStrategyDecision(new Card(Rank.NINE));
            assertEquals(Decision.HIT, decision);
            decision = playerHand.getBasicStrategyDecision(new Card(Rank.TEN));
            assertEquals(Decision.HIT, decision);
            decision = playerHand.getBasicStrategyDecision(new Card(Rank.ACE));
            assertEquals(Decision.HIT, decision);
        }

        List<Hand> doubleHands1 = new ArrayList<Hand>();
        doubleHands1.add(new Hand(new Card(Rank.TWO), new Card(Rank.NINE)));

        for (Hand playerHand : doubleHands1) {
            Decision decision = playerHand.getBasicStrategyDecision(new Card(Rank.TWO));
            assertEquals(Decision.DOUBLE, decision);
            decision = playerHand.getBasicStrategyDecision(new Card(Rank.THREE));
            assertEquals(Decision.DOUBLE, decision);
            decision = playerHand.getBasicStrategyDecision(new Card(Rank.FOUR));
            assertEquals(Decision.DOUBLE, decision);
            decision = playerHand.getBasicStrategyDecision(new Card(Rank.FIVE));
            assertEquals(Decision.DOUBLE, decision);
            decision = playerHand.getBasicStrategyDecision(new Card(Rank.SIX));
            assertEquals(Decision.DOUBLE, decision);
            decision = playerHand.getBasicStrategyDecision(new Card(Rank.SEVEN));
            assertEquals(Decision.DOUBLE, decision);
            decision = playerHand.getBasicStrategyDecision(new Card(Rank.EIGHT));
            assertEquals(Decision.DOUBLE, decision);
            decision = playerHand.getBasicStrategyDecision(new Card(Rank.NINE));
            assertEquals(Decision.DOUBLE, decision);
            decision = playerHand.getBasicStrategyDecision(new Card(Rank.TEN));
            assertEquals(Decision.DOUBLE, decision);
            // decision = playerHand.getBasicStrategyDecision(new
            // Card(Rank.ACE));
            // assertEquals(Decision.DOUBLE, decision);
        }

        List<Hand> doubleHands2 = new ArrayList<Hand>();
        doubleHands2.add(new Hand(new Card(Rank.TWO), new Card(Rank.EIGHT)));

        for (Hand playerHand : doubleHands2) {
            Decision decision = playerHand.getBasicStrategyDecision(new Card(Rank.TWO));
            assertEquals(Decision.DOUBLE, decision);
            decision = playerHand.getBasicStrategyDecision(new Card(Rank.THREE));
            assertEquals(Decision.DOUBLE, decision);
            decision = playerHand.getBasicStrategyDecision(new Card(Rank.FOUR));
            assertEquals(Decision.DOUBLE, decision);
            decision = playerHand.getBasicStrategyDecision(new Card(Rank.FIVE));
            assertEquals(Decision.DOUBLE, decision);
            decision = playerHand.getBasicStrategyDecision(new Card(Rank.SIX));
            assertEquals(Decision.DOUBLE, decision);
            decision = playerHand.getBasicStrategyDecision(new Card(Rank.SEVEN));
            assertEquals(Decision.DOUBLE, decision);
            decision = playerHand.getBasicStrategyDecision(new Card(Rank.EIGHT));
            assertEquals(Decision.DOUBLE, decision);
            decision = playerHand.getBasicStrategyDecision(new Card(Rank.NINE));
            assertEquals(Decision.DOUBLE, decision);
        }

        List<Hand> doubleHands3 = new ArrayList<Hand>();
        doubleHands3.add(new Hand(new Card(Rank.TWO), new Card(Rank.SEVEN)));

        for (Hand playerHand : doubleHands3) {
            Decision decision = playerHand.getBasicStrategyDecision(new Card(Rank.THREE));
            assertEquals(Decision.DOUBLE, decision);
            decision = playerHand.getBasicStrategyDecision(new Card(Rank.FOUR));
            assertEquals(Decision.DOUBLE, decision);
            decision = playerHand.getBasicStrategyDecision(new Card(Rank.FIVE));
            assertEquals(Decision.DOUBLE, decision);
            decision = playerHand.getBasicStrategyDecision(new Card(Rank.SIX));
            assertEquals(Decision.DOUBLE, decision);
        }

    }
}
