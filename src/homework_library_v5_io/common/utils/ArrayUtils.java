package homework_library_v5_io.common.utils;

import java.util.function.Supplier;

public final class ArrayUtils {
    private ArrayUtils() {
    }

    public static <T> void copyNotNullElements(T[] src, T[] dest) {
        int i = 0;
        for (T t : src) {
            if (t != null) {
                dest[i] = t;
                i++;
            }
        }
    }

    public static <T> void copyElements(T[] src, T[] dest) {
        for (int i = 0; i < src.length; i++) {
            dest[i] = src[i];
        }
    }

    public static <T> T[] increaseStorage(T[] oldArray, Supplier<T[]> increaser) {
        T[] newArray = increaser.get();
        ArrayUtils.copyElements(oldArray, newArray);
        return newArray;
    }

}
