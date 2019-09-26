package homework_library_v5_io.author.service;

import homework_library_v5_io.author.domain.Author;
import homework_library_v5_io.common.ItemNotFoundException;
import homework_library_v5_io.common.service.BasicService;

import java.util.List;
import java.util.Optional;


public interface AuthorService extends BasicService<Author, Long> {
    Author findByFullName(String lastName, String name) throws ItemNotFoundException;

    List<Author> findByLastName(String name);
}
