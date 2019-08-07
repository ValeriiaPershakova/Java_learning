package Homework_3_Library;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Book {
    private String title;
    private Edition edition;
    private int id;

    public String getTitle() {
        return title;
    }

    public Edition getEdition() {
        return edition;
    }

    public int getId() {
        return id;
    }

    public Author[] getAuthors() {
        return authors;
    }

    private Author[] authors;

    public Book(String title, Edition edition, Author[] authors) {
        this.title = title;
        this.edition = edition;
        this.id = Library.AllBooks.size();
        this.authors = authors;
    }

    public static Book newBook(String title, Edition edition, Author[] authors) {
        Integer odjIndexIfBookAlreadyExist = doesBookAlreadyExists(title, edition, authors);
        if (odjIndexIfBookAlreadyExist != null) {
            System.out.println("This book already exists");
            return Library.AllBooks.get(odjIndexIfBookAlreadyExist);
        } else {
            Book book=new Book(title, edition, authors);
            Library.AllBooks.add(book);
            return book;
        }
    }

    private static Integer doesBookAlreadyExists(String title, Edition edition, Author[] authors) {
        Integer bookId = null;
        for (Book book : Library.AllBooks) {
            boolean bookAlreadyExist = book.compare(title, edition, authors);
            if (bookAlreadyExist) {
                bookId = Library.AllBooks.indexOf(book);
                break;
            }
        }
        return bookId;
    }

    private boolean compare(String title, Edition edition, Author[] authors) {
        if (this.title == title && this.authors == authors && this.edition.equals(edition)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", edition:" + edition +
                ", id=" + id +
                ", authors=" + Arrays.toString(authors) +
                '}';
    }
}







