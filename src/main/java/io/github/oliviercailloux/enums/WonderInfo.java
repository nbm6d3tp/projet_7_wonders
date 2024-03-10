package io.github.oliviercailloux.enums;

import io.github.oliviercailloux.game.Stage;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Contain list of all the wonders of the game and their informations: name,
 * default resource, cost (list of resources) and reward (number of victory
 * points) of each stage.
 */
public enum WonderInfo {
	TAE("Le temple d'Artemis à Éphese", GreyResource.PAPYRUS,
			new Stage(new Resource[] { BrownResource.CLAY, BrownResource.CLAY }, 5),
			new Stage(new Resource[] { BrownResource.STONE, BrownResource.STONE }, 10),
			new Stage(new Resource[] { BrownResource.WOOD, BrownResource.ORE }, 5)),
	PA("Le phare d’Alexandrie", GreyResource.GLASS,
			new Stage(new Resource[] { BrownResource.CLAY, BrownResource.CLAY }, 7),
			new Stage(new Resource[] { BrownResource.WOOD, BrownResource.WOOD }, 5),
			new Stage(new Resource[] { BrownResource.STONE, BrownResource.CLAY }, 8)),
	SZO("La statue de Zeus à Olympie", BrownResource.ORE,
			new Stage(new Resource[] { GreyResource.LOOM, GreyResource.PAPYRUS }, 5),
			new Stage(new Resource[] { BrownResource.CLAY, BrownResource.WOOD }, 8),
			new Stage(new Resource[] { BrownResource.STONE, BrownResource.STONE }, 7)),
	MH("Le mausolée d’Halicarnasse", BrownResource.STONE,
			new Stage(new Resource[] { GreyResource.GLASS, GreyResource.LOOM }, 5),
			new Stage(new Resource[] { BrownResource.WOOD, BrownResource.CLAY }, 8),
			new Stage(new Resource[] { BrownResource.STONE, BrownResource.WOOD }, 7)),

	CR("Le Colosse de Rhodes", BrownResource.ORE,
			new Stage(new Resource[] { BrownResource.WOOD, BrownResource.WOOD }, 3),
			new Stage(new Resource[] { BrownResource.CLAY, BrownResource.CLAY }, 10),
			new Stage(new Resource[] { BrownResource.ORE, BrownResource.ORE }, 7)),

	JSB("Les Jardins Suspendus de Babylone ", BrownResource.CLAY,
			new Stage(new Resource[] { BrownResource.CLAY, BrownResource.CLAY }, 7),
			new Stage(new Resource[] { BrownResource.WOOD, BrownResource.WOOD }, 6),
			new Stage(new Resource[] { BrownResource.ORE, BrownResource.ORE }, 7)),

	GPG("La grande Pyramide de Gizeh", BrownResource.STONE,
			new Stage(new Resource[] { BrownResource.STONE, BrownResource.STONE }, 4),
			new Stage(new Resource[] { BrownResource.WOOD, BrownResource.WOOD }, 6),
			new Stage(new Resource[] { BrownResource.ORE, BrownResource.ORE }, 10));

	public final String name;
	public final Resource defaultResource;
	public Set<Stage> stages;

	private WonderInfo(String name, Resource defaultResource, Stage... stages) {
		this.name = name;
		this.defaultResource = defaultResource;
		this.stages = new LinkedHashSet<>();
		for (Stage stage : stages) {
			this.stages.add(stage);
		}
	}

	public static int size() {
		return WonderInfo.values().length;
	}

	/**
	 * This function helps for the function getTwoRandomWonders() in the class
	 * Wonder.
	 *
	 * @param  index if the wonder
	 * @return       a wonder basing on the provided index.
	 */
	public static WonderInfo getWonderByIndex(int index) {
		if (index >= size() || index < 0) {
			throw new IllegalArgumentException();
		}
		return WonderInfo.values()[index];
	}
}
