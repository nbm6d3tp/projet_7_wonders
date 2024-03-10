package io.github.oliviercailloux.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.github.oliviercailloux.enums.WonderInfo;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class GameTest {

	@Test
	void testDistributeCards() {
		Player player1 = new Player("Anna", new Wonder(WonderInfo.MH));
		Player player2 = new Player("Anna", new Wonder(WonderInfo.MH));
		Game game = new Game(player1, player2);
		assertEquals(7, player1.getCardsOnHand().size());
		assertEquals(7, player2.getCardsOnHand().size());
	}

	@Test
	void testSwapCards()
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
		Player player1 = new Player("Anna", new Wonder(WonderInfo.MH));
		Player player2 = new Player("Anna", new Wonder(WonderInfo.MH));
		Game game = new Game(player1, player2);
		List<Card> player1CardsOnHand = player1.getCardsOnHand();
		List<Card> player2CardsOnHand = player2.getCardsOnHand();
		getMethod("swapCards").invoke(game);
		assertEquals(player2CardsOnHand, player1.getCardsOnHand());
		assertEquals(player1CardsOnHand, player2.getCardsOnHand());
	}

	@Test
	void testStartWar()
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
		Player player1 = new Player("Anna", new Wonder(WonderInfo.MH));
		Player player2 = new Player("Anna", new Wonder(WonderInfo.MH));
		Game game = new Game(player1, player2);
		player1.addToConflictPoints(2);
		player2.addToConflictPoints(2);
		getMethod("startWar").invoke(game);
		List<Integer> drawTest = new ArrayList<>();
		assertTrue(player1.getMilitaryTokens().equals(drawTest));

		player1.addToConflictPoints(1);
		getMethod("startWar").invoke(game);
		List<Integer> winAge1est = new ArrayList<>();
		List<Integer> loseAge1Test = new ArrayList<>();
		winAge1est.add(1);
		loseAge1Test.add(-1);
		assertTrue(player1.getMilitaryTokens().equals(winAge1est));
		assertTrue(player2.getMilitaryTokens().equals(loseAge1Test));

		player1.getMilitaryTokens().removeAll(player1.getMilitaryTokens());
		player2.getMilitaryTokens().removeAll(player2.getMilitaryTokens());
		getMethod("nextAgeForTest").invoke(game);
		getMethod("startWar").invoke(game);
		List<Integer> winAge2Test = new ArrayList<>();
		List<Integer> loseAge2Test = new ArrayList<>();
		winAge2Test.add(3);
		loseAge2Test.add(-1);
		assertTrue(player1.getMilitaryTokens().equals(winAge2Test));
		assertTrue(player2.getMilitaryTokens().equals(loseAge2Test));
	}

	@Test
	void testMoney() {
		Player player1 = new Player("Coucou", new Wonder(WonderInfo.MH));
		Player player2 = new Player("Coucou", new Wonder(WonderInfo.MH));
		assertEquals(3, player1.getCoins());
		assertEquals(3, player2.getCoins());
		Game.transferMoney(player1, player2, 3);
		assertEquals(0, player1.getCoins());
		assertEquals(6, player2.getCoins());
		Game.payMoney(player1, 5);
		assertEquals(5, player1.getCoins());
		Game.takeMoney(player2, 2);
		assertEquals(4, player2.getCoins());
		assertThrows(IllegalArgumentException.class, () -> Game.transferMoney(player1, player2, -1));
		assertThrows(IllegalArgumentException.class, () -> Game.payMoney(player1, -1));
		assertThrows(IllegalArgumentException.class, () -> Game.takeMoney(player1, -1));
	}

	@Test
	void testNextAge()
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
		Player player1 = new Player("Coucou", new Wonder(WonderInfo.MH));
		Player player2 = new Player("Coucou", new Wonder(WonderInfo.MH));
		Game game = new Game(player1, player2);
		assertEquals(1, game.getCurrentAge());
		getMethod("nextAge").invoke(game);
		assertEquals(2, game.getCurrentAge());
		getMethod("nextAge").invoke(game);
		assertEquals(3, game.getCurrentAge());
		getMethod("nextAge").invoke(game);
		assertEquals(3, game.getCurrentAge());
	}

	private Method getMethod(String methodName) throws NoSuchMethodException {
		Method method = Game.class.getDeclaredMethod(methodName);
		method.setAccessible(true);
		return method;
	}
}