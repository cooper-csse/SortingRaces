class QuickSort {
	static void sort(int[] array) {
		sort(array, 0, array.length - 1);
	}

	private static void sort(int[] array, int left, int right) {
		if (left >= right) return;
		int pivot = array[left + (right - left) / 2];
		int i = left, j = right;
		while (i <= j) {
			while (array[i] < pivot) i++;
			while (array[j] > pivot) j--;
			if (i <= j) {
				int item = array[i];
				array[i] = array[j];
				array[j] = item;
				i++; j--;
			}
		}
		sort(array, left, i - 1);
		sort(array, i, right);
	}
}
