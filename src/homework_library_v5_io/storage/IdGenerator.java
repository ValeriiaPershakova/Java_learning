package homework_library_v5_io.storage;

public final class IdGenerator {
    public static long id = 0L;

    private IdGenerator() {

    }
    public static long generateId() {
        return ++id;
    }
}
