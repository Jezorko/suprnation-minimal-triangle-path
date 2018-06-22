package jezorko.github.minimaltrianglepath.domain.minimalpath;

import jezorko.github.minimaltrianglepath.domain.input.Triangle;
import jezorko.github.minimaltrianglepath.domain.input.TriangleNode;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Stack;

import static java.math.BigInteger.ZERO;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

/**
 * Calculates minimal {@link Triangle} path by
 * accumulating the calculations for every row.
 * Consider the following {@link Triangle}:
 * <pre>
 *       22
 *     12   3
 *   78  53  34
 * 11  25  10  82
 * </pre>
 * in the first step, the initial calculation
 * frame is created from the top node [22].
 * Then, frame moves downwards and nodes below
 * are added to the lowest node from above them.
 * The second step would then look like:
 * <pre>
 *    [24  25]
 *   78  53  34
 * 11  25  10  82
 * </pre>
 * Of course, there is not much choice because
 * there is only one top node, but in the next
 * step the algorithm finally gets to choose:
 * <pre>
 *  [102    77   59]
 * 11    25   10   82
 * </pre>
 * And finally, the last step yields the result
 * as we are left with leaves:
 * <pre>
 * [113 102  69 141]
 * </pre>
 * All that's left is to iterate over all the
 * sums and choose the lowest one.
 */
class CumulativeMinimalTrianglePathCalculator implements MinimalTrianglePathCalculator {

    @NotNull
    @Override
    public TrianglePath calculateFrom(Triangle triangle) {
        CalculationFrame initialFrame = new CalculationFrame();
        final CalculationPart topPart = new CalculationPart();
        topPart.path.push(triangle.getTop());
        topPart.addToValue(triangle.getTop()
                                   .getValue());
        initialFrame.add(topPart);
        CalculationFrame resultFrame = calculateFrom(initialFrame);

        final CalculationPart minimalPart = resultFrame.stream()
                                                       .min(comparing(part -> part.valueSoFar))
                                                       .orElseThrow();

        return new TrianglePath(buildPathFrom(minimalPart), minimalPart.valueSoFar);
    }

    @NotNull
    private CalculationFrame calculateFrom(CalculationFrame oldFrame) {
        if (isCalculationFinished(oldFrame)) {
            return oldFrame;
        }

        final var newFrame = oldFrame.copy();

        updateFirstElement(newFrame);

        for (int i = 1; i < oldFrame.size(); ++i) {
            UpdateMiddleFramePart(oldFrame, newFrame, i);
        }

        appendNewLastElement(oldFrame, newFrame);

        // TODO: replace recursion with an iterative approach
        return calculateFrom(newFrame);
    }

    private boolean isCalculationFinished(CalculationFrame oldFrame) {
        return oldFrame.stream()
                       .map(p -> p.path)
                       .map(Stack::peek)
                       .anyMatch(TriangleNode::isLeaf);
    }

    private void updateFirstElement(CalculationFrame newFrame) {
        final var firstElement = newFrame.get(0);
        final var oldLeftFromFirst = firstElement.path.peek()
                                                      .getLeft();
        firstElement.path.add(oldLeftFromFirst);
        firstElement.addToValue(oldLeftFromFirst.getValue());
    }

    private void UpdateMiddleFramePart(CalculationFrame oldFrame, CalculationFrame newFrame, int partIndex) {
        final var smallerUpstreamValue = oldFrame.get(partIndex - 1).valueSoFar.compareTo(oldFrame.get(partIndex).valueSoFar) < 0 ?
                                         oldFrame.get(partIndex - 1) : oldFrame.get(partIndex);

        final var newValue = smallerUpstreamValue.copy();
        newValue.path.add(oldFrame.get(partIndex - 1).path.peek()
                                                          .getRight());
        newValue.addToValue(oldFrame.get(partIndex - 1).path.peek()
                                                            .getRight()
                                                            .getValue());

        newFrame.set(partIndex, newValue);
    }

    private void appendNewLastElement(CalculationFrame oldFrame, CalculationFrame newFrame) {
        final var oldLastElement = oldFrame.get(oldFrame.size() - 1);
        final var newLastElement = oldLastElement.copy();
        newLastElement.path.add(oldLastElement.path.peek()
                                                   .getRight());
        newLastElement.addToValue(oldLastElement.path.peek()
                                                     .getRight()
                                                     .getValue());
        newFrame.add(newLastElement);
    }

    @NotNull
    private TriangleNode[] buildPathFrom(CalculationPart calculationPart) {
        TriangleNode[] path = new TriangleNode[calculationPart.path.size()];
        for (int i = path.length - 1; i >= 0; --i) {
            path[i] = calculationPart.path.pop();
        }
        return path;
    }

    private static class CalculationFrame extends ArrayList<CalculationPart> {
        private CalculationFrame copy() {
            var result = new CalculationFrame();
            result.addAll(stream().map(CalculationPart::copy)
                                  .collect(toList()));
            return result;
        }
    }

    @NoArgsConstructor
    private static class CalculationPart {
        private final Stack<TriangleNode> path = new Stack<>();
        private BigInteger valueSoFar = ZERO;

        private CalculationPart(Stack<TriangleNode> path, BigInteger valueSoFar) {
            this.path.addAll(path);
            this.valueSoFar = valueSoFar;
        }

        private void addToValue(long toAdd) {
            valueSoFar = valueSoFar.add(BigInteger.valueOf(toAdd));
        }

        private CalculationPart copy() {
            return new CalculationPart(path, valueSoFar);
        }

    }
}
