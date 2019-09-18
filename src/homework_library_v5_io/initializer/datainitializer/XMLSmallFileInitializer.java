package homework_library_v5_io.initializer.datainitializer;

import homework_library_v5_io.author.InputAuthor;
import homework_library_v5_io.author.domain.Author;
import homework_library_v5_io.book.InputBook;
import homework_library_v5_io.book.domain.Book;
import homework_library_v5_io.book.domain.BookType;
import homework_library_v5_io.common.utils.FileUtils;
import homework_library_v5_io.initializer.serviceinitializer.ServicesHolder;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import static homework_library_v5_io.initializer.datainitializer.InitUtils.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class XMLSmallFileInitializer extends BasicDataInitializer {
    public XMLSmallFileInitializer(ServicesHolder servicesHolder) {
        super(servicesHolder);
    }

    private static final String PATH = "/homework_library_v5_io/initializer/datainitializer/resources.xml";


    @Override
    public void initData() throws Exception {
        File fileWithInitData = FileUtils.createFileFromSource("java_homework", "any", PATH);
        if (isFileValid(fileWithInitData)) {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document document = documentBuilder.parse(fileWithInitData);

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
        Node root = document.getElementsByTagName("resources").item(0);
        NodeList libraryObjects = root.getChildNodes();
        for (int i = 0; i < libraryObjects.getLength(); i++) {
            Node inner = libraryObjects.item(i);
            String nodeName = inner.getNodeName();
            if ("book".equals(nodeName)) {
                InputBook inputBook = parseBook(inner);
                inputBooks.add(inputBook);
            }
        }
        return inputBooks;
    }

    private List<InputAuthor> getParsedAuthors(Document document) {
        List<InputAuthor> inputAuthors = new ArrayList<>();
        Node root = document.getElementsByTagName("resources").item(0);
        NodeList libraryObjects = root.getChildNodes();
        for (int i = 0; i < libraryObjects.getLength(); i++) {
            Node inner = libraryObjects.item(i);
            String nodeName = inner.getNodeName();
            if ("author".equals(nodeName)) {
                InputAuthor inputAuthor = parseAuthor(inner);
                inputAuthors.add(inputAuthor);
            }
        }
        return inputAuthors;
    }

    private static InputAuthor parseAuthor(Node node) {
        InputAuthor inputAuthor = new InputAuthor();
        NodeList authorInners = node.getChildNodes();
        for (int j = 0; j < authorInners.getLength(); j++) {
            Node authorInner = authorInners.item(j);
            String authorInnerNodeName = authorInner.getNodeName();
            switch (authorInnerNodeName) {
                case AuthorKeys.ID: {
                    inputAuthor.setID(authorInner.getTextContent());
                    break;
                }
                case AuthorKeys.LAST_NAME: {
                    inputAuthor.setLastName(authorInner.getTextContent());
                    break;
                }
                case AuthorKeys.NAME: {
                    inputAuthor.setName(authorInner.getTextContent());
                    break;
                }
                case AuthorKeys.YEAR_OF_BIRTH: {
                    try {
                        inputAuthor.setYearOfBorn(Integer.parseInt(authorInner.getTextContent()));
                    } catch (NumberFormatException e) {
                        throw new NumberFormatException("Incorrect year of birth '" + authorInner.getTextContent() + "'");
                    }
                    break;
                }
            }
        }
        return inputAuthor;
    }

    private static InputBook parseBook(Node node) {
        InputBook inputBook = new InputBook();
        NodeList bookInners = node.getChildNodes();
        for (int j = 0; j < bookInners.getLength(); j++) {
            Node bookInner = bookInners.item(j);
            String bookInnerNodeName = bookInner.getNodeName();
            switch (bookInnerNodeName) {
                case BookKeys.BOOK_PHYSIC_TYPE: {
                    inputBook.setBookPhysicType(bookInner.getTextContent());
                    break;
                }
                case BookKeys.NAME: {
                    inputBook.setName(bookInner.getTextContent());
                    break;
                }
                case BookKeys.YEAR_OF_PUBLISH: {
                    try {
                        inputBook.setPublishYear(Integer.parseInt(bookInner.getTextContent()));
                    } catch (NumberFormatException e) {
                        throw new NumberFormatException("Incorrect publish year '" + bookInner.getTextContent() + "'");
                    }
                    break;
                }
                case BookKeys.FONT_FAMILY: {
                    inputBook.setFontFamily(bookInner.getTextContent());
                    break;
                }
                case BookKeys.PAINT: {
                    inputBook.setPaint(bookInner.getTextContent());
                    break;
                }
                case BookKeys.AUTHORS: {
                    inputBook.getAuthorsRef().addAll(Arrays.asList(bookInner.getTextContent().split(",")));
                    break;
                }
                case BookKeys.BOOK_TYPE: {
                    try {
                        inputBook.setBookType(BookType.valueOf(bookInner.getTextContent().toUpperCase()));
                        break;
                    } catch (IllegalArgumentException e) {
                        throw new IllegalArgumentException("Unknown book type '" + bookInner.getTextContent() + "'");
                    }
                }
            }
        }
        return inputBook;
    }

    private static boolean isFileValid(File file) {
        return file != null && file.isFile() && file.exists();
    }


}
