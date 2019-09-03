package homework_library_v4_generics.author.repo;

import homework_library_v4_generics.author.domain.Author;
import homework_library_v4_generics.book.domain.Book;
import homework_library_v4_generics.storage.CollectionStorage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AuthorRepoCollectionImpl<T extends Author> implements AuthorRepo<T> {
    @Override
    public int count() {
        return CollectionStorage.getTotalAuthors();
    }

    @Override
    public void print() {
        System.out.println(CollectionStorage.getAllAuthors().toString());
    }

    @Override
    public void delete(T author) {
        CollectionStorage.removeAuthor(author);
    }

    @Override
    public Long add(T author) {
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
        List<Author> searchingAuthor = new ArrayList<>();

        for (Author author : CollectionStorage.getAllAuthors()) {
            if (author.getLastName().equals(lastName)) {
                searchingAuthor.add(author);
            }
        }

        return searchingAuthor.toArray(new Author[0]);
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
    public T getById(Long authorId) {
        T author = null;
        for (Author a : CollectionStorage.getAllAuthors()) {
            if (a.getId().equals(authorId)) {
                author = (T)a;
            }
        }
        return author;

    }
}
