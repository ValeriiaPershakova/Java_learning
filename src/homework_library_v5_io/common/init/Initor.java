package homework_library_v5_io.common.init;

import homework_library_v5_io.author.InputAuthor;
import homework_library_v5_io.author.domain.Author;
import homework_library_v5_io.author.service.AuthorService;
import homework_library_v5_io.book.InputBook;
import homework_library_v5_io.book.domain.Book;
import homework_library_v5_io.book.domain.BookType;
import homework_library_v5_io.book.domain.HandWritten;
import homework_library_v5_io.book.domain.Printed;
import homework_library_v5_io.book.service.BookService;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static homework_library_v5_io.common.utils.CollectionUtils.mutableListOf;

public class Initor {

    public static void initData(InitType initType, BookService bookService, AuthorService authorService) {
        switch (initType) {
            case RAM:
                initRAM(bookService, authorService);
                break;
            case FROM_FILE:
                initFromFile(bookService, authorService);
                break;
        }
    }

    //------------------Init From File----------------------------------------------------------
    private static void initFromFile(BookService bookService, AuthorService authorService) {
        List<String> lines = readDataFromFile();
        List<Map> initObjects = new ArrayList<>();

        for (String obj : lines) {
            Map<String, String> params = getMapOfParams();
            params.put("objectType", getInitObjectType(obj));
            if (params.get("objectType") != null) {
                setInitParameters(obj, params);
                initObjects.add(params);
            }
        }
        List<Book> inputBooks = new ArrayList<>();
        List<Author> inputAuthors = new ArrayList<>();
        for (Map<String, String> map : initObjects) {
            switch (map.get("objectType")) {
                case ("Book"): {
                    Book book = null;
                    InputBook inputBook = new InputBook();
                    inputBook.setName(map.get("name"));
                    if ((map.get("year") != null)) {
                        inputBook.setPublishYear(Integer.parseInt(map.get("year")));
                    }
                    if ((map.get("totalPages") != null)) {
                        inputBook.setTotalPages(Integer.parseInt(map.get("totalPages")));
                    }
                    inputBook.setFontFamily(map.get("fontFamily"));
                    inputBook.setPaint(map.get("paint"));
                    switch (map.get("bookPhysicType")) {
                        case ("Printed"): {
                            book = valueOfPrintedBook(inputBook);
                            break;
                        }
                        case ("HandWritten"): {
                            book = valueOfHandWrittenBook(inputBook);
                            break;
                        }
                    }
                    inputBooks.add(book);
                    break;
                }
                case ("Author"): {
                    Author author = null;
                    InputAuthor inputAuthor = new InputAuthor();
                    inputAuthor.setName(map.get("name"));
                    inputAuthor.setLastName(map.get("lastName"));
                    if ((map.get("year") != null)) {
                        inputAuthor.setYearOfBorn(Integer.parseInt(map.get("year")));
                    }
                    author = valueOf(inputAuthor);

                    inputAuthors.add(author);
                }
            }
        }
        for (Book book : inputBooks) {
            bookService.add(book);
        }
        for (Author author : inputAuthors) {
            authorService.add(author);
        }
        //set authors for each new book
        for (Book newBook : inputBooks) {
            List<Author> authorsList = new ArrayList<>();
            for (Map<String, String> map : initObjects) {
                if (map.get("name").equals(newBook.getName())) {
                    String[] authorsTemp = map.get("authors").split(", ");
                    for (String tempAuthorName : authorsTemp) {
                        String[] authorLastNameAndName = tempAuthorName.split(" ");
                        Author foundAuthor=authorService.find(authorLastNameAndName[0],authorLastNameAndName[1]);
                        if (foundAuthor != null) {
                            authorsList.add(foundAuthor);
                        } else {
                            foundAuthor.setLastName(authorLastNameAndName[0]);
                            foundAuthor.setName(authorLastNameAndName[1]);
                            foundAuthor.setBooks(mutableListOf(newBook));
                            authorService.add(foundAuthor);
                        }
                    }
                }
            }
            bookService.find(newBook.getName())[0].setAuthors(authorsList);
        }
        // set books for each new author
        for (Author newAuthor : inputAuthors) {
            List<Book> booksList = new ArrayList<>();
            for (Map<String, String> map : initObjects) {
                if (map.get("name").equals(newAuthor.getName())) {
                    String[] booksTemp = map.get("books").split(", ");
                    for (String bookName : booksTemp) {
                        Book foundBook = bookService.find(bookName)[0];
                        if (foundBook!=null && foundBook.getName().equals(bookName)) {
                            booksList.add(foundBook);
                        } else if (foundBook == null) {
                            foundBook.setName(bookName);
                            foundBook.setAuthors(mutableListOf(newAuthor));
                            bookService.add(foundBook);
                        }
                    }
                }
            }
            authorService.find(newAuthor.getLastName(),newAuthor.getName()).setBooks(booksList);
        }

    }

