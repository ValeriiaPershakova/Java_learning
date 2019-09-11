package homework_library_v5_io.book.service;

import homework_library_v5_io.book.domain.Book;
import homework_library_v5_io.common.service.BasicService;

public interface BookService extends BasicService<Book, Long> {
    void printBookAndItsAuthor();

    Book[] findBooksByAuthor(long id);

}
