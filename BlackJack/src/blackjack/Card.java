package blackjack;

public class Card {

	private int suit;
	private int rank;
	private int value;

	// constructor
	public Card(int suit, int rank, int value) {
		this.suit = suit;
		this.rank = rank;
		this.value = value;
	}

	// copy constructor
	public Card(Card card) {
		this.suit = card.suit;
		this.rank = card.rank;
		this.value = card.value;
	}

	// getters and setters functions
	public int getSuit() {
		return suit;
	}

	public void setSuit(int suit) {
		this.suit = suit;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
