package io.github.oliviercailloux.view;

import io.github.oliviercailloux.controller.LoginScreenController;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

/**
 * Background of the interface of the game
 */
public class CustomBackground {
	public static Background BackgroundFactory() {
		Image backgroundImage = new Image(
				LoginScreenController.class.getClassLoader().getResourceAsStream("wonders.png"));
		BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		return new Background(background);
	}
}
