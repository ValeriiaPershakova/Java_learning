package homework_library_v4_generics.author.repo;

import homework_library_v4_generics.Repo;
import homework_library_v4_generics.author.domain.Author;

import java.util.Comparator;

public interface AuthorRepo<T> extends Repo<T> {

    Author[] find(String lastName);

    Author[] findAuthorsByBook(Long id);

}
