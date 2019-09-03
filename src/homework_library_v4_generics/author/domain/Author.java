package homework_library_v4_generics.author.domain;

import homework_library_v4_generics.book.domain.Book;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Author {


    private Long id;
    private String lastName;
    private String name;
    private int yearOfBorn;
    private List<Book> books;

    public Author(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearOfBorn() {
        return yearOfBorn;
    }

    public void setYearOfBorn(int yearOfBorn) {
        this.yearOfBorn = yearOfBorn;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(Book[] books) {
        this.books = new ArrayList<>(Arrays.asList(books));
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", name='" + name + '\'' +
                ", yearOfBorn=" + yearOfBorn +
                '}';
    }

    public void deleteBook(Book book) {
        Iterator<Book> iter = books.iterator();
        while (iter.hasNext()) {
            Book b = iter.next();
            if (b.getId().equals(book.getId())) {
                iter.remove();
            }
        }

    }

    public boolean withoutBooks() {
        return this.books.size() == 0;
    }
}

