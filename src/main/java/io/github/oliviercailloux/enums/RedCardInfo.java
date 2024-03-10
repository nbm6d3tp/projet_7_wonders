package io.github.oliviercailloux.enums;

/**
 * Informations of all the red cards: name, cost and number of conflict points
 * that the card will bring to the player. The name and cost of the card is
 * stocked in the enum CardInfo.
 */
public enum RedCardInfo {
	// Age 1
	PALISSADE(CardInfo.PALISSADE, 1), CASERNE(CardInfo.CASERNE, 1),

	// Age 2
	MURAILLE(CardInfo.MURAILLE, 2), PLACE_DARMES(CardInfo.PLACE_DARMES, 2),

	// Age 3
	FORTIFICATIONS(CardInfo.FORTIFICATIONS, 3), CIRQUE(CardInfo.CIRQUE, 3);

	public final CardInfo cardInfo;
	public final int conflictPoints;

	private RedCardInfo(CardInfo cardInfo, int conflictPoints) {
		this.cardInfo = cardInfo;
		this.conflictPoints = conflictPoints;
	}
}
