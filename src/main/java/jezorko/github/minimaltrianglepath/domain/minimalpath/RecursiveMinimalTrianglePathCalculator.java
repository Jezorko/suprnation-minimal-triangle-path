package jezorko.github.minimaltrianglepath.domain.minimalpath;

import jezorko.github.minimaltrianglepath.domain.input.Triangle;
import jezorko.github.minimaltrianglepath.domain.input.TriangleNode;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;
import java.util.Stack;

import static java.math.BigInteger.ZERO;

/**
 * Calculates minimal path recursively.
 * Iterates over every possible path in
 * the given {@link Triangle} and compares
 * the results.
 * This solution is very inefficient
 * but should yield the most accurate results,
 * therefore can be used to validate results
 * from other algorithms.
 */
class RecursiveMinimalTrianglePathCalculator implements MinimalTrianglePathCalculator {

    @NotNull
    @Override
    public TrianglePath calculateFrom(Triangle triangle) {
        final TriangleNode top = triangle.getTop();

        final PartialResult result = calculateFrom(top, ZERO);
        Stack<TriangleNode> path = result.partialPath;
        path.push(top);

        return new TrianglePath(buildPathFrom(path), result.value);
    }

    @NotNull
    private PartialResult calculateFrom(TriangleNode currentNode, BigInteger currentValue) {
        currentValue = currentValue.add(BigInteger.valueOf(currentNode.getValue()));

        if (currentNode.isLeaf()) {
            return new PartialResult(currentValue, new Stack<>());
        }

        PartialResult leftValue = calculateFrom(currentNode.getLeft(), currentValue);
        PartialResult rightValue = calculateFrom(currentNode.getRight(), currentValue);

        if (leftValue.value.compareTo(rightValue.value) < 0) {
            leftValue.partialPath.push(currentNode.getLeft());
            return leftValue;
        }
        else {
            rightValue.partialPath.push(currentNode.getRight());
            return rightValue;
        }
    }

    @NotNull
    private TriangleNode[] buildPathFrom(Stack<TriangleNode> path) {
        TriangleNode[] nodesInPath = new TriangleNode[path.size()];
        for (int i = 0; !path.isEmpty(); ++i) {
            nodesInPath[i] = path.pop();
        }
        return nodesInPath;
    }

    @RequiredArgsConstructor
    private static class PartialResult {
        private final BigInteger value;
        private final Stack<TriangleNode> partialPath;
    }

}
