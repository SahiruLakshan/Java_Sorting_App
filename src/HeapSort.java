import java.util.ArrayList;

public class HeapSort {

    public void sort(ArrayList<Double> data) {
        int n = data.size();

        // Build max heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(data, n, i);
        }

        // Extract elements from heap one by one
        for (int i = n - 1; i > 0; i--) {
            // Move current root to end
            Double temp = data.get(0);
            data.set(0, data.get(i));
            data.set(i, temp);

            // Call max heapify on the reduced heap
            heapify(data, i, 0);
        }
    }

    // To heapify a subtree rooted at node i, which is an index in data[], n is the size of the heap
    private void heapify(ArrayList<Double> data, int n, int i) {
        int largest = i; // Initialize largest as root
        int left = 2 * i + 1; // left child
        int right = 2 * i + 2; // right child

        // If left child is larger than root
        if (left < n && data.get(left) > data.get(largest)) {
            largest = left;
        }

        // If right child is larger than largest so far
        if (right < n && data.get(right) > data.get(largest)) {
            largest = right;
        }

        // If largest is not root
        if (largest != i) {
            Double swap = data.get(i);
            data.set(i, data.get(largest));
            data.set(largest, swap);

            // Recursively heapify the affected subtree
            heapify(data, n, largest);
        }
    }
}
