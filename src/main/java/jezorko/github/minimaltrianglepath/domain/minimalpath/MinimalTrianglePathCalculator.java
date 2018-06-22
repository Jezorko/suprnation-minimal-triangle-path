package jezorko.github.minimaltrianglepath.domain.minimalpath;

import jezorko.github.minimaltrianglepath.domain.input.Triangle;
import org.jetbrains.annotations.NotNull;

public interface MinimalTrianglePathCalculator {

    @NotNull
    TrianglePath calculateFrom(Triangle triangle);

}
