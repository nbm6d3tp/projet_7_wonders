package io.github.oliviercailloux.enums;

/**
 * Informations of all the yellow cards: name, cost and the special effect
 * that the card will bring to the player. The name and cost of the card is
 * stocked in the enum CardInfo.
 */
public enum YellowCardInfo {
	TAVERNE(CardInfo.TAVERNE, SpecialEffect.BRING_FIVE_COINS),
	VIGNOBLE(CardInfo.VIGNOBLE, SpecialEffect.ONE_COIN_PER_BROWN_BOTH_SIDES),
	BAZAR(CardInfo.BAZAR, SpecialEffect.TWO_COINS_PER_GREY_BOTH_SIDES),
	PORT(CardInfo.PORT, SpecialEffect.ONE_COIN_ONE_VP_PER_BROWN),
	PHARE(CardInfo.PHARE, SpecialEffect.ONE_COIN_ONE_VP_PER_YELLOW),
	CHAMBRE_DE_COMMERCE(CardInfo.CHAMBRE_DE_COMMERCE, SpecialEffect.TWO_COINS_TWO_VP_PER_GREY);

	public final CardInfo cardInfo;
	public final SpecialEffect specialEffect;

	private YellowCardInfo(CardInfo cardInfo, SpecialEffect specialEffect) {
		this.cardInfo = cardInfo;
		this.specialEffect = specialEffect;
	}
}
