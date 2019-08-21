package homework_library_v3_services.author.repo;

import homework_library_v3_services.author.domain.Author;
import homework_library_v3_services.StorageList;
import homework_library_v3_services.book.domain.Book;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AuthorRepoCollectionImpl implements AuthorRepo {
    @Override
    public int count() {
        return StorageList.authors.size();
    }

    @Override
    public void print() {
        System.out.println(StorageList.authors.toString());
    }

    @Override
    public void delete(Author author) {
        StorageList.removeAuthor(author);
    }

    @Override
    public Long add(Author author) {
        StorageList.addAuthor(author);
        return author.getId();
    }

    @Override
    public void sort() {
        StorageList.authors.sort(new LastNameComparator());
    }

    @Override
    public Author[] find(String lastName) {
        List<Author> searchingAuthor = new ArrayList<>();
        Iterator<Author> iter = StorageList.authors.iterator();
        while (iter.hasNext()) {
            Author author = iter.next();
            if (author.getLastName().equals(lastName)) {
                searchingAuthor.add(author);
            }
        }
        Author[] authorsArray = searchingAuthor.toArray(new Author[0]);
        return authorsArray;
    }

    @Override
    public Author[] findAuthorsByBook(Long id) {
        List<Author> searchingAuthor = new ArrayList<>();
        Iterator<Author> authorIterator = StorageList.authors.iterator();
        while (authorIterator.hasNext()) {
            Author author = authorIterator.next();
            for (Book b : author.getBooks()) {
                if (b.getId().equals(id)) {
                    searchingAuthor.add(author);
                    break;
                }
            }
        }
        Author[] authorsArray = searchingAuthor.toArray(new Author[0]);
        return authorsArray;
    }
}
