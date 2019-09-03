package homework_library_v4_generics.book.service;

import homework_library_v4_generics.author.domain.Author;
import homework_library_v4_generics.author.repo.AuthorRepo;
import homework_library_v4_generics.book.domain.Book;
import homework_library_v4_generics.book.repo.BookRepo;

import java.util.Comparator;

public class BookServiceImpl implements BookService {
    private final AuthorRepo<Author> authorRepo;
    private final BookRepo<Book> bookRepo;

    public BookServiceImpl(AuthorRepo<Author> authorRepo, BookRepo<Book> bookRepo) {
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
        return bookRepo.findBooksByAuthorAsArray(id);
    }

    @Override
    public void defaultSort() {
        bookRepo.sort();
    }

    @Override
    public void sort(Comparator comparator) {
        bookRepo.sort(comparator);
    }

    @Override
    public Book find(String name) {
        return bookRepo.find(name);
    }

    @Override
    public Book getById(Long bookId) {
        return bookRepo.getById(bookId);
    }
}
