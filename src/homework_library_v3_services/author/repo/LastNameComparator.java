package homework_library_v3_services.author.repo;

import homework_library_v3_services.author.domain.Author;

import java.util.Comparator;

public class LastNameComparator implements Comparator<Author> {
    @Override
    public int compare(Author o1, Author o2) {
        return o1.getLastName().compareTo(o2.getLastName());
    }
}
