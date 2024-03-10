package io.github.oliviercailloux.view;

import io.github.oliviercailloux.game.BlueCard;
import io.github.oliviercailloux.game.BrownCard;
import io.github.oliviercailloux.game.Card;
import io.github.oliviercailloux.game.GreyCard;
import io.github.oliviercailloux.game.PurpleCard;
import io.github.oliviercailloux.game.RedCard;
import io.github.oliviercailloux.game.YellowCard;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

/**
 * View of a card in the game, containing the name of the card and have the same
 * color as the card. It has black color of the card is unusable
 */
public class CardView extends javafx.scene.layout.StackPane {
	private final Card cardInfo;
	private boolean canBePlayed;

	public CardView(Card cardInfo, boolean canBePlayed) {
		this.cardInfo = cardInfo;
		this.canBePlayed = canBePlayed;
		Rectangle cardBackground = new Rectangle(140, 90);
		if (!canBePlayed) {
			cardBackground.setFill(javafx.scene.paint.Color.BLACK);
			getChildren().add(cardBackground);
		} else {
			Label cardName = new Label(this.cardInfo.getName());
			cardName.setTextFill(javafx.scene.paint.Color.WHITE);
			VBox infoBox = new VBox(5);
			infoBox.setPadding(new Insets(5));
			if (cardInfo instanceof BrownCard) {
				cardBackground.setFill(javafx.scene.paint.Color.BROWN);
			} else if (cardInfo instanceof GreyCard) {
				cardBackground.setFill(javafx.scene.paint.Color.GREY);
			} else if (cardInfo instanceof YellowCard) {
				cardBackground.setFill(javafx.scene.paint.Color.YELLOW);
			} else if (cardInfo instanceof PurpleCard) {
				cardBackground.setFill(javafx.scene.paint.Color.PURPLE);
			} else if (cardInfo instanceof RedCard) {
				cardBackground.setFill(javafx.scene.paint.Color.RED);
			} else if (cardInfo instanceof BlueCard) {
				cardBackground.setFill(javafx.scene.paint.Color.BLUE);
			} else {
				cardBackground.setFill(javafx.scene.paint.Color.GREEN);
			}
			infoBox.getChildren().add(cardName);
			getChildren().addAll(cardBackground, infoBox);
		}
	}

	public boolean isCanBePlayed() {
		return canBePlayed;
	}

}
