package homework_library_v3_services.author.repo;

import homework_library_v3_services.author.domain.Author;
import homework_library_v3_services.book.domain.Book;
import homework_library_v3_services.Storage;

public class AuthorRepoArrayImpl implements AuthorRepo {


    @Override
    public int count() {
        int result = 0;

        for (Author author : Storage.authors) {
            if (author != null) {
                result++;
            }
        }
        return result;
    }

    @Override
    public void print() {
        for (Author author : Storage.authors) {
            if (author != null) {
                System.out.println(author.toString());
            }
        }
    }

    @Override
    public void delete(Author author) {
        Storage.removeAuthor(author);
    }

    @Override
    public Long add(Author author) {
        Storage.addAuthor(author);
        return author.getId();
    }

    //sort by Insertion
    @Override
    public void sort() {
        Author[] authors = Storage.authors;
        for (int left = 0; left < Storage.authorIndex; left++) {
            // take out the value of the element
            Author value = authors[left];
            // Look left (elements before taken)
            int i = left - 1;
            for (; i >= 0; i--) {
                // If the value is less, move the greater elements right
                if (value.getLastName().compareTo(authors[i].getLastName()) < 0) {
                    authors[i + 1] = authors[i];
                } else {
                    // If the value is bigger - stop
                    break;
                }
            }
            // Put the value to the new free space
            authors[i + 1] = value;
        }
        Storage.authors = authors;

    }

    @Override
    public Author[] find(String lastName) {
        Author[] author = new Author[5];
        int index = 0;
        for (Author a:Storage.authors) {
            if (a!=null) {
                if (a.getLastName().equals(lastName)) {
                    author[index] = a;
                    index++;
                }
            }
        }
        return author;
    }

    @Override
    public Author[] findAuthorsByBook(Long id) {
        Author[] authors = new Author[100];
        int index = 0;

        for (Author author : Storage.authors) {
            if (author!=null) {
                for (Book b : author.getBooks()) {
                    if (b.getId().equals(id)) {
                        authors[index] = author;
                        index++;
                        break;
                    }
                }
            }
        }

        return authors;
    }
}
