package homework_library_v5_io.common.service;

import java.util.Comparator;

public interface BasicService<T, ID> {
    int count();

    void print();

    void delete(T t);

    ID add(T book);

    void defaultSort();

    void sort(Comparator comparator);

    T[] find(String name);


    T getById(ID bookId);
}
