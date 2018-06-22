package jezorko.github.minimaltrianglepath.domain.minimalpath;

import jezorko.github.minimaltrianglepath.domain.input.Triangle;
import jezorko.github.minimaltrianglepath.domain.input.TriangleNode;
import lombok.RequiredArgsConstructor;

import java.math.BigInteger;
import java.util.Stack;

import static java.math.BigInteger.ZERO;

public class RecursiveMinimalTrianglePathCalculator implements MinimalTrianglePathCalculator {

    @Override
    public TrianglePath calculateFrom(Triangle triangle) {
        final TriangleNode top = triangle.getTop();

        final PartialResult result = calculateFrom(top, ZERO);
        Stack<TriangleNode> path = result.partialPath;
        path.push(top);

        TriangleNode[] nodesInPath = new TriangleNode[path.size()];
        for (int i = 0; !path.isEmpty(); ++i) {
            nodesInPath[i] = path.pop();
        }

        return new TrianglePath(nodesInPath, result.value);
    }

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

    @RequiredArgsConstructor
    private static class PartialResult {
        private final BigInteger value;
        private final Stack<TriangleNode> partialPath;
    }

}
