package Homework_3_Library_v2;

public class Book {
    private Long id;
    private String name;
    private int publishYear;
    private int totalPages;
    private Author[] authors;
    private Genre genre;

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

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", publishYear=" + publishYear +
                ", totalPages=" + totalPages +
                ", genre=" + genre +
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
