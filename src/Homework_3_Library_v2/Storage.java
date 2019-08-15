package Homework_3_Library_v2;

import Homework_3_Library_v2.Author.Author;
import Homework_3_Library_v2.Book.Book;

public final class Storage {
    public static final int CAPACITY = 10;
    public static Book[] books = new Book[CAPACITY];
    public static int bookIndex = 0;


    public static Author[] authors = new Author[CAPACITY];
    public static int authorIndex = 0;

    private Storage() {
    }

    public static void increaseBookIndex() {
        bookIndex++;
    }

    public static void increaseAuthorIndex() {
        authorIndex++;
    }

    public static void increaseAuthorsStorage() {

        Author[] authors = new Author[authorIndex + CAPACITY];
        System.arraycopy(Storage.authors, 0, authors, 0, authorIndex);


        Storage.authors = authors;
    }

    public static void addAuthor(Author author) {
        author.setId(System.currentTimeMillis());

        if ((authorIndex % CAPACITY) == 0 && authorIndex != 0) {
            increaseAuthorsStorage();
            authors[authorIndex] = author;
        } else {
            authors[authorIndex] = author;
        }

        Storage.increaseAuthorIndex();
    }

    public static void addBook(Book book) {
        book.setId(System.currentTimeMillis());

        if ((bookIndex % CAPACITY) == 0 && bookIndex != 0) {
            increaseBookStorage();
            books[bookIndex] = book;
        } else {
            books[bookIndex] = book;
        }
        increaseBookIndex();
    }

    public static void increaseBookStorage() {
        Book[] books = new Book[bookIndex + CAPACITY];
        System.arraycopy(Storage.books, 0, books, 0, bookIndex);
        Storage.books = books;
    }
    public static Integer indexOf(Object object, Object[] array) {
        for (int i = 0;i<array.length;i++ ) {
            if (array[i]==object) {
                return i;
            }
        }
        return null;
    }
}
