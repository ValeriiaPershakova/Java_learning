package homework_library_v5_io.book;

import homework_library_v5_io.book.domain.BookType;

import java.util.HashSet;
import java.util.Set;

public class InputBook {
    private String name;
    private int publishYear;
    private int totalPages;
    private BookType bookType;

    private String bookPhysicType;
    private String paint;
    private String fontFamily;
    private Set<String> authorsRef = new HashSet<>();

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

    public BookType getBookType() {
        return bookType;
    }

    public void setBookType(BookType bookType) {
        this.bookType = bookType;
    }

    public String getPaint() {
        return paint;
    }

    public void setPaint(String paint) {
        this.paint = paint;
    }

    public String getFontFamily() {
        return fontFamily;
    }

    public void setFontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
    }

    public String getBookPhysicType() {
        return bookPhysicType;
    }

    public void setBookPhysicType(String bookPhysicType) {
        this.bookPhysicType = bookPhysicType;
    }

    public Set<String> getAuthorsRef() {
        return authorsRef;
    }

    public void setAuthorsRef(Set<String> authorsRef) {
        this.authorsRef = authorsRef;
    }
}
