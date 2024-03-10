package io.github.oliviercailloux.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

/**
 * The common layout of all the screens in the application
 */
public class CustomLayout extends VBox {
	public CustomLayout() {
		setSpacing(10);
		setAlignment(Pos.CENTER);
		setPadding(new Insets(10));
		setBackground(CustomBackground.BackgroundFactory());
	}
}
