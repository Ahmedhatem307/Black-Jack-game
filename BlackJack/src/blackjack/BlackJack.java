package blackjack;


public class BlackJack {
	static Game game = new Game();

	public static void main(String[] args) {

		GUI gui = new GUI();

		game.generateDeck();
		game.playersName();
		game.updateMaxScore();
		gui.runGUI(game.deck, game.players[0].getCards(), game.players[1].getCards(), game.players[2].getCards(),
				game.players[3].getCards());

		// to know the high score and the initial score of the dealer
		System.out.println("high Score " + game.highScore);
		System.out.println("dealer Score " + game.players[3].getScore());

		game.turns(gui);
		System.out.println(game.winner(game.players));
	}
}
