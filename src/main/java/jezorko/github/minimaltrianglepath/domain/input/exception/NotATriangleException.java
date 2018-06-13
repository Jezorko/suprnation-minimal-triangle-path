package jezorko.github.minimaltrianglepath.domain.input.exception;

public class NotATriangleException extends RuntimeException {
    public NotATriangleException(int expectedNumbersAmount, String rawRowData) {
        super("Expected " + expectedNumbersAmount + " numbers in a row '" + rawRowData + "'");
    }
}
