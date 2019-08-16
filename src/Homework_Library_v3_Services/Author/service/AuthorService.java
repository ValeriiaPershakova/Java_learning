package Homework_Library_v3_Services.Author.service;

import Homework_Library_v3_Services.Author.domain.Author;

public interface AuthorService {
    int count();
    void print();
    void delete(Author author);
    Long add(Author author);
    void sort();
    Author[] find(String lastName);
    //void deleteBooksWhichOldAndSentReport();
}
