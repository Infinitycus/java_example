package by.infinity.examples.jtable.bean;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Eugene Myslovets
 */
public class BookFrame extends JFrame {

    private JTable booksTable;
    private List<Book> books = new ArrayList<Book>();

    public BookFrame() {
        setTitle("Пример JTable");
        setMinimumSize(new Dimension(550, 250));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(0, 0));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());

        JButton addButton = new JButton();
        addButton.setPreferredSize(new Dimension(140, 30));
        addButton.setText("Добавить");
        addButton.addActionListener(new ActionHandler("Add"));
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        buttonPanel.add(addButton, gbc);

        JButton deleteButton = new JButton();
        deleteButton.setPreferredSize(new Dimension(140, 30));
        deleteButton.setText("Удалить");
        deleteButton.addActionListener(new ActionHandler("Delete"));
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        buttonPanel.add(deleteButton, gbc);

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
        booksTable = new JTable();
        booksTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        booksScrollPane.setViewportView(booksTable);
        centerPanel.add(booksScrollPane, BorderLayout.CENTER);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        getContentPane().add(mainPanel);
        pack();

        reloadTableContent();
    }

    /**
     * Обновляет отображение таблицы.
     */
    public void reloadTableContent() {
        BookTableModel orderTableModel = new BookTableModel(this.books);
        booksTable.setModel(orderTableModel);
    }

    /**
     * Возвращает список книг.
     */
    public List<Book> getBooks() {
        return books;
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
            AddBookDialog addBookDialog = new AddBookDialog(this);
            addBookDialog.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            addBookDialog.setLocationRelativeTo(BookFrame.this);
            addBookDialog.setResizable(false);
            addBookDialog.setModal(true);
            addBookDialog.setVisible(true);

            reloadTableContent();
        }
        if ("Delete".equals(buttonName)) {
            int rowIndex = this.booksTable.getSelectedRow();
            if (rowIndex >= 0) {
                BookTableModel orderTableModel = (BookTableModel) this.booksTable.getModel();
                Book book = orderTableModel.getBook(rowIndex);
                if (book == null) {
                    JOptionPane.showMessageDialog(BookFrame.this, "Необходимо выделить строку");
                    return;
                }

                this.books.remove(book);
                reloadTableContent();
            } else {
                JOptionPane.showMessageDialog(BookFrame.this, "Необходимо выделить строку");
                return;
            }
        }
        if ("Exit".equals(buttonName)) {
            System.exit(0);
        }
    }
}
