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
		long startTimeFull, elapsedTimeFull;

		// array size must be an int. You will need to use something much larger
		int size = 1000000;
		
		// Each integer will have the range from [0, maxValue). If this is significantly higher than size, you
		// will have small likelihood of getting duplicates.
		int maxValue = Integer.MAX_VALUE; 

		startTimeFull = System.currentTimeMillis();

		// Test 1: Array of random values.
		int[] randomIntArray = getRandomIntArray(size, maxValue);
		runAllSortsForOneArray(randomIntArray, " - Random");

		int[] shuffledUniqueIntArray = getUniqueElementArray(size);
		runAllSortsForOneArray(shuffledUniqueIntArray, "\n - Shuffled Unique");

		int[] almostSortedIntArray = getAlmostSortedIntArray(size);
		runAllSortsForOneArray(almostSortedIntArray, "\n - Almost Sorted");

		int[] almostSortedReverseIntArray = getAlmostSortedReverseIntArray(size);
		runAllSortsForOneArray(almostSortedReverseIntArray, "\n - Almost Sorted Reverse");

		elapsedTimeFull = System.currentTimeMillis() - startTimeFull;
		System.out.printf("\nAll sorting methods completed in:\n    %d milliseconds\n", elapsedTimeFull);
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
		displayResults("int", "DefaultSort", elapsedTime, size, isSorted);
		
		// What is the default sort for Integers (which are objects that wrap ints)?
		startTime = System.currentTimeMillis();  
		Arrays.sort(sortedIntegersUsingDefaultSort); 
		elapsedTime = (System.currentTimeMillis() - startTime);
		isSorted = verifySort(sortedIntegersUsingDefaultSort);
		displayResults("Integer", "DefaultSort", elapsedTime, size, isSorted);

		startTime = System.currentTimeMillis();
		TreeSetSort.sort(sortedIntegersUsingTreeSort);
		elapsedTime = (System.currentTimeMillis() - startTime);
		isSorted = verifySort(sortedIntegersUsingTreeSort);
		displayResults("Integer", "TreeSetSort", elapsedTime, size, isSorted);

		startTime = System.currentTimeMillis();
		QuickSort.sort(sortedIntsUsingQuickSort);
		elapsedTime = (System.currentTimeMillis() - startTime);
		isSorted = verifySort(sortedIntsUsingQuickSort);
		displayResults("int", "QuickSort", elapsedTime, size, isSorted);

		startTime = System.currentTimeMillis();
		BinaryHeap.sort(sortedIntegersUsingHeapSort);
		elapsedTime = (System.currentTimeMillis() - startTime);
		isSorted = verifySort(sortedIntegersUsingHeapSort);
		displayResults("Integers", "HeapSort", elapsedTime, size, isSorted);
	}
	

	private static void displayResults(String typeName, String sortName, long elapsedTime, int size,  boolean isSorted) {
		if (isSorted) System.out.printf("Sorted %.1e using %s<%s> in:\n    %d milliseconds\n", (double)size, sortName, typeName, elapsedTime);
		else System.out.printf("ARRAY NOT SORTED using %s<%s>\n", sortName, typeName);
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

	/**
	 * Creates and returns a shuffled array of ints 0 to size-1
	 *
	 * @param size size of the array
	 * @return An array of the ints from 0 to size-1, all shuffled
	 */
	private static int[] getUniqueElementArray(int size) {
		int[] a = new int[size];
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 0; i < size; i++) list.add(i);
		Collections.shuffle(list);
		for (int i = 0; i < size; i++) a[i] = list.get(i);
		return a;
	}

	/**
	 * Creates and returns an array with ints 0 to size-1 in order with size/100 swaps
	 *
	 * @param size the size of the array
	 * @return An array of the ints from 0 to size-1, with a few swaps
	 */
	private static int[] getAlmostSortedIntArray(int size) {
		int[] a = new int[size];
		for (int i = 0; i < size; i++) a[i] = i;
		return swapFewItems(a);
	}

	/**
	 * Creates and returns an array with ints size-1 to 0 in order with size/100 swaps
	 *
	 * @param size the size of the array
	 * @return An array of the ints from size-1 to 0, with a few swaps
	 */
	private static int[] getAlmostSortedReverseIntArray(int size) {
		int[] a = new int[size];
		for (int i = 0; i < size; i++) a[i] = size - i - 1;
		return swapFewItems(a);
	}

	/**
	 * Performs n / 100 swaps on the array
	 *
	 * @param a the array
	 * @return array
	 */
	private static int[] swapFewItems(int[] a) {
		for (int r = 0; r < a.length / 100.0; r++) {
			int index1 = rand.nextInt(a.length), index2;
			do index2 = rand.nextInt(a.length); while (index1 == index2);
			int item = a[index1];
			a[index1] = a[index2];
			a[index2] = item;
		}
		return a;
	}
	
}
