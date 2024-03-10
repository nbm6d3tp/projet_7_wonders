package io.github.oliviercailloux.game;

import io.github.oliviercailloux.enums.Resource;
import io.github.oliviercailloux.enums.ScienceSymbol;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * Class representing a player in a card game. Each player has a name, a hand
 * of cards, a wonder, a list of resources, an adversary, a number of coins, a
 * number of victory points and a number of conflict points.
 */
public class Player {
	private String name;
	private Hand hand;
	private Wonder wonder;
	private List<Resource> resources;
	private List<ScienceSymbol> scienceSymbols;
	private Player adversary;

	private static final Logger LOGGER = LoggerFactory.getLogger(Player.class);

	/**
	 * List of military tokens, for exemple [1, -1, +3, -1]
	 */
	private List<Integer> militaryTokens;
	private int conflictPoints;
	private int coins;
	private int victoryPoints;

	/**
	 * List of played cards of the player
	 */
	private List<Card> playedCards;

	/**
	 * @param name
	 */
	public Player(String name, Wonder wonder) {
		super();
		this.name = name;
		playedCards = new ArrayList<>();
		resources = new ArrayList<>();
		scienceSymbols = new ArrayList<>();
		hand = new Hand(this);
		militaryTokens = new ArrayList<>();
		conflictPoints = 0;
		coins = 3;
		victoryPoints = 0;
		addToResources(wonder.getDefaultResource());
		this.wonder = wonder;
	}

	/**
	 * Get the players's current victory points
	 */
	public int getVictoryPoints() {
		return victoryPoints;
	}

	public void addToVictoryPoints(int i) {
		victoryPoints += i;
	}

	/**
	 * @param amount to pay to the bank
	 */
	public void payMoney(int amount) {
		LOGGER.info("Le Joeur paye la banque .", name, amount);

		if (amount < 0) {
			throw new IllegalArgumentException("Invalid amount.");
		}
		if (coins < amount) {
			throw new IllegalStateException("Not enough coins.");
		}
		coins -= amount;
	}

	/**
	 * @param amount of money to take back
	 */
	public void takeMoney(int amount) {

		LOGGER.info("Joeur Prend l'argent", name, amount);

		if (amount < 0) {
			throw new IllegalArgumentException("Invalid amount.");
		}
		coins += amount;
	}

	/**
	 * Add a military token to the list of military tokens
	 *
	 * @param  token
	 * @throws IllegalArgumentException token is different from -1, 1, 3, 5
	 */
	public void addMilitaryTokens(int token) {
		LOGGER.info("Le joeur ajoute military token", name, token);

		if (token != -1 && token != 1 && token != 3 && token != 5) {
			throw new IllegalArgumentException();
		}
		militaryTokens.add(token);
	}

	/**
	 * @return total victory points at the end of the game, after having calculated
	 *         the current victory points with all the military tokens and
	 *         victory points from science symbols
	 */
	public int getTotalVictoryPoints() {
		LOGGER.info("Calculer le total des points de victoir" + victoryPoints);
		int totalVictoryPoints = 0;
		for (Integer token : militaryTokens) {
			totalVictoryPoints += token;
		}
		totalVictoryPoints += victoryPoints;
		return totalVictoryPoints + this.getVictoryPointScienceSymbol() + coins / 3;
	}

	/**
	 * Adds the specified number of conflict points to the player.
	 *
	 * @param  conflictPointsToAdd      the number of conflict points to add
	 * @throws IllegalArgumentException if conflictPointsToAdd is less than 0
	 */
	public void addToConflictPoints(int conflictPointsToAdd) {
		LOGGER.info("Adding a conflict point for player: {}" + conflictPoints);
		if (conflictPointsToAdd < 0) {
			throw new IllegalArgumentException("The number of conflict points to add cannot be negative.");
		}

		conflictPoints += conflictPointsToAdd;
	}

	/**
	 * @param  index
	 * @throws some  exceptions if card not found or index is invalid...
	 * @return
	 */
	public Card getCardOnHand(int index) {
		LOGGER.info("Les cartes dans la main du joeur ");
		if (index < 0 || index >= hand.getSizeCardsOnHand()) {
			throw new IllegalArgumentException("Invalid index.");
		}
		return hand.getCard(index);
	}

	/**
	 * Sets the list of cards on hand for the player.
	 *
	 * @param  cards                    the list of cards to set
	 * @throws IllegalArgumentException if the list of cards is empty or null
	 */
	public void setCardsOnHand(List<Card> cards) {
		if (cards == null || cards.isEmpty()) {
			throw new IllegalArgumentException("List of cards cannot be empty or null.");
		}
		hand.setCardsOnHand(cards);
	}

	/**
	 * Add a science symbol to the list of science symbols
	 *
	 * @param effect
	 */
	public void addScienceSymbol(ScienceSymbol scienceSymbol) {
		scienceSymbols.add(scienceSymbol);
	}

	/**
	 * Returns the count of brown cards played by the player.
	 *
	 * @return the count of played brown cards
	 */
	public int getNumberBrownCards() {
		int count = 0;
		for (Card card : playedCards) {
			if (card instanceof BrownCard) {
				count++;
			}
		}
		return count;
	}

	/**
	 * Returns the count of greencards played by the player.
	 *
	 * @return the count of played green cards
	 */
	public int getNumberGreenCards() {
		int count = 0;
		for (Card card : playedCards) {
			if (card instanceof GreenCard) {
				count++;
			}
		}
		return count;
	}

	/**
	 * Returns the count of redcards played by the player.
	 *
	 * @return the count of played red cards
	 */
	public int getNumberRedCards() {
		int count = 0;
		for (Card card : playedCards) {
			if (card instanceof RedCard) {
				count++;
			}
		}
		return count;
	}

