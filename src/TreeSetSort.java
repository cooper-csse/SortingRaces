import java.util.HashMap;
import java.util.TreeSet;

class TreeSetSort {
	static void sort(Integer[] array) {
		HashMap<Integer, Integer> counts = new HashMap<>();
		TreeSet<Integer> set = new TreeSet<>();
		for (Integer item : array) {
			if (!set.add(item)) {
				if (!counts.containsKey(item)) counts.put(item, 2);
				else counts.put(item, counts.remove(item) + 1);
			}
		}
		int index = 0;
		for (Integer item : set) {
			try {
				Integer count = counts.get(item);
				for (int i = 0; i < count; i++) array[index++] = item;
			} catch (NullPointerException e) {
				array[index++] = item;
			}
		}
	}
}
