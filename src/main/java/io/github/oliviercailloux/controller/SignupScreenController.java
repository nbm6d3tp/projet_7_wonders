package io.github.oliviercailloux.controller;

import io.github.oliviercailloux.MainFX;
import io.github.oliviercailloux.model.DatabaseConnection;
import io.github.oliviercailloux.utils.Function;
import io.github.oliviercailloux.view.BigTitleLabel;
import io.github.oliviercailloux.view.CustomButton;
import io.github.oliviercailloux.view.CustomLayout;
import io.github.oliviercailloux.view.CustomTextField;
import io.github.oliviercailloux.view.ReturnButton;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

/**
 * Screen which will display a formula of sign up
 */
public class SignupScreenController {

	/**
	 * Show Sign up screen
	 *
	 * @param primaryStage
	 */
	public static void showInscriptionFormula() {
		BigTitleLabel insriptionLabel = new BigTitleLabel("S'inscrire");
		TextField usernameField = CustomTextField.CustomTextFieldFactory("Votre Login", false);
		TextField passwordField = CustomTextField.CustomTextFieldFactory("Mot de passe", true);
		TextField retapPasswordField = CustomTextField.CustomTextFieldFactory("Ressaisir le mot de passe", true);

		CustomButton confirmButton = new CustomButton("Valider", event -> {
			handleSignup(usernameField.getText(), passwordField.getText(), retapPasswordField.getText());
		});

		CustomLayout layout = new CustomLayout();
		layout.getChildren().addAll(insriptionLabel, usernameField, passwordField, retapPasswordField, confirmButton);

		Scene scene = new Scene(
				ReturnButton.ReturnButtonLayoutFactory(event -> LoginScreenController.showLoginScreen(), layout),
				MainFX.getWidthWindow(), MainFX.getHeightWindow());
		MainFX.getPrimaryStage().setScene(scene);
	}

	private static void handleSignup(String playerEmail, String playerPassword1, String playerPassword2) {
		boolean isEmptyField = playerEmail.isEmpty() || playerPassword1.isEmpty() || playerPassword2.isEmpty();
		boolean isValidEmail = Function.isValidEmailFormat(playerEmail);
		boolean isSamePass = playerPassword1.equals(playerPassword2);

		Alert alertFail = new Alert(AlertType.ERROR);
		alertFail.setTitle("Erreur");
		alertFail.setHeaderText(null);
		if (isEmptyField) {
			alertFail.setContentText("Champ vide!");
			alertFail.getDialogPane().setStyle("-fx-background-color: #ffcccc; -fx-text-fill: red;");
			alertFail.showAndWait();
		} else if (!isValidEmail) {
			alertFail.setContentText("Pas sous forme email!");
			alertFail.getDialogPane().setStyle("-fx-background-color: #ffcccc; -fx-text-fill: red;");
			alertFail.showAndWait();
		} else if (!isSamePass) {
			alertFail.setContentText("Pas le même mdp!");
			alertFail.getDialogPane().setStyle("-fx-background-color: #ffcccc; -fx-text-fill: red;");
			alertFail.showAndWait();
		} else {
			try {
				DatabaseConnection.saveUser(playerEmail, playerPassword1);
				Alert alertSuccess = new Alert(AlertType.INFORMATION);
				alertSuccess.setTitle("Succès");
				alertSuccess.setContentText("Inscription avec succès!");
				alertSuccess.setHeaderText(null);
				alertSuccess.getDialogPane().setStyle("-fx-background-color: green; -fx-text-fill: green;");
				alertSuccess.showAndWait();
				LoginScreenController.showLoginScreen();
			} catch (Exception e) {
				alertFail.setContentText("Inscription échec");
				alertFail.getDialogPane().setStyle("-fx-background-color: #ffcccc; -fx-text-fill: red;");
				alertFail.showAndWait();
			}
		}
	}
}
