package homework_library_v5_io.author.domain;

import homework_library_v5_io.book.domain.Book;
import homework_library_v5_io.common.domain.BasicDomain;

import java.util.Iterator;
import java.util.List;

public class Author extends BasicDomain<Long> {


    private String lastName;
    private String name;
    private int yearOfBorn;
    private List<Book> books;

    public Author(Long id) {
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

    public void setBooks(List<Book> books) {
        this.books = (books);
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

