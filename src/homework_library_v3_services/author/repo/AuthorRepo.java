package homework_library_v3_services.author.repo;

import homework_library_v3_services.author.domain.Author;

import java.util.Comparator;

public interface AuthorRepo {
    int count();

    void print();

    void delete(Author author);

    Long add(Author author);

    void sort();

    void sort(Comparator comparator);

    Author[] find(String lastName);

    Author[] findAuthorsByBook(Long id);

    Author getById(Long authorId);
}
