package homework_library_v3_services.book.repo;

import homework_library_v3_services.author.domain.Author;
import homework_library_v3_services.book.domain.Book;
import homework_library_v3_services.Storage;

public class BookRepoArrayImpl implements BookRepo {

    @Override
    public int count() {
        int result = 0;

        for (Book book : Storage.books) {
            if (book != null) {
                result++;
            }
        }
        return result;
    }

    @Override
    public void print() {
        for (Book book : Storage.books) {
            if (book != null) {
                System.out.println(book.toString());
            }
        }
    }

    @Override
    public void printBookAndItsAuthor() {
        for (Book b : Storage.books) {
            if (b != null) {
                System.out.println(b.toString2());
            }
        }
     }

    @Override
    public void delete(Book book) {
        Storage.removeBook(book);
    }

    @Override
    public Long add(Book book) {
        Storage.addBook(book);
        return book.getId();
    }

    @Override
    public Book[] findBooksByAuthor(long id) {
        Book[] books = new Book[100];
        int index = 0;

        for (Book book : Storage.books) {
            if (book!=null) {
                for (Author a : book.getAuthors()) {
                    if (a.getId().equals(id)) {
                        books[index] = book;
                        index++;
                        break;
                    }
                }
            }
        }

        //if no books then null
        return books;
    }

    @Override
    public void sort() {
        Book[] books = Storage.books;
        for (int left = 0; left < Storage.bookIndex; left++) {
            // Вытаскиваем значение элемента
            Book value = books[left];
            // Look left (elements before taken)
            int i = left - 1;
            for (; i >= 0; i--) {
                // If the value is less, move the greater elements right
                if (value.getName().compareTo(books[i].getName()) < 0) {
                    books[i + 1] = books[i];
                } else {
                    //If the value is bigger - stop
                    break;
                }
            }
            // Put the value to the new free space
            books[i + 1] = value;
        }
        Storage.books = books;
    }

    @Override
    public Book find(String name) {
        Book book = null;
        for (Book a : Storage.books) {
            if (a!=null) {
                if (a.getName().equals(name)) {
                    book = a;
                    break;
                }
            }
        }
        return book;
    }
}
