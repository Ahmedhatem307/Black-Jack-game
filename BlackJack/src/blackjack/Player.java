package blackjack;

public class Player {

	private String name;
	private int score = 0;
	private Card[] cards = new Card[11];

	// getters and setters functions
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score += score;
	}

	public Card[] getCards() {
		return cards;
	}

	public void setCards(Card[] cards) {
		this.cards = cards;
	}
	
	

}
