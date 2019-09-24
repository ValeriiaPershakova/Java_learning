package homework_library_v5_io.author.repo;

import homework_library_v5_io.author.domain.Author;
import homework_library_v5_io.author.service.LastNameComparator;
import homework_library_v5_io.book.domain.Book;
import homework_library_v5_io.storage.CollectionStorage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

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
    public void sort(List<Author> itemsToSort, Comparator<Author> comparator) {
        itemsToSort.sort(comparator);
    }

    @Override
    public Author[] find(String lastName) {
        List<Author> searchingAuthor = new ArrayList<>();

        for (Author author : CollectionStorage.getAllAuthors()) {
            if (author.getLastName().equals(lastName)) {
                searchingAuthor.add(author);
            }
        }

        return searchingAuthor.toArray(new Author[0]);
    }

    @Override
    public Author find(String lastName, String name) {
        Author author = null;
        for (Author a : CollectionStorage.getAllAuthors()) {
            if (a.getLastName().equals(lastName) && a.getName().equals(name)) {
                author = a;
            }
        }
        return author;
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
    public Author getById(Long authorId) {
        Author author = null;
        for (Author a : CollectionStorage.getAllAuthors()) {
            if (a.getId().equals(authorId)) {
                author = a;
            }
        }
        return author;

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
