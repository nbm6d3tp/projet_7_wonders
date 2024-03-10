package io.github.oliviercailloux.game;

import io.github.oliviercailloux.enums.BlueCardInfo;
import io.github.oliviercailloux.enums.BrownCardInfo;
import io.github.oliviercailloux.enums.GreenCardInfo;
import io.github.oliviercailloux.enums.GreyCardInfo;
import io.github.oliviercailloux.enums.PurpleCardInfo;
import io.github.oliviercailloux.enums.RedCardInfo;
import io.github.oliviercailloux.enums.YellowCardInfo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to control the ages of the game and the set of cards to be used in each
 * age.
 */
class Age {
	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory.getLogger(Age.class);
	private int currentAge;

	/**
	 * Each age has his own deck of cards. The attribut indicate the available deck
	 * of cards of the current age.
	 */
	private List<Card> cards;

	Age() {
		currentAge = 1;
		cards = new ArrayList<>();
		initCards();
	}

	/**
	 * Initiate the relevant deck of cards basing on the current age.
	 */
	void initCards() {
		LOGGER.info("Initier les cartes");
		cards.removeAll(cards);
		if (currentAge == 1) {
			// Brown cards
			cards.add(new BrownCard(BrownCardInfo.CHANTIER));
			cards.add(new BrownCard(BrownCardInfo.CAVITE));
			cards.add(new BrownCard(BrownCardInfo.BASSIN_ARGILEUX));
			cards.add(new BrownCard(BrownCardInfo.FILON));

			// Grey cards
			cards.add(new GreyCard(GreyCardInfo.METIER_A_TISSER));
			cards.add(new GreyCard(GreyCardInfo.VERRERIE));
			cards.add(new GreyCard(GreyCardInfo.PRESSE));

			// Blue cards
			cards.add(new BlueCard(BlueCardInfo.PRETEUR_SUR_GAGES));
			cards.add(new BlueCard(BlueCardInfo.AUTEL));

			// Red cards
			cards.add(new RedCard(RedCardInfo.PALISSADE));
			cards.add(new RedCard(RedCardInfo.CASERNE));

			// Green cards
			cards.add(new GreenCard(GreenCardInfo.OFFICINE));
			cards.add(new GreenCard(GreenCardInfo.ATELIER));
			cards.add(new GreenCard(GreenCardInfo.SCRIPTORIUM));
		} else if (currentAge == 2) {
			// Brown cards
			cards.add(new BrownCard(BrownCardInfo.SCIERIE));
			cards.add(new BrownCard(BrownCardInfo.CARRIERE));
			cards.add(new BrownCard(BrownCardInfo.BRIQUETERIE));
			cards.add(new BrownCard(BrownCardInfo.FONDERIE));

			// Blue cards
			cards.add(new BlueCard(BlueCardInfo.AQUEDUC));
			cards.add(new BlueCard(BlueCardInfo.TEMPLE));

			// Red cards
			cards.add(new RedCard(RedCardInfo.MURAILLE));
			cards.add(new RedCard(RedCardInfo.PLACE_DARMES));

			// Green cards
			cards.add(new GreenCard(GreenCardInfo.DISPENSAIRE));
			cards.add(new GreenCard(GreenCardInfo.BIBLIOTHEQUE));
			cards.add(new GreenCard(GreenCardInfo.ECOLE));

			// Yellow cards
			cards.add(new YellowCard(YellowCardInfo.TAVERNE));
			cards.add(new YellowCard(YellowCardInfo.BAZAR));
			cards.add(new YellowCard(YellowCardInfo.VIGNOBLE));
		} else {
			// Blue cards
			cards.add(new BlueCard(BlueCardInfo.PANTHEON));
			cards.add(new BlueCard(BlueCardInfo.JARDIN));
			cards.add(new BlueCard(BlueCardInfo.HOTEL_DE_VILLE));

			// Red cards
			cards.add(new RedCard(RedCardInfo.FORTIFICATIONS));
			cards.add(new RedCard(RedCardInfo.CIRQUE));

			// Yellow cards
			cards.add(new YellowCard(YellowCardInfo.PHARE));
			cards.add(new YellowCard(YellowCardInfo.PORT));
			cards.add(new YellowCard(YellowCardInfo.CHAMBRE_DE_COMMERCE));

			// Green cards
			cards.add(new GreenCard(GreenCardInfo.OBSERVATOIRE));
			cards.add(new GreenCard(GreenCardInfo.UNIVERSITE));
			cards.add(new GreenCard(GreenCardInfo.ACADEMIE));

			// Purple card
			List<Card> purpleCards = new ArrayList<>();
			purpleCards.add(new PurpleCard(PurpleCardInfo.GUILDE_DES_COMMERCANTS));
			purpleCards.add(new PurpleCard(PurpleCardInfo.GUILDE_DES_PHILOSOPHES));
			purpleCards.add(new PurpleCard(PurpleCardInfo.GUILDE_DES_TRAVAILLEURS));
			purpleCards.add(new PurpleCard(PurpleCardInfo.GUILDE_DES_ESPIONS));
			purpleCards.add(new PurpleCard(PurpleCardInfo.GUILDE_DES_BATISSEURS));
			purpleCards.add(new PurpleCard(PurpleCardInfo.GUILDE_DES_ARTISANS));
			purpleCards.add(new PurpleCard(PurpleCardInfo.GUILDE_DES_STRATEGES));
			purpleCards.add(new PurpleCard(PurpleCardInfo.GUILDE_DES_MAGISTRATS));
			purpleCards.add(new PurpleCard(PurpleCardInfo.GUILDE_DES_AMATEURS));
			Collections.shuffle(purpleCards);
			List<Card> ThreeRandomPurpleCards = purpleCards.subList(0, 3);
			cards.addAll(ThreeRandomPurpleCards);
		}
	}

	/**
	 * Start next age
	 */
	void nextAge() {
		LOGGER.info("Age suivant ");
		LOGGER.info("Age avant: " + currentAge);
		if (currentAge < 3) {
			currentAge++;
			initCards();

		}
		LOGGER.info("Age suivant:  " + currentAge);
	}

	/**
	 * @return the military token that a player can recieve when win the military
	 *         conflict at the end of each age. This won military token is different
	 *         basing on the current age. It's 1 at the first age, 3 et the second
	 *         age and 5 et the last age.
	 */
	int getCurrentMilitaryTokenWhenWin() {

		if (currentAge == 1) {
			return 1;
		} else if (currentAge == 2) {
			return 3;
		} else {
			return 5;
		}
	}

	List<Card> getCards() {
		return cards;
	}

	int getCurrentAge() {

		return currentAge;
	}
}
