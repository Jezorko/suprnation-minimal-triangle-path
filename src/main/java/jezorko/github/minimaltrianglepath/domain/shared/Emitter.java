package jezorko.github.minimaltrianglepath.domain.shared;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * Emits (yields) objects of given type
 * or an empty {@link Optional} if no more
 * objects are available.
 *
 * @param <T> emitted type
 */
public interface Emitter<T> extends Supplier<Optional<T>> {
}
