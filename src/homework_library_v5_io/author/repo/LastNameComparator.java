package homework_library_v5_io.author.repo;

import homework_library_v5_io.author.domain.Author;

import java.util.Comparator;

public class LastNameComparator implements Comparator<Author> {
    @Override
    public int compare(Author o1, Author o2) {
        if (o1 != null) {
            return o1.getLastName().compareTo(o2.getLastName());
        } else {
            return 1;
        }
    }
}
