package ro.sci.electingapp;

import java.util.HashMap;

public class TestUtils {

	static boolean compareMap(HashMap<String, Float> map1, HashMap<String, Float> map2) {
		if (map1 == null || map2 == null)
			return false;
		for (String ch1 : map1.keySet()) {
			if (!(map1.get(ch1).equals(map2.get(ch1))))
				return false;
		}
		for (String ch2 : map2.keySet()) {
			if (!(map2.get(ch2).equals(map1.get(ch2))))
				return false;
		}
		return true;
	}
}
