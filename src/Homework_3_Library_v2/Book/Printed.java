package Homework_3_Library_v2.Book;

public class Printed extends Book {
    private String fontFamily;

    public String getFontFamily() {
        return fontFamily;
    }

    public void setFontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
    }

    @Override
    public String toString() {
        return "Printed{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", publishYear=" + publishYear +
                ", totalPages=" + totalPages +
                ", bookType=" + bookType +
                ", fontFamily=" + fontFamily+
                '}';
    }
}
