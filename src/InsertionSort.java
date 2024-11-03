import java.util.ArrayList;

public class InsertionSort {

    public void insertionSort(ArrayList<Double> data) {
        for (int i = 1; i < data.size(); i++) {
            Double key = data.get(i);
            int j = i - 1;

            // Move elements of data[0..i-1] that are greater than key to one position ahead of their current position
            while (j >= 0 && data.get(j) > key) {
                data.set(j + 1, data.get(j));
                j = j - 1;
            }
            data.set(j + 1, key);
        }
    }
}
