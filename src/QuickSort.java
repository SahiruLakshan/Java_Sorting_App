import java.util.ArrayList;

public class QuickSort {
    
    public void sort(ArrayList<Double> data) {
        quickSort(data, 0, data.size() - 1);
    }

    private void quickSort(ArrayList<Double> data, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(data, low, high);
            quickSort(data, low, pivotIndex - 1);  // Recursively sort elements before pivot
            quickSort(data, pivotIndex + 1, high); // Recursively sort elements after pivot
        }
    }

    private int partition(ArrayList<Double> data, int low, int high) {
        Double pivot = data.get(high); // Choose the last element as pivot
        int i = low - 1; // Index of smaller element
        
        for (int j = low; j < high; j++) {
            if (data.get(j) <= pivot) {
                i++;
                swap(data, i, j);
            }
        }
        swap(data, i + 1, high);
        return i + 1;
    }

    private void swap(ArrayList<Double> data, int i, int j) {
        Double temp = data.get(i);
        data.set(i, data.get(j));
        data.set(j, temp);
    }
}
