package homework_library_v5_io.author.repo;


import homework_library_v5_io.author.domain.Author;
import homework_library_v5_io.book.domain.Book;
import homework_library_v5_io.common.utils.CollectionUtils;
import homework_library_v5_io.storage.ArrayStorage;

import java.util.*;
import java.util.function.Predicate;

public class AuthorRepoArrayImpl implements AuthorRepo {

    @Override
    public List<Author> getAll() {
        return CollectionUtils.mutableListOf(ArrayStorage.getAllAuthors());
    }

    @Override
    public int count() {
        return ArrayStorage.getTotalAuthors();
    }

    @Override
    public void print() {
        for (Author author : ArrayStorage.getAllAuthors()) {
            if (author != null) {
                System.out.println(author.toString());
            }
        }
    }

    @Override
    public void delete(Author author) {
        ArrayStorage.removeAuthor(author);
    }

    @Override
    public Long add(Author author) {
        ArrayStorage.addAuthor(author);
        return (author).getId();
    }

    //sort by Insertion
    @Override
    public void sort() {
        ArrayStorage.sortAuthors();
    }

    @Override
    public void sort(Comparator comparator) {
        ArrayStorage.sortAuthors(comparator);
    }


    @Override
    public Author[] find(String lastName) {
        List<Author> found = new ArrayList<>();
        for (Author a : ArrayStorage.getAllAuthors()) {
            if (a != null) {
                if (a.getLastName().equals(lastName)) {
                    found.add(a);
                }
            }
        }
        return found.toArray(new Author[0]);
    }

    @Override
    public Optional<Author> find(String lastName, String name) {
        Optional<Author> authorOptional = Optional.ofNullable(null);
        for (Author a : ArrayStorage.getAllAuthors()) {
            if (a != null) {
                if (a.getLastName().equals(lastName) && a.getName().equals(name)) {
                    authorOptional = Optional.of(a);
                }
            }
        }
        return authorOptional;
    }

    @Override
    public Author[] findAuthorsByBook(Long id) {
        List<Author> found = new ArrayList<>();

        for (Author author : ArrayStorage.getAllAuthors()) {
            if (author != null) {
                for (Book b : author.getBooks()) {
                    if (b.getId().equals(id)) {
                        found.add(author);
                        break;
                    }
                }
            }
        }

        return found.toArray(new Author[0]);
    }

    @Override
    public Optional<Author> getById(Long authorId) {
        Optional<Author> authorOptional = Optional.ofNullable(null);
        for (Author a : ArrayStorage.getAllAuthors()) {
            if (a.getId().equals(authorId)) {
                authorOptional = Optional.of(a);
            }
        }
        return authorOptional;
    }

    @Override
    public List<Author> find(List<Author> items, Predicate<Author> predicate) {
        List<Author> foundAuthors = new ArrayList<>();
        for (Author author : items) {
            if (predicate.test(author)) {
                foundAuthors.add(author);
            }
        }
        return foundAuthors;
    }
}
