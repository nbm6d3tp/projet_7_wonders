package io.github.oliviercailloux.view;

import io.github.oliviercailloux.game.BlueCard;
import io.github.oliviercailloux.game.BrownCard;
import io.github.oliviercailloux.game.Card;
import io.github.oliviercailloux.game.GreyCard;
import io.github.oliviercailloux.game.Player;
import io.github.oliviercailloux.game.PurpleCard;
import io.github.oliviercailloux.game.RedCard;
import io.github.oliviercailloux.game.YellowCard;
import javafx.geometry.Insets;
import javafx.scene.control.Label;

/**
 * View displaying the list of played cards of a player. Useful for players when
 * they play
 * some cards having special effects relating to played cards
 */
public class PlayedCardsView extends javafx.scene.layout.FlowPane {
	private Player player;

	public PlayedCardsView(Player player) {
		this.player = player;
		setHgap(5);
		setVgap(5);
		setPrefWrapLength(200);
		String backgroundColor = "-fx-background-color: white;";
		setStyle(backgroundColor);
		refreshView();
	}

	/**
	 * Refresh the view displaying the list of played cards of the player (after a
	 * card is played)
	 */
	public void refreshView() {
		getChildren().clear();
		for (Card playedCard : player.getPlayedCards()) {
			Label playedCardLabel = new Label(playedCard.getName());
			playedCardLabel.setPadding(new Insets(5));
			if (playedCard instanceof BrownCard) {
				playedCardLabel.setStyle("-fx-background-color: brown;");
			} else if (playedCard instanceof GreyCard) {
				playedCardLabel.setStyle("-fx-background-color: grey;");
			} else if (playedCard instanceof YellowCard) {
				playedCardLabel.setStyle("-fx-background-color: yellow;");
			} else if (playedCard instanceof PurpleCard) {
				playedCardLabel.setStyle("-fx-background-color: purple;");
			} else if (playedCard instanceof RedCard) {
				playedCardLabel.setStyle("-fx-background-color: red;");
			} else if (playedCard instanceof BlueCard) {
				playedCardLabel.setStyle("-fx-background-color: blue;");
			} else {
				playedCardLabel.setStyle("-fx-background-color: green;");
			}
			getChildren().add(playedCardLabel);
		}
	}
}
