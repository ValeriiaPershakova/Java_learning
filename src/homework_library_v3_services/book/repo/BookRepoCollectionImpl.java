package homework_library_v3_services.book.repo;

import homework_library_v3_services.author.domain.Author;
import homework_library_v3_services.StorageList;
import homework_library_v3_services.book.domain.Book;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BookRepoCollectionImpl implements BookRepo {
    @Override
    public int count() {
        return StorageList.books.size();
    }

    @Override
    public void print() {
        System.out.println(StorageList.books.toString());
    }

    @Override
    public void printBookAndItsAuthor() {
        for (Book b : StorageList.books) {
            if (b != null) {
                System.out.println(b.toString2());
            }
        }
    }

    @Override
    public void delete(Book book) {
        StorageList.removeBook(book);
    }

    @Override
    public Long add(Book book) {
        StorageList.addBook(book);
        return book.getId();
    }

    @Override
    public Book[] findBooksByAuthor(long id) {
        List<Book> searchingBook = new ArrayList<>();
        Iterator<Book> bookIterator = StorageList.books.iterator();
        while (bookIterator.hasNext()) {
            Book book = bookIterator.next();
            for (Author a : book.getAuthors()) {
                if (a.getId().equals(id)) {
                    searchingBook.add(book);
                    break;
                }
            }
        }
        Book[] bookArray = searchingBook.toArray(new Book[0]);
        return bookArray;
    }

    @Override
    public void sort() {
        StorageList.books.sort(new NameComparator());
    }

    @Override
    public Book find(String name) {
        Book searchingBook = null;
        for (Book book : StorageList.books) {
            if (book.getName().equals(name)) {
                searchingBook = book;
                break;
            }
        }
        return searchingBook;
    }
}
