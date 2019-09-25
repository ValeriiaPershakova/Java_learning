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
    public void sortByName(List<Book> books) {
        bookRepo.getAll().sort((b1,b2)->String.valueOf(b1.getName()).compareTo(b2.getName()));
    }

    @Override
    public void sortByPublishYear(List<Book> books) {
        bookRepo.getAll().sort((b1,b2)->Integer.compare(b1.getPublishYear(),b2.getPublishYear()));
    }


    @Override
    public Book getById(Long bookId) {
        return bookRepo.getById(bookId);
    }

    @Override
    public List<Book> findByName(String name){
        return bookRepo.find(bookRepo.getAll(),(book -> book.getName().equals(name)));
    }

    @Override
    public List<Book> findByPublishYear(int year) {
        return bookRepo.find(bookRepo.getAll(),(book -> Integer.compare(book.getPublishYear(),year)==0));
    }

}
