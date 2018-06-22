package jezorko.github.minimaltrianglepath.domain.minimalpath;

/**
 * Exposes methods for creating {@link MinimalTrianglePathCalculator} instances.
 */
public class MinimalPathCalculatorStaticFactory {

    /**
     * Creates default minimal path calculation algorithm class.
     * Instances of this class are immutable and may be used asynchronously.
     *
     * @return {@link CumulativeMinimalTrianglePathCalculator}
     */
    public static MinimalTrianglePathCalculator defaultCalculator() {
        return new CumulativeMinimalTrianglePathCalculator();
    }

}
