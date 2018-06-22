package jezorko.github.minimaltrianglepath

import jezorko.github.minimaltrianglepath.domain.input.InputStreamTriangleReader
import jezorko.github.minimaltrianglepath.domain.input.Triangle
import jezorko.github.minimaltrianglepath.domain.input.TriangleNode
import jezorko.github.minimaltrianglepath.domain.minimalpath.TrianglePath

import static java.nio.charset.StandardCharsets.UTF_8
import static org.apache.commons.io.IOUtils.toInputStream
import static org.apache.commons.lang.RandomStringUtils.randomNumeric

class TestUtils {

    static shallowPathsEquals(TrianglePath that, TrianglePath other) {
        if (!Objects.equals(that?.value, other?.value) ||
                !Objects.equals(that?.nodes?.length, other?.nodes?.length)) {
            return false
        }

        for (int i = 0; i < that?.nodes?.length; ++i) {
            if (!Objects.equals(that?.nodes[i]?.getValue(), other?.nodes[i]?.getValue())) {
                return false
            }
        }
        return true
    }

    static Triangle randomTriangle(int rowsCount) {
        StringBuilder builder = new StringBuilder()
        for (int row = 1; row <= rowsCount; ++row) {
            for (int element = 0; element < row - 1; ++element) {
                builder.append(randomNumeric(3))
                       .append(" ")
            }
            builder.append(randomNumeric(3))
            if (row != rowsCount) {
                builder.append("\n")
            }
        }
        def result = null
        new InputStreamTriangleReader(inputAsStream(builder.toString())).get()
                                                                        .doOnSuccess({ result = it })
                                                                        .doOnError({ throw it })
        return result
    }

    static path(nodes, int value) {
        new TrianglePath(nodes as TriangleNode[], BigInteger.valueOf(value as long))
    }

    static formatTriangleInput(String input) {
        "'${input.replaceAll("\n", "\\\\n")}'"
    }

    static inputAsBufferedReader(String input) {
        new BufferedReader(new InputStreamReader(inputAsStream(input)))
    }

    static inputAsStream(String input) {
        toInputStream(input, UTF_8)
    }

    static triangle(TriangleNode top) {
        new Triangle(top)
    }

    static node(value, TriangleNode left = null, TriangleNode right = null) {
        def result = new TriangleNode(value as long)
        result.left = left
        result.right = right
        result
    }

}
