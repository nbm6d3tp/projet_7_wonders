package io.github.oliviercailloux.view;

import io.github.oliviercailloux.MainFX;
import io.github.oliviercailloux.controller.GameScreenController;
import io.github.oliviercailloux.controller.MenuScreenController;
import io.github.oliviercailloux.game.Card;
import io.github.oliviercailloux.game.Player;
import io.github.oliviercailloux.model.DatabaseConnection;
import io.github.oliviercailloux.model.GameResult;
import io.github.oliviercailloux.utils.Function;
import java.time.Instant;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Display all the cards on hand of a player + Handle the actions of
 * players (play/discard/use to build stage a card, ...)
 */
public class HandView extends javafx.scene.layout.VBox {
	/**
	 * Player who owns the hand
	 */
	private Player player;

	public HandView(Player player) {
		this.player = player;
		setSpacing(5);
		setAlignment(Pos.CENTER);
		refreshView();
	}

	/**
	 * Refresh the view displaying the list of cards on hand a the player (after the
	 * state of the game changed/the list of cards of the player changed/...)
	 */
	public void refreshView() {
		getChildren().clear();
		for (int i = 0; i < player.getCardsOnHand().size(); i++) {
			Card card = player.getCardOnHand(i);
			CardView cardView = new CardView(card, GameScreenController.getGame().getPlayerInTurn().equals(player)
					&& player.getSizeCardsOnHand() != 1);
			if (GameScreenController.getGame().getPlayerInTurn().equals(player)
					&& !GameScreenController.getGame().isGameEnd() && cardView.isCanBePlayed()) {
				cardView.setOnMouseClicked(event -> {
					GameScreenController.getDialog().getChildren().clear();
					Label cardName = new Label(card.getName());
					Label cardCosts = new Label("Prix: " + card.getCostInString());
					Label cardEffects = new Label("Effets: " + card.getEffectsInString());
					cardCosts.setWrapText(true);
					cardEffects.setWrapText(true);
					GameScreenController.getDialog().getChildren().addAll(cardName, cardEffects, cardCosts);
					if (card.getStatusToPlay()) {
						Button playButton = new Button("Jouer avec " + card.getAmountToPayToPlay() + " pièce (s)");
						playButton.setMaxWidth(200);
						playButton.setStyle("-fx-alignment: center;");
						playButton.setOnAction(playEvent -> {
							play(card);
						});
						GameScreenController.getDialog().getChildren().add(playButton);
					}
					if (player.getStatusToBuildStage()) {
						Button buildStageButton = new Button(
								"Construire étape avec " + player.getAmountToPayToBuildStage() + " pièce (s)");
						buildStageButton.setMaxWidth(200);
						buildStageButton.setStyle("-fx-alignment: center;");
						buildStageButton.setOnAction(buildEvent -> {
							buildStage(card);
						});
						GameScreenController.getDialog().getChildren().add(buildStageButton);
					}
					Button discardButton = new Button("Jeter pour 3 pièces");
					discardButton.setMaxWidth(200);
					discardButton.setStyle("-fx-alignment: center;");
					discardButton.setOnAction(discardEvent -> {
						discard(card);
					});
					GameScreenController.getDialog().getChildren().add(discardButton);
				});
			}
			getChildren().add(cardView);
		}
	}

	/**
	 * Control and update the views of the screen when the player plays a card
	 *
	 * @param card
	 */
	void play(Card card) {
		player.play(card);
		GameScreenController.getDialog().getChildren().clear();
		if (GameScreenController.getGame().getFirstPlayer().getSizeCardsOnHand() == 1
				&& GameScreenController.getGame().getSecondPlayer().getSizeCardsOnHand() == 1) {
			startWar();
			if (GameScreenController.getGame().getCurrentAge() == 3) {
				endGame();
			} else {
				nextAge();
			}
		}
		GameScreenController.getGame().incrementSwapCounter();
		GameScreenController.refreshView();
	}

	/**
	 * Control and update the views of the screen when the player uses a card to
	 * build stage
	 *
	 * @param card
	 */
	void buildStage(Card card) {
		player.buildStage(card);
		GameScreenController.getDialog().getChildren().clear();
		if (GameScreenController.getGame().getFirstPlayer().getSizeCardsOnHand() == 1
				&& GameScreenController.getGame().getSecondPlayer().getSizeCardsOnHand() == 1) {
			startWar();
			if (GameScreenController.getGame().getCurrentAge() == 3) {
				endGame();
			} else {
				nextAge();
			}
		}
		GameScreenController.getGame().incrementSwapCounter();
		GameScreenController.refreshView();
	}

