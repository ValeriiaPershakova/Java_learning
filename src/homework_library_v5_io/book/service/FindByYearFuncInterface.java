package homework_library_v5_io.book.service;

import homework_library_v5_io.book.domain.Book;

public interface FindByYearFuncInterface {
    Book[] findByYear(int year);
}
