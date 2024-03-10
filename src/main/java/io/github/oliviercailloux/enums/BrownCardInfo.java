package io.github.oliviercailloux.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * Informations of all the brown cards: name, cost and the brown resources
 * that the card will bring to the player. The name and cost of the card is
 * stocked in the enum CardInfo.
 */
public enum BrownCardInfo {
	// Age 1
	CHANTIER(CardInfo.CHANTIER, BrownResource.WOOD), CAVITE(CardInfo.CAVITE, BrownResource.STONE),
	BASSIN_ARGILEUX(CardInfo.BASSIN_ARGILEUX, BrownResource.CLAY), FILON(CardInfo.FILON, BrownResource.ORE),

	// Age 2
	SCIERIE(CardInfo.SCIERIE, new BrownResource[] { BrownResource.WOOD, BrownResource.WOOD }),
	CARRIERE(CardInfo.CARRIERE, new BrownResource[] { BrownResource.STONE, BrownResource.STONE }),
	BRIQUETERIE(CardInfo.BRIQUETERIE, new BrownResource[] { BrownResource.CLAY, BrownResource.CLAY }),
	FONDERIE(CardInfo.FONDERIE, new BrownResource[] { BrownResource.ORE, BrownResource.ORE });

	public final CardInfo cardInfo;
	public final List<BrownResource> resources;

	private BrownCardInfo(CardInfo cardInfo, BrownResource[] resources) {
		this.cardInfo = cardInfo;
		this.resources = new ArrayList<>();
		for (BrownResource brownResource : resources) {
			this.resources.add(brownResource);
		}
	}

	private BrownCardInfo(CardInfo cardInfo, BrownResource resources) {
		this.cardInfo = cardInfo;
		this.resources = new ArrayList<>();
		this.resources.add(resources);
	}

}
