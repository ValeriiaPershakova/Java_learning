package homework_library_v5_io.initializer.datainitializer;

import homework_library_v5_io.author.domain.Author;
import homework_library_v5_io.book.domain.Book;
import homework_library_v5_io.common.utils.CollectionUtils;

import java.util.List;

public class ParseResult {
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
