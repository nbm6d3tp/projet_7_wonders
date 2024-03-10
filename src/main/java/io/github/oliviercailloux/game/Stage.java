package io.github.oliviercailloux.game;

import io.github.oliviercailloux.enums.Resource;
import io.github.oliviercailloux.utils.Function;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Class describing a stage of a wonder.
 */
public class Stage {
	/**
	 * The cost - list of needed resources to build this stage
	 */
	private List<Resource> cost;
	/**
	 * The reward that the stage will bring to the player one this stage is builded
	 */
	private Integer reward;

	private static final Logger LOGGER = LoggerFactory.getLogger(Stage.class);


	public Stage(Resource[] cost, Integer reward) {
		this.cost = Function.createListFromArray(cost);
		this.reward = reward;
	}

	public List<Resource> getCost() {
		return cost;
	}

	public void setCost(List<Resource> cost) {
		this.cost = cost;
	}

	public Integer getReward() {
		return reward;
	}

	public void setReward(Integer reward) {
		this.reward = reward;
	}

	@Override
	public String toString() {
		LOGGER.info("Convertir le stage en string.");
		String stageString = "";
		stageString += Function.changeArrayToReadableString(cost) + "; " + reward + " points de victoire";
		return stageString;
	}
}
