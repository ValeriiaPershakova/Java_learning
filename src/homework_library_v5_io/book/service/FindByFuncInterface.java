package homework_library_v5_io.book.service;

import homework_library_v5_io.book.domain.Book;

public interface FindByFuncInterface <T extends Book> {
    T[] find(String findBy, String param);
}
