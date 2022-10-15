package blackjack;

import java.util.Random;
import java.util.Objects;
import java.util.Scanner;

public class Game {

	Player[] players = new Player[4];
	Card[] deck = new Card[52];
	static int highScore = 0;

	// this function is for creating a deck
	void generateDeck() {
		int counter = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 13; j++) {
				if (j >= 10) {
					deck[counter] = new Card(i, j, 10);
				} else {
					deck[counter] = new Card(i, j, j + 1);
				}
				counter++;
			}
		}
	}

	//this function is for drawing a card
	Card draw() {
		Random rand = new Random();

		while (true) {
			int randChoice = rand.nextInt(52);
			Card drawnCard = deck[randChoice];
			if (!Objects.isNull(drawnCard)) {
				deck[randChoice] = null;
				return drawnCard;
			}
		}

	}

	// setting players names and drawing two cards for each player
	void playersName() {
		Scanner input = new Scanner(System.in);

		for (int i = 0; i < 4; i++) {
			if (i < 3) {
				players[i] = new Player();
				System.out.println("Please enter the name of player " + (i + 1));
				players[i].setName(input.next());
			} else {
				players[3] = new Player();
				players[3].setName("The Dealer");
			}

			Card[] tempArr = new Card[11]; // a temp array for holding the first two cards and setting them to each
											// player
			for (int j = 0; j < 2; j++) {
				tempArr[j] = draw();
				players[i].setScore(tempArr[j].getValue());
			}
			players[i].setCards(tempArr);
		}

	}

	// this function is for updating the high score
	void updateMaxScore() {
		for (int i = 0; i < 3; i++) {
			if (players[i].getScore() > highScore && players[i].getScore() <= 21) {
				highScore = players[i].getScore();
			}
		}

	}

	//this function is for deciding the winner or if it was a push then returns the result
	String winner(Player[] players) {
		int numberOfWinners = 0;
		int indexOfWinner = 0;
		for (int i = 0; i < 4; i++) {
			if (players[i].getScore() == highScore || players[i].getScore() == 21) {
				indexOfWinner = i;
				numberOfWinners++;
			}
		}
		if (players[3].getScore() > highScore && players[3].getScore() <= 21) {
			return players[3].getName() + " has won!!";
		}

		if (numberOfWinners == 1) {
			return players[indexOfWinner].getName() + " has won!!";
		} else {
			return "Push!!";
		}
	}

	// this function is for how each turn is played
	void turns(GUI gui) {
		Scanner input = new Scanner(System.in);

		for (int i = 0; i < 4; i++) {

			int addIndex = 1; // the index of the last element that has a value in the player's cards
			Card[] holdArr = new Card[11]; // a temporary array that holds the cards from player class and draw function
											// and sets the the card array

			if (i < 3) {
				while (players[i].getScore() < 21) {

					int condition = input.nextInt(); // for a hit press 1 and for a stand press 2

					if (condition == 1) {
						
						//here we start at index 2 and make a copy of each player's cards and then take a random card
						// and put this in the temp array and then set the player's array like the temp array
						
						addIndex++;
						holdArr = players[i].getCards();
						holdArr[addIndex] = draw();
						players[i].setCards(holdArr);
						players[i].setScore(holdArr[addIndex].getValue());
						gui.updatePlayerHand(holdArr[addIndex], i);
						updateMaxScore();

					} else {
						break;
					}
				}
			} else {

				while (players[i].getScore() < 21 || players[i].getScore() < highScore) {

					int condition = input.nextInt();

					if (condition == 1) {
						
						// the same steps but for the dealer
						
						addIndex++;
						holdArr = players[i].getCards();
						holdArr[addIndex] = draw();
						players[i].setCards(holdArr);
						players[i].setScore(holdArr[addIndex].getValue());
						gui.updateDealerHand(holdArr[addIndex], deck);

					}
					if (players[i].getScore() > highScore && players[i].getScore() <= 21) {
						break;
					}
				}
			}
		}
	}
}
