package by.infinity.examples.jtable.bean;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Диалоговое окно для задания значений записи о книге.
 *
 * @author Eugene Myslovets
 */
public class AddBookDialog extends JDialog {

    private JTextField authorTextField;
    private JTextField nameTextField;
    private BookFrame owner;

    public AddBookDialog(BookFrame bookFrame) {
        this.owner = bookFrame;

        setTitle("Добавление книги");
        setMinimumSize(new Dimension(350, 150));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(0, 0));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        buttonPanel.setPreferredSize(new Dimension(350, 53));

        JButton saveButton = new JButton();
        saveButton.setPreferredSize(new Dimension(100, 30));
        saveButton.setText("Добавить");
        saveButton.setToolTipText("Добавить запись и закрыть окно");
        saveButton.addActionListener(new ActionHandler("Add"));
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
        typeLabel.setText("Автор");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        centerPanel.add(typeLabel, gbc);

        authorTextField = new JTextField();
        authorTextField.setPreferredSize(new Dimension(100, 30));
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        centerPanel.add(authorTextField, gbc);

        JLabel costLabel = new JLabel();
        costLabel.setText("Название");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        centerPanel.add(costLabel, gbc);

        nameTextField = new JTextField();
        nameTextField.setPreferredSize(new Dimension(100, 30));
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        centerPanel.add(nameTextField, gbc);

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
        if ("Add".equals(buttonName)) {
            Book book = new Book();
            book.setAuthor(authorTextField.getText());
            book.setName(nameTextField.getText());
            this.owner.getBooks().add(book);

            this.owner.reloadTableContent();
        }
        setVisible(false);
    }
}
