package homework_library_v4_generics.common.service;

import homework_library_v4_generics.book.domain.Book;

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
