package io.github.oliviercailloux.controller;

import io.github.oliviercailloux.MainFX;
import io.github.oliviercailloux.game.Game;
import io.github.oliviercailloux.game.Player;
import io.github.oliviercailloux.game.Wonder;
import io.github.oliviercailloux.view.DialogView;
import io.github.oliviercailloux.view.HandView;
import io.github.oliviercailloux.view.InfoPlayerView;
import io.github.oliviercailloux.view.PlayedCardsView;
import io.github.oliviercailloux.view.WonderView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * Screen where the game will happen
 */
public class GameScreenController {
	private static Game game;
	private static WonderView wonderPlayer1;
	private static InfoPlayerView infoPlayer1;
	private static WonderView wonderPlayer2;
	private static InfoPlayerView infoPlayer2;
	private static DialogView dialog;
	private static HandView leftCardList;
	private static HandView rightCardList;
	private static PlayedCardsView playedCardsListPlayer1;
	private static PlayedCardsView playedCardsListPlayer2;
	private static Label namePlayer1;
	private static Label namePlayer2;
	private static Label age;

	/**
	 * Show game screen
	 *
	 * @param primaryStage
	 */
	public static void showGameScreen() {
		Wonder[] chosenWonders = Wonder.getTwoRandomWonders();

		game = new Game(new Player(MainFX.getUser1().getEmail(), chosenWonders[0]),
				new Player(MainFX.getUser2().getEmail(), chosenWonders[1]));

		wonderPlayer1 = new WonderView(game.getFirstPlayer().getWonder());
		infoPlayer1 = new InfoPlayerView(game.getFirstPlayer());

		wonderPlayer2 = new WonderView(game.getSecondPlayer().getWonder());
		infoPlayer2 = new InfoPlayerView(game.getSecondPlayer());

		dialog = new DialogView();

		leftCardList = new HandView(game.getFirstPlayer());
		rightCardList = new HandView(game.getSecondPlayer());

		playedCardsListPlayer1 = new PlayedCardsView(game.getFirstPlayer());
		playedCardsListPlayer2 = new PlayedCardsView(game.getSecondPlayer());

		namePlayer1 = new Label("Joueur 1: " + game.getFirstPlayer().getName());
		namePlayer1.setTextFill(Color.RED);
		namePlayer2 = new Label("Joueur 2: " + game.getSecondPlayer().getName());
		namePlayer1.setStyle("-fx-font-weight: bold; -fx-font-size: 20px;");
		namePlayer2.setStyle("-fx-font-weight: bold; -fx-font-size: 20px;");
		namePlayer1.setTranslateY(-20);
		namePlayer2.setTranslateY(-20);

		VBox infoPlayer1Container = new VBox(50);
		infoPlayer1Container.setPadding(new Insets(10));
		infoPlayer1Container.setAlignment(Pos.CENTER);
		infoPlayer1Container.getChildren().addAll(namePlayer1, playedCardsListPlayer1, infoPlayer1, wonderPlayer1);
		VBox infoPlayer2Container = new VBox(50);
		infoPlayer2Container.setPadding(new Insets(10));
		infoPlayer2Container.setAlignment(Pos.CENTER);
		infoPlayer2Container.getChildren().addAll(namePlayer2, wonderPlayer2, infoPlayer2, playedCardsListPlayer2);

		HBox player1Part = new HBox(50);
		player1Part.getChildren().addAll(leftCardList, infoPlayer1Container);
		player1Part.setAlignment(Pos.CENTER_LEFT);

		HBox player2Part = new HBox(50);
		player2Part.getChildren().addAll(infoPlayer2Container, rightCardList);
		player2Part.setAlignment(Pos.CENTER_RIGHT);

		VBox dialogContainer = new VBox(0);
		age = new Label("Age " + game.getCurrentAge());
		age.setStyle("-fx-font-weight: bold; -fx-font-size: 20px;");
		age.setTranslateY(-20);

		dialogContainer.getChildren().addAll(age, dialog);
		dialogContainer.setAlignment(Pos.CENTER);

		// Create a BorderPane as the main container
		BorderPane root = new BorderPane();

		// Set the left and right card lists as the left and right children of the
		// BorderPane
		root.setLeft(player1Part);
		root.setCenter(dialogContainer);
		root.setRight(player2Part);
		root.setPadding(new Insets(10, 10, 30, 10));

		// Create and set the scene
		Scene scene = new Scene(root, MainFX.getWidthWindow(), MainFX.getHeightWindow());
		MainFX.getPrimaryStage().setScene(scene);
	}

	/**
	 * Refresh the state of all the components of the screen
	 */
	public static void refreshView() {
		wonderPlayer1.refreshView();
		wonderPlayer2.refreshView();
		infoPlayer1.refreshView();
		infoPlayer2.refreshView();
		leftCardList.refreshView();
		rightCardList.refreshView();
		playedCardsListPlayer1.refreshView();
		playedCardsListPlayer2.refreshView();
		if (game.isFirstPlayerTurn()) {
			namePlayer1.setTextFill(Color.RED);
			namePlayer2.setTextFill(Color.BLACK);
		} else {
			namePlayer1.setTextFill(Color.BLACK);
			namePlayer2.setTextFill(Color.RED);
		}
		age.setText("Age " + game.getCurrentAge());
	}

	public static Game getGame() {
		return game;
	}

	public static DialogView getDialog() {
		return dialog;
	}
}