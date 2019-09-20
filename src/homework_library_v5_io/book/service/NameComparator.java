package homework_library_v5_io.book.service;

import homework_library_v5_io.book.domain.Book;

import java.util.Comparator;

public class NameComparator implements Comparator<Book> {
    @Override
    public int compare(Book o1, Book o2) {
        if (o1!=null) {
            return o1.getName().compareTo(o2.getName());
        }else{
            return 1;
        }
    }
}
