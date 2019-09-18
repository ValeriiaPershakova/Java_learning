package homework_library_v5_io.initializer.datainitializer;

import homework_library_v5_io.author.InputAuthor;
import homework_library_v5_io.author.domain.Author;
import homework_library_v5_io.book.InputBook;
import homework_library_v5_io.book.domain.Book;
import homework_library_v5_io.book.domain.BookType;
import homework_library_v5_io.common.utils.CollectionUtils;
import homework_library_v5_io.common.utils.FileUtils;
import homework_library_v5_io.initializer.serviceinitializer.ServicesHolder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static homework_library_v5_io.initializer.datainitializer.InitUtils.*;


public class TXTFileInitializer extends BasicDataInitializer {
    public TXTFileInitializer(ServicesHolder servicesHolder) {
        super(servicesHolder);
    }

    private static String PATH = "/homework_library_v5_io/initializer/datainitializer/resources.txt";


    private static class Separators {
        private static final String BOOK_SEP = "Book {";
        private static final String AUTHOR_SEP = "Author {";
    }


    @Override
    public void initData() throws Exception { //throws exception, finish program if not successfull

        File fileWithInitData = FileUtils.createFileFromSource("java_homework", "any", PATH);
        List<String> data = Files.readAllLines(Paths.get(fileWithInitData.getAbsolutePath()));
        ParseResult parseResult = parseFile(data);

        if (parseResult.hasAuthors()) {
            this.servicesHolder.getAuthorService().add(parseResult.authors);
        }
        if (parseResult.hasBooks()) {
            this.servicesHolder.getBookService().add(parseResult.books);
        }
    }

    private ParseResult parseFile(List<String> fileData) {
        List<InputAuthor> parsedAuthors = getParsedAuthors(fileData);
        List<InputBook> parsedBooks = getParsedBooks(fileData);

        Map<String, Author> authorsMap = valueOfInputAuthors(parsedAuthors);
        Map<InputBook, Book> booksMap = valueOfInputBooks(parsedBooks);

        linkAuthorsAndBooks(authorsMap, booksMap);
        return new ParseResult(new ArrayList<>(booksMap.values()), new ArrayList<>(authorsMap.values()));
    }


    private List<InputBook> getParsedBooks(List<String> fileData) {
        List<InputBook> books = new ArrayList<>();
        Iterator<String> iter = fileData.iterator();

        while (iter.hasNext()) {
            String line = iter.next();
            if (line.equals(Separators.BOOK_SEP)) {
                List<String> bookData = readIterSeveralTimesReturningResult(iter, 6);
                books.add(parseBook(bookData));
            }
        }
        return books;
    }

    private InputBook parseBook(List<String> bookData) { //throws exception with enum BookType, Integer.parseInt
        InputBook inputBook = new InputBook();

        for (String bookDataLine : bookData) {
            String key = bookDataLine.split("[=;]")[0];
            String value = bookDataLine.split("[=;]")[1];

            switch (key) {
                case BookKeys.BOOK_PHYSIC_TYPE: {
                    inputBook.setBookPhysicType(value);
                    break;
                }
                case BookKeys.NAME: {
                    inputBook.setName(value);
                    break;
                }
                case BookKeys.YEAR_OF_PUBLISH: {
                    try {
                        inputBook.setPublishYear(Integer.parseInt(value));
                    } catch (NumberFormatException e) {
                        throw new NumberFormatException("Incorrect publish year '" + value + "'");
                    }
                    break;
                }
                case BookKeys.FONT_FAMILY: {
                    inputBook.setFontFamily(value);
                    break;
                }
                case BookKeys.PAINT: {
                    inputBook.setPaint(value);
                    break;
                }
                case BookKeys.AUTHORS: {
                    inputBook.getAuthorsRef().addAll(Arrays.asList(value.split(",")));
                    break;
                }
                case BookKeys.BOOK_TYPE: {
                    try {
                        inputBook.setBookType(BookType.valueOf(value.toUpperCase()));
                        break;
                    } catch (IllegalArgumentException e) {
                        throw new IllegalArgumentException("Unknown book type '" + value + "'");
                    }
                }
            }
        }
        return inputBook;
    }

    private List<InputAuthor> getParsedAuthors(List<String> fileData) {
        List<InputAuthor> result = new ArrayList<>();

        Iterator<String> iter = fileData.iterator();
        while (iter.hasNext()) {
            String line = iter.next();
            if (line.equals(Separators.AUTHOR_SEP)) {
                List<String> authorData = readIterSeveralTimesReturningResult(iter, 4);
                result.add(parseAuthor(authorData));
            }
        }
        return result;
    }

    private InputAuthor parseAuthor(List<String> authorData) { //exception with split
        InputAuthor inputAuthor = new InputAuthor();
        for (String authorDataLine : authorData) {
            String key = authorDataLine.split("[=;]")[0];
            String value = authorDataLine.split("[=;]")[1];

            switch (key) {
                case AuthorKeys.ID: {
                    inputAuthor.setID(value);
                    break;
                }
                case AuthorKeys.LAST_NAME: {
                    inputAuthor.setLastName(value);
                    break;
                }
                case AuthorKeys.NAME: {
                    inputAuthor.setName(value);
                    break;
                }
                case AuthorKeys.YEAR_OF_BIRTH: {
                    try {
                        inputAuthor.setYearOfBorn(Integer.parseInt(value));
                    } catch (NumberFormatException e) {
                        throw new NumberFormatException("Incorrect year of birth '" + value + "'");
                    }
                    break;
                }

            }
        }
        return inputAuthor;
    }

    private List<String> readIterSeveralTimesReturningResult(Iterator<String> iter, int numberOfCall) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < numberOfCall; i++) {
            result.add(iter.next());
        }
        return result;
    }


}

