package io.github.oliviercailloux.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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

class CardTest {

	private Card brownCard = new BrownCard(BrownCardInfo.FONDERIE);
	private Card greyCard = new GreyCard(GreyCardInfo.METIER_A_TISSER);
	private Card blueCard = new BlueCard(BlueCardInfo.PANTHEON);
	private Card redCard = new RedCard(RedCardInfo.CASERNE);
	private Card greenCard = new GreenCard(GreenCardInfo.DISPENSAIRE);

	@Test
	public void testCardInfo() {
		assertEquals("Fonderie", brownCard.getName());
		assertTrue(brownCard.getCostInResources().isEmpty());
		assertTrue(brownCard.getCostInCoins().isPresent());
		assertEquals(1, brownCard.getCostInCoins().get());

		assertEquals("Pantheon", blueCard.getName());
		assertTrue(blueCard.getCostInResources().isPresent());
		assertTrue(blueCard.getCostInCoins().isEmpty());
		List<Resource> resourcesList = new ArrayList<>();
		resourcesList.add(BrownResource.CLAY);
		resourcesList.add(BrownResource.ORE);
		resourcesList.add(BrownResource.WOOD);
		assertEquals(resourcesList, blueCard.getCostInResources().get());
	}

	@Test
	public void testStatusToPlay() {
		Player player = new Player("Anna", new Wonder(WonderInfo.TAE));// Default resource: Papyrus
		Player adversary = new Player("Anna", new Wonder(WonderInfo.SZO));// Default resource: Ore
		player.setAdversary(adversary);
		adversary.setAdversary(player);
		player.addToResources(BrownResource.CLAY);

		brownCard.verifyStatusToPlay(player);
		assertTrue(brownCard.getStatusToPlay());
		assertEquals(1, brownCard.getAmountToPayToPlay());

		// needed cost: BrownResource.ORE, BrownResource.CLAY, BrownResource.WOOD
		blueCard.verifyStatusToPlay(player);
		assertFalse(blueCard.getStatusToPlay());
		assertEquals(0, blueCard.getAmountToPayToPlay());

		adversary.addToResources(BrownResource.CLAY);
		adversary.addToResources(BrownResource.WOOD);
		blueCard.verifyStatusToPlay(player);
		assertEquals(4, blueCard.getAmountToPayToPlay());
		assertFalse(blueCard.getStatusToPlay());

		Game.transferMoney(adversary, player, 3);
		blueCard.verifyStatusToPlay(player);
		assertEquals(4, blueCard.getAmountToPayToPlay());
		assertTrue(blueCard.getStatusToPlay());

		player.addToResources(BrownResource.ORE);
		blueCard.verifyStatusToPlay(player);
		assertTrue(blueCard.getStatusToPlay());
		assertEquals(2, blueCard.getAmountToPayToPlay());

		player.addToResources(BrownResource.WOOD);
		blueCard.verifyStatusToPlay(player);
		assertTrue(blueCard.getStatusToPlay());
		assertEquals(0, blueCard.getAmountToPayToPlay());

		player.getWonder().unlockNewStage();
		player.getWonder().unlockNewStage();
		player.getWonder().unlockNewStage();
		player.verifyStatusToBuildStage();
		assertFalse(player.getStatusToBuildStage());
	}

	@Test
	public void testBringEffectsTo() {
		Player player = new Player("Anna", new Wonder(WonderInfo.TAE));
		brownCard.bringEffectsTo(player);
		assertEquals(BrownResource.ORE, player.getResources().get(1));
		assertEquals(BrownResource.ORE, player.getResources().get(2));
		greyCard.bringEffectsTo(player);
		assertEquals(GreyResource.LOOM, player.getResources().get(3));
		assertEquals(0, player.getVictoryPoints());
		blueCard.bringEffectsTo(player);
		assertEquals(10, player.getVictoryPoints());
		assertEquals(0, player.getConflictPoints());
		redCard.bringEffectsTo(player);
		assertEquals(1, player.getConflictPoints());
		assertFalse(player.getScienceSymbols().contains(ScienceSymbol.RULER));
		greenCard.bringEffectsTo(player);
		assertTrue(player.getScienceSymbols().contains(ScienceSymbol.RULER));
	}

	@Test
	public void testYellowCards()
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

		getMethodAddToPlayedCards().invoke(player, brown1);
		getMethodAddToPlayedCards().invoke(player, brown2);
		getMethodAddToPlayedCards().invoke(player, brown3);
		getMethodAddToPlayedCards().invoke(player, grey1);
		getMethodAddToPlayedCards().invoke(player, grey2);
		getMethodAddToPlayedCards().invoke(player, yellow);

		assertEquals(3, player.getCoins());
		yellow.bringEffectsTo(player);
		assertEquals(8, player.getCoins());

		getMethodAddToPlayedCards().invoke(adversary, brown1);
		getMethodAddToPlayedCards().invoke(adversary, grey1);

		Card yellowCard1 = new YellowCard(YellowCardInfo.BAZAR);
		Card yellowCard2 = new YellowCard(YellowCardInfo.CHAMBRE_DE_COMMERCE);
		Card yellowCard3 = new YellowCard(YellowCardInfo.PHARE);
		Card yellowCard4 = new YellowCard(YellowCardInfo.PORT);
		Card yellowCard5 = new YellowCard(YellowCardInfo.VIGNOBLE);

