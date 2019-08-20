package homework_3_library;

public class Author implements Cloneable{
    private String name;
    private String lastName;
    private int id;

    private Author(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
        this.id = Library.AllAuthors.size();
    }

    public static Author newAuthor(String name, String lastName) {
        Integer objIndexIfAuthorAlreadyExist = doesAuthorAlreadyExist(name, lastName);
        if (objIndexIfAuthorAlreadyExist != null) {
            System.out.println("This author already exists");
            return Library.AllAuthors.get(objIndexIfAuthorAlreadyExist);
        } else {
            Author author=new Author(name, lastName);
            Library.AllAuthors.add(author);
            return author;
        }

    }

    private static Integer doesAuthorAlreadyExist(String name, String lastName) {
        Integer authorInd=null;
        for (Author author : Library.AllAuthors) {
            boolean authorAlreadyExist = author.compare(name, lastName);
            if (authorAlreadyExist) {
                authorInd = Library.AllAuthors.indexOf(author);
                break;
            }
        }
        return authorInd;
    }


    public boolean compare(String name, String lastName) {
        if (this.lastName == lastName && this.name == name) {
            return true;
        } else {
            return false;
        }
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Author other = (Author) obj;
        if (name != other.name)
            return false;
        if (lastName != other.lastName)
            return false;
        return true;
    }

}
