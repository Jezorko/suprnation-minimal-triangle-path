package jezorko.github.minimaltrianglepath.domain.shared;

public interface CheckedSupplier<T> {

    T get() throws Throwable;

}
