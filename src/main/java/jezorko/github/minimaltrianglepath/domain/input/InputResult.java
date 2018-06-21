package jezorko.github.minimaltrianglepath.domain.input;

import jezorko.github.minimaltrianglepath.domain.shared.CheckedSupplier;

import java.util.function.Consumer;

/**
 * Result of reading input.
 * If reading succeeds, {@link #doOnSuccess(Consumer)} method can be used to consume the result.
 * Otherwise, {@link #doOnError(Consumer)} can be used to react to an error.
 *
 * @param <Result> the type of the result
 */
public interface InputResult<Result> {

    /**
     * Wraps result creation in a try-catch block.
     * If the supplier throws, an {@link #error(Throwable)} result is returned.
     * Otherwise, a {@link #success(Object)} result is returned.
     *
     * @param resultSupplier wraps result creating logic
     * @param <Result>       type of the result
     *
     * @return a new {@link InputResult} instance
     */
    static <Result> InputResult<Result> tryOf(CheckedSupplier<Result> resultSupplier) {
        try {
            return success(resultSupplier.get());
        } catch (Throwable exception) {
            return error(exception);
        }
    }

    /**
     * Creates a new {@link InputResult} from a successful input reading attempt
     *
     * @param result   to consumed by {@link #doOnSuccess(Consumer)} method
     * @param <Result> the type of the result
     *
     * @return a new instance of {@link InputResult}
     */
    static <Result> InputResult<Result> success(Result result) {
        return new InputResult<>() {
            @Override
            public InputResult<Result> doOnSuccess(Consumer<Result> consumer) {
                consumer.accept(result);
                return this;
            }

            @Override
            public InputResult<Result> doOnError(Consumer<Throwable> consumer) {
                return this;
            }
        };
    }

    /**
     * Creates a new {@link InputResult} from a failed reading attempt
     *
     * @param error    to consumed by {@link #doOnError(Consumer)} method
     * @param <Result> the type of the result
     *
     * @return a new instance of {@link InputResult}
     */
    static <Result> InputResult<Result> error(Throwable error) {
        return new InputResult<>() {
            @Override
            public InputResult<Result> doOnSuccess(Consumer<Result> consumer) {
                return this;
            }

            @Override
            public InputResult<Result> doOnError(Consumer<Throwable> consumer) {
                consumer.accept(error);
                return this;
            }
        };
    }

    /**
     * @param consumer that will accept result if present
     */
    InputResult<Result> doOnSuccess(Consumer<Result> consumer);

    /**
     * @param consumer that will accept error if occurred
     */
    InputResult<Result> doOnError(Consumer<Throwable> consumer);

}
