package jezorko.github.minimaltrianglepath.domain.input

import static java.nio.charset.StandardCharsets.UTF_8
import static org.apache.commons.io.IOUtils.toInputStream

class TestUtils {

    static formatTriangleInput(String input) {
        input.replaceAll("\n", "\\\\n")
    }

    static inputAsStream(String input) {
        toInputStream(input, UTF_8)
    }

    static triangle(TriangleNode top) {
        new Triangle(top)
    }

    static node(long value, TriangleNode left = null, TriangleNode right = null) {
        def result = new TriangleNode(value)
        result.left = left
        result.right = right
        result
    }

}
