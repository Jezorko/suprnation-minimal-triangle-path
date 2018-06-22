package jezorko.github.minimaltrianglepath.domain.minimalpath;

import com.carrotsearch.junitbenchmarks.BenchmarkOptions;
import com.carrotsearch.junitbenchmarks.BenchmarkRule;
import jezorko.github.minimaltrianglepath.domain.input.Triangle;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.stream.Collectors.toList;
import static jezorko.github.minimaltrianglepath.domain.input.TriangleInputReaderStaticFactory.streamReader;
import static org.apache.commons.io.IOUtils.toInputStream;
import static org.apache.commons.lang.RandomStringUtils.randomNumeric;

public class CumulativeMinimalTrianglePathCalculatorBenchmarkTest {

    @Rule
    public TestRule benchmarkRun = new BenchmarkRule();

    private static final Random RANDOM = new Random();
    private static final int TRIANGLE_SIZE = 200;
    private static final List<Triangle> TRIANGLES = IntStream.range(2, 500)
                                                             .mapToObj(it -> randomTriangle(TRIANGLE_SIZE))
                                                             .collect(toList());

    private static final MinimalTrianglePathCalculator RECURSIVE = new CumulativeMinimalTrianglePathCalculatorWithRecursion();
    private static final MinimalTrianglePathCalculator ITERATIVE = new CumulativeMinimalTrianglePathCalculator();

    @Test
    @BenchmarkOptions(benchmarkRounds = 1000, warmupRounds = 100)
    public void recursiveApproach() {
        RECURSIVE.calculateFrom(randomTriangleFromList());
    }

    @Test
    @BenchmarkOptions(benchmarkRounds = 1000, warmupRounds = 100)
    public void iterativeApproach() {
        ITERATIVE.calculateFrom(randomTriangleFromList());
    }

    static Triangle randomTriangleFromList() {
        return TRIANGLES.get(RANDOM.nextInt(TRIANGLES.size()));
    }

    static Triangle randomTriangle(int rowsCount) {
        StringBuilder builder = new StringBuilder();
        for (int row = 1; row <= rowsCount; ++row) {
            for (int element = 0; element < row - 1; ++element) {
                builder.append(randomNumeric(3))
                       .append(" ");
            }
            builder.append(randomNumeric(3));
            if (row != rowsCount) {
                builder.append("\n");
            }
        }
        var result = new Object() {
            Triangle result;
        };
        streamReader(toInputStream(builder.toString(), UTF_8)).get()
                                                              .doOnSuccess(it -> result.result = it)
                                                              .doOnError(it -> { throw new RuntimeException(it); });
        return result.result;
    }

}