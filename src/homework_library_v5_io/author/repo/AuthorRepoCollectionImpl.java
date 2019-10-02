package homework_library_v5_io.author.repo;

import homework_library_v5_io.author.domain.Author;
import homework_library_v5_io.author.service.LastNameComparator;
import homework_library_v5_io.book.domain.Book;
import homework_library_v5_io.storage.CollectionStorage;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AuthorRepoCollectionImpl implements AuthorRepo {
    @Override
    public List<Author> getAll() {
        return CollectionStorage.getAllAuthors();
    }

    @Override
    public int count() {
        return CollectionStorage.getTotalAuthors();
    }

    @Override
    public void print() {
        System.out.println(CollectionStorage.getAllAuthors().toString());
    }

    @Override
    public void delete(Author author) {
        CollectionStorage.removeAuthor(author);
    }

    @Override
    public Long add(Author author) {
        CollectionStorage.addAuthor(author);
        return author.getId();
    }

    @Override
    public void sort() {
        CollectionStorage.getAllAuthors().sort(new LastNameComparator());
    }

    @Override
    public void sort(Comparator comparator) {
        CollectionStorage.getAllAuthors().sort(comparator);
    }


    @Override
    public Author[] find(String lastName) {
        List<Author> searchingAuthor = CollectionStorage.getAllAuthors().stream()
                .filter(author -> lastName.equals(author.getLastName()))
                .collect(Collectors.toList());
        return searchingAuthor.toArray(new Author[0]);
    }

    @Override
    public Optional<Author> find(String lastName, String name) {
        Optional<Author> authorOptional = CollectionStorage.getAllAuthors().stream()
                .filter(author -> lastName.equals(author.getLastName()) && name.equals(author.getName()))
                .findFirst();
        return authorOptional;
    }

    @Override
    public Author[] findAuthorsByBook(Long id) {
        List<Author> searchingAuthor = new ArrayList<>();

        for (Author author : CollectionStorage.getAllAuthors()) {
            for (Book b : author.getBooks()) {
                if (b.getId().equals(id)) {
                    searchingAuthor.add(author);
                    break;
                }
            }
        }

        return searchingAuthor.toArray(new Author[0]);
    }

    @Override
    public Optional<Author> getById(Long authorId) {
        Optional<Author> authorOptional = CollectionStorage.getAllAuthors().stream()
                .filter(author -> authorId.equals(author.getId()))
                .findFirst();
        return authorOptional;

    }

    @Override
    public List<Author> find(List<Author> items, Predicate<Author> predicate) {
        List<Author> foundAuthors = items.stream()
                .filter(predicate)
                .collect(Collectors.toList());
        return foundAuthors;
    }
}
