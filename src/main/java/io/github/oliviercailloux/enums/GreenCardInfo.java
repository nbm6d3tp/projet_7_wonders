package io.github.oliviercailloux.enums;

/**
 * Informations of all the green cards: name, cost and the science symbol
 * that the card will bring to the player. The name and cost of the card is
 * stocked in the enum CardInfo.
 */
public enum GreenCardInfo {
	// Age 1
	OFFICINE(CardInfo.OFFICINE, ScienceSymbol.TRAP), ATELIER(CardInfo.ATELIER, ScienceSymbol.RULER),
	SCRIPTORIUM(CardInfo.SCRIPTORIUM, ScienceSymbol.BOOK),

	// Age 2
	DISPENSAIRE(CardInfo.DISPENSAIRE, ScienceSymbol.RULER), BIBLIOTHEQUE(CardInfo.BIBLIOTHEQUE, ScienceSymbol.BOOK),
	ECOLE(CardInfo.ECOLE, ScienceSymbol.TRAP),

	// Age 3
	OBSERVATOIRE(CardInfo.OBSERVATOIRE, ScienceSymbol.RULER), UNIVERSITE(CardInfo.UNIVERSITE, ScienceSymbol.BOOK),
	ACADEMIE(CardInfo.ACADEMIE, ScienceSymbol.TRAP);

	public final CardInfo cardInfo;
	public final ScienceSymbol scienceSymbol;

	private GreenCardInfo(CardInfo cardInfo, ScienceSymbol scienceSymbol) {
		this.cardInfo = cardInfo;
		this.scienceSymbol = scienceSymbol;
	}
}
