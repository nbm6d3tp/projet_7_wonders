package io.github.oliviercailloux.enums;

/**
 * All the special effects of the game.
 */
public enum SpecialEffect {
	BRING_FIVE_COINS("Apporter 5 pièces"),
	ONE_COIN_PER_BROWN_BOTH_SIDES("Apporter 1 pièce par carte brune jouée des 2 joueurs"),
	TWO_COINS_PER_GREY_BOTH_SIDES("Apporter 1 pièce par carte grise jouée des 2 joueurs"),
	ONE_COIN_ONE_VP_PER_BROWN("Apporter 1 pièce et 1 point de victore par carte brune jouée"),
	ONE_COIN_ONE_VP_PER_YELLOW("Apporter 1 pièce et 1 point de victore par carte jaune jouée"),
	TWO_COINS_TWO_VP_PER_GREY("Apporter 2 pièces et 2 points de victore par carte grise jouée"),

	// Purple Cards
	ONE_VP_PER_BROWN_BOTH_SIDES("Apporter 1 point de victoire par carte marron construite des 2 joueurs"),
	TWO_VP_PER_GREY_BOTH_SIDES("Apporter 2 points de victoire par carte gris construite des 2 joueurs"),
	ONE_VP_PER_YELLOW_BOTH_SIDES("Apporter 1 point de victoire par carte jaune construite des 2 joueurs"),
	ONE_VP_PER_GREEN_BOTH_SIDES("Apporter  1 point de victoire par carte scientifique construite des 2 joueurs"),
	ONE_VP_PER_RED_BOTH_SIDES("Apporter 1 point de victoire par carte rouge construite des 2 joueurs"),
	ONE_VP_PER_NEGATIVE_MILITARY_TOKEN_BOTH_SIDES(
			"Apporter 1 point de victoire par jeton militaire négatif des 2 joueurs"),
	ONE_VP_PER_BROWN_PURPLE_GREY(
			"Apporter 1 point de victoire par carte violette construite et 1 point de victoire par carte gris et 1 point de victoire par carte marron du joueur"),
	ONE_VP_PER_BLUE_BOTH_SIDES("Apporter 1 point de victoire par carte bleue construite des 2 joueurs"),
	ONE_VP_PER_STAGE_BUILD_BOTH_SIDES("Apporter 1 point de victoire par étape construite des merveilles des 2 joueurs");

	public final String name;

	private SpecialEffect(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}
