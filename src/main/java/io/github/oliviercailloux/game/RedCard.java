package io.github.oliviercailloux.game;

import io.github.oliviercailloux.enums.RedCardInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class implemented the functionalities and properties of a red card.
 */
public class RedCard extends Card {
	/**
	 * Conflict points that the red card will bring.
	 */
	private int conflictPoints;
	private static final Logger LOGGER = LoggerFactory.getLogger(RedCard.class);

	public RedCard(RedCardInfo redCardInfo) {
		super(redCardInfo.cardInfo);
		conflictPoints = redCardInfo.conflictPoints;
	}

	/**
	 * Add the conflict points that the red card will bring to the total number of
	 * conflict points of the player.
	 *
	 * @param player The player to apply the effects to.
	 */
	@Override
	public void bringEffectsTo(Player player) {
		player.addToConflictPoints(conflictPoints);
	}

	/**
	 * @return conflict points that the card will bring.
	 */
	public int getConflictPoints() {
		return conflictPoints;
	}

	@Override
	public String getEffectsInString() {
		return conflictPoints + " points de conflicts";
	}
}
