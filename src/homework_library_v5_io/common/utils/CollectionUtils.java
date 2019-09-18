package homework_library_v5_io.common.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public final class CollectionUtils {
    private CollectionUtils() {
    }
    public static <T> List<T> mutableListOf(T...items) {
        return new ArrayList<>(Arrays.asList(items));
    }
    public static boolean isNotBlank(Collection<?> items) {
        return items != null && !items.isEmpty();
    }
}
