package jezorko.github.minimaltrianglepath.domain.minimalpath;

import jezorko.github.minimaltrianglepath.domain.input.Triangle;
import org.jetbrains.annotations.NotNull;

/**
 * Calculates minimal path of given {@link Triangle}.
 * A triangle path is a set of nodes that begins at the
 * top of the triangle and ends at one of the leaves.
 * Minimal triangle path is such a set where the sum
 * of nodes' values is the lowest of all paths.
 * For example, triangle:
 * <pre>
 *       22
 *     12   3
 *   78  53  34
 * 11  25  10  82
 * </pre>
 * consists of the following paths:
 * <pre>
 * [ 22, 12, 78, 11 ] = 123
 * [ 22, 12, 78, 25 ] = 137
 * [ 22, 12, 53, 25 ] = 112
 * [ 22, 12, 53, 10 ] = 97
 * [ 22,  3, 53, 25 ] = 103
 * [ 22,  3, 53, 10 ] = 88
 * [ 22,  3, 34, 10 ] = 69
 * [ 22,  3, 34, 82 ] = 141
 * </pre>
 * from these paths, the minimal is:
 * <pre>
 * [ 22,  3, 34, 10 ] = 69
 * </pre>
 * because the sum of it's elements is the lowest.
 */
public interface MinimalTrianglePathCalculator {

    /**
     * @param triangle to calculate minimal path of
     *
     * @return new {@link TrianglePath} representing minimal path in given triangle
     */
    @NotNull
    TrianglePath calculateFrom(Triangle triangle);

}
