package io.github.oliviercailloux.controller;

import io.github.oliviercailloux.MainFX;
import io.github.oliviercailloux.view.CustomButton;
import io.github.oliviercailloux.view.CustomLayout;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

/**
 * Screen which will display a menu to navigate to different
 * screens/functionalities of the game.
 */
public class MenuScreenController {
	/**
	 * Show the menu screen
	 *
	 * @param primaryStage
	 */
	public static void showOnboardingScreen() {
		CustomButton playButton = new CustomButton("Jouer!", event -> GameScreenController.showGameScreen());
		CustomButton playHistoryP1Button = new CustomButton("Profile joueur 1",
				event -> PlayHistoryScreenController.showPlayHistoryScreen(MainFX.getUser1().getIdToken()));
		CustomButton playHistoryP2Button = new CustomButton("Profile joueur 2",
				event -> PlayHistoryScreenController.showPlayHistoryScreen(MainFX.getUser2().getIdToken()));
		CustomButton tutorialButton = new CustomButton("Tutoriel",
				event -> TutorialScreenController.showTutorialScreen());
		CustomButton deconnectionButton = new CustomButton("Déconnecter", event -> {
			MainFX.setUser1(null);
			MainFX.setUser2(null);
			LoginScreenController.showLoginScreen();
		});

		VBox buttonBox = new VBox(10);
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.getChildren().addAll(playButton, playHistoryP1Button, playHistoryP2Button, tutorialButton,
				deconnectionButton);

		CustomLayout layout = new CustomLayout();
		layout.getChildren().add(buttonBox);

		Scene scene = new Scene(layout, MainFX.getWidthWindow(), MainFX.getHeightWindow());
		MainFX.getPrimaryStage().setScene(scene);
	}
}
