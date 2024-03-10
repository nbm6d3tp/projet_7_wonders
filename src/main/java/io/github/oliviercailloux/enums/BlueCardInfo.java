package io.github.oliviercailloux.enums;

/**
 * Informations of all the blue cards: name, cost and number of victory points
 * that the card will bring to the player. The name and cost of the card is
 * stocked in the enum CardInfo.
 */
public enum BlueCardInfo {
	// Age 1
	PRETEUR_SUR_GAGES(CardInfo.PRETEUR_SUR_GAGES, 3), AUTEL(CardInfo.AUTEL, 3),

	// Age 2
	AQUEDUC(CardInfo.AQUEDUC, 5), TEMPLE(CardInfo.TEMPLE, 5),

	// Age 3
	PANTHEON(CardInfo.PANTHEON, 10), JARDIN(CardInfo.JARDIN, 10), HOTEL_DE_VILLE(CardInfo.HOTEL_DE_VILLE, 7);

	public final CardInfo cardInfo;
	public final int victoryPoints;

	private BlueCardInfo(CardInfo cardInfo, int victoryPoints) {
		this.cardInfo = cardInfo;
		this.victoryPoints = victoryPoints;
	}
}
