package jezorko.github.minimaltrianglepath.domain.input;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import static lombok.AccessLevel.PACKAGE;

/**
 * Representation of a triangle built from numbers.
 * Example:
 * <pre>
 *       22
 *     12   3
 *   78  53  34
 * 11  25  10  82
 * </pre>
 * Each number is represented by a {@link TriangleNode}.
 * The top node (number 22 in the example above) is
 * accessible from within this class using the
 * {@link #getTop()} method.
 * It is guaranteed that the top node will never be null.
 */

@EqualsAndHashCode
@RequiredArgsConstructor(access = PACKAGE)
public class Triangle {

    @NonNull
    private final TriangleNode top;

    /**
     * @return the top node of this {@link Triangle}
     */
    @NotNull
    public TriangleNode getTop() {
        return top;
    }

    @Override
    public String toString() {
        return "Δ(" + top + ")";
    }
}
