package Homework_3_Library_v2;

public class Storage {
    public static final int CAPACITY = 10;
    public static Book[] books = new Book[CAPACITY];
    public static int bookIndex = 0;

    public static void increaseBookIndex() {
        bookIndex++;
    }

    public static Author[] authors = new Author[CAPACITY];
    public static int authorIndex = 0;

    public static void increaseAuthorIndex() {
        authorIndex++;
    }

    public static void increaseAuthorsStorage() {

        Author[] authors = new Author[authorIndex + CAPACITY];
        System.arraycopy(Storage.authors,0,authors,0,authorIndex);
        /*for (int i = 0; i < Storage.authors.length; i++) {
            authors[i] = Storage.authors[i];
        }*/

        Storage.authors = authors;
    }

    public static void addAuthor(Author author) {
        author.setId(System.currentTimeMillis());

        if (authorIndex % (CAPACITY) == 0 && authorIndex != 0) {
            increaseAuthorsStorage();
            authors[authorIndex] = author;
        } else {
            authors[authorIndex] = author;
        }

        Storage.increaseAuthorIndex();
    }
    public static void addBook(Book book) {
        book.setId(System.currentTimeMillis());

        if ((bookIndex % CAPACITY) == 0 && bookIndex != 0) {
            increaseBookStorage();
            books[bookIndex] = book;
        } else {
            books[bookIndex]=book;
        }
        increaseBookIndex();
    }
    public static void increaseBookStorage() {
        Book[] books = new Book[bookIndex+CAPACITY];
        System.arraycopy(Storage.books,0,books,0,bookIndex);
        Storage.books=books;
    }
}
