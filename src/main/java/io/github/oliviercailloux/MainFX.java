package io.github.oliviercailloux;

import io.github.oliviercailloux.controller.LoginScreenController;
import io.github.oliviercailloux.model.UserInfo;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainFX extends Application {
	/**
	 * Info of the 2 players during the session of connection
	 */
	private static UserInfo user1;
	private static UserInfo user2;

	/**
	 * The dimension of the screen of the computer which launches the game
	 */
	private static int widthWindow;
	private static int heightWindow;
	private static Stage commonPrimaryStage;

	@Override
	public void start(Stage primaryStage) {
		MainFX.commonPrimaryStage = primaryStage;
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		widthWindow = gd.getDisplayMode().getWidth();
		heightWindow = gd.getDisplayMode().getHeight();
		try {
			MainFX.commonPrimaryStage.setFullScreen(true);
			MainFX.commonPrimaryStage.setTitle("7 Wonders");
			LoginScreenController.showLoginScreen();
			MainFX.commonPrimaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static UserInfo getUser1() {
		return user1;
	}

	public static void setUser1(UserInfo user1) {
		MainFX.user1 = user1;
	}

	public static UserInfo getUser2() {
		return user2;
	}

	public static void setUser2(UserInfo user2) {
		MainFX.user2 = user2;
	}

	public static int getWidthWindow() {
		return widthWindow;
	}

	public static int getHeightWindow() {
		return heightWindow;
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static Stage getPrimaryStage() {
		return commonPrimaryStage;
	}
}
