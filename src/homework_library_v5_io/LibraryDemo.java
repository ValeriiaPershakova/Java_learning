package homework_library_v5_io;

import homework_library_v5_io.author.repo.LastNameComparator;
import homework_library_v5_io.author.service.AuthorService;
import homework_library_v5_io.book.repo.NameComparator;
import homework_library_v5_io.book.service.BookService;
import homework_library_v5_io.exporter.LibraryDataExport;
import homework_library_v5_io.initializer.serviceinitializer.ServiceInitializer;
import homework_library_v5_io.initializer.serviceinitializer.ServicesHolder;
import homework_library_v5_io.storage.StorageType;
import homework_library_v5_io.initializer.datainitializer.*;
import org.omg.CORBA.WStringSeqHelper;

import java.io.FileNotFoundException;
import java.io.IOException;

import static homework_library_v5_io.initializer.datainitializer.DataInitializerType.*;

public class LibraryDemo {
    public static void main(String[] args) {

        StorageType storageType = StorageType.COLLECTION;
        DataInitializerType dataInitializerType = FROM_XML_FILE_BIG;

        ServicesHolder servicesHolder = new ServiceInitializer().initServices(storageType);
        BasicDataInitializer dataInitializer = DataInitializerFactory.getDataInitializer(dataInitializerType, servicesHolder);
        try {
            dataInitializer.initData();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.exit(2);
        } catch (IOException e) {
            System.out.println("Incorrest path to file");
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
        bookService.print();
        authorService.print();

        System.out.println("DELETING");
        authorService.delete(authorService.find("Dyachenko")[0]);
        bookService.delete(bookService.find("Zolotaya rybka")[0]);

        authorService.print();
        bookService.printBookAndItsAuthor();

        String exportPath = "out/production/Java_learning/homework_library_v5_io/export.txt";
        try {
            LibraryDataExport.export(exportPath, servicesHolder);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(5);
        }

    }


}
