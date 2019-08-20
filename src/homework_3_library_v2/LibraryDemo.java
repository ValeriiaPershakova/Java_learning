package homework_3_library_v2;

import homework_3_library_v2.Author.Author;
import homework_3_library_v2.Author.AuthorsRepoMemory;
import homework_3_library_v2.Author.InputAuthor;
import homework_3_library_v2.Book.*;

public class LibraryDemo {
    public static void main(String[] args) {
        Library library = new Library();

        initData(library);

        library.printAuthors();
        library.printBooks();
        library.printBookTitleAndAuthor();

        BookRepo bookRepo1 = new AuthorsRepoMemory();
        BookRepo bookRepo2 = new BooksRepoMemory();

        System.out.println("TOTAL");
        System.out.println("Authors: " + bookRepo1.total() + "; Books: " + bookRepo2.total());


        System.out.println("SORTING");
        bookRepo1.sort();
        bookRepo2.sort();
        library.printAuthors();
        library.printBooks();

        System.out.println("DELETING");
        bookRepo1.delete("Sergei","Dyachenko");
        bookRepo2.delete("Zolotaya rybka", null);

        library.printAuthors();
        library.printBookTitleAndAuthor();


    }

    private static void initData(Library library) {
        InputBook inputBook1 = new InputBook();
        inputBook1.setName("Zolotaya rybka");
        inputBook1.setPublishYear(11);
        inputBook1.setBookType(BookType.FICTION);
        inputBook1.setPaint("Ink");
        Book book1 = valueOfHandWrittenBook(inputBook1);

        InputBook inputBook2 = new InputBook();
        inputBook2.setName("Rusla and Ludmila");
        inputBook2.setPublishYear(11);
        inputBook2.setBookType(BookType.ROMANTIC);
        inputBook2.setFontFamily("Times new roman");
        Book book2 = valueOfPrintedBook(inputBook2);

        InputBook inputBook3 = new InputBook();
        inputBook3.setName("Ritual");
        inputBook3.setPublishYear(18);
        inputBook3.setBookType(BookType.FICTION);
        inputBook3.setFontFamily("Times new roman");
        Book book3 = valueOfPrintedBook(inputBook3);

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
        book3.setAuthors(new Author[]{author2, author3});


        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);

        library.addAuthor(author1);
        library.addAuthor(author2);
        library.addAuthor(author3);

        //Проверка работоспособности increaseAuthorsStorage()
//        Author author4 = valueOf(inputAuthor1);
//        for (int i = Storage.authorIndex; i < 12; i++) {
//            library.addAuthor(author4);
//        }


    }



    private static Author valueOf(InputAuthor inputAuthor) {
        Author author = new Author(null);
        author.setLastName(inputAuthor.getLastName());
        author.setName(inputAuthor.getName());
        author.setYearOfBorn(inputAuthor.getYearOfBorn());
        return author;
    }

    private static Book valueOfHandWrittenBook(InputBook inputBook) {
        HandWritten book = new HandWritten();
        book.setName(inputBook.getName());
        book.setPublishYear(inputBook.getPublishYear());
        book.setBookType(inputBook.getBookType());
        book.setPaint(inputBook.getPaint());

        return book;
    }

    private static Book valueOfPrintedBook(InputBook inputBook) {
        Printed book = new Printed();
        book.setName(inputBook.getName());
        book.setPublishYear(inputBook.getPublishYear());
        book.setBookType(inputBook.getBookType());
        book.setFontFamily(inputBook.getFontFamily());

        return book;
    }

}
