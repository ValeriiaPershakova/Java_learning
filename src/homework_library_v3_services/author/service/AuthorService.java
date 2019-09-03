package homework_library_v3_services.author.service;

import homework_library_v3_services.author.domain.Author;

import java.util.Comparator;

public interface AuthorService {
    int count();

    void print();

    void delete(Author author);

    Long add(Author author);

    void defaultSort();

    void sort(Comparator comparator);

    Author[] find(String lastName);

    Author getById(Long authorId);
}
