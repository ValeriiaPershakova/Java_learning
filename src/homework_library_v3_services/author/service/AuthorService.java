package homework_library_v3_services.author.service;

import homework_library_v3_services.author.domain.Author;

public interface AuthorService {
    int count();
    void print();
    void delete(Author author);
    Long add(Author author);
    void sort();
    Author[] find(String lastName);
    //void deleteBooksWhichOldAndSentReport();
}
