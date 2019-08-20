package homework_3_library_v2.Book;

import homework_3_library_v2.Author.Author;
import homework_3_library_v2.Author.AuthorsRepoMemory;
import homework_3_library_v2.BookRepo;
import homework_3_library_v2.Storage;

public class BooksRepoMemory implements BookRepo {
    @Override
    public int total() {
        return Storage.bookIndex;
    }

    @Override
    public Object find(String lastName) {
        Book book = null;
        for (int i = 0; i < Storage.bookIndex; i++) {
            if (Storage.books[i].getName() == lastName) {
                book = Storage.books[i];
                break;
            }
        }
        return book;
    }

    public Book find(long id) {
        Book book = null;
        for (int i = 0; i < Storage.bookIndex; i++) {
            if (Storage.books[i].getId() == id) {
                book = Storage.books[i];
                break;
            }
        }
        return book;
    }

    @Override
    public void sort() {
        Book[] books = Storage.books;
        for (int left = 0; left < Storage.bookIndex; left++) {
            // Вытаскиваем значение элемента
            Book value = books[left];
            // Перемещаемся по элементам, которые перед вытащенным элементом
            int i = left - 1;
            for (; i >= 0; i--) {
                // Если вытащили значение меньшее — передвигаем больший элемент дальше
                if (value.getName().compareTo(books[i].getName()) < 0) {
                    books[i + 1] = books[i];
                } else {
                    // Если вытащенный элемент больше — останавливаемся
                    break;
                }
            }
            // В освободившееся место вставляем вытащенное значение
            books[i + 1] = value;
        }
        Storage.books = books;

    }

    @Override
    public void add(Object object) {
        Storage.addBook((Book) object);

    }

    @Override
    public void delete(Object object) {
        Book book = (Book) object;
        if (book != null) {
            Integer i = Storage.indexOf(book, Storage.books);

            if (i != null) {
                deleteBookFromStorageByIndex(i);

                if (bookHasOnlyOneAuthor(book) && authorHasOnlyOneBook(book.getAuthors()[0])) {
                    Integer index = Storage.indexOf(book.getAuthors()[0], Storage.authors);
                    if (index != null) {
                        AuthorsRepoMemory authorsRepoMemory = new AuthorsRepoMemory();
                        authorsRepoMemory.delete(index);
                    }
                } else {
                    for (int j = 0; j < book.getAuthors().length; j++) {
                        Integer authorIndex = Storage.indexOf(book.getAuthors()[j], Storage.authors);
                        if (authorIndex != null) {
                            Integer bookIndexInAuthor = Storage.indexOf(book, Storage.authors[authorIndex].getBooks());
                            Book[] booksUpdate = deleteBookFromArrayByIndex(bookIndexInAuthor, Storage.authors[authorIndex].getBooks());
                            Storage.authors[authorIndex].setBooks(booksUpdate);
                        }

                    }
                }

            } else {
                System.out.println("Error! Book not found");
            }
        } else {
            System.out.println("Error! Book not found");
        }
    }

    @Override
    public void delete(long id) {
        Book book = find(id);
        this.delete(book);
    }

    @Override
    public void delete(int index) {
        if (index >= 0 && index < Storage.bookIndex) {
            Book book = Storage.books[index];
            this.delete(book);
        } else {
            System.out.println("Incorrect index");
        }
    }

    @Override
    public void delete(String name, String lastName) {
        Book book = (Book) find(name);
        this.delete(book);
    }

    public void delete(String name) {
        this.delete(name, null);
    }

    private void deleteBookFromStorageByIndex(int i) {
        Book[] books = new Book[Storage.books.length];
        System.arraycopy(Storage.books, 0, books, 0, i);
        System.arraycopy(Storage.books, i + 1, books, i, Storage.books.length - i - 1);
        Storage.books = books;
        Storage.bookIndex--;
    }

    private Book[] deleteBookFromArrayByIndex(int i, Book[] array) {
        Book[] books = new Book[array.length];
        System.arraycopy(array, 0, books, 0, i);
        System.arraycopy(array, i + 1, books, i, array.length - i - 1);
        return books;
    }

    private boolean bookHasOnlyOneAuthor(Book book) {
        return book.getAuthors().length == 1;
    }

    private boolean authorHasOnlyOneBook(Author author) {
        return Storage.authors[Storage.indexOf(author, Storage.authors)].getBooks().length == 1;
    }
}
