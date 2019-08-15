package Homework_3_Library_v2.Author;

import Homework_3_Library_v2.Book.Book;
import Homework_3_Library_v2.Book.BooksRepoMemory;
import Homework_3_Library_v2.BookRepo;
import Homework_3_Library_v2.Storage;

public class AuthorsRepoMemory implements BookRepo {

    @Override
    public int total() {
        return Storage.authorIndex;
    }

    @Override
    public Object find(String lastName) {
        Author author = null;
        for (int i = 0; i < Storage.authorIndex; i++) {
            if (Storage.authors[i].getLastName() == lastName) {
                author = Storage.authors[i];
                break;
            }
        }
        return author;
    }

    public Author find(long id) {
        Author author = null;
        for (int i = 0; i < Storage.authorIndex; i++) {
            if (Storage.authors[i].getId() == id) {
                author = Storage.authors[i];
                break;
            }
        }
        return author;
    }

    private Author find(String name, String lastName) {
        Author author = null;
        for (int i = 0; i < Storage.authorIndex; i++) {
            if (Storage.authors[i].getLastName() == lastName && Storage.authors[i].getName() == name) {
                author = Storage.authors[i];

                break;
            }
        }
        return author;
    }

    @Override
    public void sort() {
        Author[] authors = Storage.authors;
        for (int left = 0; left < Storage.authorIndex; left++) {
            // Вытаскиваем значение элемента
            Author value = authors[left];
            // Перемещаемся по элементам, которые перед вытащенным элементом
            int i = left - 1;
            for (; i >= 0; i--) {
                // Если вытащили значение меньшее — передвигаем больший элемент дальше
                if (value.getLastName().compareTo(authors[i].getLastName()) < 0) {
                    authors[i + 1] = authors[i];
                } else {
                    // Если вытащенный элемент больше — останавливаемся
                    break;
                }
            }
            // В освободившееся место вставляем вытащенное значение
            authors[i + 1] = value;
        }
        Storage.authors = authors;

    }

    @Override
    public void add(Object object) {
        Storage.addAuthor((Author) object);

    }

    @Override
    public void delete(Object object) {
        Author author = (Author) object;
        if (author != null) {
            Integer i = Storage.indexOf(author, Storage.authors);

            if (i != null) {
                deleteAuthorFromStorageByIndex(i);
                BooksRepoMemory booksRepoMemory = new BooksRepoMemory();
                for (int k = 0; k < author.getBooks().length; k++) {
                    booksRepoMemory.delete(author.getBooks()[k]);
                }

            } else {
                System.out.println("Error! Author not found");
            }
        } else {
            System.out.println("Error! Author not found");
        }

    }

    @Override
    public void delete(long id) {
        Author author = (Author) find(id);
        this.delete(author);
    }

    @Override
    public void delete(int index) {
        if (index >= 0 && index < Storage.authorIndex) {
            Author author = Storage.authors[index];
            this.delete(author);
        } else {
            System.out.println("Incorrect index");
        }
    }


    @Override
    public void delete(String name, String lastName) {
        Author author = find(name, lastName);
        this.delete(author);
    }

    private void deleteAuthorFromStorageByIndex(int i) {
        Author[] authors = new Author[Storage.authors.length];
        System.arraycopy(Storage.authors, 0, authors, 0, i);
        System.arraycopy(Storage.authors, i + 1, authors, i, Storage.authors.length - i - 1);
        Storage.authors = authors;
        Storage.authorIndex--;
    }


    private void deleteBookFromStorage(Book book) {

    }
}
