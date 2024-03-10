package io.github.oliviercailloux.view;

import io.github.oliviercailloux.game.Player;
import io.github.oliviercailloux.utils.Function;
import javafx.scene.control.Label;

/**
 * View to display all the informations of a player during the game
 */
public class InfoPlayerView extends javafx.scene.layout.VBox {
	private Player player;

	public InfoPlayerView(Player player) {
		this.player = player;
		setSpacing(10);
		refreshView();
		setMaxWidth(300);
		setMinWidth(300);
	}

	/**
	 * Refresh the view displaying the informations of the player (after the
	 * state of the game changed/a card is played/...)
	 */
	public void refreshView() {
		getChildren().clear();
		Label coinsPlayer = new Label("Pièces: " + player.getCoins());
		Label VPPlayer = new Label("Points de victoire: " + player.getVictoryPoints());
		Label conflictPointsPlayer = new Label("Points de conflict: " + player.getConflictPoints());
		Label militaryTokensPlayer = new Label("Jetons militaires: " + player.getMilitaryTokens());
		Label resourcesPlayer = new Label("Ressources: " + Function.changeArrayToReadableString(player.getResources()));
		resourcesPlayer.setWrapText(true);
		Label scienceSymmbolsPlayer = new Label(
				"Sciences symboles: " + Function.changeArrayToReadableString(player.getScienceSymbols()));
		scienceSymmbolsPlayer.setWrapText(true);
		getChildren().addAll(coinsPlayer, VPPlayer, conflictPointsPlayer, militaryTokensPlayer, resourcesPlayer,
				scienceSymmbolsPlayer);
	}

	public Player getPlayer() {
		return player;
	}
}
