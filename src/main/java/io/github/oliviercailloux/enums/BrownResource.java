package io.github.oliviercailloux.enums;

public enum BrownResource implements Resource {
	CLAY("Argile"), STONE("Pierre"), WOOD("Bois"), ORE("Minerai");

	public final String name;

	private BrownResource(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}
