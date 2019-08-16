package Homework_Library_v3_Services;

import Homework_Library_v3_Services.Author.domain.Author;
import Homework_Library_v3_Services.Book.domain.Book;

public final class Storage {
    public static final int CAPACITY = 10;
    public static Book[] books = new Book[CAPACITY];
    public static int bookIndex = 0;
    public static long bookID = 1L;


    public static Author[] authors = new Author[CAPACITY];
    public static int authorIndex = 0;
    public static long authorID = 1L;

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
        author.setId(authorID);

        if ((authorIndex % CAPACITY) == 0 && authorIndex != 0) {
            increaseAuthorsStorage();
            authors[authorIndex] = author;
        } else {
            authors[authorIndex] = author;
        }

        Storage.increaseAuthorIndex();
        authorID++;
    }

    public static void addBook(Book book) {
        book.setId(bookID);

        if ((bookIndex % CAPACITY) == 0 && bookIndex != 0) {
            increaseBookStorage();
            books[bookIndex] = book;
        } else {
            books[bookIndex] = book;
        }
        increaseBookIndex();
        bookID++;
    }

    public static void increaseBookStorage() {
        Book[] books = new Book[bookIndex + CAPACITY];
        System.arraycopy(Storage.books, 0, books, 0, bookIndex);
        Storage.books = books;
    }

//    public static Integer indexOf(Object object, Object[] array) {
//        for (int i = 0; i < array.length; i++) {
//            if (array[i] == object) {
//                return i;
//            }
//        }
//        return null;
//    }

    public static void removeBook(Book book) {
        for (int i = 0; i < books.length; i++) {

            if (book.getId().equals(books[i].getId())) {
                books[i] = null;
                bookIndex--;
                break;
            }
        }

        Book[] newBooks = new Book[books.length];
        int index = 0;
        for (Book b : books) {
            if (b != null) {
                newBooks[index] = b;
                index++;
            }
        }

        books = newBooks;
    }
    public static void removeAuthor(Author author) {

        for (int i = 0; i < authors.length; i++) {

            if (author.getId().equals(authors[i].getId())) {
                authors[i] = null;
                authorIndex--;
                break;
            }

        }
        Author[] newAuthors = new Author[Storage.authors.length];
        int index = 0;
        for (Author a : authors) {
            if (a != null) {
                newAuthors[index] = a;
                index++;
            }
        }

        authors = newAuthors;
    }
}
