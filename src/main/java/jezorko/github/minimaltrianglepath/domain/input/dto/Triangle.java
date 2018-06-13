package jezorko.github.minimaltrianglepath.domain.input.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

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
 * It is split into rows which can be accessed
 * using {@link #getRows()} method.
 */

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor(access = PACKAGE)
public class Triangle {

    /**
     * Rows of the triangle.
     * Consecutive values represent rows from top to bottom,
     * e.g. the 0-th element is the topmost row.
     */
    private final List<TriangleRow> rows;

}
