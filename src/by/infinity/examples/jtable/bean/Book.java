package by.infinity.examples.jtable.bean;

/**
 * Модель данных для хранения информации о книге.
 *
 * @author Eugene Myslovets
 */
public class Book {

    /**
     * Название книги
     */
    private String name;

    /**
     * Автор
     */
    private String author;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
