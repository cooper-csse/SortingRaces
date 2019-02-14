import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;


/**
 * This program runs various sorts and gathers timing information on them.
 *
 * @author <<YOUR NAMES HERE>>
 *         Created May 7, 2013.
 */
public class SortRunner {
	private static Random rand = new Random(17); // uses a fixed seed for debugging. Remove the parameter later.
	
	/**
	 * Starts here.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		// array size must be an int. You will need to use something much larger
		int size = 100;
		
		// Each integer will have the range from [0, maxValue). If this is significantly higher than size, you
		// will have small likelihood of getting duplicates.
		int maxValue = Integer.MAX_VALUE; 
		
		// Test 1: Array of random values.
		int[] randomIntArray = getRandomIntArray(size, maxValue);
		runAllSortsForOneArray(randomIntArray, "Random");

		int[] shuffledIntArray = getShuffledIntArray(size);
		runAllSortsForOneArray(shuffledIntArray, "\nShuffled");

		int[] almostSortedIntArray = getAlmostSortedIntArray(size);
		runAllSortsForOneArray(almostSortedIntArray, "\nAlmost Sorted");
	}

	/**
	 * 
	 * Runs all the specified sorts on the given array and outputs timing results on each.
	 *
	 * @param array
	 */
	private static void runAllSortsForOneArray(int[] array, String name) {
		long startTime, elapsedTime; 
		boolean isSorted = false;

		// TODO: Read this.
		// We prepare the arrays. This can take as long as needed to shuffle items, convert
		// back and forth from ints to Integers and vice-versa, since you aren't timing this 
		// part. You are just timing the sort itself.
		
		int[] sortedIntsUsingDefaultSort = array.clone();
		Integer[] sortedIntegersUsingDefaultSort = copyToIntegerArray(array);
		Integer[] sortedIntegersUsingHeapSort = sortedIntegersUsingDefaultSort.clone();
		Integer[] sortedIntegersUsingTreeSort = sortedIntegersUsingDefaultSort.clone();
		// No skiplist this term. Integer[] sortedIntegersUsingSkipListSort = sortedIntegersUsingDefaultSort.clone();
		int[] sortedIntsUsingQuickSort = array.clone();

		int size = array.length;

		System.out.println(name + " Integer Array:");
		
		// What is the default sort for ints? Read the javadoc.
		startTime = System.currentTimeMillis();  
		Arrays.sort(sortedIntsUsingDefaultSort); 
		elapsedTime = (System.currentTimeMillis() - startTime);
		isSorted = verifySort(sortedIntsUsingDefaultSort);
		displayResults("int", "the default sort", elapsedTime, size, isSorted);
		
		// What is the default sort for Integers (which are objects that wrap ints)?
		startTime = System.currentTimeMillis();  
		Arrays.sort(sortedIntegersUsingDefaultSort); 
		elapsedTime = (System.currentTimeMillis() - startTime);
		isSorted = verifySort(sortedIntegersUsingDefaultSort);
		displayResults("Integer", "the default sort", elapsedTime, size, isSorted);

		// Sort using the following methods, and time and verify each like done above. 
		// TODO: a simple sort that uses a TreeSet but handles a few duplicates gracefully. 
		
		// TODO: your implementation of quick sort. I suggest putting this in a static method in a Quicksort class.
		
		// TODO: your BinaryHeap sort. You can put this sort in a static method in another class. 
		
	}
	

	private static void displayResults(String typeName, String sortName, long elapsedTime, int size,  boolean isSorted) {
		if (isSorted) {
			System.out.printf("Sorted %.1e %ss using %s in %d milliseconds\n", (double)size, typeName, sortName, elapsedTime);
		} else {
			System.out.println("ARRAY NOT SORTED");
		}
	}
	
	/**
	 * Checks in O(n) time if this array is sorted.
	 *
	 * @param a An array to check to see if it is sorted.
	 */
	private static boolean verifySort(int[] a) {
		int previous = Integer.MIN_VALUE;
		for (int current : a) {
			if (current < previous) return false;
			previous = current;
		}
		return true;
	}

	/**
	 * Checks in O(n) time if this array is sorted.
	 *
	 * @param a An array to check to see if it is sorted.
	 */
	private static boolean verifySort(Integer[] a) {
		Integer previous =  null;
		for (int current : a) {
			if (previous != null && current < previous) return false;
			previous = current;
		}
		return true;
	}

	/**
	 * Copies from an int array to an Integer array.
	 *
	 * @param ints
	 * @return A clone of the primitive int array, but with Integer objects.
	 */
	private static Integer[] copyToIntegerArray(int[] ints) {
		Integer[] integers = new Integer[ints.length];
		for (int i = 0; i < ints.length; i++) {
			integers[i] = ints[i];
		}
		return integers;
	}

	/**
	 * Creates and returns an array of random ints of the given size.
	 *
	 * @return An array of random ints.
	 */
	private static int[] getRandomIntArray(int size, int maxValue) {
		int[] a = new int[size];
		for (int i = 0; i < size; i++) {
			a[i] = rand.nextInt(maxValue);
		}
		return a;
	}

	private static int[] getShuffledIntArray(int size) {
		int[] a = new int[size];
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 0; i < size; i++) list.add(i);
		Collections.shuffle(list);
		for (int i = 0; i < size; i++) a[i] = list.get(i);
		return a;
	}

	private static int[] getAlmostSortedIntArray(int size) {
		int[] a = new int[size];
		for (int i = 0; i < size; i++) a[i] = i;
		for (int r = 0; r < size / 100.0; r++) {
			int index1 = rand.nextInt(size), index2;
			do index2 = rand.nextInt(size); while (index1 == index2);
			int item = a[index1];
			a[index1] = a[index2];
			a[index2] = item;
		}
		return a;
	}

	/**
	 * Creates a shuffled random array.
	 *
	 * @param size
	 * @return An array of the ints from 0 to size-1, all shuffled
	 */
	private static int[] getUniqueElementArray(int size) {
		// TODO: implement and call this method.
		return null;
	}
	
}
