package by.infinity.examples.jtable.matrix;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

/**
 * @author Eugene Myslovets
 */
public class MatrixFrame extends JFrame {

    private JTable matrix;

    public MatrixFrame() {
        setTitle("Пример JTable");
        setMinimumSize(new Dimension(550, 250));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(0, 0));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());

        JButton addButton = new JButton();
        addButton.setPreferredSize(new Dimension(140, 30));
        addButton.setText("Создать таблицу");
        addButton.addActionListener(new ActionHandler("Create"));
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        buttonPanel.add(addButton, gbc);

        JButton getDataButton = new JButton();
        getDataButton.setPreferredSize(new Dimension(140, 30));
        getDataButton.setText("Получить данные");
        getDataButton.addActionListener(new ActionHandler("GetData"));
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        buttonPanel.add(getDataButton, gbc);

        JButton exitButton = new JButton();
        exitButton.setPreferredSize(new Dimension(140, 30));
        exitButton.setText("Выход");
        exitButton.addActionListener(new ActionHandler("Exit"));
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 1;
        buttonPanel.add(exitButton, gbc);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout(0, 0));

        JScrollPane booksScrollPane = new JScrollPane();
        this.matrix = new JTable();
        this.matrix.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.matrix.setCellSelectionEnabled(true);
        booksScrollPane.setViewportView(matrix);
        centerPanel.add(booksScrollPane, BorderLayout.CENTER);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        getContentPane().add(mainPanel);
        pack();
    }

    /**
     * Метод создает таблицу и заполняет ее данными в цикле.
     *
     * @param rowCount количество строк в таблице
     * @param colCount количество столбцов в таблице
     */
    public void createTable(int rowCount, int colCount) {
        Object[][] elements = new Object[rowCount][colCount];
        int k = 1;
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                elements[i][j] = k++;
            }
        }
        String[] columnNames = new String[colCount];
        for (int i = 0; i < columnNames.length; i++) {
            columnNames[i] = String.valueOf(i);
        }
        this.matrix.setModel(new DefaultTableModel(elements, columnNames));
    }

    private class ActionHandler implements ActionListener {
        private String buttonName;

        public ActionHandler(String buttonName) {
            this.buttonName = buttonName;
        }

        public void actionPerformed(ActionEvent event) {
            buttonAction(buttonName);
        }
    }

    private void buttonAction(String buttonName) {
        if ("Create".equals(buttonName)) {
            CreateMatrixDialog createMatrixDialog = new CreateMatrixDialog(this);
            createMatrixDialog.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            createMatrixDialog.setLocationRelativeTo(MatrixFrame.this);
            createMatrixDialog.setResizable(false);
            createMatrixDialog.setModal(true);
            createMatrixDialog.setVisible(true);
        }
        if ("GetData".equals(buttonName)) {
            int rowCount = this.matrix.getRowCount();
            int colCount = this.matrix.getColumnCount();
            Integer[][] elements = new Integer[rowCount][colCount];
            for (int i = 0; i < rowCount; i++) {
                for (int j = 0; j < colCount; j++) {
                    elements[i][j] = Integer.parseInt(String.valueOf(this.matrix.getValueAt(i, j)));
                }
            }
            JOptionPane.showMessageDialog(MatrixFrame.this, "Вы ввели массив: " + Arrays.deepToString(elements));
        }
        if ("Exit".equals(buttonName)) {
            System.exit(0);
        }
    }

}
