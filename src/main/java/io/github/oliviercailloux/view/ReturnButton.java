package io.github.oliviercailloux.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

/**
 * The styled return button dedicated for the application
 */
public class ReturnButton {
	public static StackPane ReturnButtonLayoutFactory(EventHandler<ActionEvent> value, Node layout) {
		Button returnButton = new Button("Return");
		returnButton.setStyle(
				"-fx-pref-width: 70px; -fx-pref-height: 30px; -fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 14px;");
		returnButton.setOnAction(value);
		StackPane root = new StackPane(layout, returnButton);
		StackPane.setAlignment(returnButton, Pos.TOP_LEFT);
		return root;
	}
}
