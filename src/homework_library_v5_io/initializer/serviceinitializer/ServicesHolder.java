package homework_library_v5_io.initializer.serviceinitializer;

import homework_library_v5_io.author.service.AuthorService;
import homework_library_v5_io.book.service.BookService;

public class ServicesHolder {
    private final BookService bookService;
    private final AuthorService authorService;

    public ServicesHolder(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    public BookService getBookService() {
        return bookService;
    }

    public AuthorService getAuthorService() {
        return authorService;
    }
}
