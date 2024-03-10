package io.github.oliviercailloux.game;

import io.github.oliviercailloux.enums.CardInfo;
import io.github.oliviercailloux.enums.Resource;
import io.github.oliviercailloux.utils.Function;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

/**
 * Class implemented the fundamental functionalities and properties (cost, name,
 * status to be played, amount to pay to play) of a card.
 */
public abstract class Card {

	/**
	 * Fundamental informations of a card: cost, name.
	 */

	private CardInfo cardInfo;

	/**
	 * To check if this card can be played or not, basing on being on hand of which
	 * player (basing on the player's resources, money and adversary's resources)
	 * and the cost of the card.
	 */
	private boolean statusToPlay;

	/**
	 * To check the needed amount of money to pay to other players to play the card,
	 * basing on being on hand of which player (basing on the player's resources,
	 * money and
	 * adversary's resources) and the cost of the card.
	 */
	private int amountToPayToPlay;

	private static final Logger LOGGER = LoggerFactory.getLogger(Card.class);
	Card(CardInfo cardInfo) {
		this.cardInfo = cardInfo;
	}

	/**
	 * Set statusToPlay and amountToPayToOtherPlayersToPlay basing on being on hand
	 * of which player (basing on the player's resources, money and adversary's
	 * resources) and the cost of the card.
	 * A. if the cost of the card is in coins:
	 *
	 * A.I. if the player has enough money to afford the cost of the cards
	 * => statusToPlay=true and amountToPayToPlay=cost of the card in coins
	 *
	 * A.II. if the player doesn't have enough money to afford the cost of the cards
	 * => statusToPlay=false and amountToPayToPlay=cost of the card in coins
	 *
	 * B. if the cost of the card is in resources:
	 *
	 * B.I. if the player has enough resources to play the card
	 * => statusToPlay=true and amountToPayToPlay=0
	 *
	 * B.II if the player doesn't have enough resources:
	 *
	 * B.II.1. if his adversary doesn't have necessary resources
	 * => statusToPlay=false and amountToPayToPlay=0
	 *
	 * B.II.2 if his adversary have necessary resources:
	 *
	 * B.II.2.a. if the player doesn't have enough money to purchase the resources
	 * from his adversary
	 * => statusToPlay=false and
	 * amountToPayToPlay=2*number of lacked resources
	 *
	 * B.II.2.b if the player have enough money to purchase the resources from his
	 * adversary
	 * => statusToPlay=true and
	 * amountToPayToPlay=2*number of lacked resources
	 *
	 * @param player
	 */
	public void verifyStatusToPlay(Player player) {
		LOGGER.info("Vérification du statut du joeur pour jouer la carte ", cardInfo.name);

		amountToPayToPlay = 0;
		if (isCostInCoins()) {
			if (player.getCoins() > getCostInCoins().get()) {
				statusToPlay = true;
			} else {
				statusToPlay = false;
			}
			amountToPayToPlay = getCostInCoins().get();
		} else {
			List<Resource> playerResources = Function.cloneListResources(player.getResources());
			List<Resource> adversaryResources = Function.cloneListResources(player.getAdversary().getResources());
			int amount = 0;
			for (Resource resource : getCostInResources().get()) {
				if (playerResources.remove(resource)) {
					continue;
				}
				if (adversaryResources.remove(resource)) {
					amount += 2;
				} else {
					statusToPlay = false;
					amountToPayToPlay = 0;
					LOGGER.info("Ressources insuffisantes pour jouer la carte ", cardInfo.name);

					return;

				}
			}
			amountToPayToPlay = amount;
			if (player.getCoins() >= amountToPayToPlay) {
				statusToPlay = true;
			} else {
				statusToPlay = false;
				LOGGER.info("Pièces insuffisantes pour jouer la carte", cardInfo.name);

			}
		}
	}

	/**
	 * To verify if the cost of the card is in coins or in resources.
	 *
	 * @return
	 */
	public boolean isCostInCoins() {
		LOGGER.info("Verification si le coût est en pièces pour la carte", cardInfo.name);

		if (cardInfo.costInCoins.isPresent()) {
			return true;
		}
		return false;
	}

	/**
	 * @return the cost of the card in resources. If the card doesn't have cost in
	 *         resources, the returned value is empty.
	 */
	public Optional<List<Resource>> getCostInResources() {
		LOGGER.info("Obtention du coût en ressources pour la carte", cardInfo.name);
		return cardInfo.costInResources;
	}

	/**
	 * @return the cost of the card in coins. If the card doesn't have cost in
	 *         coins, the returned value is empty.
	 */
	public Optional<Integer> getCostInCoins() {
		LOGGER.info("Obtention du coût en pièces pour la carte", cardInfo.name);

		return cardInfo.costInCoins;
	}

	/**
	 * Bring the effects of the card to the player. The kind of effects depend on
	 * the type of the card.
	 *
	 * @param player
	 */
	public abstract void bringEffectsTo(Player player);

	public String getCostInString() {
		if (isCostInCoins()) {
			return getCostInCoins().get() + " pieces";
		}
		return Function.changeArrayToReadableString(getCostInResources().get());
	}

	public abstract String getEffectsInString();

	public boolean getStatusToPlay() {
		return statusToPlay;
	}

	public int getAmountToPayToPlay() {
		return amountToPayToPlay;
	}

	public String getName() {
		return cardInfo.name;
	}
}
