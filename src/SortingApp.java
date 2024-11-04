import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SortingApp {

    private static File selectedFile; // Store the selected CSV file
    private static ArrayList<Double> unsortedData; // Store unsorted data from selected column
    private static ArrayList<ArrayList<Double>> sortedDataList = new ArrayList<>(); // Store sorted data for each algorithm
    private static double[] executionTimes = new double[5]; // Store execution times for each algorithm

    public static void main(String[] args) {
        // Create the JFrame
        JFrame jframe = new JFrame();
        jframe.setSize(600, 650);
        jframe.setLocation(500, 100);
        jframe.setTitle("Java Sorting Application");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setLayout(null);  // Use null layout for absolute positioning
        jframe.setResizable(false);

        // Title label
        JLabel titleLabel = new JLabel("JAVA SORTING APPLICATION", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBounds(0, 20, 600, 30);
        jframe.add(titleLabel);

        // Step 1 label
        JLabel step1Label = new JLabel("1st Step - Choose CSV File", JLabel.CENTER);
        step1Label.setFont(new Font("Arial", Font.BOLD, 15));
        step1Label.setBounds(200, 80, 200, 30);
        jframe.add(step1Label);

        // Choose CSV File button
        JButton chooseFileButton = new JButton("Choose CSV File");
        chooseFileButton.setBounds(200, 120, 200, 30);
        jframe.add(chooseFileButton);

        // Label to display the selected file name
        JLabel fileNameLabel = new JLabel("No file chosen");
        fileNameLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        fileNameLabel.setBounds(400, 120, 200, 30);
        jframe.add(fileNameLabel);

        // Step 2 label
        JLabel step2Label = new JLabel("2nd Step - Select a Numeric Column", JLabel.LEFT);
        step2Label.setFont(new Font("Arial", Font.BOLD, 15));
        step2Label.setBounds(170, 160, 300, 30);
        jframe.add(step2Label);

        // Dropdown for numeric columns
        JComboBox<String> columnDropdown = new JComboBox<>();
        columnDropdown.setBounds(200, 200, 200, 30);
        jframe.add(columnDropdown);

        // Start Sorting button
        JButton startsort = new JButton("Start Sorting");
        startsort.setBounds(240, 240, 120, 30);
        jframe.add(startsort);

        JLabel labeltime = new JLabel("Execution Time", JLabel.LEFT);
        labeltime.setFont(new Font("Arial", Font.BOLD, 12));
        labeltime.setBounds(450, 280, 100, 30);
        jframe.add(labeltime);

        // Labels and buttons for sorting algorithms
        String[] algorithms = {"Insertion Sort", "Shell Sort", "Merge Sort","Heap Sort","Quick Sort"};
        JLabel[] algorithmLabels = new JLabel[algorithms.length];
        JButton[] downloadButtons = new JButton[algorithms.length];
        JLabel[] executionTimeLabels = new JLabel[algorithms.length];

        for (int i = 0; i < algorithms.length; i++) {
            algorithmLabels[i] = new JLabel(algorithms[i] + " : ", JLabel.LEFT);
            algorithmLabels[i].setFont(new Font("Arial", Font.BOLD, 12));
            algorithmLabels[i].setBounds(50, 320 + (i * 40), 100, 30);
            jframe.add(algorithmLabels[i]);

            downloadButtons[i] = new JButton("Download Sorted Data");
            downloadButtons[i].setFont(new Font("Arial", Font.BOLD, 10));
            downloadButtons[i].setBounds(150, 320 + (i * 40), 150, 25);
            jframe.add(downloadButtons[i]);

            executionTimeLabels[i] = new JLabel("0ms", JLabel.RIGHT);
            executionTimeLabels[i].setFont(new Font("Arial", Font.BOLD, 12));
            executionTimeLabels[i].setBounds(400, 320 + (i * 40), 100, 20);
            jframe.add(executionTimeLabels[i]);
        }

        JLabel status = new JLabel("Best Algorithm :- ", JLabel.CENTER);
        status.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
        status.setBounds(150, 540, 150, 30);
        jframe.add(status);

        JLabel bestAlgorithmLabel = new JLabel("", JLabel.CENTER);
        bestAlgorithmLabel.setFont(new Font("Arial", Font.ITALIC, 15));
        bestAlgorithmLabel.setBounds(280, 540, 180, 30);
        jframe.add(bestAlgorithmLabel);

        // Action for choose file button
        chooseFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter("CSV Files", "csv"));

                int result = fileChooser.showOpenDialog(jframe);
                if (result == JFileChooser.APPROVE_OPTION) {
                    selectedFile = fileChooser.getSelectedFile();
                    fileNameLabel.setText(selectedFile.getName());
                    
                    // Get and display numeric columns in the dropdown
                    List<String> numericColumns = getNumericColumns(selectedFile);
                    columnDropdown.removeAllItems();
                    for (String column : numericColumns) {
                        columnDropdown.addItem(column);
                    }
                }
            }
        });

        // Action for Start Sorting button
        startsort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedColumn = (String) columnDropdown.getSelectedItem();
                if (selectedFile != null && selectedColumn != null) {
                    unsortedData = getColumnData(selectedFile, selectedColumn);
                    sortedDataList.clear(); // Clear previous results

                    // Execute sorting algorithms
                    for (int i = 0; i < algorithms.length; i++) {
                        ArrayList<Double> sortedData = new ArrayList<>(unsortedData); // Copy unsorted data

                        long startTime = System.nanoTime();
                        switch (i) {
                            case 0:
                                new InsertionSort().insertionSort(sortedData);
                                break;
                            case 1:
                                new ShellSort().sort(sortedData);
                                break;
                            case 2:
                                new MergeSort().sort(sortedData);
                                break;
                            case 3:
                                new HeapSort().sort(sortedData);
                                break;
                            case 4:
                                new QuickSort().sort(sortedData);
                                break;
                        }
                        long endTime = System.nanoTime();
                        double executionTimeMillis = (endTime - startTime) / 1_000_000.0;

                        executionTimes[i] = executionTimeMillis;
                        executionTimeLabels[i].setText(String.format("%.2f ms", executionTimeMillis));
                        sortedDataList.add(sortedData); // Save the sorted result
                    }

                    // Determine best algorithm
                    double bestTime = Double.MAX_VALUE;
                    int bestIndex = 0;
                    for (int i = 0; i < executionTimes.length; i++) {
                        if (executionTimes[i] < bestTime) {
                            bestTime = executionTimes[i];
                            bestIndex = i;
                        }
                    }
                    bestAlgorithmLabel.setText(String.format("%s: %.2f ms", algorithms[bestIndex], bestTime));
                } else {
                    JOptionPane.showMessageDialog(jframe, "Please select a CSV file and a column.");
                }
            }
        });

        // Actions for Download Sorted Data buttons
        for (int i = 0; i < downloadButtons.length; i++) {
            final int index = i;
            final JComboBox<String> finalColumnDropdown = columnDropdown; // Capture the dropdown for use in the listener
            downloadButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!sortedDataList.isEmpty()) {
                        String columnName = (String) finalColumnDropdown.getSelectedItem(); // Get the selected column name
                        JFileChooser saveFileChooser = new JFileChooser();
                        saveFileChooser.setDialogTitle("Save Sorted Data");
                        saveFileChooser.setSelectedFile(new File(algorithms[index].replace(" ", "_") + "_sorted_data.csv"));

                        int userSelection = saveFileChooser.showSaveDialog(jframe);

                        if (userSelection == JFileChooser.APPROVE_OPTION) {
                            File fileToSave = saveFileChooser.getSelectedFile();
                            saveSortedData(fileToSave, sortedDataList.get(index), columnName); // Pass the column name
                            JOptionPane.showMessageDialog(jframe, "File saved successfully!");
                        }
                    } else {
                        JOptionPane.showMessageDialog(jframe, "Please sort the data first.");
                    }
                }
            });
        }

        // Set frame visibility
        jframe.setVisible(true);
    }

    // Method to get numeric columns from the CSV file
    private static List<String> getNumericColumns(File file) {
        List<String> numericColumns = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String headerLine = br.readLine();
            if (headerLine == null) return numericColumns;
            String[] headers = headerLine.split(",");
            boolean[] isNumeric = new boolean[headers.length];
            for (int i = 0; i < headers.length; i++) isNumeric[i] = true;
            int checkLimit = 10;
            
            String line;
            while ((line = br.readLine()) != null && checkLimit-- > 0) {
                String[] values = line.split(",");
                for (int i = 0; i < headers.length; i++) {
                    if (i >= values.length || !isNumeric[i]) continue;
                    try {
                        Double.valueOf(values[i].trim());
                    } catch (NumberFormatException e) {
                        isNumeric[i] = false;
                    }
                }
            }

            for (int i = 0; i < headers.length; i++) {
                if (isNumeric[i]) numericColumns.add(headers[i].trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return numericColumns;
    }

    // Method to retrieve data for a specific column from the CSV file
    private static ArrayList<Double> getColumnData(File file, String column) {
        ArrayList<Double> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String headerLine = br.readLine();
            if (headerLine == null) return data;
            String[] headers = headerLine.split(",");
            int columnIndex = -1;
            for (int i = 0; i < headers.length; i++) {
                if (headers[i].trim().equals(column)) {
                    columnIndex = i;
                    break;
                }
            }
            if (columnIndex == -1) return data;

            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (columnIndex < values.length) {
                    try {
                        data.add(Double.parseDouble(values[columnIndex].trim()));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    // Method to save sorted data to a CSV file
    private static void saveSortedData(File file, ArrayList<Double> data, String columnName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            writer.println(columnName); // Use the selected column name
            for (Double value : data) {
                writer.println(value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}