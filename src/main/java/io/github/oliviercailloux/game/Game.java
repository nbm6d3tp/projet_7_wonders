package io.github.oliviercailloux.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class implemented the main logic of the game and to control the game.
 */
public class Game {
	private Age age;
	private static final Logger LOGGER = LoggerFactory.getLogger(Game.class);

	/**
	 * Player who plays first in the first age. This player will be the second to
	 * play in the second age and be the first to play again in the first age.
	 */
	private Player firstPlayer;

	/**
	 * Contrary to the firstPlayer
	 */
	private Player secondPlayer;
	private boolean isFirstPlayerTurn;
	private int swapCardsCounter;
	private boolean gameEnd;

	/**
	 * Initiate the game. Determine the first player to player in the first age
	 * within 2 players. Make 2 players becoming adversary of each other.
	 *
	 * @param player1
	 * @param player2
	 */
	public Game(Player player1, Player player2) {
		super();
		determineFirstPlayer(player1, player2);
		firstPlayer.setAdversary(secondPlayer);
		secondPlayer.setAdversary(firstPlayer);
		age = new Age();
		swapCardsCounter = 0;
		isFirstPlayerTurn = true;
		gameEnd = false;
		distributeCards();
	}

	/**
	 * Transfer an amount of money from one player to another player.
	 *
	 * @param payer
	 * @param receiver
	 * @param amount
	 */
	static public void transferMoney(Player payer, Player receiver, int amount) {
		payer.payMoney(amount);
		receiver.takeMoney(amount);
	}

	public void changeTurn() {
		isFirstPlayerTurn = !isFirstPlayerTurn;
	}

	public void setFirstPlayerTurn(boolean isFirstPlayerTurn) {
		this.isFirstPlayerTurn = isFirstPlayerTurn;
	}

	public void incrementSwapCounter() {
		if (getFirstPlayer().getSizeCardsOnHand() == 1 && getSecondPlayer().getSizeCardsOnHand() == 1) {
			nextAge();
		} else {
			swapCardsCounter++;
			if (swapCardsCounter == 2) {
				swapCards();
				swapCardsCounter = 0;
			}
			isFirstPlayerTurn = !isFirstPlayerTurn;
		}
	}

	public boolean isFirstPlayerTurn() {
		return isFirstPlayerTurn;
	}

	public Player getPlayerInTurn() {
		if (isFirstPlayerTurn) {
			return firstPlayer;
		}
		return secondPlayer;
	}

	/**
	 * Take money from one player (when player have to pay the cost in coins of a
	 * card to the bank to play this card for example)
	 *
	 * @param payer
	 * @param amount
	 */
	static public void takeMoney(Player payer, int amount) {
		payer.payMoney(amount);
	}

	/**
	 * Pay money to a player (when the player discard a card for example)
	 *
	 * @param receiver
	 * @param amount
	 */
	static public void payMoney(Player receiver, int amount) {
		receiver.takeMoney(amount);
	}

	/**
	 * Determine randomly the player to play first (assign to firstPlayer) and the
	 * player to play in second (assign to secondePlayer) in the first age.
	 */
	void determineFirstPlayer(Player player1, Player player2) {
		LOGGER.info("Déterminer joueur à jouer en premier ...");
		int playerToPlayFirst = (int) Math.round(Math.random());
		if (playerToPlayFirst == 0) {
			firstPlayer = player1;
			secondPlayer = player2;
		} else {
			firstPlayer = player2;
			secondPlayer = player1;
		}
		LOGGER.info("joueur à jouer en premier ", firstPlayer.getName());
		LOGGER.info("");

	}

	/**
	 * Distribute cards to each players at the beginning of each age.
	 */
	void distributeCards() {
		LOGGER.info("Distribuer cartes aux 2 joueurs...");

		List<Card> cardsOnHandFirstPlayerCards = new ArrayList<>();
		List<Card> cardsOnHandSecondPlayerCards = new ArrayList<>();
		Random rand = new Random();
		System.out.println(rand);

		for (int i = 0; i < 7; i++) {
			int indexRandomCard = rand.nextInt(age.getCards().size());
			cardsOnHandFirstPlayerCards.add(age.getCards().get(indexRandomCard));
			age.getCards().remove(indexRandomCard);
			indexRandomCard = rand.nextInt(age.getCards().size());
			cardsOnHandSecondPlayerCards.add(age.getCards().get(indexRandomCard));
			age.getCards().remove(indexRandomCard);
		}
		firstPlayer.setCardsOnHand(cardsOnHandFirstPlayerCards);
		secondPlayer.setCardsOnHand(cardsOnHandSecondPlayerCards);
	}

