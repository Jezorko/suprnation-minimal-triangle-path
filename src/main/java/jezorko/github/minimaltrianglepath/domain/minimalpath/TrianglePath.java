package jezorko.github.minimaltrianglepath.domain.minimalpath;

import jezorko.github.minimaltrianglepath.domain.input.TriangleNode;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigInteger;

import static java.util.Arrays.stream;
import static lombok.AccessLevel.PACKAGE;

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor(access = PACKAGE)
public class TrianglePath {

    private final TriangleNode[] nodes;
    private final BigInteger value;

    @Override
    public String toString() {
        return stream(nodes).map(TriangleNode::getValue)
                            .map(String::valueOf)
                            .reduce((v1, v2) -> v1 + " + " + v2)
                            .orElse("?") + " = " + value;
    }

}
