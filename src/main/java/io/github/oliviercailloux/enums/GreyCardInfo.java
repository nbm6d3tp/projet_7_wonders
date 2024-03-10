package io.github.oliviercailloux.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * Informations of all the grey cards: name, cost and grey resources
 * that the card will bring to the player. The name and cost of the card is
 * stocked in the enum CardInfo.
 */
public enum GreyCardInfo {
	PRESSE(CardInfo.PRESSE, GreyResource.PAPYRUS), METIER_A_TISSER(CardInfo.METIER_A_TISSER, GreyResource.LOOM),
	VERRERIE(CardInfo.VERRERIE, GreyResource.GLASS);

	public final CardInfo cardInfo;
	public final List<GreyResource> resources;

	private GreyCardInfo(CardInfo cardInfo, GreyResource[] resources) {
		this.cardInfo = cardInfo;
		this.resources = new ArrayList<>();
		for (GreyResource greyResource : resources) {
			this.resources.add(greyResource);
		}
	}

	private GreyCardInfo(CardInfo cardInfo, GreyResource resources) {
		this.cardInfo = cardInfo;
		this.resources = new ArrayList<>();
		this.resources.add(resources);
	}
}
