package homework_library_v5_io.book.service;

import homework_library_v5_io.book.domain.Book;
import homework_library_v5_io.common.service.BasicService;

import java.util.Comparator;
import java.util.List;

public interface BookService extends BasicService<Book, Long> {
    void printBookAndItsAuthor();

    Book[] findBooksByAuthor(long id);

    List<Book> findByName(String name);
    List<Book> findByPublishYear(int year);

    void sortByName(List<Book> books);
    void sortByPublishYear(List<Book> books);


}
