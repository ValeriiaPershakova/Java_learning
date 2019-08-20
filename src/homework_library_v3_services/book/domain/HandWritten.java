package homework_library_v3_services.book.domain;

public class HandWritten extends Book {
    private String paint;

    public String getPaint() {
        return paint;
    }

    public void setPaint(String paint) {
        this.paint = paint;
    }

    @Override
    public String toString() {
        return "HandWritten{" +
                 "id=" + id +
                ", name='" + name + '\'' +
                ", publishYear=" + publishYear +
                ", totalPages=" + totalPages +
                ", bookType=" + bookType +
                ", paint='" + paint +
                '}';
    }
}
