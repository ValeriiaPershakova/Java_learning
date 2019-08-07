package Homework_3_Library;

public class Edition {
    private String publisher;
    private short year;
    private short numberOfPages;

    public Edition(String publisher, short year, short numberOfPages) {
        this.publisher = publisher;
        this.year = year;
        this.numberOfPages = numberOfPages;
    }

    public String getPublisher() {
        return publisher;
    }

    public short getYear() {
        return year;
    }

    public short getNumberOfPages() {
        return numberOfPages;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Edition other = (Edition) obj;
        if (getPublisher() != other.getPublisher())
            return false;
        if (getYear() != other.getYear())
            return false;
        if (getNumberOfPages() !=other.getNumberOfPages())
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Edition:" +
                "publisher: '" + publisher + '\'' +
                ", " + year +", "
                 + numberOfPages +"p;"
                ;
    }
}
