package homework_library_v4_generics.book.repo;

import homework_library_v4_generics.Repo;
import homework_library_v4_generics.book.domain.Book;

import java.util.Comparator;
import java.util.List;

public interface BookRepo<T> extends Repo<T> {

    void printBookAndItsAuthor();

    Book[] findBooksByAuthorAsArray(long id);

    List<Book> findBooksByAuthorAsList(long id);

    Book find(String name);


}
