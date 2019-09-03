package homework_library_v3_services.book.domain;

import homework_library_v3_services.author.domain.Author;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Book {
    protected Long id;
    protected String name;
    protected int publishYear;
    protected int totalPages;
    protected List<Author> authors;
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

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Author[] authors) {
        this.authors = new ArrayList<>(Arrays.asList(authors));
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
        for (Author author: authors) {
            if (author != null) {
                string.append(author.getLastName() + ", ");
            }
        }
        return string.toString();
    }

    public void deleteAuthor(Author author) {
        Iterator<Author> iter = authors.iterator();
        while (iter.hasNext()) {
            Author a = iter.next();
            if (a.getId().equals(author.getId())) {
                iter.remove();
            }
        }
    }

    public boolean withoutAuthors() {
        return this.authors.size()==0;
    }

}
