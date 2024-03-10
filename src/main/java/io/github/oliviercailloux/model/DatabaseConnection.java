package io.github.oliviercailloux.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * Class which manage the connection to database (Realtime Database - Firebase)
 */
public class DatabaseConnection {
	/**
	 * Web API Key
	 */
	private final static String KEY = "AIzaSyDFAILMi-hipv3Mi10TaTylw0GDJLeUm8M";

	/**
	 * Save an user to database (used when an user wants to create an account/sign
	 * up)
	 *
	 * @param  email
	 * @param  password
	 * @return          info of the user if request succeeds, else null
	 */
	public static Optional<UserInfo> saveUser(String email, String password) {
		try {
			URL url = new URL("https://identitytoolkit.googleapis.com/v1/accounts:signUp?key=" + KEY);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setDoOutput(true);
			String requestBody = "{\"email\":\"" + email + "\",\"password\":\"" + password + "\"}";
			OutputStream outputStream = connection.getOutputStream();
			outputStream.write(requestBody.getBytes());
			outputStream.flush();
			outputStream.close();

			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			StringBuilder response = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				response.append(line);
			}

			reader.close();

			Gson gson = new Gson();
			Response responseJson = gson.fromJson(response.toString(), Response.class);

			URL urlUpdate = new URL("https://identitytoolkit.googleapis.com/v1/accounts:update?key=" + KEY);
			connection = (HttpURLConnection) urlUpdate.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setDoOutput(true);
			String requestBodyUpdate = "{\"localId\":\"" + responseJson.localId + "\"}";
			outputStream = new DataOutputStream(connection.getOutputStream());
			outputStream.write(requestBodyUpdate.getBytes());
			outputStream.flush();
			outputStream.close();

			connection.disconnect();
			return Optional.of(new UserInfo(responseJson.localId, email));
		} catch (MalformedURLException e) {
			throw new IllegalStateException(e);
		} catch (ProtocolException e) {
			throw new IllegalStateException(e);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	/**
	 * Get info of an user from database (used when an user wants to login)
	 *
	 * @param  email
	 * @param  password
	 * @return          info of the user if request succeeds, else null
	 */
	public static Optional<UserInfo> getUser(String email, String password) {
		try {
			URL url = new URL("https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=" + KEY);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setDoOutput(true);
			String requestBody = "{\"email\":\"" + email + "\",\"password\":\"" + password + "\"}";
			OutputStream outputStream = connection.getOutputStream();
			outputStream.write(requestBody.getBytes());
			outputStream.flush();
			outputStream.close();
			int responseCode = connection.getResponseCode();

			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			StringBuilder response = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				response.append(line);
			}

			reader.close();
			connection.disconnect();

			Gson gson = new Gson();
			Response responseJson = gson.fromJson(response.toString(), Response.class);
			return Optional.of(new UserInfo(responseJson.localId, email));
		} catch (MalformedURLException e) {
			throw new IllegalStateException(e);
		} catch (ProtocolException e) {
			throw new IllegalStateException(e);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	/**
	 * Save a game result to database (used at the end of a game)
	 *
	 * @param idPlayer
	 * @param gameResult
	 */
	public static void savePlayHistory(String idPlayer, GameResult gameResult) {
		try {
			URL url = new URL(
					"https://wonders-e2863-default-rtdb.europe-west1.firebasedatabase.app/" + idPlayer + ".json");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setDoOutput(true);

			Gson gson = new Gson();
			String requestBody = gson.toJson(gameResult);
			OutputStream outputStream = connection.getOutputStream();
			outputStream.write(requestBody.getBytes());
			outputStream.flush();
			outputStream.close();

			int responseCode = connection.getResponseCode();
			System.out.println("Response Code: " + responseCode);

			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			StringBuilder response = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				response.append(line);
			}
			reader.close();
			connection.disconnect();
		} catch (MalformedURLException e) {
			throw new IllegalStateException(e);
		} catch (ProtocolException e) {
			throw new IllegalStateException(e);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	/**
	 * Fetch play histories of an user (used in the PlayHistories screen)
	 *
	 * @param  idPlayer
	 * @return          list of play histories of an user if the request succeeds,
	 *                  else null
	 */
	public static List<GameResult> getPlayHistories(String idPlayer) {
		try {
			URL url = new URL(
					"https://wonders-e2863-default-rtdb.europe-west1.firebasedatabase.app/" + idPlayer + ".json");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setDoOutput(true);

			int responseCode = connection.getResponseCode();
			System.out.println("Response Code: " + responseCode);
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			StringBuilder response = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				response.append(line);
			}
			System.out.println(response);
			reader.close();
			connection.disconnect();
			Gson gson = new Gson();
			Type playHistoryType = new TypeToken<HashMap<String, GameResult>>() {
			}.getType();
			HashMap<String, GameResult> playHistoryMap = gson.fromJson(response.toString(), playHistoryType);
			List<GameResult> gameResults = new ArrayList<>();
			if (playHistoryMap == null) {
				return null;
			}
			gameResults.addAll(playHistoryMap.values());
			return gameResults;
		} catch (MalformedURLException e) {
			throw new IllegalStateException(e);
		} catch (ProtocolException e) {
			throw new IllegalStateException(e);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}
}
