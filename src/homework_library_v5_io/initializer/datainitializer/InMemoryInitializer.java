package homework_library_v5_io.initializer.datainitializer;

import homework_library_v5_io.author.InputAuthor;
import homework_library_v5_io.author.domain.Author;
import homework_library_v5_io.author.service.AuthorService;
import homework_library_v5_io.book.InputBook;
import homework_library_v5_io.book.domain.Book;
import homework_library_v5_io.book.domain.BookType;
import homework_library_v5_io.book.service.BookService;
import homework_library_v5_io.initializer.serviceinitializer.ServicesHolder;

import static homework_library_v5_io.common.utils.CollectionUtils.mutableListOf;

public class InMemoryInitializer extends BasicDataInitializer {
    private final BookService bookService;
    private final AuthorService authorService;

    public InMemoryInitializer(ServicesHolder servicesHolder) {
        super(servicesHolder);
        this.bookService = servicesHolder.getBookService();
        this.authorService = servicesHolder.getAuthorService();
    }

    @Override
    public void initData() {
        InputBook inputBook1 = new InputBook();
        inputBook1.setName("Zolotaya rybka");
        inputBook1.setPublishYear(11);
        inputBook1.setBookType(BookType.FICTION);
        inputBook1.setPaint("Ink");
        Book book1 = valueOfInputHandWrittenBook(inputBook1);

        InputBook inputBook2 = new InputBook();
        inputBook2.setName("Rusla and Ludmila");
        inputBook2.setPublishYear(11);
        inputBook2.setBookType(BookType.ROMANTIC);
        inputBook2.setFontFamily("Times new roman");
        Book book2 = valueOfInputPrintedBook(inputBook2);

        InputBook inputBook3 = new InputBook();
        inputBook3.setName("Ritual");
        inputBook3.setPublishYear(18);
        inputBook3.setBookType(BookType.FICTION);
        inputBook3.setFontFamily("Times new roman");
        Book book3 = valueOfInputPrintedBook(inputBook3);

        InputAuthor inputAuthor1 = new InputAuthor();
        inputAuthor1.setLastName("Pushkin");
        inputAuthor1.setYearOfBorn(22);
        Author author1 = valueOfInputAuthor(inputAuthor1);
        author1.setBooks(mutableListOf(book1, book2));

        InputAuthor inputAuthor2 = new InputAuthor();
        inputAuthor2.setLastName("Dyachenko");
        inputAuthor2.setName("Marina");
        inputAuthor2.setYearOfBorn(22);
        Author author2 = valueOfInputAuthor(inputAuthor2);

        InputAuthor inputAuthor3 = new InputAuthor();
        inputAuthor3.setLastName("Dyachenko");
        inputAuthor3.setName("Sergei");
        inputAuthor3.setYearOfBorn(22);
        Author author3 = valueOfInputAuthor(inputAuthor3);

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
}
