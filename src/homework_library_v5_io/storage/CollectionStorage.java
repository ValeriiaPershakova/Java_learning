package homework_library_v5_io.storage;

import homework_library_v5_io.author.domain.Author;
import homework_library_v5_io.book.domain.Book;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class CollectionStorage {
    private static List<Book> books = new ArrayList<>();
    private static List<Author> authors = new ArrayList<>();

    private CollectionStorage() {
    }

    //------Books----------------------------------------------------------------
    public static List<Book> getAllBooks() {
        return books;
    }

    public static int getTotalBooks() {
        return books.size();
    }

    public static void addBook(Book book) {
        if (book != null) {
            book.setId(IdGenerator.generateId());

            books.add(book);
        }
    }

    public static void removeBook(Book book) {
        Iterator<Book> booksIter = books.iterator();
        while (booksIter.hasNext()) {
            boolean idsMatches = booksIter.next().getId().equals(book.getId());
            if (idsMatches) {
                booksIter.remove();
                break;
            }
        }
    }

    //-------Author--------------------------------------------------------------
    public static List<Author> getAllAuthors() {
        return authors;
    }

    public static int getTotalAuthors() {
        return authors.size();
    }

    public static void addAuthor(Author author) {
        if (author != null) {
            author.setId(IdGenerator.generateId());
            authors.add(author);
        }

    }

    public static void removeAuthor(Author author) {
        Iterator<Author> authorsIter = authors.iterator();
        while (authorsIter.hasNext()) {
            boolean idsMatches = authorsIter.next().getId().equals(author.getId());
            if (idsMatches) {
                authorsIter.remove();
                break;
            }
        }
    }


}
