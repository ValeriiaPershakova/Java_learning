package homework_library_v5_io.book.service;

import homework_library_v5_io.author.domain.Author;
import homework_library_v5_io.author.repo.AuthorRepo;
import homework_library_v5_io.book.domain.Book;
import homework_library_v5_io.book.repo.BookRepo;
import homework_library_v5_io.common.ItemNotFoundException;
import homework_library_v5_io.common.utils.ArrayUtils;
import homework_library_v5_io.storage.ArrayStorage;

import java.util.*;

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
        if (book != null) {
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
    public List<Book> sortByName(List<Book> books) {
        books.sort((b1, b2) -> {
            if (b1!=null){
               return String.valueOf(b1.getName()).compareTo(b2.getName());
            }else return 1;
        });
        return books;
    }

    @Override
    public List<Book> sortByPublishYear(List<Book> books) {
        books.sort((b1, b2) -> {
            if (b1 != null) {
                return Integer.valueOf(b1.getPublishYear()).compareTo(Integer.valueOf(b2.getPublishYear()));
            }else return 1;
        });
        return books;
    }


    @Override
    public Book getById(Long bookId) throws ItemNotFoundException {
        Optional<Book> bookOptional = bookRepo.getById(bookId);
        Book book = bookOptional.orElseThrow(() -> new ItemNotFoundException("Book with ID = " + bookId + " does not exist"));
        return book;
    }

    @Override
    public List<Book> findByName(String name) {
        return bookRepo.find(bookRepo.getAll(), (book -> book != null && book.getName().equals(name)));
    }

    @Override
    public List<Book> findByPublishYear(int year) {
        return bookRepo.find(bookRepo.getAll(), (book -> book != null && Integer.compare(book.getPublishYear(), year) == 0));
    }

}
