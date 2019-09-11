package homework_library_v5_io.author.service;

import homework_library_v5_io.author.domain.Author;
import homework_library_v5_io.common.service.BasicService;


public interface AuthorService extends BasicService<Author, Long> {
    Author find(String lastName, String name);
}
