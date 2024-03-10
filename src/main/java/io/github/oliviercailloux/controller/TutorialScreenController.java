package io.github.oliviercailloux.controller;

import io.github.oliviercailloux.MainFX;
import io.github.oliviercailloux.view.ReturnButton;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;

/**
 * Screen which will display the tutorial of the game 7 Wonders
 */
public class TutorialScreenController {
	private static final int NUM_IMAGES = 12;

	public static void showTutorialScreen() {
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setFitToWidth(true);
		scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
		FlowPane imagePane = new FlowPane();
		imagePane.setAlignment(Pos.CENTER);
		imagePane.setHgap(10);
		imagePane.setVgap(10);
		for (int i = 1; i <= NUM_IMAGES; i++) {
			String imagePath = "rule" + i + ".png"; // Chemin vers les images
			Image image = new Image(TutorialScreenController.class.getClassLoader().getResourceAsStream(imagePath));
			ImageView imageView = new ImageView(image);
			imageView.setPreserveRatio(true);
			imageView.setFitWidth(MainFX.getWidthWindow());
			imageView.setFitHeight(MainFX.getHeightWindow());
			imagePane.getChildren().add(imageView);
		}
		scrollPane.setContent(imagePane);
		StackPane root = ReturnButton.ReturnButtonLayoutFactory(event -> MenuScreenController.showOnboardingScreen(),
				scrollPane);
		Scene scene = new Scene(root, MainFX.getWidthWindow(), MainFX.getHeightWindow());
		MainFX.getPrimaryStage().setScene(scene);
	}
}
