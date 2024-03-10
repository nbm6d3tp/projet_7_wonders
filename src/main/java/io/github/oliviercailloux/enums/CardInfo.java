package io.github.oliviercailloux.enums;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Contain list of all the cards of the game, and their fundemental informations
 * like name and costs. A card can have 1 in 2 types of costs: cost in coins or
 * cost in resources. Either cost in coins or cost in resources, not both. If
 * the cost of the card is in coins, the value of costInResources is populated
 * and the value of costInResources is empty, and reciprocally.
 */
public enum CardInfo {
	/**
	 * The brown cards
	 */
	// Age 1
	CHANTIER("Chantier", null, 0), CAVITE("Cavite", null, 0), BASSIN_ARGILEUX("Bassin Argileux", null, 0),
	FILON("Filon", null, 0),

	// Age 2
	CARRIERE("Carriere", null, 1), BRIQUETERIE("Briqueterie", null, 1), FONDERIE("Fonderie", null, 1),
	SCIERIE("Scierie", null, 1),

	/**
	 * The grey cards
	 */
	// Age 1 + 2
	PRESSE("Presse", null, 0), METIER_A_TISSER("Metier à Tisser", null, 0), VERRERIE("Verrerie", null, 0),

	/**
	 * The blue cards
	 */
	// Age 1
	PRETEUR_SUR_GAGES("Preteur sur Gages", null, 0), AUTEL("Autel", null, 0),

	// Age 2
	AQUEDUC("Aqueduc", new Resource[] { BrownResource.STONE, BrownResource.WOOD }, null),
	TEMPLE("Temple", new Resource[] { BrownResource.ORE, BrownResource.CLAY }, null),

	// Age 3
	PANTHEON("Pantheon", new Resource[] { BrownResource.CLAY, BrownResource.ORE, BrownResource.WOOD }, null),
	JARDIN("Jardin",
			new Resource[] { BrownResource.WOOD, BrownResource.STONE, BrownResource.CLAY, GreyResource.PAPYRUS }, null),
	HOTEL_DE_VILLE("Hôtel de Ville", new Resource[] { BrownResource.ORE, BrownResource.STONE, GreyResource.GLASS },
			null),

	/**
	 * The red cards
	 */
	// Age 1
	PALISSADE("Palissade", new Resource[] { BrownResource.WOOD }, null),
	CASERNE("Caserne", new Resource[] { BrownResource.ORE }, null),

	// Age 2
	MURAILLE("Walls", new Resource[] { BrownResource.STONE, GreyResource.LOOM }, null),
	PLACE_DARMES("Place Armes", new Resource[] { BrownResource.CLAY, GreyResource.GLASS }, null),

	// Age 3
	CIRQUE("Cique", new Resource[] { BrownResource.STONE, BrownResource.CLAY, GreyResource.PAPYRUS }, null),
	FORTIFICATIONS("Fortifications", new Resource[] { BrownResource.ORE, BrownResource.WOOD, GreyResource.LOOM }, null),

	/**
	 * The green cards
	 */
	// Age 1
	OFFICINE("Officine", new Resource[] { GreyResource.LOOM }, null),
	ATELIER("Atelier", new Resource[] { GreyResource.GLASS }, null),
	SCRIPTORIUM("Scriptorium", new Resource[] { GreyResource.PAPYRUS }, 0),

	// Age 2
	DISPENSAIRE("Dispensaire", new Resource[] { GreyResource.GLASS, BrownResource.CLAY }, null),
	BIBLIOTHEQUE("Bibliotheque", new Resource[] { GreyResource.LOOM, BrownResource.ORE }, null),
	ECOLE("Ecole", new Resource[] { GreyResource.PAPYRUS, BrownResource.STONE }, 0),

	// Age 3
	OBSERVATOIRE("Observatoire", new Resource[] { GreyResource.GLASS, GreyResource.LOOM, BrownResource.WOOD }, null),
	UNIVERSITE("Universite", new Resource[] { GreyResource.PAPYRUS, GreyResource.GLASS, BrownResource.WOOD }, null),
	ACADEMIE("Academie", new Resource[] { GreyResource.LOOM, GreyResource.PAPYRUS, BrownResource.STONE }, null),

	/**
	 * The yellow cards
	 */
	// Age 3
	TAVERNE("Tavern", null, 0), BAZAR("Bazar", null, 1),
	VIGNOBLE("Vignoble", new Resource[] { BrownResource.CLAY }, null),
	PORT("Port", new Resource[] { GreyResource.LOOM, BrownResource.ORE }, null),
	PHARE("Phare", new Resource[] { GreyResource.GLASS, BrownResource.STONE }, null),
	CHAMBRE_DE_COMMERCE("Chambre de commerce", new Resource[] { GreyResource.PAPYRUS, BrownResource.CLAY }, null),

	/**
	 * The purple cards
	 */

	// Age 3
	GUILDE_DES_TRAVAILLEURS("Guilde des Travailleurs",
			new Resource[] { BrownResource.CLAY, BrownResource.STONE, BrownResource.WOOD }, null),
	GUILDE_DES_ARTISANS("Guilde des Artisans",
			new Resource[] { BrownResource.STONE, BrownResource.STONE, BrownResource.ORE }, null),
	GUILDE_DES_COMMERCANTS("Guilde des Commercants",
			new Resource[] { GreyResource.GLASS, GreyResource.LOOM, GreyResource.PAPYRUS }, null),
	GUILDE_DES_PHILOSOPHES("Guilde des Philosophes",
			new Resource[] { GreyResource.PAPYRUS, BrownResource.CLAY, GreyResource.LOOM }, null),
	GUILDE_DES_ESPIONS("Guilde des Espions",
			new Resource[] { BrownResource.CLAY, BrownResource.CLAY, GreyResource.GLASS }, null),
	GUILDE_DES_STRATEGES("Guilde des Strateges",
			new Resource[] { BrownResource.STONE, BrownResource.ORE, BrownResource.ORE }, null),
	GUILDE_DES_AMATEURS("Guilde des Amateurs",
			new Resource[] { BrownResource.WOOD, BrownResource.WOOD, GreyResource.PAPYRUS }, null),
	GUILDE_DES_MAGISTRATS("Guilde des Magistrats",
			new Resource[] { GreyResource.LOOM, BrownResource.STONE, BrownResource.WOOD }, null),
	GUILDE_DES_BATISSEURS("Guilde des Batisseurs",
			new Resource[] { GreyResource.GLASS, BrownResource.STONE, BrownResource.CLAY }, null);

	public final String name;
	/**
	 * The cost of the card in resources if has
	 */
	public final Optional<List<Resource>> costInResources;
	/**
	 * The cost of the card in coins if has
	 */
	public final Optional<Integer> costInCoins;

	private CardInfo(String name, Resource[] costInResources, Integer costInCoins) {
		this.name = name;
		if (costInResources != null) {
			List<Resource> resourcesList = new ArrayList<>();
			for (Resource resource : costInResources) {
				resourcesList.add(resource);
			}
			this.costInResources = Optional.of(resourcesList);
		} else {
			this.costInResources = Optional.ofNullable(null);
		}
		if (costInCoins != null) {
			this.costInCoins = Optional.of(costInCoins);
		} else {
			this.costInCoins = Optional.ofNullable(null);
		}
	}
}
