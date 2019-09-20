package homework_library_v5_io.initializer.datainitializer.xml;

import homework_library_v5_io.author.InputAuthor;
import homework_library_v5_io.author.domain.Author;
import homework_library_v5_io.book.InputBook;
import homework_library_v5_io.book.domain.Book;
import homework_library_v5_io.book.domain.BookType;
import homework_library_v5_io.common.utils.FileUtils;
import homework_library_v5_io.initializer.datainitializer.BasicDataInitializer;
import homework_library_v5_io.initializer.datainitializer.ParseResult;
import homework_library_v5_io.initializer.serviceinitializer.ServicesHolder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


import static homework_library_v5_io.common.utils.InitUtils.*;
import static homework_library_v5_io.common.utils.xml.dom.XmlDomUtils.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class XmlDomInitializer extends BasicDataInitializer {
    public XmlDomInitializer(ServicesHolder servicesHolder) {
        super(servicesHolder);
    }

    private static final String PATH = "/homework_library_v5_io/initializer/datainitializer/resources.xml";


    @Override
    public void initData() throws Exception {
        File fileWithInitData = FileUtils.createFileFromSource("java_homework_XmlDom", "any", PATH);
        if (FileUtils.isFileValid(fileWithInitData)) {
            Document document = getDocument(fileWithInitData);

            ParseResult parseResult = parseDocument(document);

            if (parseResult.hasAuthors()) {
                this.servicesHolder.getAuthorService().add(parseResult.authors);
            }
            if (parseResult.hasBooks()) {
                this.servicesHolder.getBookService().add(parseResult.books);
            }
        }
    }


    private ParseResult parseDocument(Document document) {
        List<InputAuthor> parsedAuthors = getParsedAuthors(document);
        List<InputBook> parsedBooks = getParsedBooks(document);

        Map<String, Author> authorsMap = valueOfInputAuthors(parsedAuthors);
        Map<InputBook, Book> booksMap = valueOfInputBooks(parsedBooks);

        linkAuthorsAndBooks(authorsMap, booksMap);
        return new ParseResult(new ArrayList<>(booksMap.values()), new ArrayList<>(authorsMap.values()));
    }

    private List<InputBook> getParsedBooks(Document document) {
        List<InputBook> inputBooks = new ArrayList<>();
        Element root = getOneElement(document, "library");
        NodeList books = root.getElementsByTagName("book");
        for (int i = 0; i < books.getLength(); i++) {
            Node inner = books.item(i);
            InputBook inputBook = parseBook(inner);
            inputBooks.add(inputBook);
        }
        return inputBooks;
    }

    private List<InputAuthor> getParsedAuthors(Document document) {
        List<InputAuthor> inputAuthors = new ArrayList<>();
        Element root = getOneElement(document, "library");
        NodeList authors = root.getElementsByTagName("author");
        for (int i = 0; i < authors.getLength(); i++) {
            Node inner = authors.item(i);
            InputAuthor inputAuthor = parseAuthor(inner);
            inputAuthors.add(inputAuthor);
        }
        return inputAuthors;
    }

    private static InputAuthor parseAuthor(Node xmlAuthor) {
        InputAuthor inputAuthor = new InputAuthor();
        inputAuthor.setID(getOneElementTextContent((Element) xmlAuthor, AuthorKeys.ID));
        inputAuthor.setLastName(getOneElementTextContent((Element) xmlAuthor, AuthorKeys.LAST_NAME));
        inputAuthor.setName(getOneElementTextContent((Element) xmlAuthor, AuthorKeys.NAME));
        try {
            inputAuthor.setYearOfBorn(Integer.parseInt(getOneElementTextContent((Element) xmlAuthor, AuthorKeys.YEAR_OF_BIRTH)));
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Incorrect year of birth for author" + inputAuthor.getLastName() + " " + inputAuthor.getName());
        }

        return inputAuthor;
    }

    private static InputBook parseBook(Node xmlBook) {
        InputBook inputBook = new InputBook();
        inputBook.setName(getOneElementTextContent((Element) xmlBook, BookKeys.NAME));
        inputBook.setBookPhysicType(getOneElementTextContent((Element) xmlBook, BookKeys.BOOK_PHYSIC_TYPE));
        switch (inputBook.getBookPhysicType()) {
            case "HandWritten": {
                inputBook.setPaint(getOneElementTextContent((Element) xmlBook, BookKeys.PAINT));
                break;
            }
            case"Printed":{
                inputBook.setFontFamily(getOneElementTextContent((Element) xmlBook, BookKeys.FONT_FAMILY));
            }
        }

        inputBook.getAuthorsRef().addAll(Arrays.asList(getOneElementTextContent((Element) xmlBook, BookKeys.AUTHORS).split(",")));
        try {
            inputBook.setPublishYear(Integer.parseInt(getOneElementTextContent((Element) xmlBook, BookKeys.YEAR_OF_PUBLISH)));
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Incorrect publish year for book " + inputBook.getName());
        }
        try {
            inputBook.setBookType(BookType.valueOf(getOneElementTextContent((Element) xmlBook, BookKeys.BOOK_TYPE).toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unknown book type for book" + inputBook.getName());
        }

        return inputBook;
    }


}
