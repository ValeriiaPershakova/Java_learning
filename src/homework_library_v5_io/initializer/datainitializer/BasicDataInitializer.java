package homework_library_v5_io.initializer.datainitializer;

import homework_library_v5_io.author.InputAuthor;
import homework_library_v5_io.author.domain.Author;
import homework_library_v5_io.book.InputBook;
import homework_library_v5_io.book.domain.Book;
import homework_library_v5_io.book.domain.HandWritten;
import homework_library_v5_io.book.domain.Printed;
import homework_library_v5_io.initializer.serviceinitializer.ServicesHolder;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BasicDataInitializer {
    protected final ServicesHolder servicesHolder;

    protected BasicDataInitializer(ServicesHolder servicesHolder) {
        this.servicesHolder = servicesHolder;
    }

    public abstract void initData() throws Exception; //throws Exception;

    protected static Author valueOfInputAuthor(InputAuthor inputAuthor) {
        Author author = new Author(null);
        author.setLastName(inputAuthor.getLastName());
        author.setName(inputAuthor.getName());
        author.setYearOfBorn(inputAuthor.getYearOfBorn());
        return author;
    }

    protected static Book valueOfInputHandWrittenBook(InputBook inputBook) {
        HandWritten book = new HandWritten();
        book.setName(inputBook.getName());
        book.setPublishYear(inputBook.getPublishYear());
        book.setBookType(inputBook.getBookType());
        book.setPaint(inputBook.getPaint());

        return book;
    }

    protected static Book valueOfInputPrintedBook(InputBook inputBook) {
        Printed book = new Printed();
        book.setName(inputBook.getName());
        book.setPublishYear(inputBook.getPublishYear());
        book.setBookType(inputBook.getBookType());
        book.setFontFamily(inputBook.getFontFamily());

        return book;
    }
    protected Map<String, Author> valueOfInputAuthors(Collection<InputAuthor> parsedAuthors) {
        Map<String, Author> result = new HashMap<>(); //почему Linked а не просто HashMap
        for (InputAuthor parsedAuthor : parsedAuthors) {
            result.put(parsedAuthor.getID(), valueOfInputAuthor(parsedAuthor));
        }
        return result;
    }
    protected Map<InputBook, Book> valueOfInputBooks(List<InputBook> parsedBooks) {
        Map<InputBook, Book> booksMap = new HashMap<>();
        for (InputBook book:parsedBooks) {
            booksMap.put(book, valueOfInputBook(book));
        }
        return booksMap;
    }

    private Book valueOfInputBook(InputBook inputBook){ //throws Exception if type doesn't match. Or don't need? (enum)
        if ("Printed".equals(inputBook.getBookPhysicType())) {
            return valueOfInputPrintedBook(inputBook);
        } else if ("HandWritten".equals(inputBook.getBookPhysicType())) {
            return valueOfInputHandWrittenBook(inputBook);
        } else {return null;}/*
        else {
        throw new Exception ("Unknown book physic type '" + inputBook.getBookPhysicType()+"'");*/
    }
}
