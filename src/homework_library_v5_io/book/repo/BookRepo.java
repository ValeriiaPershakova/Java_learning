package homework_library_v5_io.book.repo;

import homework_library_v5_io.book.domain.Book;
import homework_library_v5_io.common.repo.BasicRepo;

import java.util.List;

public interface BookRepo extends BasicRepo<Book, Long> {

    void printBookAndItsAuthor();

    Book[] findBooksByAuthorAsArray(long id);

    List<Book> findBooksByAuthorAsList(long id);

    Book[] find(String name);


}
