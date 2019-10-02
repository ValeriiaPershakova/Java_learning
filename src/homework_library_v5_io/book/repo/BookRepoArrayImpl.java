package homework_library_v5_io.book.repo;

import homework_library_v5_io.author.domain.Author;
import homework_library_v5_io.book.domain.Book;
import homework_library_v5_io.common.utils.CollectionUtils;
import homework_library_v5_io.storage.ArrayStorage;
import homework_library_v5_io.storage.CollectionStorage;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BookRepoArrayImpl implements BookRepo {

    @Override
    public List<Book> getAll() {
        return CollectionUtils.mutableListOf(ArrayStorage.getAllBooks());
    }

    @Override
    public int count() {
        return ArrayStorage.getTotalBooks();
    }

    @Override
    public void print() {
        for (Book book : ArrayStorage.getAllBooks()) {
            if (book != null) {
                System.out.println(book.toString());
            }
        }
    }

    @Override
    public void printBookAndItsAuthor() {
        for (Book b : ArrayStorage.getAllBooks()) {
            if (b != null) {
                System.out.println(b.toString2());
            }
        }
    }

    @Override
    public void delete(Book book) {
        ArrayStorage.removeBook(book);
    }

    @Override
    public Long add(Book book) {
        ArrayStorage.addBook(book);
        return book.getId();
    }

    @Override
    public Book[] findBooksByAuthorAsArray(long authorId) {
        return findBooksByAuthorAsList(authorId).toArray(new Book[0]);
    }

    @Override
    public List<Book> findBooksByAuthorAsList(long authorId) {
        List<Book> found = new ArrayList<>();
        for (Book book : ArrayStorage.getAllBooks()) {
            if (book != null) {
                for (Author a : book.getAuthors()) {
                    if (a != null && Long.valueOf(authorId).equals(a.getId())) {
                        found.add(book);
                        break;
                    }
                }
            }
        }
        return found;
    }

    @Override
    public void sort() {
        ArrayStorage.sortBooks();
    }

    @Override
    public void sort(Comparator comparator) {
        ArrayStorage.sortBooks(comparator);
    }


    @Override
    public Book[] find(String name) {
        List<Book> book = Arrays.stream(ArrayStorage.getAllBooks())
                .filter(book1 -> book1 != null && name.equals(book1.getName()))
                .collect(Collectors.toList());
        return book.toArray(new Book[0]);
    }

    @Override
    public Optional<Book> getById(Long bookId) {
        Optional<Book> bookOptional = Arrays.stream(ArrayStorage.getAllBooks())
                .filter(book -> book != null && bookId.equals(book.getId()))
                .findFirst();
        return bookOptional;
    }

    @Override
    public List<Book> find(List<Book> items, Predicate<Book> predicate) {
        List<Book> foundBooks = items.stream()
                .filter(predicate)
                .collect(Collectors.toList());
        return foundBooks;
    }
}
