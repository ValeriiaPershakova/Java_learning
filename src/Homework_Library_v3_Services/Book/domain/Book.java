package Homework_Library_v3_Services.Book.domain;

import Homework_Library_v3_Services.Author.domain.Author;

public class Book {
    protected Long id;
    protected String name;
    protected int publishYear;
    protected int totalPages;
    protected Author[] authors;
    protected BookType bookType;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public Author[] getAuthors() {
        return authors;
    }

    public void setAuthors(Author[] authors) {
        this.authors = authors;
    }

    public BookType getBookType() {
        return bookType;
    }

    public void setBookType(BookType bookType) {
        this.bookType = bookType;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", publishYear=" + publishYear +
                ", totalPages=" + totalPages +
                ", genre=" + bookType +
                '}';
    }

    public String toString2() {
        return "Book{" +
                "name='" + name + '\'' +
                ", Authors:" + this.getAuthorsAsStr() +
                '}';
    }

    private String getAuthorsAsStr() {
        StringBuilder string= new StringBuilder();
        for (int i = 0; i < authors.length; i++) {
            Author author = authors[i];
            if (author != null) {
                string.append(author.getLastName() + ", ");
            }
        }
        return string.toString();
    }

    public void deleteAuthor(Author author) {
        for (int i = 0; i<this.authors.length;i++) {
            if (this.authors[i].getId().equals(author.getId())) {
                this.authors[i] = null;
                break;
            }
        }
        Author[] newAuthors = new Author[this.authors.length - 1];
        int index = 0;
        for (Author a : this.authors) {
            if (a != null) {
                newAuthors[index] = a;
                index++;
            }
        }
        this.authors = newAuthors;
    }

    public boolean withoutAuthors() {
        return this.authors.length==0;
    }

}
