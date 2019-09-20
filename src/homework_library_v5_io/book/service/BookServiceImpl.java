package homework_library_v5_io.book.service;

import homework_library_v5_io.author.domain.Author;
import homework_library_v5_io.author.repo.AuthorRepo;
import homework_library_v5_io.book.domain.Book;
import homework_library_v5_io.book.repo.BookRepo;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class BookServiceImpl implements BookService {
    private final AuthorRepo authorRepo;
    private final BookRepo bookRepo;

    public BookServiceImpl(AuthorRepo authorRepo, BookRepo bookRepo) {
        this.authorRepo = authorRepo;
        this.bookRepo = bookRepo;
    }

    @Override
    public List<Book> getAll() {
        return bookRepo.getAll();
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
    public void add(Collection<Book> books) {
        for (Book book : books) {
            add(book);
        }
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
    public Book[] find(String name) {
        return bookRepo.find(name);
    }


    @Override
    public Book getById(Long bookId) {
        return bookRepo.getById(bookId);
    }

    @Override
    public Book[] findByName(FindByNameFuncInterface findByNameFuncInterface, String name){
        return findByNameFuncInterface.findByName(name);
    }

    @Override
    public Book[] findByYear(FindByYearFuncInterface findByYearFuncInterface, int year) {
        return findByYearFuncInterface.findByYear(year);
    }

    @Override
    public Book[] findBy(FindByFuncInterface findByFuncInterface, String findBy, String param) {
        return findByFuncInterface.find(findBy,param);
    }
}
