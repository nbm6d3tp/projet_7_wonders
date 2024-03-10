package io.github.oliviercailloux.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 * The styled button dedicated for the application
 */
public class CustomButton extends Button {
	public CustomButton(String title, EventHandler<ActionEvent> value) {
		setText(title);
		setOnAction(value);
		setStyle(
				"-fx-pref-width: 200px; -fx-pref-height: 40px; -fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 16px;");
	}
}
