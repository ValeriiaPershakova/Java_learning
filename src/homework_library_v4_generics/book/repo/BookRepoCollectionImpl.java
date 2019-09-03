package homework_library_v4_generics.book.repo;

import homework_library_v4_generics.author.domain.Author;
import homework_library_v4_generics.book.domain.Book;
import homework_library_v4_generics.storage.CollectionStorage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BookRepoCollectionImpl<T extends Book> implements BookRepo<T> {
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
    public void delete(T book) {
        CollectionStorage.removeBook(book);
    }

    @Override
    public Long add(T book) {
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
    public Book find(String name) {
        Book searchingBook = null;
        for (Book book : CollectionStorage.getAllBooks()) {
            if (book.getName().equals(name)) {
                searchingBook = book;
                break;
            }
        }
        return searchingBook;
    }

    @Override
    public T getById(Long bookId) {
        T book = null;
        for (Book b : CollectionStorage.getAllBooks()) {
            if (b.getId().equals(bookId)) {
                book = (T)b;
            }
        }
        return book;
    }
}
