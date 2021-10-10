package app.placement.utils;

import java.util.Collection;
import java.util.Map;

public class GenericUtils {

	private GenericUtils() {
	}

	public static <T> boolean isNullOrEmptyCollection(Collection<T> collection) {
		return collection == null || collection.isEmpty();
	}

	public static boolean isNullOrEmptyMap(Map<?, ?> map) {
		return map == null || map.isEmpty();
	}
}
