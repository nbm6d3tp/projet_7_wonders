package io.github.oliviercailloux.game;

import io.github.oliviercailloux.enums.Resource;
import io.github.oliviercailloux.utils.Function;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class describing the hand of a player, implemented in detail logic of some
 * actions of the players like play, discard, buildStage, ... One player can
 * have one and only hand and reciprocally.
 */
public class Hand {

	private static final Logger LOGGER = LoggerFactory.getLogger(Hand.class);

	private List<Card> cardsOnHand;
	private Player player;
	/**
	 * To check if we can build a stage or not, basing on the
	 * player's resources, money and adversary's resources.
	 */
	private boolean statusToBuildStage;

	/**
	 * To check the needed amount of money to pay to other players to build a stage,
	 * basing on the player's resources, money and adversary's resources.
	 */
	private int amountToPayToBuildStage;

	Hand(Player player) {
		cardsOnHand = new ArrayList<>();
		this.player = player;
	}

	/**
	 * Set the cards on the hand of a player. Used when players have to swap their
	 * cards to each other or when the game distributes the cards to each player.
	 *
	 * @param set of cards to be set
	 */
	void setCardsOnHand(List<Card> cards) {
		cardsOnHand = cards;
		for (Card card : cardsOnHand) {
			card.verifyStatusToPlay(player);
		}
		verifyStatusToBuildStage();
	}

	/**
	 * Choose a card to play on the player's hand. Pay to the bank or the adversary
	 * if needs. Add the effects of the card into the Player's resources. After
	 * that, get rid of the card.
	 *
	 * @param  card                      a card on the hand
	 * @throws IllegalArgumentExceptionl if the chosen card is not playable
	 *                                   (statusToPlay of the card is false)
	 *
	 */
	void play(Card card) {
		if (cardsOnHand.size() == 1) {
			return;
		}
		if (!card.getStatusToPlay()) {
			throw new IllegalArgumentException("Card cannot be played.");
		}
		removeCardFromHand(card);
		player.addToPlayedCardsList(card);
		if (card.isCostInCoins()) {
			Game.takeMoney(player, card.getAmountToPayToPlay());
		} else {
			Game.transferMoney(player, player.getAdversary(), card.getAmountToPayToPlay());
		}
		card.bringEffectsTo(player);

		LOGGER.info("La carte  était jouée!", card.getName());

	}

	/**
	 * Discard a card from the player's hand and adds 3
	 * coins to the player's coins.
	 *
	 * @param card a card on the hand
	 */
	void discard(Card card) {
		if (cardsOnHand.size() == 1) {
			return;
		}
		LOGGER.info("La carte {} était détruite pour 3 pièces!", card.getName());

		removeCardFromHand(card);
		Game.payMoney(player, 3);
	}

	/**
	 *
	 * Build a stage of the player's Wonder using a card from his hand.
	 *
	 * @param  card                     a card on the hand
	 *
	 * @throws IllegalArgumentException if the card cannot be used to build a stage
	 *                                  (statusToBuildStage is false)
	 */

	void buildStage(Card card) {
		if (cardsOnHand.size() == 1) {
			return;
		}
		if (!player.getStatusToBuildStage()) {
			throw new IllegalArgumentException("Can't use this card to build a stage.");
		}
		Game.transferMoney(player, player.getAdversary(), player.getAmountToPayToBuildStage());
		player.addToVictoryPoints(player.getWonder().getRewardNextStage());
		player.getWonder().unlockNewStage();
		removeCardFromHand(card);

		LOGGER.info("La carte  était utilisée pour construire une étape de la Merveille!", card.getName());

	}

	/**
	 * Set statusToBuildStage and amountToPayToBuildStage base on the resources of
	 * the player, resources of his adversary and the demanded cost of the stage
	 * that he wants to build.
	 *
	 * A. if the last stage of the wonder is unlocked
	 * => statusToBuildStage=false and amountToPayToBuildStage=0
	 *
	 * B. if the last stage of the wonder haven't been unlocked yet:
	 *
	 * B.I if the player has enough resources
	 * => statusToBuildStage=true and amountToPayToBuildStage=0
	 *
	 * B.II if the player doesn't have enough resources:
	 *
	 * B.II.1 if his adversary doesn't have necessary resources
	 * => statusToBuildStage=false and amountToPayToBuildStage=0
	 *
	 * B.II.2 if his adversary have necessary resources:
	 *
	 * B.II.2.a if the player doesn't have enough money to purchase the resources
	 * from his adversary
	 * => statusToBuildStage=false and
	 * amountToPayToBuildStage=2*numberOfLackedResources
	 *
	 * B.II.2.b if the player have enough money to purchase the resources from his
	 * adversary
	 * => statusToBuildStage=true and
	 * amountToPayToBuildStage=2*numberOfLackedResources
	 *
	 * @param player
	 */
	void verifyStatusToBuildStage() {
		amountToPayToBuildStage = 0;
		if (player.getWonder().isLastStageUnlocked()) {
			statusToBuildStage = false;
		} else {
			List<Resource> playerResources = Function.cloneListResources(player.getResources());
			List<Resource> adversaryResources = Function.cloneListResources(player.getAdversary().getResources());
			int amount = 0;
			for (Resource resource : player.getWonder().getNeededCostToBuildNextStage()) {
				if (playerResources.remove(resource)) {
					continue;
				}
				if (adversaryResources.remove(resource)) {
					amount += 2;
				} else {
					statusToBuildStage = false;
					amountToPayToBuildStage = 0;
					LOGGER.info("Impossible de construire l'étape : ressources insuffisantes.");
					return;
				}
			}
			amountToPayToBuildStage = amount;
			if (player.getCoins() >= amountToPayToBuildStage) {
				statusToBuildStage = true;
			} else {
				statusToBuildStage = false;
			}
		}
	}

	List<Card> getCardsOnHand() {
		return cardsOnHand;
	}

	void removeCardFromHand(Card card) {
		cardsOnHand.remove(card);
	}

	int getSizeCardsOnHand() {
		return cardsOnHand.size();
	}

	Card getCard(int index) {
		return cardsOnHand.get(index);
	}

	boolean getStatusToBuildStage() {
		return statusToBuildStage;
	}

	int getAmountToPayToBuildStage() {
		return amountToPayToBuildStage;
	}
}
