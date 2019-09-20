package homework_library_v5_io.common.service;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public interface BasicService<T, ID> {
    List<T> getAll();

    int count();

    void print();

    void delete(T t);

    ID add(T book);

    void add(Collection<T> items);

    void defaultSort();

    void sort(Comparator comparator);

    T[] find(String name);



    T getById(ID bookId);
}
