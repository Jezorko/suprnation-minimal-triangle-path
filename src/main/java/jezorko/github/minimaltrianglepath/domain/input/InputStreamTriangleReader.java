package jezorko.github.minimaltrianglepath.domain.input;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;

import static jezorko.github.minimaltrianglepath.domain.input.InputResult.tryOf;
import static lombok.AccessLevel.PACKAGE;

/**
 * Builds a {@link Triangle} from given {@link InputStream}.
 */
@RequiredArgsConstructor(access = PACKAGE)
final class InputStreamTriangleReader implements TriangleReader, AutoCloseable {

    private final TriangleRowsReader rows;

    InputStreamTriangleReader(InputStream inputStream) {
        this(new TriangleRowsReader(new BufferedReader(new InputStreamReader(inputStream))));
    }

    @Override
    @SneakyThrows
    public InputResult<Triangle> get() {
        return tryOf(() -> {
            Iterator<TriangleNode[]> rowsIterator = rows.iterator();

            TriangleNode[] previousRow = rowsIterator.next();
            final TriangleNode top = previousRow[0];

            while (rowsIterator.hasNext()) {
                final TriangleNode[] currentRow = rowsIterator.next();

                previousRow[0].setLeft(currentRow[0]);

                for (int nodeId = 1; nodeId < currentRow.length - 1; ++nodeId) {
                    previousRow[nodeId - 1].setRight(currentRow[nodeId]);
                    previousRow[nodeId].setLeft(currentRow[nodeId]);
                }

                previousRow[previousRow.length - 1].setRight(currentRow[currentRow.length - 1]);

                previousRow = currentRow;
            }

            return new Triangle(top);
        });
    }

    @Override
    public void close() throws Exception {
        rows.close();
    }

}
