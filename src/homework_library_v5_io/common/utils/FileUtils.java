package homework_library_v5_io.common.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class FileUtils {
    private FileUtils() {
    }

    public static File createFileFromSource(String fileNamePrefix, String fileNameSuffix, String resourcePath) throws IOException {
        try (InputStream inputStream = File.class.getResourceAsStream(resourcePath)){
            Path tempFile = Files.createTempFile(fileNamePrefix,fileNameSuffix);
            Files.copy(inputStream, tempFile, REPLACE_EXISTING);
            return tempFile.toFile();
        }
    }
    public static boolean isFileValid(File file) {
        return file != null && file.isFile() && file.exists();
    }
}
