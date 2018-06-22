package jezorko.github.minimaltrianglepath

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

    static Triangle randomTriangle(int rowsCount, TriangleNode currentNode = null) {
        if (currentNode == null) {
            currentNode = randomNode()

            randomTriangle(rowsCount - 1, currentNode)

            return new Triangle(currentNode)
        } else if (rowsCount != 0 as int) {
            currentNode.left = randomNode()
            currentNode.right = randomNode()
            randomTriangle(rowsCount - 1, currentNode.left)
            randomTriangle(rowsCount - 1, currentNode.right)
        }
        null
    }

    static randomNode() {
        long value = Long.valueOf(randomNumeric(3))
        new TriangleNode(value)
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
