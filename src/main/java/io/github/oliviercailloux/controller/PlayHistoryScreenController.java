package io.github.oliviercailloux.controller;

import io.github.oliviercailloux.MainFX;
import io.github.oliviercailloux.model.DatabaseConnection;
import io.github.oliviercailloux.model.GameResult;
import io.github.oliviercailloux.view.ReturnButton;
import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * Screen which will display all the history of all the played games of an user.
 */
public class PlayHistoryScreenController {
	public static void showPlayHistoryScreen(String idToken) {
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setFitToWidth(true);
		scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

		VBox resultsBox = new VBox(50);
		resultsBox.setPadding(new Insets(20));

		List<GameResult> gameResults = DatabaseConnection.getPlayHistories(idToken);
		if (gameResults != null) {
			for (GameResult gameResult : gameResults) {
				HBox resultBox = new HBox(50);

				VBox resultPlayer1 = createResultPlayerBox(gameResult.getDate(), gameResult.getNamePlayer1(),
						gameResult.getVPPlayer1(), gameResult.getConflictPointsPlayer1(), gameResult.getCoinsPlayer1(),
						gameResult.getWonderPlayer1(), gameResult.getMilitaryTokensPlayer1(),
						gameResult.getResourcesPlayer1(), gameResult.getScienceSymbolsPlayer1());

				VBox resultPlayer2 = createResultPlayerBox(null, gameResult.getNamePlayer2(), gameResult.getVPPlayer2(),
						gameResult.getConflictPointsPlayer2(), gameResult.getCoinsPlayer2(),
						gameResult.getWonderPlayer2(), gameResult.getMilitaryTokensPlayer2(),
						gameResult.getResourcesPlayer2(), gameResult.getScienceSymbolsPlayer2());

				resultBox.setAlignment(Pos.CENTER);
				resultBox.getChildren().addAll(resultPlayer1, resultPlayer2);
				resultsBox.getChildren().add(resultBox);
			}
		} else {
			Label noHistoryLabel = new Label("Pas encore d'histoire de jouer");
			noHistoryLabel.setFont(Font.font("Arial", 18));
			resultsBox.getChildren().add(noHistoryLabel);
		}

		scrollPane.setContent(resultsBox);
		scrollPane.setPadding(new Insets(20));
		StackPane root = ReturnButton.ReturnButtonLayoutFactory(event -> MenuScreenController.showOnboardingScreen(),
				scrollPane);

		Scene scene = new Scene(root, MainFX.getWidthWindow(), MainFX.getHeightWindow());
		MainFX.getPrimaryStage().setScene(scene);
	}

	private static VBox createResultPlayerBox(String date, String playerName, int vp, int conflictPoints, int coins,
			String wonder, String militaryTokens, String resources, String scienceSymbols) {
		VBox resultPlayerBox = new VBox(10);
		resultPlayerBox.setAlignment(Pos.CENTER);

		Label dateLabel = new Label(date != null ? "Date: " + date : "");
		dateLabel.setFont(Font.font("Arial", 14));
		resultPlayerBox.getChildren().add(dateLabel);

		Label playerNameLabel = new Label(playerName);
		playerNameLabel.setFont(Font.font("Arial", 16));
		Label vpLabel = new Label("Points de victoire: " + vp);
		Label conflictPointsLabel = new Label("Points de conflict: " + conflictPoints);
		Label coinsLabel = new Label("Pièces: " + coins);
		Label wonderLabel = new Label("Merveille: " + wonder);
		Label militaryTokensLabel = new Label("Jetons militaires: " + militaryTokens);
		Label resourcesLabel = new Label("Ressources: " + resources);
		Label scienceSymbolsLabel = new Label("Sciences symbols: " + scienceSymbols);

		resultPlayerBox.getChildren().addAll(playerNameLabel, vpLabel, conflictPointsLabel, coinsLabel, wonderLabel,
				militaryTokensLabel, resourcesLabel, scienceSymbolsLabel);

		return resultPlayerBox;
	}
}