	/**
	 * Swap cards between players when both players have finished their turns.
	 */
	void swapCards() {
		List<Card> tmpCards = firstPlayer.getCardsOnHand();
		firstPlayer.setCardsOnHand(secondPlayer.getCardsOnHand());
		secondPlayer.setCardsOnHand(tmpCards);

		LOGGER.info("Echanger les cartes entre 2 joueurs !");

	}

	/**
	 * Start next age. Before come to next age, start the war. For the first and
	 * second age, after having come to next age, distribute the cards to 2 players.
	 * For the last age, end the game.
	 */
	void nextAge() {
		if (age.getCurrentAge() < 3) {
			age.nextAge();
			isFirstPlayerTurn = getCurrentAge() % 2 == 1;
			distributeCards();
		}
	}

	public boolean isGameEnd() {
		return gameEnd;
	}

	/**
	 * Start war before coming to next age. Compare conflict points between 2
	 * players. Give the relevant amount of military tokens to the 2 players
	 */
	public String startWar() {
		LOGGER.info("La guerre commence!");
		if (firstPlayer.getConflictPoints() > secondPlayer.getConflictPoints()) {
			firstPlayer.addMilitaryTokens(age.getCurrentMilitaryTokenWhenWin());
			secondPlayer.addMilitaryTokens(-1);
			LOGGER.info(firstPlayer.getName() + " gagne contre " + secondPlayer.getName() + " dans la guerre");
			return "Joueur 1 est le gagnant de la guerre!";
		} else if (firstPlayer.getConflictPoints() < secondPlayer.getConflictPoints()) {
			secondPlayer.addMilitaryTokens(age.getCurrentMilitaryTokenWhenWin());
			firstPlayer.addMilitaryTokens(-1);
			LOGGER.info(secondPlayer.getName() + " gagne contre " + firstPlayer.getName() + " dans la guerre");
			return "Joueur 2 est le gagnant de la guerre!";
		} else {
			LOGGER.info("2 joueurs à égalité dans la guerre!");
			return "2 joueurs à égalité dans la guerre!";
		}
	}

	/**
	 * End game. Calculate the final points of victory of each players. Define who
	 * is the winner. If the points of victory are equal, we will compare total
	 * coins of each players. If total coins of each players are equal, we will
	 * compare total number of resources of each players.
	 */
	public String endGame() {
		LOGGER.info("Fin du jeu...");
		gameEnd = true;

		if (firstPlayer.getTotalVictoryPoints() > secondPlayer.getTotalVictoryPoints()) {
			LOGGER.info(firstPlayer.getName() + " est le gagnant !");
			return "Joueur 1 est le gagnant !";
		} else if (firstPlayer.getTotalVictoryPoints() < secondPlayer.getTotalVictoryPoints()) {
			LOGGER.info(secondPlayer.getName() + " est le gagnant !");
			return "Joueur 2 est le gagnant !";
		} else {
			if (firstPlayer.getCoins() > secondPlayer.getCoins()) {
				LOGGER.info(firstPlayer.getName() + " est le gagnant (basé sur le nombre de pièces)!");
				return "Joueur 1 est le gagnant (basé sur le nombre de pièces)!";
			} else if (firstPlayer.getCoins() < secondPlayer.getCoins()) {
				LOGGER.info(secondPlayer.getName() + " est le gagnant (basé sur le nombre de pièces)!");
				return "Joueur 2 est le gagnant (basé sur le nombre de pièces)!";
			} else {
				if (firstPlayer.getResources().size() > secondPlayer.getResources().size()) {
					LOGGER.info(firstPlayer.getName() + " est le gagnant (basé sur le nombre de ressources)!");
					return "Joueur 1 est le gagnant (basé sur le nombre de ressources)!";
				}
				if (firstPlayer.getResources().size() < secondPlayer.getResources().size()) {
					LOGGER.info(secondPlayer.getName() + " est le gagnant (basé sur le nombre de ressources)!");
					return "Joueur 2 est le gagnant (basé sur le nombre de ressources)!";
				}
				LOGGER.info("2 joueurs à égalité !");
				return "2 joueurs à égalité !";
			}
		}
	}

	public Player getFirstPlayer() {
		return firstPlayer;
	}

	public Player getSecondPlayer() {
		return secondPlayer;
	}

	public int getCurrentAge() {
		return age.getCurrentAge();
	}

	void nextAgeForTest() {
		age.nextAge();
	}
}
