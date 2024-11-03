import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SortApp {
    private static File selectedFile;
    private static ArrayList<Double> unsortedData;

    public static void main(String[] args) {
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

        // JButton startsort = new JButton("Start Sorting");
        // startsort.setBounds(240, 240, 120, 30);
        // jframe.add(startsort);

        JLabel labeltime = new JLabel("Execution Time", JLabel.LEFT);
        labeltime.setFont(new Font("Arial", Font.BOLD, 12));
        labeltime.setBounds(450, 280, 100, 30);
        jframe.add(labeltime);

        //Insertion Sort
        JLabel labelinsertion = new JLabel("Insertion Sort : ", JLabel.LEFT);
        labelinsertion.setFont(new Font("Arial", Font.BOLD, 12));
        labelinsertion.setBounds(50, 320, 100, 30);
        jframe.add(labelinsertion);

        JButton sort1 = new JButton("Start Sorting");
        sort1.setFont(new Font("Arial", Font.BOLD, 10));
        sort1.setBounds(150, 320, 150, 25);
        jframe.add(sort1);

        JLabel et1 = new JLabel("10ms", JLabel.RIGHT);
        et1.setFont(new Font("Arial", Font.BOLD, 12));
        et1.setBounds(400, 320, 100, 20);
        jframe.add(et1);

        //Shell Sort
        JLabel labelshell = new JLabel("Shell Sort : ", JLabel.LEFT);
        labelshell.setFont(new Font("Arial", Font.BOLD, 12));
        labelshell.setBounds(50, 360, 100, 30);
        jframe.add(labelshell);

        JButton download2 = new JButton("Download Sorted Data");
        download2.setFont(new Font("Arial", Font.BOLD, 10));
        download2.setBounds(150, 360, 150, 25);
        jframe.add(download2);

        JLabel et2 = new JLabel("8ms", JLabel.RIGHT);
        et2.setFont(new Font("Arial", Font.BOLD, 12));
        et2.setBounds(400, 360, 100, 20);
        jframe.add(et2);

        //Merge Sort
        JLabel labemerge = new JLabel("Merge Sort : ", JLabel.LEFT);
        labemerge.setFont(new Font("Arial", Font.BOLD, 12));
        labemerge.setBounds(50, 400, 100, 30);
        jframe.add(labemerge);

        JButton download3 = new JButton("Download Sorted Data");
        download3.setFont(new Font("Arial", Font.BOLD, 10));
        download3.setBounds(150, 400, 150, 25);
        jframe.add(download3);

        JLabel et3 = new JLabel("8ms", JLabel.RIGHT);
        et3.setFont(new Font("Arial", Font.BOLD, 12));
        et3.setBounds(400, 400, 100, 20);
        jframe.add(et3);

        //Quick Sort
        JLabel labequick = new JLabel("Quick Sort : ", JLabel.LEFT);
        labequick.setFont(new Font("Arial", Font.BOLD, 12));
        labequick.setBounds(50, 440, 100, 30);
        jframe.add(labequick);

        JButton download4 = new JButton("Start Sorting");
        download4.setFont(new Font("Arial", Font.BOLD, 10));
        download4.setBounds(150, 440, 150, 25);
        jframe.add(download4);

        JLabel et4 = new JLabel("8ms", JLabel.RIGHT);
        et4.setFont(new Font("Arial", Font.BOLD, 12));
        et4.setBounds(400, 440, 100, 20);
        jframe.add(et4);

        //Heap Sort
        JLabel labeheap = new JLabel("Heap Sort : ", JLabel.LEFT);
        labeheap.setFont(new Font("Arial", Font.BOLD, 12));
        labeheap.setBounds(50, 480, 100, 30);
        jframe.add(labeheap);

        JButton download5 = new JButton("Download Sorted Data");
        download5.setFont(new Font("Arial", Font.BOLD, 10));
        download5.setBounds(150, 480, 150, 25);
        jframe.add(download5);

        JLabel et5 = new JLabel("8ms", JLabel.RIGHT);
        et5.setFont(new Font("Arial", Font.BOLD, 12));
        et5.setBounds(400, 480, 100, 20);
        jframe.add(et5);

        JLabel status = new JLabel("Best Algorithm : ", JLabel.CENTER);
        status.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
        status.setBounds(180, 540, 150, 30);
        jframe.add(status);

        JLabel bestal = new JLabel("Insertion Sort", JLabel.CENTER);
        bestal.setFont(new Font("Arial", Font.ITALIC, 15));
        bestal.setBounds(320, 540, 100, 30);
        jframe.add(bestal);

        jframe.setVisible(true);

        chooseFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               JFileChooser fileChooser = new JFileChooser();
               FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV FILES", "csv");
               fileChooser.setFileFilter(filter);
               int option = fileChooser.showOpenDialog(jframe);
               if(option == JFileChooser.APPROVE_OPTION){
                File file = fileChooser.getSelectedFile();
                fileNameLabel.setText(file.getName());

                List<String> numericColumns = getNumericColumns(file);
                columnDropdown.removeAllItems();
                for (String column : numericColumns) {
                    columnDropdown.addItem(column);
                }
               }else{
                  fileNameLabel.setText("Open command canceled");
               }
            }

            private List<String> getNumericColumns(File file) {
                List<String> numericColumns = new ArrayList<>();

                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    String[] headers = br.readLine().split(",");
                    boolean[] isNumeric = new boolean[headers.length];
            
                    // Initially mark all columns as numeric
                    for (int i = 0; i < headers.length; i++) isNumeric[i] = true;
            
                    // Check rows to confirm numeric columns
                    String line;
                    int checkLimit = 10; // Check first 10 rows
                    while ((line = br.readLine()) != null && checkLimit-- > 0) {
                        String[] values = line.split(",");
                        for (int i = 0; i < values.length; i++) {
                            try {
                                Double.parseDouble(values[i].trim());
                            } catch (NumberFormatException e) {
                                isNumeric[i] = false;
                            }
                        }
                    }
            
                    // Add numeric columns to the list
                    for (int i = 0; i < headers.length; i++) {
                        if (isNumeric[i]) numericColumns.add(headers[i].trim());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return numericColumns;
            
            }
        });

        sort1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedColumn = (String) columnDropdown.getSelectedItem();
                unsortedData = getColumnData(selectedFile, selectedColumn);
                if (selectedColumn == null) {
                    JOptionPane.showMessageDialog(jframe, "Please select a numeric column.");
                }else{
                    InsertionSort insertionSort = new InsertionSort();
                    insertionSort.insertionSort();
                }
            }

            private ArrayList<Double> getColumnData(File selectedFile, String selectedColumn) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
    }
}
