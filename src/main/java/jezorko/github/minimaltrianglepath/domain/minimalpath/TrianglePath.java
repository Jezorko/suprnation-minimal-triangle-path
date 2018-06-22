package jezorko.github.minimaltrianglepath.domain.minimalpath;

import jezorko.github.minimaltrianglepath.domain.input.TriangleNode;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigInteger;

import static java.util.Arrays.stream;
import static lombok.AccessLevel.PACKAGE;

/**
 * A representation of a path in a {@link jezorko.github.minimaltrianglepath.domain.input.Triangle}.
 */
@Getter
@EqualsAndHashCode
@RequiredArgsConstructor(access = PACKAGE)
public class TrianglePath {

    /**
     * Array of nodes this path consists of,
     * where first element is the top of the
     * triangle path was created from.
     */
    private final TriangleNode[] nodes;

    /**
     * Sum of all the nodes in this path.
     */
    private final BigInteger value;

    /**
     * @return path representation as sum of nodes equation
     */
    @Override
    public String toString() {
        return stream(nodes).map(TriangleNode::getValue)
                            .map(String::valueOf)
                            .reduce((v1, v2) -> v1 + " + " + v2)
                            .orElse("?") + " = " + value;
    }

}
