package homework_library_v3_services.book.repo;

import homework_library_v3_services.book.domain.Book;

import java.util.Comparator;
import java.util.List;

public interface BookRepo {
    int count();

    void print();

    void printBookAndItsAuthor();

    void delete(Book book);

    Long add(Book book);

    Book[] findBooksByAuthorAsArray(long id);

    List<Book> findBooksByAuthorAsList(long id);

    void sort();

    void sort(Comparator comparator);

    Book find(String name);

    Book getById(Long bookId);
}
