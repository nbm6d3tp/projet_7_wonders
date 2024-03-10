package io.github.oliviercailloux.view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;

/**
 * The dialog displayed in the middle of the game screen, display all the
 * status/logs of the game; detailed informations of a card; ...
 */
public class DialogView extends javafx.scene.layout.VBox {
	public DialogView() {
		setMinSize(200, 300);
		setMaxSize(200, 300);
		String backgroundColor = "-fx-background-color: white;";
		setStyle(backgroundColor);
		setPadding(new Insets(5));
		getChildren().add(new Label("Jeu commence!"));
	}
}