	/**
	 * Returns the count of purlplecards played by the player.
	 *
	 * @return the count of played purplecards
	 */
	public int getNumberPurpleCards() {
		int count = 0;
		for (Card card : playedCards) {
			if (card instanceof PurpleCard) {
				count++;
			}
		}
		return count;
	}

	/**
	 * Returns the count of bluecards played by the player.
	 *
	 * @return the count of played bluecards
	 */
	public int getNumberBlueCards() {
		int count = 0;
		for (Card card : playedCards) {
			if (card instanceof BlueCard) {
				count++;
			}
		}
		return count;
	}

	/**
	 * Returns the count of grey cards played by the player.
	 *
	 * @return the count of played brown cards
	 */
	public int getNumberGreyCards() {
		int count = 0;
		for (Card card : playedCards) {
			if (card instanceof GreyCard) {
				count++;
			}
		}
		return count;
	}

	/**
	 * Returns the count of yellow cards played by the player.
	 *
	 * @return the count of played brown cards
	 */
	public int getNumberYellowCards() {
		int count = 0;
		for (Card card : playedCards) {
			if (card instanceof YellowCard) {
				count++;
			}
		}
		return count;
	}

	/**
	 * @return number of negative military tokens of the player
	 */
	public int getNumberOfNegativeMilitaryTokens() {
		int counter = 0;
		for (Integer integer : militaryTokens) {
			if (integer < 0) {
				counter++;
			}
		}
		return counter;
	}

	/**
	 * Add some resources to the Player's current resources
	 */
	public void addToResources(Resource resource) {
		resources.add(resource);
	}

	/**
	 * Add a list of resources to the Player's current resources
	 */
	public void addAllToResources(List<Resource> resourcesList) {
		resources.addAll(resourcesList);
	}

	public int getSizeCardsOnHand() {
		return hand.getSizeCardsOnHand();
	}

	public List<Card> getCardsOnHand() {
		return hand.getCardsOnHand();
	}

	public void verifyStatusToBuildStage() {
		hand.verifyStatusToBuildStage();
	}

	public boolean getStatusToBuildStage() {
		return hand.getStatusToBuildStage();
	}

	public int getAmountToPayToBuildStage() {
		return hand.getAmountToPayToBuildStage();
	}

	/**
	 * @param  card                     a card on hand
	 * @throws IllegalArgumentException if the card is null or not on hand.
	 */
	public void play(Card card) {
		if (!getCardsOnHand().contains(card) || card == null) {
			throw new IllegalArgumentException();
		}
		hand.play(card);
	}

	/**
	 * @param  card                     a card on hand
	 * @throws IllegalArgumentException if the card is null or not on hand.
	 */
	public void buildStage(Card card) {
		LOGGER.info("Joueur Construit un stage");
		if (!getCardsOnHand().contains(card) || card == null) {
			throw new IllegalArgumentException();
		}
		if (!getStatusToBuildStage()) {
			throw new IllegalArgumentException("Can't use this card to build a stage.");
		}
		hand.buildStage(card);
	}

	/**
	 * @param  card                     a card on hand
	 * @throws IllegalArgumentException if the card is null or not on hand.
	 */
	public void discard(Card card) {
		LOGGER.info("le joeur discard une carte");
		if (!getCardsOnHand().contains(card) || card == null) {
			throw new IllegalArgumentException();
		}
		hand.discard(card);
	}

	public List<Resource> getResources() {
		return resources;
	}

	public Wonder getWonder() {
		return wonder;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Player getAdversary() {
		return adversary;
	}

	public int getCoins() {
		return coins;
	}

	public void setAdversary(Player adversary) {
		this.adversary = adversary;
	}

	public List<Integer> getMilitaryTokens() {
		return militaryTokens;
	}

	public int getConflictPoints() {
		return conflictPoints;
	}

	/**
	 * Calculates the total victory points earned from science symbols.
	 *
	 * @return The total victory points earned from science symbols.
	 */
	public int getVictoryPointScienceSymbol() {
		int numberTrap = 0;
		int numberRuler = 0;
		int numberBook = 0;
		int groupOfThree = 0;
		int sum = 0;

		for (ScienceSymbol symbol : scienceSymbols) {
			if (symbol == ScienceSymbol.RULER) {
				numberRuler++;
			} else if (symbol == ScienceSymbol.BOOK) {
				numberBook++;
			} else if (symbol == ScienceSymbol.TRAP) {
				numberTrap++;
			}
		}
		if (numberTrap >= 4 && numberRuler >= 4 && numberBook >= 4) {
			groupOfThree += 28;
		} else if (numberTrap >= 3 && numberRuler >= 3 && numberBook >= 3) {
			groupOfThree += 21;
		} else if (numberTrap >= 2 && numberRuler >= 2 && numberBook >= 2) {
			groupOfThree += 14;
		} else if (numberTrap >= 1 && numberRuler >= 1 && numberBook >= 1) {
			groupOfThree += 7;
		}
		sum = numberBook * numberBook + numberRuler * numberRuler + numberTrap * numberTrap;
		return sum + groupOfThree;
	}

	/**
	 * Add a card to played cards list
	 *
	 * @param card
	 */
	public void addToPlayedCardsList(Card card) {
		playedCards.add(card);
	}

	public List<ScienceSymbol> getScienceSymbols() {
		return scienceSymbols;
	}

	void addToPlayedCards(Card card) {
		playedCards.add(card);
	}

	public List<Card> getPlayedCards() {
		return playedCards;
	}
}
