package io.github.oliviercailloux.view;

import javafx.scene.control.Label;

/**
 * The big title at Login screen and Sign up screen
 */
public class BigTitleLabel extends Label {
	public BigTitleLabel(String title) {
		setText(title);
		setStyle(
				"-fx-font-size: 35px; -fx-font-weight: bold; -fx-text-fill: gold; -fx-background-color: linear-gradient(to right, #8B008B, #EE82EE); -fx-background-radius: 10;");
	}
}
