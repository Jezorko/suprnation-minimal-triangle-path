package jezorko.github.minimaltrianglepath.domain.output;

import jezorko.github.minimaltrianglepath.domain.input.TriangleNode;
import jezorko.github.minimaltrianglepath.domain.minimalpath.TrianglePath;

import static java.util.Arrays.stream;

class TrianglePathStringFormatter {

    /**
     * @return path representation as sum of nodes equation
     */
    String format(TrianglePath trianglePath) {
        if (trianglePath == null) {
            return "null";
        }
        return stream(trianglePath.getNodes()).map(TriangleNode::getValue)
                                              .map(String::valueOf)
                                              .reduce((v1, v2) -> v1 + " + " + v2)
                                              .orElse("?") + " = " + trianglePath.getValue();
    }

}
