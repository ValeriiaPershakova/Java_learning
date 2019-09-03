package homework_library_v4_generics.storage;

public final class IdGenerator {
    public static long id = 0L;

    private IdGenerator() {

    }
    public static long generateId() {
        return ++id;
    }
}
