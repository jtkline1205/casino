package domain;

import java.util.ArrayList;
import java.util.List;

public class Series {
	public List<Hand> hands;

	public Series() {
		this.hands = new ArrayList<Hand>();
	}

	public Series(Hand hand) {
		this.hands = new ArrayList<Hand>();
		this.hands.add(hand);
	}

	public List<Hand> getHands() {
		return this.hands;
	}

	public void addHand(Hand hand) {
		this.hands.add(hand);
	}

	public void removeHand(Hand hand) {
		this.hands.remove(hand);
	}

	public Hand getFirstHand() {
		return this.hands.get(0);
	}

	public Double getTotalBet() {
		Double totalBet = 0.00;
		for (Hand hand : hands) {
			totalBet += hand.getBet();
		}
		return totalBet;
	}
}
