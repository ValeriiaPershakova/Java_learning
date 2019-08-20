package homework_library_v3_services;

import homework_library_v3_services.author.domain.Author;
import homework_library_v3_services.book.domain.Book;

import java.util.ArrayList;
import java.util.List;

public final class StorageList {
    public static List<Book> books = new ArrayList<>();
    public static List<Author> authors = new ArrayList<>();
    public static long bookID = 1L;
    public static long authorID = 1L;

    private StorageList(){}
    public static void addBook(Book book) {
        book.setId(bookID);
        books.add(book);
        bookID++;
    }
    public static void addAuthor(Author author) {
        author.setId(authorID);
        authors.add(author);
        authorID++;
    }
    public static void removeAuthor(Author author) {
        authors.remove(author);
    }
    public static void removeBook(Book book) {
        books.remove(book);
    }


}
