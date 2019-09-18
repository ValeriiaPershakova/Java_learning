package homework_library_v5_io.initializer.datainitializer;

import homework_library_v5_io.author.domain.Author;
import homework_library_v5_io.book.InputBook;
import homework_library_v5_io.book.domain.Book;
import homework_library_v5_io.common.utils.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class InitUtils {
    public static class AuthorKeys {
        public static final String ID = "id";
        public static final String LAST_NAME = "lastName";
        public static final String NAME = "name";
        public static final String YEAR_OF_BIRTH = "year";
    }

    public static class BookKeys {
        public static final String NAME = "name";
        public static final String BOOK_PHYSIC_TYPE = "bookPhysicType";
        public static final String BOOK_TYPE = "bookType";
        public static final String YEAR_OF_PUBLISH = "year";
        public static final String FONT_FAMILY = "fontFamily";
        public static final String PAINT = "paint";
        public static final String AUTHORS = "authorsRef";
    }
    public static class ParseResult {
        public final List<Book> books;
        public final List<Author> authors;

        public ParseResult(List<Book> books, List<Author> authors) {
            this.books = books;
            this.authors = authors;
        }

        public boolean hasAuthors() {
            return CollectionUtils.isNotBlank(authors);
        }

        public boolean hasBooks() {
            return CollectionUtils.isNotBlank(books);
        }
    }
    public static void linkAuthorsAndBooks(Map<String, Author> authorsMap, Map<InputBook, Book> booksMap) {// parseInt author ID to be sure authors_ref are correct?
        for (Map.Entry<InputBook, Book> bookEntry : booksMap.entrySet()) {
            InputBook inputBook = bookEntry.getKey();
            Set<String> authorIds = inputBook.getAuthorsRef();

            Book book = bookEntry.getValue();
            book.initAuthorsList();

            for (String authorId : authorIds) {
                Author author = authorsMap.get(authorId);
                if (author != null) {
                    author.initBooksList();
                    book.getAuthors().add(author);
                    author.getBooks().add(book);
                }
            }
        }
    }

}
