package homework_library_v4_generics.book.service;

import homework_library_v4_generics.book.domain.Book;
import homework_library_v4_generics.common.service.BasicService;

import java.util.Comparator;

public interface BookService extends BasicService<Book, Long> {
    void printBookAndItsAuthor();

    Book[] findBooksByAuthor(long id);

}
