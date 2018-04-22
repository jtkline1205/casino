package domain;
public class Card {

	private Rank rank;
	private Suit suit;

	public Card(Rank rank) {
		this.rank = rank;
		this.suit = Suit.SPADES;
	}

	public Card(Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
	}

	public Rank getRank() {
		return rank;
	}

	public void setRank(Rank rank) {
		this.rank = rank;
	}

	public Suit getSuit() {
		return suit;
	}

	public void setSuit(Suit suit) {
		this.suit = suit;
	}

	public String toString() {
		return "" + rank.getLabel() + suit.getLabel();
	}

	public String getResourceName() {
		return rank.toString().toLowerCase() + "_" + suit.toString().toLowerCase();
	}

}
