package homework_library_v3_services.book.service;

import homework_library_v3_services.book.domain.Book;

public interface BookService {
    int count();
    void print();
    void printBookAndItsAuthor();
    void delete(Book book);
    Long add(Book book);
    Book[] findBooksByAuthor(long id);
    void sort();
    Book find(String name);
}
