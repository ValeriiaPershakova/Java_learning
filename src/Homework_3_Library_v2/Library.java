package Homework_3_Library_v2;

import static Homework_3_Library_v2.Storage.*;

public class Library {
    public void addAuthor(Author author) {
        Storage.addAuthor(author);
    }

    public void addBook(Book book) {
        Storage.addBook(book);
    }

    public void printBooks() {
        for (int i = 0; i < Storage.books.length; i++) {

            Book book = Storage.books[i];
            if (book != null) {
                System.out.println(getBookAsStr(book));
            }

        }
    }

    private String getBookAsStr(Book book) {
        if (book instanceof HandWritten) {
            return book.toString() + " UNIQUE COPY";
        } else if (book instanceof Printed) {
            return book.toString() + " MANY COPY";
        } else {
            return book.toString();
        }
    }

    public void printAuthors() {
        for (int i = 0; i < authors.length; i++) {

            Author author = authors[i];
            if (author != null) {
                System.out.println(getAuthorAsStr(author));
            }

        }
    }

    private String getAuthorAsStr(Author author) {
        return author.toString();
    }

    public void printBookTitleAndAuthor() {
        for (int i = 0; i < books.length; i++) {
            Book book = Storage.books[i];
            if (book != null) {
                System.out.println(getBookTittleAuthorAsStr(book));
            }
        }
    }

    private String getBookTittleAuthorAsStr(Book book) {
        return book.toString2();
    }
}
