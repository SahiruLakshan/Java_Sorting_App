import java.util.ArrayList;

public class MergeSort {

    public void sort(ArrayList<Double> data) {
        if (data.size() < 2) return; // Base case: already sorted if only one element
        mergeSort(data, 0, data.size() - 1);
    }

    private void mergeSort(ArrayList<Double> data, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;

            mergeSort(data, left, middle);
            mergeSort(data, middle + 1, right);

            merge(data, left, middle, right);
        }
    }

    private void merge(ArrayList<Double> data, int left, int middle, int right) {
        int leftSize = middle - left + 1;
        int rightSize = right - middle;

        ArrayList<Double> leftArray = new ArrayList<>(leftSize);
        ArrayList<Double> rightArray = new ArrayList<>(rightSize);

        // Copy data into temporary arrays
        for (int i = 0; i < leftSize; i++) {
            leftArray.add(data.get(left + i));
        }
        for (int j = 0; j < rightSize; j++) {
            rightArray.add(data.get(middle + 1 + j));
        }

        // Merge the temporary arrays
        int i = 0, j = 0, k = left;
        while (i < leftSize && j < rightSize) {
            if (leftArray.get(i) <= rightArray.get(j)) {
                data.set(k, leftArray.get(i));
                i++;
            } else {
                data.set(k, rightArray.get(j));
                j++;
            }
            k++;
        }

        // Copy remaining elements of leftArray
        while (i < leftSize) {
            data.set(k, leftArray.get(i));
            i++;
            k++;
        }

        // Copy remaining elements of rightArray
        while (j < rightSize) {
            data.set(k, rightArray.get(j));
            j++;
            k++;
        }
    }
}
