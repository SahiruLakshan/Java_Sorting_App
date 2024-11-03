import java.util.ArrayList;

public class ShellSort {

    public void sort(ArrayList<Double> data) {
        int n = data.size();
        
        // Start with a big gap, then reduce the gap
        for (int gap = n / 2; gap > 0; gap /= 2) {
            // Perform a gapped insertion sort for this gap size
            for (int i = gap; i < n; i++) {
                Double temp = data.get(i);
                int j;
                
                // Shift earlier gap-sorted elements up until the correct location for data[i] is found
                for (j = i; j >= gap && data.get(j - gap) > temp; j -= gap) {
                    data.set(j, data.get(j - gap));
                }
                
                // Put temp (the original data[i]) in its correct location
                data.set(j, temp);
            }
        }
    }
}
