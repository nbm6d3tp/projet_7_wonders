package io.github.oliviercailloux.controller;

import io.github.oliviercailloux.MainFX;
import io.github.oliviercailloux.model.DatabaseConnection;
import io.github.oliviercailloux.utils.Function;
import io.github.oliviercailloux.view.BigTitleLabel;
import io.github.oliviercailloux.view.CustomButton;
import io.github.oliviercailloux.view.CustomLayout;
import io.github.oliviercailloux.view.CustomTextField;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * Screen which will display a formula of authentification
 */
public class LoginScreenController {
	/**
	 * Show Login screen
	 *
	 * @param primaryStage
	 */
	public static void showLoginScreen() {
		BigTitleLabel player1Label = new BigTitleLabel("Joueur 1");
		BigTitleLabel player2Label = new BigTitleLabel("Joueur 2");

		TextField player1LoginField = CustomTextField.CustomTextFieldFactory("Votre Login", false);
		TextField player1PasswordField = CustomTextField.CustomTextFieldFactory("Mot de passe", true);

		TextField player2LoginField = CustomTextField.CustomTextFieldFactory("Votre Login", false);
		TextField player2PasswordField = CustomTextField.CustomTextFieldFactory("Mot de passe", true);

		CustomButton loginButton = new CustomButton("Login", event -> {
			String player1Login = player1LoginField.getText();
			String player1Password = player1PasswordField.getText();
			String player2Login = player2LoginField.getText();
			String player2Password = player2PasswordField.getText();
			handleLogin(player1Login, player1Password, player2Login, player2Password);
		});

		CustomButton signupButton = new CustomButton("Inscription", event -> {
			SignupScreenController.showInscriptionFormula();
		});

		VBox buttonBox = new VBox(10);
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.getChildren().addAll(loginButton, signupButton);

		CustomLayout layout = new CustomLayout();

		layout.getChildren().addAll(player1Label, player1LoginField, player1PasswordField, player2Label,
				player2LoginField, player2PasswordField, buttonBox);

		Scene scene = new Scene(layout, MainFX.getWidthWindow(), MainFX.getHeightWindow());
		MainFX.getPrimaryStage().setScene(scene);
	}

	/**
	 * Function handling the event when the user tap the button of confirmation. If
	 * there are some empty fields, of if the email doesn't have the form of
	 * "a@b.c", or of the info of authentification of an user is wrong, there will
	 * be an alert of error displaying and precising the error.
	 *
	 * @param player1Login
	 * @param player1Password
	 * @param player2Login
	 * @param player2Password
	 * @param primaryStage
	 */
	private static void handleLogin(String player1Login, String player1Password, String player2Login,
			String player2Password) {
		boolean isEmptyField = player1Login.isEmpty() || player1Password.isEmpty() || player2Login.isEmpty()
				|| player2Password.isEmpty();
		boolean isValidEmail = Function.isValidEmailFormat(player1Login) && Function.isValidEmailFormat(player2Login);

		Alert alert = new Alert(AlertType.ERROR);
		if (isEmptyField || !isValidEmail) {
			String errorMessage = "";
			if (isEmptyField) {
				errorMessage = "Un des champs n'est pas bien rempli";
			} else {
				errorMessage = "Login ne n'est pas sous bon forme 'a@b.c'";
			}
			alert.setTitle("Erreur");
			alert.setHeaderText(null);
			alert.setContentText(errorMessage);
			alert.getDialogPane().setStyle("-fx-background-color: #ffcccc; -fx-text-fill: red;");
			alert.showAndWait();
		} else {
			try {
				MainFX.setUser1(DatabaseConnection.getUser(player1Login, player1Password).get());
				MainFX.setUser2(DatabaseConnection.getUser(player2Login, player2Password).get());
				if (MainFX.getUser1() == null || MainFX.getUser2() == null) {
					alert.setTitle("Erreur");
					alert.setHeaderText(null);
					alert.setContentText("Connection échec");
					alert.getDialogPane().setStyle("-fx-background-color: #ffcccc; -fx-text-fill: red;");
					alert.showAndWait();
				} else {
					MenuScreenController.showOnboardingScreen();
				}
			} catch (Exception e) {
				alert.setTitle("Erreur");
				alert.setHeaderText(null);
				alert.setContentText("Connection échec");
				alert.getDialogPane().setStyle("-fx-background-color: #ffcccc; -fx-text-fill: red;");
				alert.showAndWait();
			}
		}
	}
}
