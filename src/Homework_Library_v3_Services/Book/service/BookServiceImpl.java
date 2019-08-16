package Homework_Library_v3_Services.Book.service;

import Homework_Library_v3_Services.Author.domain.*;
import Homework_Library_v3_Services.Author.repo.AuthorRepo;
import Homework_Library_v3_Services.Book.domain.Book;
import Homework_Library_v3_Services.Book.repo.BookRepo;

public class BookServiceImpl implements BookService {
    private final AuthorRepo authorRepo;
    private final BookRepo bookRepo;

    public BookServiceImpl(AuthorRepo authorRepo, BookRepo bookRepo) {
        this.authorRepo = authorRepo;
        this.bookRepo = bookRepo;
    }

    @Override
    public int count() {
        return bookRepo.count();
    }

    @Override
    public void print() {
        bookRepo.print();
    }

    @Override
    public void printBookAndItsAuthor() {
        bookRepo.printBookAndItsAuthor();
    }

    @Override
    public void delete(Book book) {
        Author[] authorsOfTheBook = authorRepo.findAuthorsByBook(book.getId());

        if (authorsOfTheBook != null) {
            for (Author author : authorsOfTheBook) {
                if (author != null) {
                    author.deleteBook(book);

                    if (author.withoutBooks()) {
                        authorRepo.delete(author);
                    }
                }
            }
        }
        bookRepo.delete(book);
    }

    @Override
    public Long add(Book book) {
        return bookRepo.add(book);
    }

    @Override
    public Book[] findBooksByAuthor(long id) {
        return bookRepo.findBooksByAuthor(id);
    }

    @Override
    public void sort() {
        bookRepo.sort();
    }

    @Override
    public Book find(String name) {
        return bookRepo.find(name);
    }
}
