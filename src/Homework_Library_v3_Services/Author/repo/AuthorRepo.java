package Homework_Library_v3_Services.Author.repo;

import Homework_Library_v3_Services.Author.domain.Author;
import Homework_Library_v3_Services.Book.domain.Book;

public interface AuthorRepo {
    int count();
    void print();
    void delete(Author author);
    Long add(Author author);
    void sort();
    Author[] find(String lastName);
    Author[] findAuthorsByBook(Long id);
}