	/**
	 * Control and update the views of the screen when the player discards a card
	 *
	 * @param card
	 */
	void discard(Card card) {
		player.discard(card);
		GameScreenController.getDialog().getChildren().clear();
		if (GameScreenController.getGame().getFirstPlayer().getSizeCardsOnHand() == 1
				&& GameScreenController.getGame().getSecondPlayer().getSizeCardsOnHand() == 1) {
			startWar();
			if (GameScreenController.getGame().getCurrentAge() == 3) {
				endGame();
			} else {
				nextAge();
			}
		}
		GameScreenController.getGame().incrementSwapCounter();
		GameScreenController.refreshView();
	}

	/**
	 * Control and update the views of the screen when the war start
	 */
	void startWar() {
		GameScreenController.getDialog().getChildren().clear();
		GameScreenController.getDialog().getChildren().add(new Label("La guerre commence!!!"));
		GameScreenController.getDialog().getChildren().add(new Label(GameScreenController.getGame().startWar()));
	}

	/**
	 * Control and update the views of the screen when the game is ended
	 */
	void endGame() {
		GameScreenController.getDialog().getChildren().add(new Label("Fin du jeu..."));
		Label resultPlayer1 = new Label("Total points de victoire de joueur 1 est "
				+ GameScreenController.getGame().getFirstPlayer().getTotalVictoryPoints());
		resultPlayer1.setWrapText(true);
		GameScreenController.getDialog().getChildren().add(resultPlayer1);
		Label resultPlayer2 = new Label("Total points de victoire de joueur 2 est "
				+ GameScreenController.getGame().getSecondPlayer().getTotalVictoryPoints());
		resultPlayer2.setWrapText(true);
		GameScreenController.getDialog().getChildren().add(resultPlayer2);
		GameScreenController.getDialog().getChildren().add(new Label(GameScreenController.getGame().endGame()));

		Player player1 = GameScreenController.getGame().getFirstPlayer();
		Player player2 = GameScreenController.getGame().getSecondPlayer();
		GameResult gameResult = new GameResult(player1.getName(), player2.getName(), player1.getTotalVictoryPoints(),
				player2.getTotalVictoryPoints(), player1.getConflictPoints(), player2.getConflictPoints(),
				player1.getCoins(), player2.getCoins(), player1.getWonder().getName(), player2.getWonder().getName(),
				player1.getMilitaryTokens().toString(), player2.getMilitaryTokens().toString(),
				Function.changeArrayToReadableString(player1.getResources()),
				Function.changeArrayToReadableString(player2.getResources()),
				Function.changeArrayToReadableString(player1.getScienceSymbols()),
				Function.changeArrayToReadableString(player2.getScienceSymbols()),
				Function.givenInstant_whenUsingDateTimeFormatter_thenFormat(Instant.now()));
		Button returnOnboardingScreenButton = new Button("Retourner à l'accueil");
		returnOnboardingScreenButton.setMaxWidth(200);
		returnOnboardingScreenButton.setStyle("-fx-alignment: center;");
		returnOnboardingScreenButton.setOnAction(event -> {
			MenuScreenController.showOnboardingScreen();
		});
		GameScreenController.getDialog().getChildren().addAll(returnOnboardingScreenButton);
		try {
			DatabaseConnection.savePlayHistory(MainFX.getUser1().getIdToken(), gameResult);
			DatabaseConnection.savePlayHistory(MainFX.getUser2().getIdToken(), gameResult);
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erreur");
			alert.setHeaderText(null);
			alert.setContentText("Erreur d'enregistrer histoire de jouer");
			alert.getDialogPane().setStyle("-fx-background-color: #ffcccc; -fx-text-fill: red;");
			alert.showAndWait();
			System.out.println(e);
		}
	}

	/**
	 * Control and update the views of the screen when comes next age
	 */
	void nextAge() {
		GameScreenController.getDialog().getChildren().add(new Label("Age suivant!"));
	}
}
