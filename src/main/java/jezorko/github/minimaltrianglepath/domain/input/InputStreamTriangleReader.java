package jezorko.github.minimaltrianglepath.domain.input;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;

final class InputStreamTriangleReader implements TriangleReader, AutoCloseable {

    private final BufferedReader reader;
    private int expectedNextRowValuesAmount = 1;
    private boolean inputAvailable = true;
    private String currentLine = null;

    InputStreamTriangleReader(InputStream inputStream) {

        reader = new BufferedReader(new InputStreamReader(inputStream));
    }

    @Override
    @SneakyThrows
    public Optional<Triangle> get() {
        currentLine = reader.readLine();
        if (currentLine == null) {
            inputAvailable = false;
        }
        return Optional.empty(); // TODO: finish implementation
    }

    @Override
    public void close() throws Exception {
        reader.close();
    }
}
