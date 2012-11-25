package by.infinity.examples.jtable.matrix;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Диалоговое окно для указания размерности матрицы.
 *
 * @author Eugene Myslovets
 */
public class CreateMatrixDialog extends JDialog {

    private JTextField rowTextField;
    private JTextField columnTextField;
    private MatrixFrame owner;

    public CreateMatrixDialog(MatrixFrame matrixFrame) {
        this.owner = matrixFrame;

        setTitle("Создание таблицы");
        setMinimumSize(new Dimension(350, 150));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(0, 0));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        buttonPanel.setPreferredSize(new Dimension(350, 53));

        JButton saveButton = new JButton();
        saveButton.setPreferredSize(new Dimension(100, 30));
        saveButton.setText("Создать");
        saveButton.addActionListener(new ActionHandler("Create"));
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        buttonPanel.add(saveButton, gbc);

        JButton closeButton = new JButton();
        closeButton.setPreferredSize(new Dimension(100, 30));
        closeButton.setText("Закрыть");
        closeButton.setToolTipText("Закрыть окно");
        closeButton.addActionListener(new ActionHandler("Close"));
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        buttonPanel.add(closeButton, gbc);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());

        JLabel typeLabel = new JLabel();
        typeLabel.setText("Количество строк");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        centerPanel.add(typeLabel, gbc);

        rowTextField = new JTextField();
        rowTextField.setPreferredSize(new Dimension(100, 30));
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        centerPanel.add(rowTextField, gbc);

        JLabel costLabel = new JLabel();
        costLabel.setText("Количество столбцов");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        centerPanel.add(costLabel, gbc);

        columnTextField = new JTextField();
        columnTextField.setPreferredSize(new Dimension(100, 30));
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        centerPanel.add(columnTextField, gbc);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        getContentPane().add(mainPanel);
        pack();
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
            int rowCount = Integer.parseInt(rowTextField.getText());
            int colCount = Integer.parseInt(columnTextField.getText());
            this.owner.createTable(rowCount, colCount);
        }
        setVisible(false);
    }
}
