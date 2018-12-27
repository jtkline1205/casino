import domain.Card;
import domain.Hand;

public class HandFactory {
	public static Hand createHand(int card1, int card2) {
		return new Hand(new Card(card1), new Card(card2));
	}
}
