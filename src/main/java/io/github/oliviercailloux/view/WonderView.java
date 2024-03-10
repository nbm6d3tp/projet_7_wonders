package io.github.oliviercailloux.view;

import io.github.oliviercailloux.game.Stage;
import io.github.oliviercailloux.game.Wonder;
import javafx.geometry.Insets;
import javafx.scene.control.Label;

/**
 * View to display all the informations of a wonder during the game
 */
public class WonderView extends javafx.scene.layout.VBox {
	private final Wonder wonder;

	public WonderView(Wonder wonder) {
		this.wonder = wonder;
		setMinSize(300, 200);
		setMaxSize(300, 200);
		String backgroundColor = "-fx-background-color: white;";
		setStyle(backgroundColor);
		setPadding(new Insets(5));
		refreshView();
	}

	/**
	 * Refresh the view displaying the wonder(after the player build a stage)
	 */
	public void refreshView() {
		getChildren().clear();
		Label wonderName = new Label("Nom: " + wonder.getName());
		Label defaultResourceWonder = new Label("Ressource par défaut: " + wonder.getDefaultResource().toString());
		getChildren().addAll(wonderName, defaultResourceWonder);
		int numberStage = 1;
		for (Stage stage : wonder.getStages()) {
			getChildren().add(new Label("- Étape " + numberStage + ": " + stage.toString()
					+ (wonder.getLastUnlockedStage() >= numberStage ? "*" : "")));
			numberStage++;
		}
	}

}
