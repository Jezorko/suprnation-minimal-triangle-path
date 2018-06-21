package jezorko.github.minimaltrianglepath.domain.input;

import jezorko.github.minimaltrianglepath.domain.input.exception.InvalidTriangleNodeValueException;
import jezorko.github.minimaltrianglepath.domain.input.exception.TooFewTriangleNodesException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

import java.io.Reader;
import java.util.Iterator;

import static lombok.AccessLevel.PACKAGE;

/**
 * Class responsible for reading triangle input, row by row.
 * Implements {@link Iterable} to allow for loop syntax.
 * The iterator will not yield the same results when called multiple
 * times, it should only be used once per input source.
 */
@RequiredArgsConstructor(access = PACKAGE)
class TriangleRowsReader implements Iterable<TriangleNode[]>, AutoCloseable {

    private final Reader reader;

    private boolean inputAvailable = true;
    private int expectedNumbersAmount = 0;

    @NotNull
    @Override
    public Iterator<TriangleNode[]> iterator() {
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return inputAvailable;
            }

            @Override
            public TriangleNode[] next() {
                return nextRow(++expectedNumbersAmount);
            }
        };
    }

    @SneakyThrows
    private TriangleNode[] nextRow(int expectedNumbersAmount) {
        TriangleNode[] result = new TriangleNode[expectedNumbersAmount];

        for (int i = 0; i < result.length; ++i) {
            long nextValue = 0;
            int nextCharacter = reader.read();
            do {
                if (nextCharacter < '0' || nextCharacter > '9') {
                    if (nextCharacter == -1) {
                        throw new TooFewTriangleNodesException(expectedNumbersAmount, 0);
                    }
                    throw new InvalidTriangleNodeValueException(nextCharacter, expectedNumbersAmount - 1, i);
                }

                nextValue = (nextValue * 10) + (nextCharacter - '0');

                nextCharacter = reader.read();
            } while (nextCharacter != ' ' && nextCharacter != '\n' && nextCharacter != -1);

            if (i != result.length - 1 && nextCharacter != ' ') {
                throw new TooFewTriangleNodesException(expectedNumbersAmount, i);
            }

            if (nextCharacter == -1) {
                inputAvailable = false;
            }

            result[i] = new TriangleNode(nextValue);
        }

        return result;
    }

    @Override
    public void close() throws Exception {
        reader.close();
    }
}
