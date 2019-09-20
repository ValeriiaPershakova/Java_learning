package homework_library_v5_io.book.service;

import homework_library_v5_io.book.domain.Book;
import homework_library_v5_io.common.service.BasicService;

public interface BookService extends BasicService<Book, Long> {
    void printBookAndItsAuthor();

    Book[] findBooksByAuthor(long id);

    Book[] findByName(FindByNameFuncInterface findByNameFuncInterface, String name);
    Book[] findByYear(FindByYearFuncInterface findByNameFuncInterface, int year);
    Book[] findBy(FindByFuncInterface findByFuncInterface, String findBy, String param);


}
