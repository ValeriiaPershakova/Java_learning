package Homework_3_Library_v2.Book;

import Homework_3_Library_v2.Author.Author;

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
                ", Authors:" + this.getAuthorsAsStr()+
                '}';
    }
    private String getAuthorsAsStr() {
        String string = "";
        for (int i=0; i < authors.length; i++) {
            Author author = authors[i];
            if (author!=null) {
                string +=author.getLastName()+", ";
            }
        }
        return string;
    }

}
