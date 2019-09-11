package homework_library_v5_io;

import homework_library_v5_io.author.InputAuthor;
import homework_library_v5_io.author.domain.Author;
import homework_library_v5_io.author.repo.AuthorRepo;
import homework_library_v5_io.author.repo.AuthorRepoArrayImpl;
import homework_library_v5_io.author.repo.AuthorRepoCollectionImpl;
import homework_library_v5_io.author.repo.LastNameComparator;
import homework_library_v5_io.author.service.AuthorService;
import homework_library_v5_io.author.service.AuthorServiceImpl;
import homework_library_v5_io.book.InputBook;
import homework_library_v5_io.book.domain.Book;
import homework_library_v5_io.book.domain.BookType;
import homework_library_v5_io.book.domain.HandWritten;
import homework_library_v5_io.book.domain.Printed;
import homework_library_v5_io.book.repo.BookRepo;
import homework_library_v5_io.book.repo.BookRepoArrayImpl;
import homework_library_v5_io.book.repo.BookRepoCollectionImpl;
import homework_library_v5_io.book.repo.NameComparator;
import homework_library_v5_io.book.service.BookService;
import homework_library_v5_io.book.service.BookServiceImpl;
import homework_library_v5_io.common.init.InitType;

import static homework_library_v5_io.common.init.Initor.initData;

public class LibraryDemo {
    public static void main(String[] args) {
        //String storageType = "arrays";
        String storageType = "collection";
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
        //initData(InitType.RAM, bookService, authorService);
        initData(InitType.FROM_FILE, bookService, authorService);

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



}
