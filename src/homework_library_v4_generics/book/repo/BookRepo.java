package homework_library_v4_generics.book.repo;

import homework_library_v4_generics.common.repo.BasicRepo;
import homework_library_v4_generics.book.domain.Book;

import java.util.List;

public interface BookRepo extends BasicRepo<Book, Long> {

    void printBookAndItsAuthor();

    Book[] findBooksByAuthorAsArray(long id);

    List<Book> findBooksByAuthorAsList(long id);

    Book[] find(String name);


}
