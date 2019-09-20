package homework_library_v5_io.initializer.datainitializer.xml;

import homework_library_v5_io.author.InputAuthor;
import homework_library_v5_io.book.InputBook;
import homework_library_v5_io.book.domain.BookType;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.*;

import static homework_library_v5_io.common.utils.InitUtils.*;

public class LibrarySaxHandler extends DefaultHandler {
    private static final String LIBRARY_PATH = "library";
    private static final String AUTHOR_PATH = LIBRARY_PATH + "/author";
    private static final String AUTHOR_NAME_PATH = AUTHOR_PATH + "/" + AuthorKeys.NAME;
    private static final String AUTHOR_LAST_NAME_PATH = AUTHOR_PATH + "/" + AuthorKeys.LAST_NAME;
    private static final String AUTHOR_YEAR_OF_BORN_PATH = AUTHOR_PATH + "/" + AuthorKeys.YEAR_OF_BIRTH;
    private static final String AUTHOR_ID_PATH = AUTHOR_PATH + "/" + AuthorKeys.ID;


    private static final String BOOK_PATH = LIBRARY_PATH + "/book";
    private static final String BOOK_PHYSIC_TYPE_PATH = BOOK_PATH + "/" + BookKeys.BOOK_PHYSIC_TYPE;
    private static final String BOOK_NAME_PATH = BOOK_PATH + "/" + BookKeys.NAME;
    private static final String BOOK_TYPE_PATH = BOOK_PATH + "/" + BookKeys.BOOK_TYPE;
    private static final String BOOK_PULBISH_YEAR_PATH = BOOK_PATH + "/" + BookKeys.YEAR_OF_PUBLISH;
    private static final String BOOK_FONTFAMILY_PATH = BOOK_PATH + "/" + BookKeys.FONT_FAMILY;
    private static final String BOOK_PAINT_PATH = BOOK_PATH + "/" + BookKeys.PAINT;
    private static final String BOOK_AUTHORS_PATH = BOOK_PATH + "/" + BookKeys.AUTHORS;


    private List<InputAuthor> inputAuthors = new ArrayList<>();
    private List<InputBook> inputBooks = new ArrayList<>();
    private InputAuthor inputAuthor;
    private InputBook inputBook;

    private StringBuilder content = new StringBuilder();
    private Deque<String> tagStack = new ArrayDeque<>();


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        content.setLength(0);
        tagStack.add(qName);

        switch (stackAsStringPath()) {
            case BOOK_PATH: {
                inputBook = new InputBook();
                break;
            }
            case AUTHOR_PATH: {
                inputAuthor = new InputAuthor();
                break;
            }


        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        String dataAsStr = content.toString();
        switch (stackAsStringPath()) {
            case BOOK_PATH: {
                inputBooks.add(inputBook);
                break;
            }
            case AUTHOR_PATH: {
                inputAuthors.add(inputAuthor);
                break;
            }
            case BOOK_PHYSIC_TYPE_PATH: {
                inputBook.setBookPhysicType(dataAsStr);
                break;
            }
            case BOOK_NAME_PATH: {
                inputBook.setName(dataAsStr);
                break;
            }
            case BOOK_PULBISH_YEAR_PATH: {
                try {
                    inputBook.setPublishYear(Integer.parseInt(dataAsStr));
                    break;
                } catch (NumberFormatException e) {
                    throw new NumberFormatException("Incorrect publish year for book" + inputBook.getName());
                }

            }
            case BOOK_TYPE_PATH: {
                try {
                    inputBook.setBookType(BookType.valueOf(dataAsStr.toUpperCase()));
                    break;
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException("Unknown book type '" + dataAsStr + "'");
                }
            }
            case BOOK_PAINT_PATH: {
                inputBook.setPaint(dataAsStr);
                break;
            }
            case BOOK_FONTFAMILY_PATH: {
                inputBook.setFontFamily(dataAsStr);
                break;
            }
            case BOOK_AUTHORS_PATH: {
                inputBook.getAuthorsRef().addAll(Arrays.asList(dataAsStr.split(",")));
                break;
            }
            case AUTHOR_ID_PATH: {
                inputAuthor.setID(dataAsStr);
                break;
            }
            case AUTHOR_LAST_NAME_PATH: {
                inputAuthor.setLastName(dataAsStr);
                break;
            }
            case AUTHOR_NAME_PATH: {
                inputAuthor.setName(dataAsStr);
                break;
            }
            case AUTHOR_YEAR_OF_BORN_PATH: {
                try {
                    inputAuthor.setYearOfBorn(Integer.parseInt(dataAsStr));
                } catch (NumberFormatException e) {
                    throw new NumberFormatException("Incorrect year of birth for author" + inputAuthor.getName());
                }
                break;
            }

        }
        tagStack.removeLast();

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String data = new String(ch, start, length);
        content.append(data.replaceAll("\\n", ""));
    }

    public List<InputAuthor> getInputAuthors() {
        return inputAuthors;
    }


    public List<InputBook> getInputBooks() {
        return inputBooks;
    }


    private String stackAsStringPath() {
        StringBuilder fullPath = new StringBuilder();

        Iterator<String> iter = tagStack.iterator();
        while (iter.hasNext()) {
            String tag = iter.next();
            fullPath.append(tag);

            if (iter.hasNext()) {
                fullPath.append("/");
            }
        }

        return fullPath.toString();
    }
}
