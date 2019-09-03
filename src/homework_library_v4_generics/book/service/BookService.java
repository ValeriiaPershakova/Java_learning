package homework_library_v4_generics.book.service;

import homework_library_v4_generics.book.domain.Book;

import java.util.Comparator;

public interface BookService {
    int count();

    void print();

    void printBookAndItsAuthor();

    void delete(Book book);

    Long add(Book book);

    Book[] findBooksByAuthor(long id);

    void defaultSort();

    void sort(Comparator comparator);

    Book find(String name);

    Book getById(Long bookId);
}
