package jezorko.github.minimaltrianglepath.domain.input.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

import static lombok.AccessLevel.PACKAGE;

/**
 * A single row of a {@link Triangle} represented as a list of numbers.
 */
@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor(access = PACKAGE)
public class TriangleRow {

    /**
     * Values of the row, as they appear from left to right
     * in the triangle's graphical representation.
     */
    private final List<Long> values;

}
