package homework_library_v4_generics.book.repo;

import homework_library_v4_generics.book.domain.Book;

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
