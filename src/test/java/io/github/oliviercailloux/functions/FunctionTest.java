package io.github.oliviercailloux.functions;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.github.oliviercailloux.enums.BrownResource;
import io.github.oliviercailloux.enums.GreyResource;
import io.github.oliviercailloux.enums.Resource;
import io.github.oliviercailloux.utils.Function;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

class FunctionTest {

	@Test
	public void testCloneListResources() {
		List<Resource> resources = new ArrayList<>(Arrays.asList(BrownResource.CLAY, GreyResource.GLASS));
		List<Resource> clonedResources = Function.cloneListResources(resources);
		assertFalse(resources == clonedResources);
		assertTrue(resources.equals(clonedResources));
	}

	@Test
	public void testCloneEmptyListResources() {
		List<Resource> resources = new ArrayList<>();
		List<Resource> clonedResources = Function.cloneListResources(resources);
		assertTrue(clonedResources.isEmpty());
	}

	@Test
	public void testCloneNullListResources() {
		List<Resource> resources = null;
		assertThrows(IllegalArgumentException.class, () -> Function.cloneListResources(resources));
	}

	@Test
	public void testEmptyList() {
		List<Resource> emptyList = new ArrayList<>();
		Map<Resource, Integer> expectedMap = new HashMap<>();
		Map<Resource, Integer> actualMap = Function.changeListToMap(emptyList);
		assertTrue(expectedMap.equals(actualMap));
	}

	@Test
	public void testSingleItemList() {
		List<Resource> singleItemList = Arrays.asList(BrownResource.CLAY);
		Map<Resource, Integer> expectedMap = new HashMap<>();
		expectedMap.put(BrownResource.CLAY, 1);
		Map<Resource, Integer> actualMap = Function.changeListToMap(singleItemList);
		assertTrue(expectedMap.equals(actualMap));
	}

	@Test
	public void testMultipleItemsList() {
		List<Resource> multipleItemsList = new ArrayList<>();
		multipleItemsList.add(BrownResource.CLAY);
		multipleItemsList.add(BrownResource.WOOD);
		multipleItemsList.add(BrownResource.CLAY);
		Map<Resource, Integer> expectedMap = new HashMap<>();
		expectedMap.put(BrownResource.CLAY, 2);
		expectedMap.put(BrownResource.WOOD, 1);
		Map<Resource, Integer> actualMap = Function.changeListToMap(multipleItemsList);
		assertTrue(expectedMap.equals(actualMap));
	}
}