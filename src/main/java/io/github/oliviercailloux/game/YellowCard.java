package io.github.oliviercailloux.game;

import io.github.oliviercailloux.enums.SpecialEffect;
import io.github.oliviercailloux.enums.YellowCardInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class implemented the functionalities and properties of a yellow card.
 */
public class YellowCard extends Card {
	/**
	 * Special effect that the yellow card will bring
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(YellowCard.class);

	public SpecialEffect specialEffect;

	public YellowCard(YellowCardInfo yellowCardInfo) {
		super(yellowCardInfo.cardInfo);
		specialEffect = yellowCardInfo.specialEffect;
	}

	/**
	 * Apply the special effect of the card to the player. It will call the
	 * appropriate function implemented the logic of this special effect.
	 *
	 * @param player The player to apply the effects to.
	 */
	@Override
	public void bringEffectsTo(Player player) {
		LOGGER.info("Application de l'effet spécial de la carte jaune : ", specialEffect);

		switch (specialEffect) {

		case BRING_FIVE_COINS:
			applyBringFiveCoinsEffect(player);
			break;

		case ONE_COIN_PER_BROWN_BOTH_SIDES:
			applyOneCoinPerBrownBothSidesEffect(player);
			break;

		case TWO_COINS_PER_GREY_BOTH_SIDES:
			applyTwoCoinsPerGreyBothSidesEffect(player);
			break;

		case ONE_COIN_ONE_VP_PER_BROWN:
			applyOneCoinOneVPPerBrownEffect(player);
			break;

		case ONE_COIN_ONE_VP_PER_YELLOW:
			applyOneCoinOneVPPerYellowEffect(player);
			break;

		case TWO_COINS_TWO_VP_PER_GREY:
			applyTwoCoinsTwoVPPerGreyEffect(player);
			break;

		// $CASES-OMITTED$
		default:
			LOGGER.warn("Effet spécial inconnu : ", specialEffect);
			break;
		}
	}

	/**
	 * Give the player 5 coins
	 *
	 * @param player
	 */
	void applyBringFiveCoinsEffect(Player player) {
		LOGGER.info("Application de l'effet BRING_FIVE_COINS au joueur ", player.getName());
		Game.payMoney(player, 5);
	}

	/**
	 * Give player 1 coin for each brown card having been played by the player and
	 * his adversary
	 *
	 * @param player
	 */
	void applyOneCoinPerBrownBothSidesEffect(Player player) {
		LOGGER.info("Application de l'effet ONE_COIN_PER_BROWN_BOTH_SIDES au joueur ", player.getName());
		Game.payMoney(player, player.getNumberBrownCards() + player.getAdversary().getNumberBrownCards());
	}

	/**
	 * Give player 2 coins for each grey card having been played by the player and
	 * his adversary
	 *
	 * @param player
	 */
	void applyTwoCoinsPerGreyBothSidesEffect(Player player) {
		LOGGER.info("Application de l'effet TWO_COINS_PER_GREY_BOTH_SIDES au joueur ", player.getName());

		Game.payMoney(player, (player.getNumberGreyCards() + player.getAdversary().getNumberGreyCards()) * 2);
	}

	/**
	 * Give player 1 coin and one victory point for each brown card having been
	 * played by the player
	 *
	 * @param player
	 */
	void applyOneCoinOneVPPerBrownEffect(Player player) {
		LOGGER.info("Application de l'effet ONE_COIN_ONE_VP_PER_BROWN au joueur ", player.getName());
		Game.payMoney(player, player.getNumberBrownCards());
		player.addToVictoryPoints(player.getNumberBrownCards());
	}

	/**
	 * Give player 1 coin and one victory point for each yellow card having been
	 * played by the player
	 *
	 * @param player
	 */
	void applyOneCoinOneVPPerYellowEffect(Player player) {
		LOGGER.info("Application de l'effet ONE_COIN_ONE_VP_PER_YELLOW au joueur ", player.getName());
		Game.payMoney(player, player.getNumberYellowCards());
		player.addToVictoryPoints(player.getNumberYellowCards());
	}

	/**
	 * Give player 2 coins and 2 victory points for each grey card having been
	 * played by the player
	 *
	 * @param player
	 */
	void applyTwoCoinsTwoVPPerGreyEffect(Player player) {
		LOGGER.info("Application de l'effet TWO_COINS_TWO_VP_PER_GREY au joueur ", player.getName());
		Game.payMoney(player, player.getNumberGreyCards() * 2);
		player.addToVictoryPoints(player.getNumberGreyCards() * 2);
	}

	/**
	 * @return special effect that the card will bring
	 */
	public SpecialEffect getSpecialEffect() {
		return specialEffect;
	}

	@Override
	public String getEffectsInString() {
		return specialEffect.toString();
	}
}
