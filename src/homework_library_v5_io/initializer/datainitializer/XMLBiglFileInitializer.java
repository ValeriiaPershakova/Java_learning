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

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static homework_library_v5_io.initializer.datainitializer.InitUtils.*;

public class XMLBiglFileInitializer extends BasicDataInitializer {
    public XMLBiglFileInitializer(ServicesHolder servicesHolder) {
        super(servicesHolder);
    }

    private static final String PATH = "/homework_library_v5_io/initializer/datainitializer/resources.xml";


    @Override
    public void initData() throws Exception {
        File fileWithInitData = FileUtils.createFileFromSource("java_homework", "any", PATH);
        if (isFileValid(fileWithInitData)) {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            LibrarySaxHandler saxHandler = new LibrarySaxHandler();

            saxParser.parse(fileWithInitData,saxHandler);

            ParseResult parseResult = parseDocument(saxHandler);

            if (parseResult.hasAuthors()) {
                this.servicesHolder.getAuthorService().add(parseResult.authors);
            }
            if (parseResult.hasBooks()) {
                this.servicesHolder.getBookService().add(parseResult.books);
            }
        }
    }

    private ParseResult parseDocument(LibrarySaxHandler saxHandler) {
        List<InputAuthor> parsedAuthors = saxHandler.getInputAuthors();
        List<InputBook> parsedBooks = saxHandler.getInputBooks();

        Map<String, Author> authorsMap = valueOfInputAuthors(parsedAuthors);
        Map<InputBook, Book> booksMap = valueOfInputBooks(parsedBooks);

        linkAuthorsAndBooks(authorsMap, booksMap);
        return new ParseResult(new ArrayList<>(booksMap.values()), new ArrayList<>(authorsMap.values()));
    }


    private static boolean isFileValid(File file) {
        return file != null && file.isFile() && file.exists();
    }


}
