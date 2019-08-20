package homework_3_library_v2;

public interface BookRepo {
    int total();

    Object find(String lastName);


    //Or with parameters?
    //void sortByName(Author[] authors);
    //void sortByName(Book[] books);
    void sort();

    void add(Object object);
    //void add(Book book);

    void delete(Object object);
    void delete(long id);
    void delete(int index);
    void delete(String name, String lastName);

    /*void deleteBook(Book book);
    void deleteBook(long id);
    void deleteBook(int index);
    void deleteBook(String name);*/

}
