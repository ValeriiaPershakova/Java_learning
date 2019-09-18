package homework_library_v5_io.initializer.datainitializer;

import homework_library_v5_io.author.InputAuthor;
import homework_library_v5_io.author.domain.Author;
import homework_library_v5_io.book.InputBook;
import homework_library_v5_io.book.domain.BookType;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static homework_library_v5_io.initializer.datainitializer.InitUtils.*;

public class LibrarySaxHandler extends DefaultHandler {
    private List<InputAuthor> inputAuthors = new ArrayList<>();
    private List<InputBook> inputBooks = new ArrayList<>();
    private InputAuthor inputAuthor;
    private InputBook inputBook;
    private StringBuilder stringBuilder = new StringBuilder();

    private boolean inBook = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        switch (qName) {
            case "book": {
                inputBook = new InputBook();
                inBook = true;
                break;
            }
            case "author": {
                inputAuthor = new InputAuthor();
                break;
            }


        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        switch (qName) {
            case "book": {
                inputBooks.add(inputBook);
                inBook = false;
                break;
            }
            case "author": {
                inputAuthors.add(inputAuthor);
                break;
            }
            case BookKeys.BOOK_PHYSIC_TYPE: {
                inputBook.setBookPhysicType(stringBuilder.toString());
                break;
            }
            case BookKeys.NAME: {
                if (inBook) {
                    inputBook.setName(stringBuilder.toString());
                } else {
                    inputAuthor.setName(stringBuilder.toString());
                }
                break;
            }
            case BookKeys.YEAR_OF_PUBLISH: {
                if (inBook) {
                    inputBook.setPublishYear(Integer.parseInt(stringBuilder.toString()));
                } else {
                    inputAuthor.setYearOfBorn(Integer.parseInt(stringBuilder.toString()));
                }
                break;
            }
            case BookKeys.BOOK_TYPE: {
                try {
                    inputBook.setBookType(BookType.valueOf(stringBuilder.toString().toUpperCase()));
                    break;
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException("Unknown book type '" + stringBuilder.toString() + "'");
                }

            }
            case BookKeys.PAINT: {
                inputBook.setPaint(stringBuilder.toString());
                break;
            }
            case BookKeys.FONT_FAMILY: {
                inputBook.setFontFamily(stringBuilder.toString());
                break;
            }
            case BookKeys.AUTHORS: {
                inputBook.getAuthorsRef().addAll(Arrays.asList(stringBuilder.toString().split(",")));
                break;
            }
            case AuthorKeys.ID: {
                inputAuthor.setID(stringBuilder.toString());
                break;
            }
            case AuthorKeys.LAST_NAME: {
                inputAuthor.setLastName(stringBuilder.toString());
                break;
            }

        }
        stringBuilder = new StringBuilder();
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String data = new String(ch, start, length);
        stringBuilder.append(data.trim());
    }

    public List<InputAuthor> getInputAuthors() {
        return inputAuthors;
    }

    public void setInputAuthors(List<InputAuthor> inputAuthors) {
        this.inputAuthors = inputAuthors;
    }

    public List<InputBook> getInputBooks() {
        return inputBooks;
    }

    public void setInputBooks(List<InputBook> inputBooks) {
        this.inputBooks = inputBooks;
    }
}
