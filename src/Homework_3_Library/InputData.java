package Homework_3_Library;

public class InputData {
    private String title;
    private Edition edition;
    private Author[] authors;

    public InputData(String title, Edition edition, Author[] authors) {
        this.title = title;
        this.edition = edition;
        this.authors = authors;
    }

    public String getTitle() {
        return title;
    }

    public Edition getEdition() {
        return edition;
    }

    public Author[] getAuthors() {
        return authors;
    }
}
