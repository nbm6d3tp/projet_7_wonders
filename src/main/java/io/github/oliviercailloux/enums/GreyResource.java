package io.github.oliviercailloux.enums;

public enum GreyResource implements Resource {
	GLASS("Verre"), LOOM("Tissu"), PAPYRUS("Papyrus");

	public final String name;

	private GreyResource(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}
