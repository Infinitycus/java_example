package by.infinity.examples.jtable.bean;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * @author Eugene Myslovets
 */
public class BookTableModel extends AbstractTableModel {

    /**
     * Список объектов для отображения в таблице.
     * Строка таблицы соответствует одной записи в списке
     */
    private List<Book> books;

    /**
     * Наименование колонок
     */
    private static final String[] COLUMN_NAMES = {"Автор", "Название"};

    /**
     * Конструктор по умолчанию задает список для отображения в таблице.
     */
    public BookTableModel(List<Book> books) {
        this.books = books;
        // данный метод уведомляет наблюдателя о том, что в модели таблицы изменились данные
        fireTableDataChanged();
    }

    /**
     * Возвращает количество колонок в таблице.
     */
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    /**
     * Возвращает наименование колонки для заданного номера.
     */
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }

    /**
     * Возвращает количество строк в таблице - количество записей в списке books.
     */
    public int getRowCount() {
        return this.books != null ? this.books.size() : 0;
    }

    /**
     * Возвращает значение, находящаяся в строке rowIndex и в колонке columnIndex.
     */
    public Object getValueAt(int rowIndex, int columnIndex) {
        Book book = this.books.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return book.getName();
            case 1:
                return book.getAuthor();
            default:
                return "";
        }
    }

    /**
     * Возвращает книгу, находящуюся на позиции rowIndex в списке.
     */
    public Book getBook(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < this.books.size()) {
            return this.books.get(rowIndex);
        }
        return null;
    }
}
