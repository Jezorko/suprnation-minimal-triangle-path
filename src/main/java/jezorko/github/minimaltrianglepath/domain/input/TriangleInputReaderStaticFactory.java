package jezorko.github.minimaltrianglepath.domain.input;

import lombok.SneakyThrows;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Exposes methods for creating {@link TriangleReader} instances.
 */
public class TriangleInputReaderStaticFactory {

    /**
     * @return a {@link TriangleReader} that reads from the standard input stream (stdin).
     */
    public static TriangleReader standardInputStreamReader() {
        return streamReader(System.in);
    }

    /**
     * @param file to be read
     *
     * @return a {@link TriangleReader} that reads data from given file.
     */
    @SneakyThrows
    public static TriangleReader fileReader(File file) {
        return streamReader(new FileInputStream(file));
    }

    public static TriangleReader streamReader(InputStream stream) {
        return new InputStreamTriangleReader(stream);
    }

}
