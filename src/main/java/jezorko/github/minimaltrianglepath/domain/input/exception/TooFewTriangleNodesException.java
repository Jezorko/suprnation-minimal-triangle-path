package jezorko.github.minimaltrianglepath.domain.input.exception;

import lombok.Getter;

@Getter
public class TooFewTriangleNodesException extends RuntimeException {

    private final int expectedNodesAmount;
    private final int actualNodesAmount;

    public TooFewTriangleNodesException(int expectedNodesAmount, int actualNodesAmount) {
        super("expected to read " + expectedNodesAmount + " triangle nodes," +
              " but only " + actualNodesAmount + " nodes were in the input");
        this.expectedNodesAmount = expectedNodesAmount;
        this.actualNodesAmount = actualNodesAmount;
    }

}
