package homework_library_v3_services.book.repo;

import homework_library_v3_services.author.domain.Author;
import homework_library_v3_services.book.domain.Book;
import homework_library_v3_services.storage.ArrayStorage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BookRepoArrayImpl implements BookRepo {

    @Override
    public int count() {
        return ArrayStorage.getTotalBooks();
    }

    @Override
    public void print() {
        for (Book book : ArrayStorage.getAllBooks()) {
            if (book != null) {
                System.out.println(book.toString());
            }
        }
    }

    @Override
    public void printBookAndItsAuthor() {
        for (Book b : ArrayStorage.getAllBooks()) {
            if (b != null) {
                System.out.println(b.toString2());
            }
        }
    }

    @Override
    public void delete(Book book) {
        ArrayStorage.removeBook(book);
    }

    @Override
    public Long add(Book book) {
        ArrayStorage.addBook(book);
        return book.getId();
    }

    @Override
    public Book[] findBooksByAuthorAsArray(long authorId) {
        return findBooksByAuthorAsList(authorId).toArray(new Book[0]);
    }

    @Override
    public List<Book> findBooksByAuthorAsList(long authorId) {
        List<Book> found = new ArrayList<>();
        for (Book book : ArrayStorage.getAllBooks()) {
            for (Author a : book.getAuthors()) {
                if (Long.valueOf(authorId).equals(a.getId())) {
                    found.add(book);
                    break;
                }
            }
        }
        return found;
    }

    @Override
    public void sort() {
        ArrayStorage.sortBooks();
    }

    @Override
    public void sort(Comparator comparator) {
        ArrayStorage.sortBooks(comparator);
    }

    @Override
    public Book find(String name) {
        Book book = null;
        for (Book a : ArrayStorage.getAllBooks()) {
            if (a != null) {
                if (a.getName().equals(name)) {
                    book = a;
                    break;
                }
            }
        }
        return book;
    }

    @Override
    public Book getById(Long bookId) {
        Book book = null;
        for (Book b : ArrayStorage.getAllBooks()) {
            if (b.getId().equals(bookId)) {
                book = b;
            }
        }
        return book;
    }
}
