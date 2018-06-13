package jezorko.github.minimaltrianglepath.domain.input;

import jezorko.github.minimaltrianglepath.domain.input.dto.Triangle;

import java.util.Iterator;

/**
 * Defines a {@link Triangle} emitting class.
 */
interface TriangleReader extends Iterator<Triangle> {

    Triangle nextTriangle();

    @Override
    default Triangle next() {
        return nextTriangle();
    }

}
