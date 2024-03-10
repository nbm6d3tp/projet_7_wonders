package io.github.oliviercailloux.utils;

import io.github.oliviercailloux.enums.Resource;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class Function {
	private static final String PATTERN_FORMAT = "dd.MM.yyyy";

	/**
	 * @param  list of resources
	 * @return      a cloned List of Resources objects. It creates a new List, loops
	 *              through each object in the original list, and adds a copy of it
	 *              to
	 *              the new List. The new List is then returned.
	 */
	public static List<Resource> cloneListResources(List<Resource> resources) {
		if (resources == null) {
			throw new IllegalArgumentException();
		}
		List<Resource> clonedResources = new ArrayList<>();
		for (Resource resource : resources) {
			clonedResources.add(resource);
		}
		return clonedResources;
	}

	/**
	 * Transform a list of resources to a map, the key represents the resource and
	 * the second attribut represent the occurence of this resource
	 *
	 * @param  resources
	 * @return
	 */
	public static <T> Map<T, Integer> changeListToMap(List<T> elements) {
		Map<T, Integer> map = new HashMap<>();

		for (T element : elements) {
			if (map.containsKey(element)) {
				map.put(element, map.get(element) + 1);
			} else {
				map.put(element, 1);
			}
		}
		return map;
	}

	/**
	 * Change an arraylist of resources to String that will be used to display
	 * correctly and translate resource one by one
	 *
	 * @param  resourcesList
	 * @return
	 */
	public static <T> String changeArrayToReadableString(List<T> list) {
		if (list.size() == 0) {
			return "";
		}
		Map<T, Integer> map = new HashMap<>();
		String resultString = "";
		map = changeListToMap(list);
		for (Map.Entry<T, Integer> entry : map.entrySet()) {
			T source = entry.getKey();
			int count = entry.getValue();
			String sourceString = count + " " + source.toString() + ",";
			resultString += sourceString + " ";
		}
		return resultString.substring(0, resultString.length() - 2);
	}

	/**
	 * Change an array to type List.
	 *
	 * @param  <T>
	 * @param  array
	 * @return
	 */
	public static <T> List<T> createListFromArray(T[] array) {
		ArrayList<T> arrayList = new ArrayList<>();
		for (T element : array) {
			arrayList.add(element);
		}
		return arrayList;
	}

	/**
	 * Change an Instant to readable string
	 *
	 * @param  instant
	 * @return
	 */
	public static String givenInstant_whenUsingDateTimeFormatter_thenFormat(Instant instant) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_FORMAT).withZone(ZoneId.systemDefault());
		return formatter.format(instant);
	}

	/**
	 * Verify if a string is the form of email
	 *
	 * @param  email
	 * @return       if a string is the form of email
	 */
	public static boolean isValidEmailFormat(String email) {
		String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
		return Pattern.matches(regex, email);
	}
}