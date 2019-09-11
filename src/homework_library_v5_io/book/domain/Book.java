package homework_library_v5_io.book.domain;

import homework_library_v5_io.author.domain.Author;
import homework_library_v5_io.common.domain.BasicDomain;

import java.util.Iterator;
import java.util.List;

public class Book extends BasicDomain<Long> {
    protected String name;
    protected int publishYear;
    protected int totalPages;
    protected List<Author> authors;
    protected BookType bookType;


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

    public void setAuthors(List<Author> authors) {
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
