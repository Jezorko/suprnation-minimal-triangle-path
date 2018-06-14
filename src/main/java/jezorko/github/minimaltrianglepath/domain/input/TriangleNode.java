package jezorko.github.minimaltrianglepath.domain.input;

import lombok.*;

import static lombok.AccessLevel.PACKAGE;

/**
 * A single numerical node of a triangle.
 * Consists of two references to nodes below (children)
 * that can be obtained using getter methods.
 * If both children are absent, this node is a leaf.
 */
@Getter
@ToString
@Setter(PACKAGE)
@EqualsAndHashCode
@RequiredArgsConstructor(access = PACKAGE)
public class TriangleNode {

    private final long value;

    /**
     * Left-hand side child of this node.
     */
    private TriangleNode left;

    /**
     * Right-hand side child of this node.
     */
    private TriangleNode right;

    public final boolean isLeaf() {
        return left == null && right == null;
    }

}
