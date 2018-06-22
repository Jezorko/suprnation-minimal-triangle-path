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

public class CumulativeMinimalTrianglePathCalculator implements MinimalTrianglePathCalculator {

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

        TriangleNode[] path = new TriangleNode[minimalPart.path.size()];
        for (int i = path.length - 1; i >= 0; --i) {
            path[i] = minimalPart.path.pop();
        }

        return new TrianglePath(path, minimalPart.valueSoFar);
    }

    @NotNull
    private CalculationFrame calculateFrom(CalculationFrame frame) {
        if (frame.get(0).path.peek()
                             .isLeaf()) {
            return frame;
        }

        // create new last element
        CalculationPart currentLastElement = frame.get(frame.size() - 1);
        CalculationPart newLastElement = new CalculationPart(currentLastElement.path, currentLastElement.valueSoFar);
        newLastElement.path.add(currentLastElement.rightFromCurrent());
        newLastElement.addToValue(currentLastElement.rightFromCurrent()
                                                    .getValue());

        // create new first element
        CalculationPart currentFirstElement = frame.get(frame.size() - 1);
        CalculationPart newFirstElement = new CalculationPart(currentFirstElement.path, currentFirstElement.valueSoFar);
        newFirstElement.path.add(currentFirstElement.rightFromCurrent());
        newFirstElement.addToValue(currentFirstElement.rightFromCurrent()
                                                      .getValue());

        // update n-th element
        for (int i = 1; i < frame.size() - 1; ++i) {
            if (frame.valueOf(i) < frame.valueOf(i + 1)) { // left is smaller
                final CalculationPart partToUpdate = frame.get(i);
                partToUpdate.path.add(partToUpdate.rightFromCurrent());
                partToUpdate.addToValue(partToUpdate.rightFromCurrent()
                                                    .getValue());
            }
            else { // right is smaller
                final CalculationPart partToUpdate = frame.get(i + 1);
                final CalculationPart newPart = new CalculationPart(partToUpdate.path, partToUpdate.valueSoFar);
                newPart.path.add(partToUpdate.leftFromCurrent());
                newPart.addToValue(partToUpdate.leftFromCurrent()
                                               .getValue());
                frame.set(i, newPart);
            }
        }

        // append new elements
        frame.set(0, newFirstElement);
        frame.add(newLastElement);

        return calculateFrom(frame);
    }

    @NoArgsConstructor
    private static class CalculationPart {
        private final Stack<TriangleNode> path = new Stack<>();
        private BigInteger valueSoFar = ZERO;

        private CalculationPart(Stack<TriangleNode> path, BigInteger valueSoFar) {
            this.path.addAll(path);
            this.valueSoFar = valueSoFar;
        }

        private long topValue() {
            return path.lastElement()
                       .getValue();
        }

        private TriangleNode leftFromCurrent() {
            return path.lastElement()
                       .getLeft();
        }

        private TriangleNode rightFromCurrent() {
            return path.lastElement()
                       .getRight();
        }

        private void addToValue(long toAdd) {
            valueSoFar = valueSoFar.add(BigInteger.valueOf(toAdd));
        }

    }

    private static class CalculationFrame extends ArrayList<CalculationPart> {
        long valueOf(int index) {
            return get(index).topValue();
        }
    }
}
