package io.github.oliviercailloux.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.github.oliviercailloux.enums.BlueCardInfo;
import io.github.oliviercailloux.enums.BrownCardInfo;
import io.github.oliviercailloux.enums.BrownResource;
import io.github.oliviercailloux.enums.GreenCardInfo;
import io.github.oliviercailloux.enums.GreyCardInfo;
import io.github.oliviercailloux.enums.GreyResource;
import io.github.oliviercailloux.enums.PurpleCardInfo;
import io.github.oliviercailloux.enums.RedCardInfo;
import io.github.oliviercailloux.enums.Resource;
import io.github.oliviercailloux.enums.ScienceSymbol;
import io.github.oliviercailloux.enums.WonderInfo;
import io.github.oliviercailloux.enums.YellowCardInfo;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class PlayerTest {

	@Test
	void testPlayer() {
		Player player = new Player("Anna", new Wonder(WonderInfo.MH));
		Player adversary = new Player("Anna", new Wonder(WonderInfo.MH));
		player.setAdversary(adversary);
		assertEquals("Anna", player.getName());
		assertEquals(3, player.getCoins());
		assertEquals(0, player.getVictoryPoints());
		assertEquals(0, player.getConflictPoints());
		assertEquals(WonderInfo.MH, player.getWonder().getWonderInfo());
		assertEquals(adversary, player.getAdversary());
	}

	@Test
	void testResources() {
		Player player = new Player("Anna", new Wonder(WonderInfo.MH));
		assertTrue(player.getResources().contains(BrownResource.STONE));

		List<Resource> listToCheck = new ArrayList<>();
		listToCheck.add(GreyResource.GLASS);
		listToCheck.add(BrownResource.STONE);

		player.addToResources(GreyResource.GLASS);

		assertTrue(player.getResources().containsAll(listToCheck));

		listToCheck.add(GreyResource.LOOM);
		listToCheck.add(BrownResource.WOOD);

		List<Resource> listToAdd = new ArrayList<>();
		listToAdd.add(GreyResource.LOOM);
		listToAdd.add(BrownResource.WOOD);

		player.addAllToResources(listToAdd);
		assertTrue(player.getResources().containsAll(listToCheck));
	}

	@Test
	void testMilitaryTokens() {
		Player player = new Player("Anna", new Wonder(WonderInfo.MH));
		player.addMilitaryTokens(1);
		player.addMilitaryTokens(-1);
		player.addMilitaryTokens(3);

		assertEquals(1, player.getMilitaryTokens().get(0));
		assertEquals(-1, player.getMilitaryTokens().get(1));
		assertEquals(3, player.getMilitaryTokens().get(2));

		assertEquals(1, player.getNumberOfNegativeMilitaryTokens());

		assertThrows(IllegalArgumentException.class, () -> player.addMilitaryTokens(2));
		assertThrows(IllegalArgumentException.class, () -> player.addMilitaryTokens(6));
	}

	@Test
	void testVictoryPoints() {
		Player player = new Player("Anna", new Wonder(WonderInfo.MH));
		player.addToVictoryPoints(5);
		player.addToVictoryPoints(3);
		player.addMilitaryTokens(3);
		player.addMilitaryTokens(1);
		player.addMilitaryTokens(-1);
		player.addScienceSymbol(ScienceSymbol.RULER);
		player.addScienceSymbol(ScienceSymbol.RULER);
		player.addScienceSymbol(ScienceSymbol.RULER);
		player.addScienceSymbol(ScienceSymbol.BOOK);
		player.addScienceSymbol(ScienceSymbol.BOOK);
		player.addScienceSymbol(ScienceSymbol.TRAP);

		assertEquals(8, player.getVictoryPoints());
		assertEquals(21, player.getVictoryPointScienceSymbol());
		assertEquals(33, player.getTotalVictoryPoints());

		player.addScienceSymbol(ScienceSymbol.TRAP);
		System.err.println(player.getScienceSymbols());
		System.err.println(player.getVictoryPointScienceSymbol());
		assertEquals(31, player.getVictoryPointScienceSymbol());
		assertEquals(43, player.getTotalVictoryPoints());

		Game.payMoney(player, 3);
		assertEquals(44, player.getTotalVictoryPoints());
	}

	@Test
	public void testCardsOnHand() {
		Player player = new Player("Anna", new Wonder(WonderInfo.MH));
		Player adversary = new Player("Alice", new Wonder(WonderInfo.MH));

		player.setAdversary(adversary);
		adversary.setAdversary(player);

		Card card1 = new BrownCard(BrownCardInfo.BASSIN_ARGILEUX);
		Card card2 = new GreyCard(GreyCardInfo.METIER_A_TISSER);
		List<Card> cards = new ArrayList<>();
		cards.add(card1);
		cards.add(card2);
		player.setCardsOnHand(cards);
		assertEquals(2, player.getCardsOnHand().size());
		assertEquals(card1, player.getCardOnHand(0));
		assertEquals(card2, player.getCardOnHand(1));
		assertThrows(IllegalArgumentException.class, () -> player.getCardOnHand(2));
		assertThrows(IllegalArgumentException.class, () -> player.getCardOnHand(-1));
	}

	@Test
	public void testMoney() {
		Player player = new Player("Anna", new Wonder(WonderInfo.MH));
		player.takeMoney(3);
		assertEquals(6, player.getCoins());

		player.payMoney(2);
		assertEquals(4, player.getCoins());

		assertThrows(IllegalArgumentException.class, () -> player.takeMoney(-1));
		assertThrows(IllegalArgumentException.class, () -> player.payMoney(-1));
	}

	@Test
	public void testPlay() {
		Player player = new Player("Alice", new Wonder(WonderInfo.MH));
		Player adversary = new Player("Alice", new Wonder(WonderInfo.MH));

		player.setAdversary(adversary);
		adversary.setAdversary(player);

		List<Card> cards = new ArrayList<>();
		Card playableCard = new BrownCard(BrownCardInfo.SCIERIE);
		Card canNotPlayCard = new BlueCard(BlueCardInfo.TEMPLE);
		Card notOnHandCard = new BlueCard(BlueCardInfo.AQUEDUC);

		cards.add(playableCard);
		cards.add(canNotPlayCard);

		player.setCardsOnHand(cards);
		assertThrows(IllegalArgumentException.class, () -> player.play(notOnHandCard));
		assertThrows(IllegalArgumentException.class, () -> player.play(null));
		assertThrows(IllegalArgumentException.class, () -> player.play(canNotPlayCard));

		player.play(playableCard);
		System.out.println(player.getResources());
		assertEquals(3, player.getResources().size());
		assertEquals(BrownResource.WOOD, player.getResources().get(1));
		assertEquals(BrownResource.WOOD, player.getResources().get(2));
		assertEquals(2, player.getCoins());
		assertEquals(1, player.getCardsOnHand().size());
		assertEquals(3, player.getResources().size());
	}

	@Test
	public void testBuildStage() {
		Player player = new Player("Alice", new Wonder(WonderInfo.MH));
		Player adversary = new Player("Alice", new Wonder(WonderInfo.MH));

		player.setAdversary(adversary);
		adversary.setAdversary(player);

		List<Card> cards = new ArrayList<>();
		Card card = new BrownCard(BrownCardInfo.SCIERIE);
		Card card1 = new BrownCard(BrownCardInfo.BRIQUETERIE);
		Card notOnHandCard = new BlueCard(BlueCardInfo.AQUEDUC);

		cards.add(card);
		cards.add(card1);

		player.setCardsOnHand(cards);
		assertThrows(IllegalArgumentException.class, () -> player.buildStage(card));
		assertThrows(IllegalArgumentException.class, () -> player.buildStage(null));
		assertThrows(IllegalArgumentException.class, () -> player.buildStage(notOnHandCard));

		player.addToResources(GreyResource.GLASS);
		player.addToResources(GreyResource.LOOM);
		assertEquals(0, player.getVictoryPoints());

		player.verifyStatusToBuildStage();
		player.buildStage(card);
		assertEquals(5, player.getVictoryPoints());
		assertEquals(1, player.getCardsOnHand().size());
	}

	@Test
	public void testDiscard() {
		Player player = new Player("Alice", new Wonder(WonderInfo.MH));
		Player adversary = new Player("Alice", new Wonder(WonderInfo.MH));

		player.setAdversary(adversary);
		adversary.setAdversary(player);

		List<Card> cards = new ArrayList<>();
		Card card = new BrownCard(BrownCardInfo.BASSIN_ARGILEUX);
		Card card1 = new BrownCard(BrownCardInfo.BRIQUETERIE);
		Card notOnHandCard = new BlueCard(BlueCardInfo.AQUEDUC);

		cards.add(card);
		cards.add(card1);

		player.setCardsOnHand(cards);
		assertThrows(IllegalArgumentException.class, () -> player.discard(notOnHandCard));
		assertThrows(IllegalArgumentException.class, () -> player.discard(null));

		player.discard(card);
		assertEquals(6, player.getCoins());
		assertEquals(1, player.getCardsOnHand().size());
	}

	@Test
	public void testStatusToBuildStage() {
		Player player = new Player("Anna", new Wonder(WonderInfo.TAE));
		Player adversary = new Player("Anna", new Wonder(WonderInfo.MH));
		player.setAdversary(adversary);
		adversary.setAdversary(player);

		adversary.addToResources(BrownResource.CLAY);

		player.verifyStatusToBuildStage();
		assertFalse(player.getStatusToBuildStage());
		assertEquals(0, player.getAmountToPayToBuildStage());

		adversary.addToResources(BrownResource.CLAY);
		player.verifyStatusToBuildStage();
		assertEquals(4, player.getAmountToPayToBuildStage());
		assertFalse(player.getStatusToBuildStage());

		Game.transferMoney(adversary, player, 3);
		player.verifyStatusToBuildStage();
		assertEquals(4, player.getAmountToPayToBuildStage());
		assertTrue(player.getStatusToBuildStage());

		player.addToResources(BrownResource.CLAY);
		player.verifyStatusToBuildStage();
		assertTrue(player.getStatusToBuildStage());
		assertEquals(2, player.getAmountToPayToBuildStage());

		player.addToResources(BrownResource.CLAY);
		player.verifyStatusToBuildStage();
		assertTrue(player.getStatusToBuildStage());
		assertEquals(0, player.getAmountToPayToBuildStage());

		player.getWonder().unlockNewStage();
		player.getWonder().unlockNewStage();
		player.getWonder().unlockNewStage();
		player.verifyStatusToBuildStage();
		assertFalse(player.getStatusToBuildStage());
	}

	@Test
	public void testNumberCards()
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
		Player player = new Player("Anna", new Wonder(WonderInfo.TAE));
		Player adversary = new Player("Alice", new Wonder(WonderInfo.MH));

		player.setAdversary(adversary);
		adversary.setAdversary(player);

		Card brown1 = new BrownCard(BrownCardInfo.CHANTIER);
		Card brown2 = new BrownCard(BrownCardInfo.FILON);
		Card brown3 = new BrownCard(BrownCardInfo.BASSIN_ARGILEUX);

		Card grey1 = new GreyCard(GreyCardInfo.METIER_A_TISSER);
		Card grey2 = new GreyCard(GreyCardInfo.PRESSE);

		Card yellow = new YellowCard(YellowCardInfo.TAVERNE);

		Card green1 = new GreenCard(GreenCardInfo.ACADEMIE);
		Card green2 = new GreenCard(GreenCardInfo.ATELIER);

		Card blue1 = new BlueCard(BlueCardInfo.AQUEDUC);
		Card blue2 = new BlueCard(BlueCardInfo.HOTEL_DE_VILLE);
		Card blue3 = new BlueCard(BlueCardInfo.PANTHEON);

		Card red1 = new RedCard(RedCardInfo.CASERNE);

		Card purple1 = new PurpleCard(PurpleCardInfo.GUILDE_DES_AMATEURS);
		Card purple2 = new PurpleCard(PurpleCardInfo.GUILDE_DES_ARTISANS);
		Card purple3 = new PurpleCard(PurpleCardInfo.GUILDE_DES_BATISSEURS);
		Card purple4 = new PurpleCard(PurpleCardInfo.GUILDE_DES_COMMERCANTS);
		Card purple5 = new PurpleCard(PurpleCardInfo.GUILDE_DES_ESPIONS);

		getMethodAddToPlayedCards().invoke(player, brown1);
		getMethodAddToPlayedCards().invoke(player, brown2);
		getMethodAddToPlayedCards().invoke(player, brown3);
		getMethodAddToPlayedCards().invoke(player, grey1);
		getMethodAddToPlayedCards().invoke(player, grey2);
		getMethodAddToPlayedCards().invoke(player, yellow);
		getMethodAddToPlayedCards().invoke(player, green1);
		getMethodAddToPlayedCards().invoke(player, green2);
		getMethodAddToPlayedCards().invoke(player, blue1);
		getMethodAddToPlayedCards().invoke(player, blue2);
		getMethodAddToPlayedCards().invoke(player, blue3);
		getMethodAddToPlayedCards().invoke(player, red1);
		getMethodAddToPlayedCards().invoke(player, purple1);
		getMethodAddToPlayedCards().invoke(player, purple2);
		getMethodAddToPlayedCards().invoke(player, purple3);
		getMethodAddToPlayedCards().invoke(player, purple4);
		getMethodAddToPlayedCards().invoke(player, purple5);

		assertEquals(3, player.getNumberBrownCards());
		assertEquals(2, player.getNumberGreyCards());
		assertEquals(1, player.getNumberYellowCards());
		assertEquals(2, player.getNumberGreenCards());
		assertEquals(3, player.getNumberBlueCards());
		assertEquals(1, player.getNumberRedCards());
		assertEquals(5, player.getNumberPurpleCards());
	}

	private Method getMethodAddToPlayedCards() throws NoSuchMethodException {
		Method method = Player.class.getDeclaredMethod("addToPlayedCards", Card.class);
		method.setAccessible(true);
		return method;
	}
}