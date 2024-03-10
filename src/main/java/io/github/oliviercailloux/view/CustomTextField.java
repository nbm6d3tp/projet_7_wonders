package io.github.oliviercailloux.view;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * The styled text field dedicated for the application
 */
public class CustomTextField {
	/**
	 *
	 * @param  title
	 * @param  isPasswordField
	 * @return                 a text field or a password field
	 */
	public static TextField CustomTextFieldFactory(String title, boolean isPasswordField) {
		TextField field;
		String style = "-fx-pref-width: 200px; -fx-max-width: 200px;";
		if (isPasswordField) {
			field = new PasswordField();
		} else {
			field = new TextField();
		}
		field.setPromptText(title);
		field.setStyle(style);
		return field;
	}
}
