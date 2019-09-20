package homework_library_v5_io.book.service;

import homework_library_v5_io.book.domain.Book;

@FunctionalInterface
public interface FindByNameFuncInterface{
     Book[] findByName(String name);
}
