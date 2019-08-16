package Homework_Library_v3_Services.Author.service;

import Homework_Library_v3_Services.Author.domain.Author;
import Homework_Library_v3_Services.Author.repo.AuthorRepo;
import Homework_Library_v3_Services.Book.domain.Book;
import Homework_Library_v3_Services.Book.repo.BookRepo;

public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepo authorRepo;
    private final BookRepo bookRepo;

    public AuthorServiceImpl(AuthorRepo authorRepo,
                             BookRepo bookRepo) {
        this.authorRepo = authorRepo;
        this.bookRepo = bookRepo;
    }

    @Override
    public int count() {
        return authorRepo.count();
    }

    @Override
    public void print() {
        authorRepo.print();
    }


    @Override
    public void delete(Author author) {
        Book[] booksWithAuthor = bookRepo.findBooksByAuthor(author.getId());

        if (booksWithAuthor != null) {
            for (Book book : booksWithAuthor) {
                if (book != null) {
                    book.deleteAuthor(author);

                    if (book.withoutAuthors()) {
                        bookRepo.delete(book);
                    }
                }
            }
        }

        authorRepo.delete(author);
    }

    @Override
    public Long add(Author author) {
        return authorRepo.add(author);
    }

    @Override
    public void sort() {
        authorRepo.sort();
    }

    @Override
    public Author[] find(String lastName) {
        return authorRepo.find(lastName);
    }
}
