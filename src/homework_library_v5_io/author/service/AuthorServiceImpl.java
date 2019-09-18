package homework_library_v5_io.author.service;

import homework_library_v5_io.author.domain.Author;
import homework_library_v5_io.author.repo.AuthorRepo;
import homework_library_v5_io.book.domain.Book;
import homework_library_v5_io.book.repo.BookRepo;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepo authorRepo;
    private final BookRepo bookRepo;

    public AuthorServiceImpl(AuthorRepo authorRepo,
                             BookRepo bookRepo) {
        this.authorRepo = authorRepo;
        this.bookRepo = bookRepo;
    }

    @Override
    public List<Author> getAll() {
        return authorRepo.getAll();
    }

    @Override
    public int count() {
        return authorRepo.count();
    }

    @Override
    public void print() {
        authorRepo.print();
    }


    @Override
    public void delete(Author author) {
        if (author!=null) {
            Book[] booksWithAuthor = bookRepo.findBooksByAuthorAsArray(author.getId());

            if (booksWithAuthor != null) {
                for (Book book : booksWithAuthor) {
                    if (book != null) {
                        book.deleteAuthor(author);

                        if (book.withoutAuthors()) {
                            bookRepo.delete(book);
                        }
                    }
                }
            }

            authorRepo.delete(author);
        }
    }

    @Override
    public Long add(Author author) {
        return authorRepo.add(author);
    }

    @Override
    public void add(Collection<Author> authors) {
        for (Author author : authors) {
            add(author);
        }
    }

    @Override
    public void defaultSort() {
        authorRepo.sort();
    }

    @Override
    public void sort(Comparator comparator) {
        authorRepo.sort(comparator);
    }
    @Override
    public Author[] find(String lastName) {
        return authorRepo.find(lastName);
    }
    public Author find(String lastName, String name) {
        return authorRepo.find(lastName,name);
    }

    @Override
    public Author getById(Long authorId) {
        return authorRepo.getById(authorId);
    }
}