    private static List<String> readDataFromFile() {
        String filePath = new File("").getAbsolutePath();
        System.out.println(filePath);
        File file = new File(filePath.concat("\\rsc\\resources.txt"));
        List<String> lines = new ArrayList<>();
        try (BufferedReader buf = new BufferedReader(new FileReader(file))) {
            int i = 0;
            StringBuilder temp = new StringBuilder();
            while (buf.ready()) {
                String line = buf.readLine();
                if (line.contains("***")) {
                    lines.add(i, temp.toString());
                    i++;
                    temp.setLength(0);
                    continue;
                }
                line.replaceAll("\n", "");
                temp.append(line);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    private static Map<String, String> getMapOfParams() {
        HashMap<String, String> params = new HashMap<>();
        params.put("objectType", null);
        params.put("bookPhysicType", null);
        params.put("name", null);
        params.put("lastName", null);
        params.put("year", null);
        params.put("bookType", null);
        params.put("paint", null);
        params.put("fontFamily", null);
        params.put("totalPage", null);
        params.put("authors", null);
        params.put("books", null);
        return params;
    }

    private static String getInitObjectType(String obj) {
        Pattern pattern = Pattern.compile("^Book|Author");
        Matcher matcher = pattern.matcher(obj);
        if (matcher.find()) {
            return matcher.group(0);
        } else return null;
    }

    private static void setInitParameters(String obj, Map<String, String> params) {
        for (String key : params.keySet()) {
            Pattern pattern = Pattern.compile(key + "=");
            Matcher matcher = pattern.matcher(obj);
            if (matcher.find()) {
                pattern = Pattern.compile("[\\w\\s,]+");
                Matcher innerMatcher = pattern.matcher(obj);
                if (innerMatcher.find(matcher.end())) {
                    params.replace(key, innerMatcher.group(0));
                }
            }
        }
    }


    //---------------Init from RAM-------------------------------------------------------------------
    private static void initRAM(BookService bookService, AuthorService authorService) {
        InputBook inputBook1 = new InputBook();
        inputBook1.setName("Zolotaya rybka");
        inputBook1.setPublishYear(11);
        inputBook1.setBookType(BookType.FICTION);
        inputBook1.setPaint("Ink");
        Book book1 = valueOfHandWrittenBook(inputBook1);

        InputBook inputBook2 = new InputBook();
        inputBook2.setName("Rusla and Ludmila");
        inputBook2.setPublishYear(11);
        inputBook2.setBookType(BookType.ROMANTIC);
        inputBook2.setFontFamily("Times new roman");
        Book book2 = valueOfPrintedBook(inputBook2);

        InputBook inputBook3 = new InputBook();
        inputBook3.setName("Ritual");
        inputBook3.setPublishYear(18);
        inputBook3.setBookType(BookType.FICTION);
        inputBook3.setFontFamily("Times new roman");
        Book book3 = valueOfPrintedBook(inputBook3);

        InputAuthor inputAuthor1 = new InputAuthor();
        inputAuthor1.setLastName("Pushkin");
        inputAuthor1.setYearOfBorn(22);
        Author author1 = valueOf(inputAuthor1);
        author1.setBooks(mutableListOf(book1, book2));

        InputAuthor inputAuthor2 = new InputAuthor();
        inputAuthor2.setLastName("Dyachenko");
        inputAuthor2.setName("Marina");
        inputAuthor2.setYearOfBorn(22);
        Author author2 = valueOf(inputAuthor2);

        InputAuthor inputAuthor3 = new InputAuthor();
        inputAuthor3.setLastName("Dyachenko");
        inputAuthor3.setName("Sergei");
        inputAuthor3.setYearOfBorn(22);
        Author author3 = valueOf(inputAuthor3);

        author2.setBooks(mutableListOf(book3));
        author3.setBooks(mutableListOf(book3));

        book1.setAuthors(mutableListOf(author1));
        book2.setAuthors(mutableListOf(author1));
        book3.setAuthors(mutableListOf(author2, author3));


        bookService.add(book1);
        bookService.add(book2);
        bookService.add(book3);

        authorService.add(author1);
        authorService.add(author2);
        authorService.add(author3);
    }

    private static Author valueOf(InputAuthor inputAuthor) {
        Author author = new Author(null);
        author.setLastName(inputAuthor.getLastName());
        author.setName(inputAuthor.getName());
        author.setYearOfBorn(inputAuthor.getYearOfBorn());
        return author;
    }

    private static Book valueOfHandWrittenBook(InputBook inputBook) {
        HandWritten book = new HandWritten();
        book.setName(inputBook.getName());
        book.setPublishYear(inputBook.getPublishYear());
        book.setBookType(inputBook.getBookType());
        book.setPaint(inputBook.getPaint());

        return book;
    }

    private static Book valueOfPrintedBook(InputBook inputBook) {
        Printed book = new Printed();
        book.setName(inputBook.getName());
        book.setPublishYear(inputBook.getPublishYear());
        book.setBookType(inputBook.getBookType());
        book.setFontFamily(inputBook.getFontFamily());

        return book;
    }
}
