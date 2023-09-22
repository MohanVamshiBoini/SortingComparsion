import java.util.Random;
import java.util.Arrays;

public class Comparesort {
		
	// Insertion Sort
    public static void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= 0 && key < array[j]) {
            	array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }
	
    // Merge Sort
    public static void mergeSort(int[] array) {
        if (array.length <= 1) {
            return;
        }
        int middle = array.length / 2;
        int[] leftValues = Arrays.copyOfRange(array, 0, middle);
        int[] rightValues = Arrays.copyOfRange(array, middle, array.length);
        mergeSort(leftValues);
        mergeSort(rightValues);
        merge(array, leftValues, rightValues);
    }
    
    public static void merge(int[] array, int[] leftValues, int[] rightValues) {
        int i = 0, j = 0, k = 0;
        while (i < leftValues.length && j < rightValues.length) {
            if (leftValues[i] < rightValues[j]) {
            	array[k++] = leftValues[i++];
            } else {
            	array[k++] = rightValues[j++];
            }
        }
        while (i < leftValues.length) {
        	array[k++] = leftValues[i++];
        }
        while (j < rightValues.length) {
        	array[k++] = rightValues[j++];
        }
    }

   
    public static double timeMeasurment(Runnable task) {
        long start = System.nanoTime();
        task.run();
        long end = System.nanoTime();
        return (end - start) / 1e9; //convert to seconds
    }

    public static void main(String[] args) {
        int[] nValues = { 10, 25, 50, 100, 250, 500 };
        
        System.out.println("n\tMerge Sort Time (s)\tInsertion Sort Time (s)");
        
        for (int n : nValues) {
            int[] array = new int[n];
            Random rand = new Random();
            
            for (int i = 0; i < n; i++) {
            	array[i] = rand.nextInt(1001);
            }
            
            double mergeSortTime = timeMeasurment(() -> mergeSort(array.clone()));
            double insertionSortTime = timeMeasurment(() -> insertionSort(array.clone()));
            
            System.out.printf("%d\t%.6f\t\t%.6f\n", n, mergeSortTime, insertionSortTime);
        }
    }
}