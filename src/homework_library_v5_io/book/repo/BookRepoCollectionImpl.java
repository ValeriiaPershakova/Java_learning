package homework_library_v5_io.book.repo;

import homework_library_v5_io.author.domain.Author;
import homework_library_v5_io.book.domain.Book;
import homework_library_v5_io.book.service.NameComparator;
import homework_library_v5_io.storage.CollectionStorage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class BookRepoCollectionImpl implements BookRepo {
    @Override
    public List<Book> getAll() {
        return CollectionStorage.getAllBooks();
    }

    @Override
    public int count() {
        return CollectionStorage.getTotalBooks();
    }

    @Override
    public void print() {
        System.out.println(CollectionStorage.getAllBooks().toString());
    }

    @Override
    public void printBookAndItsAuthor() {
        for (Book b : CollectionStorage.getAllBooks()) {
            if (b != null) {
                System.out.println(b.toString2());
            }
        }
    }

    @Override
    public void delete(Book book) {
        CollectionStorage.removeBook(book);
    }

    @Override
    public Long add(Book book) {
        CollectionStorage.addBook(book);
        return book.getId();
    }

    @Override
    public Book[] findBooksByAuthorAsArray(long authorId) {
        return findBooksByAuthorAsList(authorId).toArray(new Book[0]);
    }

    @Override
    public List<Book> findBooksByAuthorAsList(long authorId) {
        List<Book> found = new ArrayList<>();
        for (Book b : CollectionStorage.getAllBooks()) {
            for (Author a : b.getAuthors()) {
                if (a != null && authorId == a.getId()) {
                    found.add(b);
                    break;
                }
            }
        }
        return found;
    }

    @Override
    public void sort() {
        CollectionStorage.getAllBooks().sort(new NameComparator());
    }

    @Override
    public void sort(Comparator comparator) {
        CollectionStorage.getAllBooks().sort(comparator);
    }


    @Override
    public Book[] find(String name) {
        List<Book> searchingBook = new ArrayList<>();
        for (Book book : CollectionStorage.getAllBooks()) {
            if (book.getName().equals(name)) {
                searchingBook.add(book);
                break;
            }
        }
        return searchingBook.toArray(new Book[0]);
    }

    @Override
    public Optional<Book> getById(Long bookId) {
        Optional<Book> bookOptional = Optional.ofNullable(null);
        for (Book b : CollectionStorage.getAllBooks()) {
            if (b.getId().equals(bookId)) {
                bookOptional = Optional.of(b);
            }
        }
        return bookOptional;
    }

    @Override
    public List<Book> find(List<Book> items, Predicate<Book> predicate) {
        List<Book> foundBooks = new ArrayList<>();
        for (Book book : items) {
            if (predicate.test(book)) {
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }
}
