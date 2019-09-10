package homework_library_v4_generics.author.repo;

import homework_library_v4_generics.common.repo.BasicRepo;
import homework_library_v4_generics.author.domain.Author;

public interface AuthorRepo extends BasicRepo<Author, Long> {

    Author[] find(String lastName);

    Author[] findAuthorsByBook(Long id);

}