		yellowCard1.bringEffectsTo(player);
		assertEquals(14, player.getCoins());

		assertEquals(0, player.getVictoryPoints());
		yellowCard2.bringEffectsTo(player);
		assertEquals(18, player.getCoins());
		assertEquals(4, player.getVictoryPoints());

		yellowCard3.bringEffectsTo(player);
		assertEquals(19, player.getCoins());
		assertEquals(5, player.getVictoryPoints());

		yellowCard4.bringEffectsTo(player);
		assertEquals(22, player.getCoins());
		assertEquals(8, player.getVictoryPoints());

		yellowCard5.bringEffectsTo(player);
		assertEquals(26, player.getCoins());
	}

	@Test
	public void testPurpleCards()
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
		Card green3 = new GreenCard(GreenCardInfo.DISPENSAIRE);

		Card blue1 = new BlueCard(BlueCardInfo.AQUEDUC);
		Card blue2 = new BlueCard(BlueCardInfo.HOTEL_DE_VILLE);
		Card blue3 = new BlueCard(BlueCardInfo.PANTHEON);

		Card red1 = new RedCard(RedCardInfo.CASERNE);
		Card red2 = new RedCard(RedCardInfo.CIRQUE);

		Card purple1 = new PurpleCard(PurpleCardInfo.GUILDE_DES_AMATEURS);
		Card purple2 = new PurpleCard(PurpleCardInfo.GUILDE_DES_ARTISANS);
		Card purple3 = new PurpleCard(PurpleCardInfo.GUILDE_DES_BATISSEURS);
		Card purple4 = new PurpleCard(PurpleCardInfo.GUILDE_DES_COMMERCANTS);
		Card purple5 = new PurpleCard(PurpleCardInfo.GUILDE_DES_ESPIONS);
		Card purple6 = new PurpleCard(PurpleCardInfo.GUILDE_DES_MAGISTRATS);
		Card purple7 = new PurpleCard(PurpleCardInfo.GUILDE_DES_PHILOSOPHES);
		Card purple8 = new PurpleCard(PurpleCardInfo.GUILDE_DES_STRATEGES);

		player.getWonder().unlockNewStage();
		player.getWonder().unlockNewStage();
		player.addMilitaryTokens(-1);
		player.addMilitaryTokens(-1);
		player.addMilitaryTokens(3);

		adversary.getWonder().unlockNewStage();
		adversary.getWonder().unlockNewStage();
		adversary.addMilitaryTokens(1);
		adversary.addMilitaryTokens(-1);

		getMethodAddToPlayedCards().invoke(player, brown1);
		getMethodAddToPlayedCards().invoke(player, brown2);
		getMethodAddToPlayedCards().invoke(player, brown3);
		getMethodAddToPlayedCards().invoke(player, grey1);
		getMethodAddToPlayedCards().invoke(player, grey2);
		getMethodAddToPlayedCards().invoke(player, yellow);
		getMethodAddToPlayedCards().invoke(player, green1);
		getMethodAddToPlayedCards().invoke(player, green2);
		getMethodAddToPlayedCards().invoke(player, green3);
		getMethodAddToPlayedCards().invoke(player, blue1);
		getMethodAddToPlayedCards().invoke(player, blue2);
		getMethodAddToPlayedCards().invoke(player, blue3);
		getMethodAddToPlayedCards().invoke(player, red1);
		getMethodAddToPlayedCards().invoke(player, red2);
		getMethodAddToPlayedCards().invoke(player, purple1);
		getMethodAddToPlayedCards().invoke(player, purple2);
		getMethodAddToPlayedCards().invoke(player, purple3);

		getMethodAddToPlayedCards().invoke(adversary, brown1);
		getMethodAddToPlayedCards().invoke(adversary, grey1);
		getMethodAddToPlayedCards().invoke(adversary, green1);
		getMethodAddToPlayedCards().invoke(adversary, blue1);
		getMethodAddToPlayedCards().invoke(adversary, red1);
		getMethodAddToPlayedCards().invoke(adversary, purple1);

		purple1.bringEffectsTo(player);
		assertEquals(8, player.getVictoryPoints());
		purple2.bringEffectsTo(player);
		assertEquals(14, player.getVictoryPoints());
		purple3.bringEffectsTo(player);
		assertEquals(18, player.getVictoryPoints());
		purple4.bringEffectsTo(player);
		assertEquals(19, player.getVictoryPoints());
		purple5.bringEffectsTo(player);
		assertEquals(22, player.getVictoryPoints());
		purple6.bringEffectsTo(player);
		assertEquals(26, player.getVictoryPoints());
		purple7.bringEffectsTo(player);
		assertEquals(30, player.getVictoryPoints());
		purple8.bringEffectsTo(player);
		assertEquals(33, player.getVictoryPoints());
	}

	private Method getMethodAddToPlayedCards() throws NoSuchMethodException {
		Method method = Player.class.getDeclaredMethod("addToPlayedCards", Card.class);
		method.setAccessible(true);
		return method;
	}
}