package Homework_Library_v3_Services.Author.domain;

import Homework_Library_v3_Services.Book.domain.Book;

public class Author {


    private Long id;
    private String lastName;
    private String name;
    private int yearOfBorn;
    private Book[] books;

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

    public Book[] getBooks() {
        return books;
    }

    public void setBooks(Book[] books) {
        this.books = books;
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
        for (int i = 0; i<this.books.length; i++) {
            if (this.books[i].getId().equals(book.getId())) {
                this.books[i] = null;
                break;
            }
        }
        Book[] newBooks = new Book[this.books.length - 1];
        int index = 0;
        for (Book b : this.books) {
            if (b!=null) {
                newBooks[index]=b;
                index++;
            }
        }
        this.books = newBooks;
    }

    public boolean withoutBooks() {
        return this.books.length==0;
    }
}

