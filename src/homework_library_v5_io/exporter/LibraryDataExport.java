package homework_library_v5_io.exporter;

import homework_library_v5_io.author.domain.Author;
import homework_library_v5_io.author.service.AuthorService;
import homework_library_v5_io.book.domain.Book;
import homework_library_v5_io.book.service.BookService;
import homework_library_v5_io.initializer.serviceinitializer.ServicesHolder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LibraryDataExport {
    public static void export(String PATH, ServicesHolder servicesHolder) throws IOException {
        if (!Files.exists(Paths.get(PATH))) {
            try {
                Files.createFile(Paths.get(PATH));
            } catch (IOException e) {
                throw new IOException("Incorrect path for export file");
            }
        }
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(PATH))) {
            BookService bookService = servicesHolder.getBookService();
            AuthorService authorService = servicesHolder.getAuthorService();
            for (Book book : bookService.getAll()) {
                writer.print(book.forExport());
            }
            for (Author author : authorService.getAll()) {
                writer.print(author.forExport());
            }

        }
    }


}
