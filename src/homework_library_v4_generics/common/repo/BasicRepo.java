package homework_library_v4_generics.common.repo;

import java.util.Comparator;

public interface BasicRepo<T,ID>{
    int count();

    void print();

    void delete(T obj);

    ID add(T obj);

    T getById(ID Id);

    void sort();

    void sort(Comparator comparator);
}
