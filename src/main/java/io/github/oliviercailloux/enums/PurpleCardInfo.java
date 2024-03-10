package io.github.oliviercailloux.enums;

/**
 * Information of all the purple cards: name, cost and the special effect
 * that the card will bring to the player. The name and cost of the card is
 * stocked in the enum CardInfo.
 */
public enum PurpleCardInfo {

	// Age 3

	GUILDE_DES_TRAVAILLEURS(CardInfo.GUILDE_DES_TRAVAILLEURS, SpecialEffect.ONE_VP_PER_BROWN_BOTH_SIDES),
	GUILDE_DES_ARTISANS(CardInfo.GUILDE_DES_ARTISANS, SpecialEffect.TWO_VP_PER_GREY_BOTH_SIDES),
	GUILDE_DES_COMMERCANTS(CardInfo.GUILDE_DES_COMMERCANTS, SpecialEffect.ONE_VP_PER_YELLOW_BOTH_SIDES),
	GUILDE_DES_PHILOSOPHES(CardInfo.GUILDE_DES_PHILOSOPHES, SpecialEffect.ONE_VP_PER_GREEN_BOTH_SIDES),
	GUILDE_DES_ESPIONS(CardInfo.GUILDE_DES_ESPIONS, SpecialEffect.ONE_VP_PER_RED_BOTH_SIDES),
	GUILDE_DES_STRATEGES(CardInfo.GUILDE_DES_STRATEGES, SpecialEffect.ONE_VP_PER_NEGATIVE_MILITARY_TOKEN_BOTH_SIDES),
	GUILDE_DES_AMATEURS(CardInfo.GUILDE_DES_AMATEURS, SpecialEffect.ONE_VP_PER_BROWN_PURPLE_GREY),
	GUILDE_DES_MAGISTRATS(CardInfo.GUILDE_DES_MAGISTRATS, SpecialEffect.ONE_VP_PER_BLUE_BOTH_SIDES),
	GUILDE_DES_BATISSEURS(CardInfo.GUILDE_DES_BATISSEURS, SpecialEffect.ONE_VP_PER_STAGE_BUILD_BOTH_SIDES);

	public final CardInfo cardInfo;
	public final SpecialEffect specialEffect;

	private PurpleCardInfo(CardInfo cardInfo, SpecialEffect specialEffect) {
		this.cardInfo = cardInfo;
		this.specialEffect = specialEffect;
	}
}
