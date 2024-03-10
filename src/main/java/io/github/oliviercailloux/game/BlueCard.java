package io.github.oliviercailloux.game;

import io.github.oliviercailloux.enums.BlueCardInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class implemented the functionalities and properties of a blue card.
 */
public class BlueCard extends Card {

	/**
	 * Victory points that the blue card will bring.
	 */
	private int victoryPoints;
	private static final Logger LOGGER = LoggerFactory.getLogger(BlueCard.class);


	public BlueCard(BlueCardInfo blueCardInfo) {
		super(blueCardInfo.cardInfo);
		victoryPoints = blueCardInfo.victoryPoints;
	}

	/**
	 * Add the victory points that the blue card will bring to the total number of
	 * victory points of the player.
	 *
	 * @param player The player to apply the effects to.
	 */
	@Override
	public void bringEffectsTo(Player player) {

		player.addToVictoryPoints(victoryPoints);
		LOGGER.info(" Effets de la carte blue appliqués au joueur :: " + player.getName());

	}

	/**
	 * @return victory points that the card will bring.
	 */
	public int getVictoryPoints() {
		return victoryPoints;
	}

	@Override
	public String getEffectsInString() {
		return victoryPoints + " points de victoire";
	}
}
