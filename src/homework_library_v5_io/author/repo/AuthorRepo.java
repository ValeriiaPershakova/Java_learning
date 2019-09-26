package homework_library_v5_io.author.repo;

import homework_library_v5_io.author.domain.Author;
import homework_library_v5_io.common.repo.BasicRepo;

import java.util.Optional;

public interface AuthorRepo extends BasicRepo<Author, Long> {

    Author[] find(String lastName);

    Optional<Author> find(String lastName, String name);

    Author[] findAuthorsByBook(Long id);

}
