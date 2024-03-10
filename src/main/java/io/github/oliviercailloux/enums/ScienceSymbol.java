package io.github.oliviercailloux.enums;

public enum ScienceSymbol {
	BOOK("Livre"), RULER("Regle"), TRAP("Piege");

	public final String name;

	private ScienceSymbol(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}
