package jezorko.github.minimaltrianglepath.domain.shared;

/**
 * Equivalent of {@link java.util.function.Supplier}, but
 * can throw any exception.
 * @param <T> the type of results supplied by this supplier
 * @param <E> exception type that can be thrown by this supplier
 */
public interface ThrowingSupplier<T, E extends Throwable> {

    T get() throws E;

}
