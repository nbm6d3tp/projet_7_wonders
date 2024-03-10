package io.github.oliviercailloux.game;

import io.github.oliviercailloux.enums.GreenCardInfo;
import io.github.oliviercailloux.enums.ScienceSymbol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class implemented the functionalities and properties of a green card.
 */
public class GreenCard extends Card {
	private static final Logger LOGGER = LoggerFactory.getLogger(GreenCard.class);

	/**
	 * Science symbol that the green card will bring.
	 */
	public ScienceSymbol scienceSymbol;

	/**
	 * Constructs a GreenCard object with the provided GreenCardInfo.
	 *
	 * @param greencardInfo The GreenCardInfo containing the card information and
	 *                      effect.
	 */
	public GreenCard(GreenCardInfo greencardInfo) {
		super(greencardInfo.cardInfo);
		scienceSymbol = greencardInfo.scienceSymbol;
	}

	/**
	 * Add the science symbol to the list of science symbols of the player
	 *
	 * @param player The player to apply the effects to.
	 */
	@Override
	public void bringEffectsTo(Player player) {
		player.addScienceSymbol(scienceSymbol);
		LOGGER.info("Effets de la carte Green appliqués au joueur : " + player.getName());

	}

	/**
	 * @return science symbol that the card will bring.
	 */
	public ScienceSymbol getScienceSymbol() {
		return scienceSymbol;
	}

	@Override
	public String getEffectsInString() {
		return scienceSymbol.toString();
	}

}
