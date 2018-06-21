package jezorko.github.minimaltrianglepath.domain.input.exception;

import lombok.Getter;

@Getter
public class InvalidTriangleNodeValueException extends RuntimeException {

    private final int receivedInvalidValue;

    public InvalidTriangleNodeValueException(int receivedValue) {
        super("triangle node cannot be built from given input, only digits, space character and new line is allowed" +
              " but received " + receivedValue + formatAsCharIfAsciiCharacterOrDigit(receivedValue));
        this.receivedInvalidValue = receivedValue;
    }

    private static String formatAsCharIfAsciiCharacterOrDigit(int receivedValue) {
        if (receivedValue >= ' ' && receivedValue <= '~') {
            return " (ascii '" + ((char) receivedValue) + "')";
        }
        return "";
    }
}
