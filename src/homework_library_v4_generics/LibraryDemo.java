package homework_library_v4_generics;

import homework_library_v4_generics.author.InputAuthor;
import homework_library_v4_generics.author.domain.Author;
import homework_library_v4_generics.author.repo.AuthorRepo;
import homework_library_v4_generics.author.repo.AuthorRepoArrayImpl;
import homework_library_v4_generics.author.repo.AuthorRepoCollectionImpl;
import homework_library_v4_generics.author.repo.LastNameComparator;
import homework_library_v4_generics.author.service.AuthorService;
import homework_library_v4_generics.author.service.AuthorServiceImpl;
import homework_library_v4_generics.book.InputBook;
import homework_library_v4_generics.book.domain.Book;
import homework_library_v4_generics.book.domain.BookType;
import homework_library_v4_generics.book.domain.HandWritten;
import homework_library_v4_generics.book.domain.Printed;
import homework_library_v4_generics.book.repo.BookRepo;
import homework_library_v4_generics.book.repo.BookRepoArrayImpl;
import homework_library_v4_generics.book.repo.BookRepoCollectionImpl;
import homework_library_v4_generics.book.repo.NameComparator;
import homework_library_v4_generics.book.service.BookService;
import homework_library_v4_generics.book.service.BookServiceImpl;

import static homework_library_v4_generics.common.utils.CollectionUtils.mutableListOf;

public class LibraryDemo {
    public static void main(String[] args) {
        String storageType = "arrays";
        //String storageType = "collection";
        BookRepo bookRepo = null;
        AuthorRepo authorRepo = null;

        if (storageType.equals("arrays")) {
            bookRepo = new BookRepoArrayImpl();
            authorRepo = new AuthorRepoArrayImpl();
        } else if (storageType.equals("collection")) {
            bookRepo = new BookRepoCollectionImpl();
            authorRepo = new AuthorRepoCollectionImpl();
        }

        AuthorService authorService = new AuthorServiceImpl(authorRepo, bookRepo);
        BookService bookService = new BookServiceImpl(authorRepo, bookRepo);
        initData(bookService, authorService);

        authorService.print();
        bookService.print();


        System.out.println("TOTAL");
        System.out.println("Authors: " + authorService.count() + "; Books: " + bookService.count());

        System.out.println("SORTING");
        authorService.defaultSort();
        bookService.defaultSort();
        authorService.sort(new LastNameComparator());
        bookService.sort(new NameComparator());
        bookService.print();
        authorService.print();

        System.out.println("DELETING");
        authorService.delete(authorService.find("Dyachenko")[0]);
        bookService.delete(bookService.find("Zolotaya rybka")[0]);

        authorService.print();
        bookService.printBookAndItsAuthor();

    }

    private static void initData(BookService bookService, AuthorService authorService) {
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
        author1.setBooks(mutableListOf(book1, book2));

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

        author2.setBooks(mutableListOf(book3));
        author3.setBooks(mutableListOf(book3));

        book1.setAuthors(mutableListOf(author1));
        book2.setAuthors(mutableListOf(author1));
        book3.setAuthors(mutableListOf(author2, author3));


        bookService.add(book1);
        bookService.add(book2);
        bookService.add(book3);

        authorService.add(author1);
        authorService.add(author2);
        authorService.add(author3);

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
