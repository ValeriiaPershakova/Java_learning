package Homework_3_Library_v2;

import Homework_3_Library.InputData;

public class LibraryDemo {
    public static void main(String[] args) {
        Library library = new Library();

        initData(library);

        library.printAuthors();
        library.printBooks();
        library.printBookTitleAndAuthor();
    }

    private static void initData(Library library) {
        InputBook inputBook1 = new InputBook();
        inputBook1.setName("Zolotaya rybka");
        inputBook1.setPublishYear(11);
        inputBook1.setGenre(Genre.FICTION);
        HandWritten book1 = valueOfHW(inputBook1);

        InputBook inputBook2 = new InputBook();
        inputBook2.setName("Rusla and Ludmila");
        inputBook2.setPublishYear(11);
        inputBook2.setGenre(Genre.ROMANTIC);
        Printed book2 = valueOfP(inputBook2);

        InputBook inputBook3 = new InputBook();
        inputBook3.setName("Ritual");
        inputBook3.setPublishYear(18);
        inputBook3.setGenre(Genre.FICTION);
        Book book3 = valueOf(inputBook3);

        InputAuthor inputAuthor1 = new InputAuthor();
        inputAuthor1.setLastName("Pushkin");
        inputAuthor1.setYearOfBorn(22);
        Author author1 = valueOf(inputAuthor1);
        author1.setBooks(new Book[]{book1, book2});

        InputAuthor inputAuthor2 = new InputAuthor();
        inputAuthor2.setLastName("Dyachenko");
        inputAuthor2.setName("Marina");
        inputAuthor2.setYearOfBorn(22);
        Author author2 = valueOf(inputAuthor2);

        InputAuthor inputAuthor3 = new InputAuthor();
        inputAuthor3.setLastName("Dyachenko");
        inputAuthor3.setName("Sergei");
        inputAuthor3.setYearOfBorn(22);
        Author author3 = valueOf(inputAuthor3);

        author2.setBooks(new Book[]{book3});
        author3.setBooks(new Book[]{book3});

        book1.setAuthors(new Author[]{author1});
        book2.setAuthors(new Author[]{author1});
        book3.setAuthors(new Author[]{author2,author3});


        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);

        library.addAuthor(author1);
        library.addAuthor(author2);
        library.addAuthor(author3);


    }

    private static Book valueOf(InputBook inputBook) {
        Book book = new Book();
        book.setName(inputBook.getName());
        book.setPublishYear(inputBook.getPublishYear());
        book.setGenre(inputBook.getGenre());

        return book;
    }

    private static Author valueOf(InputAuthor inputAuthor) {
        Author author = new Author(null);
        author.setLastName(inputAuthor.getLastName());
        return author;
    }

    private static HandWritten valueOfHW(InputBook inputBook) {
        HandWritten book = new HandWritten();
        book.setName(inputBook.getName());
        book.setPublishYear(inputBook.getPublishYear());
        book.setGenre(inputBook.getGenre());

        return book;
    }
    private static Printed valueOfP(InputBook inputBook) {
        Printed book = new Printed();
        book.setName(inputBook.getName());
        book.setPublishYear(inputBook.getPublishYear());
        book.setGenre(inputBook.getGenre());

        return book;
    }

}
