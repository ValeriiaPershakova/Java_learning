package homework_library_v4_generics.storage;

import homework_library_v4_generics.author.domain.Author;
import homework_library_v4_generics.book.domain.Book;

import java.util.Arrays;
import java.util.Comparator;

public final class ArrayStorage {
    private static final int CAPACITY = 10;

    private static Book[] books = new Book[CAPACITY];
    private static int bookIndex = 0;

    private static Author[] authors = new Author[CAPACITY];
    private static int authorIndex = 0;


    private ArrayStorage() {
    }
//---------Books-------------------------------------------------------

    public static Book[] getAllBooks() {
        return books;
    }

    public static int getTotalBooks() {
        return bookIndex;
    }

    public static void addBook(Book book) {
        book.setId(IdGenerator.generateId());
        if ((bookIndex % CAPACITY) == 0 && bookIndex != 0) {
            increaseBookStorage();
        }
        books[bookIndex] = book;
        bookIndex++;
    }

    public static void removeBook(Book book) {
        for (int i = 0; i < books.length; i++) {

            if (book.getId().equals(books[i].getId())) {
                books[i] = null;
                bookIndex--;
                break;
            }
        }

        Book[] newBooks = new Book[books.length];
        int index = 0;
        for (Book b : books) {
            if (b != null) {
                newBooks[index] = b;
                index++;
            }
        }

        books = newBooks;
    }

    public static void increaseBookStorage() {
        Book[] books = new Book[bookIndex + CAPACITY];
        System.arraycopy(ArrayStorage.books, 0, books, 0, bookIndex);
        ArrayStorage.books = books;
    }

    //Sort book by Name
    public static void sortBooks() {
        Book[] books = ArrayStorage.books;
        for (int left = 0; left < ArrayStorage.bookIndex; left++) {
            // Вытаскиваем значение элемента
            Book value = books[left];
            // Look left (elements before taken)
            int i = left - 1;
            for (; i >= 0; i--) {
                // If the value is less, move the greater elements right
                if (value.getName().compareTo(books[i].getName()) < 0) {
                    books[i + 1] = books[i];
                } else {
                    //If the value is bigger - stop
                    break;
                }
            }
            // Put the value to the new free space
            books[i + 1] = value;
        }
        ArrayStorage.books = books;
    }
    public static void sortBooks(Comparator comparator) {
        Arrays.sort(ArrayStorage.books, comparator);
    }

//--------Author-----------------------------------------------------

    public static Author[] getAllAuthors() {
        return authors;
    }

    public static int getTotalAuthors() {
        return authorIndex;
    }

    public static void addAuthor(Author author) {
        author.setId(IdGenerator.generateId());
        if ((authorIndex % CAPACITY) == 0 && authorIndex != 0) {
            increaseAuthorsStorage();
        }
        authors[authorIndex] = author;
        authorIndex++;
    }

    public static void increaseAuthorsStorage() {

        Author[] authors = new Author[authorIndex + CAPACITY];
        System.arraycopy(ArrayStorage.authors, 0, authors, 0, authorIndex);
        ArrayStorage.authors = authors;
    }

    public static void removeAuthor(Author author) {

        for (int i = 0; i < authors.length; i++) {

            if (author!=null && author.getId().equals(authors[i].getId())) {
                authors[i] = null;
                authorIndex--;
                break;
            }

        }
        Author[] newAuthors = new Author[ArrayStorage.authors.length];
        int index = 0;
        for (Author a : authors) {
            if (a != null) {
                newAuthors[index] = a;
                index++;
            }
        }

        authors = newAuthors;
    }

    //Sort authors by Last name
    public static void sortAuthors() {
        Author[] authors = ArrayStorage.authors;
        for (int left = 0; left < authorIndex; left++) {
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
        ArrayStorage.authors = authors;
    }
    public static void sortAuthors(Comparator<Author> comparator) {
        Arrays.sort(ArrayStorage.authors, comparator);
    }
}
