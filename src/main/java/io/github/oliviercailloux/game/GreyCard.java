package io.github.oliviercailloux.game;

import io.github.oliviercailloux.enums.GreyCardInfo;
import io.github.oliviercailloux.enums.GreyResource;
import io.github.oliviercailloux.utils.Function;
import java.util.List;

/**
 * Class implemented the functionalities and properties of a grey card.
 */
public class GreyCard extends Card {
	/**
	 * Grey resources that the card will bring
	 */
	private List<GreyResource> resources;

	public GreyCard(GreyCardInfo greyCardInfo) {
		super(greyCardInfo.cardInfo);
		resources = greyCardInfo.resources;
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
	}

	/**
	 * @return grey resources that the card will bring
	 */
	public List<GreyResource> getResources() {
		return resources;
	}

	@Override
	public String getEffectsInString() {
		return Function.changeArrayToReadableString(resources);
	}
}
