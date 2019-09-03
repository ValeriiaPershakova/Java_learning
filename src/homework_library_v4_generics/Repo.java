package homework_library_v4_generics;

import java.util.Comparator;

public interface Repo <T>{
    int count();

    void print();

    void delete(T obj);

    Long add(T obj);

    T getById(Long Id);

    void sort();

    void sort(Comparator comparator);
}
