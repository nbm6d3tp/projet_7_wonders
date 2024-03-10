package io.github.oliviercailloux.model;

/**
 * Model of a game result
 */
public class GameResult {
	private String namePlayer1;
	private String namePlayer2;
	private int VPPlayer1;
	private int VPPlayer2;
	private int conflictPointsPlayer1;
	private int conflictPointsPlayer2;
	private int coinsPlayer1;
	private int coinsPlayer2;
	private String wonderPlayer1;
	private String wonderPlayer2;
	private String militaryTokensPlayer1;
	private String militaryTokensPlayer2;
	private String resourcesPlayer1;
	private String resourcesPlayer2;
	private String scienceSymbolsPlayer1;
	private String scienceSymbolsPlayer2;
	private String date;

	public GameResult(String namePlayer1, String namePlayer2, int vPPlayer1, int vPPlayer2, int conflictPointsPlayer1,
			int conflictPointsPlayer2, int coinsPlayer1, int coinsPlayer2, String wonderPlayer1, String wonderPlayer2,
			String militaryTokensPlayer1, String militaryTokensPlayer2, String resourcesPlayer1,
			String resourcesPlayer2, String scienceSymbolsPlayer1, String scienceSymbolsPlayer2, String date) {
		super();
		this.namePlayer1 = namePlayer1;
		this.namePlayer2 = namePlayer2;
		VPPlayer1 = vPPlayer1;
		VPPlayer2 = vPPlayer2;
		this.conflictPointsPlayer1 = conflictPointsPlayer1;
		this.conflictPointsPlayer2 = conflictPointsPlayer2;
		this.coinsPlayer1 = coinsPlayer1;
		this.coinsPlayer2 = coinsPlayer2;
		this.wonderPlayer1 = wonderPlayer1;
		this.wonderPlayer2 = wonderPlayer2;
		this.militaryTokensPlayer1 = militaryTokensPlayer1;
		this.militaryTokensPlayer2 = militaryTokensPlayer2;
		this.resourcesPlayer1 = resourcesPlayer1;
		this.resourcesPlayer2 = resourcesPlayer2;
		this.scienceSymbolsPlayer1 = scienceSymbolsPlayer1;
		this.scienceSymbolsPlayer2 = scienceSymbolsPlayer2;
		this.date = date;
	}

	@Override
	public String toString() {
		return "PlayHistory [namePlayer1=" + namePlayer1 + ", namePlayer2=" + namePlayer2 + ", VPPlayer1=" + VPPlayer1
				+ ", VPPlayer2=" + VPPlayer2 + ", conflictPointsPlayer1=" + conflictPointsPlayer1
				+ ", conflictPointsPlayer2=" + conflictPointsPlayer2 + ", coinsPlayer1=" + coinsPlayer1
				+ ", coinsPlayer2=" + coinsPlayer2 + ", wonderPlayer1=" + wonderPlayer1 + ", wonderPlayer2="
				+ wonderPlayer2 + ", militaryTokensPlayer1=" + militaryTokensPlayer1 + ", militaryTokensPlayer2="
				+ militaryTokensPlayer2 + ", resourcesPlayer1=" + resourcesPlayer1 + ", resourcesPlayer2="
				+ resourcesPlayer2 + ", scienceSymbolsPlayer1=" + scienceSymbolsPlayer1 + ", scienceSymbolsPlayer2="
				+ scienceSymbolsPlayer2 + "]";
	}

	public String getNamePlayer1() {
		return namePlayer1;
	}

	public String getNamePlayer2() {
		return namePlayer2;
	}

	public int getVPPlayer1() {
		return VPPlayer1;
	}

	public int getVPPlayer2() {
		return VPPlayer2;
	}

	public int getConflictPointsPlayer1() {
		return conflictPointsPlayer1;
	}

	public int getConflictPointsPlayer2() {
		return conflictPointsPlayer2;
	}

	public int getCoinsPlayer1() {
		return coinsPlayer1;
	}

	public int getCoinsPlayer2() {
		return coinsPlayer2;
	}

	public String getWonderPlayer1() {
		return wonderPlayer1;
	}

	public String getWonderPlayer2() {
		return wonderPlayer2;
	}

	public String getMilitaryTokensPlayer1() {
		return militaryTokensPlayer1;
	}

	public String getMilitaryTokensPlayer2() {
		return militaryTokensPlayer2;
	}

	public String getResourcesPlayer1() {
		return resourcesPlayer1;
	}

	public String getResourcesPlayer2() {
		return resourcesPlayer2;
	}

	public String getScienceSymbolsPlayer1() {
		return scienceSymbolsPlayer1;
	}

	public String getScienceSymbolsPlayer2() {
		return scienceSymbolsPlayer2;
	}

	public String getDate() {
		return date;
	}
}
