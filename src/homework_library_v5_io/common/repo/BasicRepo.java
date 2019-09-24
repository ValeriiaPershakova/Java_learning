package homework_library_v5_io.common.repo;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public interface BasicRepo<T,ID>{
    List<T> getAll();

    int count();

    void print();

    void delete(T obj);

    ID add(T obj);

    T getById(ID Id);

    void sort();

    void sort(Comparator comparator);
    void sort(List<T> itemsToSort, Comparator<T> comparator );
    List<T> find(List<T> items, Predicate<T> predicate);
}
