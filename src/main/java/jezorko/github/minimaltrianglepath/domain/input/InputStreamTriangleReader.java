package jezorko.github.minimaltrianglepath.domain.input;

import jezorko.github.minimaltrianglepath.domain.input.dto.Triangle;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

final class InputStreamTriangleReader implements TriangleReader, AutoCloseable {

    private final BufferedReader reader;
    private boolean inputAvailable = true;
    private String currentLine = null;

    InputStreamTriangleReader(InputStream inputStream) {
        reader = new BufferedReader(new InputStreamReader(inputStream));
    }

    @Override
    @SneakyThrows
    public Triangle nextTriangle() {
        currentLine = reader.readLine();
        return null; // TODO: finish implementation
    }

    @Override
    public boolean hasNext() {
        return inputAvailable;
    }

    @Override
    public void close() throws Exception {
        reader.close();
    }
}
