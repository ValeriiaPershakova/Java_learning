package homework_library_v4_generics.author.repo;


import homework_library_v4_generics.author.domain.Author;
import homework_library_v4_generics.book.domain.Book;
import homework_library_v4_generics.storage.ArrayStorage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AuthorRepoArrayImpl<T extends Author> implements AuthorRepo<T> {

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
    public void delete(T author) {
        ArrayStorage.removeAuthor(author);
    }

    @Override
    public Long add(T author) {
        ArrayStorage.addAuthor(author);
        return  (author).getId();
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
    public T getById(Long authorId) {
        T author = null;
        for (Author a : ArrayStorage.getAllAuthors()) {
            if (a.getId().equals(authorId)) {
                author = (T)a;
            }
        }
        return author;
    }
}
