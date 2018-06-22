package jezorko.github.minimaltrianglepath.domain.output;

import jezorko.github.minimaltrianglepath.domain.minimalpath.TrianglePath;

/**
 * Prints given {@link TrianglePath} to standard console output.
 */
public class StandardOutputTrianglePathPrinter {

    private static final TrianglePathStringFormatter FORMATTER = new TrianglePathStringFormatter();

    public static void print(TrianglePath trianglePath) {
        System.out.println("Minimal path is: " + FORMATTER.format(trianglePath));
    }

}
