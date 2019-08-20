package homework_library_v3_services.author.repo;

import homework_library_v3_services.author.domain.Author;

public interface AuthorRepo {
    int count();
    void print();
    void delete(Author author);
    Long add(Author author);
    void sort();
    Author[] find(String lastName);
    Author[] findAuthorsByBook(Long id);
}
