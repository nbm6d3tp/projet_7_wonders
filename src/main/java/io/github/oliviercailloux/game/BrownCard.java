package io.github.oliviercailloux.game;

import io.github.oliviercailloux.enums.BrownCardInfo;
import io.github.oliviercailloux.enums.BrownResource;
import io.github.oliviercailloux.utils.Function;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Class implemented the functionalities and properties of a brown card.
 */
public class BrownCard extends Card {
	/**
	 * Brown resources that the card will bring
	 */
	private List<BrownResource> resources;
	private static final Logger LOGGER = LoggerFactory.getLogger(BrownCard.class);

	public BrownCard(BrownCardInfo brownCardInfo) {
		super(brownCardInfo.cardInfo);
		resources = brownCardInfo.resources;
	}

	/**
	 * Add the resources that the card will bring to the list of resources of the
	 * user
	 *
	 * @param player The player to apply the effects to.
	 */
	@Override
	public void bringEffectsTo(Player player) {
		player.getResources().addAll(resources);
		LOGGER.info("Effects de la carte brown appliquer au joueur  " + player.getName());

	}

	/**
	 * @return brown resources that the cards bring;
	 */
	public List<BrownResource> getResources() {
		return resources;
	}

	@Override
	public String getEffectsInString() {
		return Function.changeArrayToReadableString(resources);
	}
}
