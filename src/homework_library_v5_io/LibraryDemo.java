package homework_library_v5_io;

import homework_library_v5_io.author.service.LastNameComparator;
import homework_library_v5_io.author.service.AuthorService;
import homework_library_v5_io.book.domain.Book;
import homework_library_v5_io.book.service.NameComparator;
import homework_library_v5_io.book.service.BookService;
import homework_library_v5_io.exporter.LibraryDataExport;
import homework_library_v5_io.initializer.serviceinitializer.ServiceInitializer;
import homework_library_v5_io.initializer.serviceinitializer.ServicesHolder;
import homework_library_v5_io.storage.StorageType;
import homework_library_v5_io.initializer.datainitializer.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static homework_library_v5_io.initializer.datainitializer.DataInitializerType.*;

public class LibraryDemo {
    public static void main(String[] args) {

        StorageType storageType = StorageType.COLLECTION;
        DataInitializerType dataInitializerType = FROM_XML_WITH_DOM;

        ServicesHolder servicesHolder = new ServiceInitializer().initServices(storageType);
        BasicDataInitializer dataInitializer = DataInitializerFactory.getDataInitializer(dataInitializerType, servicesHolder);
        try {
            dataInitializer.initData();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.exit(1);
        } catch (IOException e) {
            System.out.println("Incorrest path to file");
            System.exit(2);
        } catch (ParserConfigurationException | SAXException e) {
            System.out.println("Problems with parsing XML with SAX");
            System.exit(3);
        } catch (Exception e) {
            System.out.println("Incorrect file");
            e.printStackTrace();
            System.exit(4);
        }
        BookService bookService = servicesHolder.getBookService();
        AuthorService authorService = servicesHolder.getAuthorService();

        authorService.print();
        bookService.print();


        System.out.println("TOTAL");
        System.out.println("Authors: " + authorService.count() + "; Books: " + bookService.count());

        System.out.println("SORTING");
        authorService.defaultSort();
        bookService.defaultSort();
        authorService.sort(new LastNameComparator());
        bookService.sort(new NameComparator());
        //sorting by book name
        bookService.getAll().sort((b1, b2) -> b1.getName().compareTo(b2.getName()));
        bookService.print();
        //sorting by publish year
        bookService.getAll().sort((b1, b2) -> Integer.compare(b1.getPublishYear(), b2.getPublishYear()));
        bookService.print();
        authorService.print();

        System.out.println("DELETING");
        authorService.delete(authorService.find("Dyachenko")[0]);
        bookService.delete(bookService.find("Zolotaya rybka")[0]);

        authorService.print();
        bookService.printBookAndItsAuthor();

        //Search
        System.out.println("Searched books");
        System.out.println(bookService.findByName(bookService::find, "Ritual")[0].getName());
        System.out.println(bookService.findByYear((year -> {
            List<Book> books = new ArrayList<>();
            for (Book book : bookService.getAll()) {
                if (book.getPublishYear() == year) {
                    books.add(book);
                }
            }
            return books.toArray(new Book[0]);
        }), 18)[0].getName());

        Book searchBook = bookService.findBy((findBy, param) -> {
            List<Book> books = new ArrayList<>();
            switch (findBy) {
                case "name": {
                    for (Book book : bookService.getAll()) {
                        if (book.getName().equals(param)) {
                            books.add(book);
                        }
                    }
                    break;
                }
                case "year": {
                    for (Book book : bookService.getAll()) {
                        if (book.getPublishYear()== Integer.parseInt(param)) {
                            books.add(book);
                        }
                    }
                    break;
                }

            }
            return books.toArray(new Book[0]);
        }, "name", "Ritual")[0];
        System.out.println(searchBook.getName());


        String exportPath = "out/production/Java_learning/homework_library_v5_io/export.txt";
        try {
            LibraryDataExport.export(exportPath, servicesHolder);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(5);
        }

    }


}
