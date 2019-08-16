package Homework_Library_v3_Services.Book.service;

import Homework_Library_v3_Services.Book.domain.Book;

public interface BookService {
    int count();
    void print();
    void printBookAndItsAuthor();
    void delete(Book book);
    Long add(Book book);
    Book[] findBooksByAuthor(long id);
    void sort();
    Book find(String name);
}
