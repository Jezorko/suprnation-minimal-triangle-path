package jezorko.github.minimaltrianglepath.domain.minimalpath

import org.spockframework.util.Pair
import spock.lang.Specification
import spock.lang.Unroll

import static jezorko.github.minimaltrianglepath.TestUtils.randomTriangle

class MinimalTrianglePathCalculatorSpecITTest extends Specification {

    static ALGORITHMS_TO_TEST = [
            new CumulativeMinimalTrianglePathCalculator()
    ]

    static MINIMUM_ROWS_AMOUNT = 2
    static MAXIMUM_ROWS_AMOUNT = 17

    static ALGORITHMS_AND_ROW_AMOUNTS = {
        ALGORITHMS_TO_TEST.collect { (MINIMUM_ROWS_AMOUNT..MAXIMUM_ROWS_AMOUNT).collect({ rows -> Pair.of(rows, it) }) }
                          .flatten() as List<Pair<Long, MinimalTrianglePathCalculator>>
    }()

    @Unroll
    "results of #algorithm.class.simpleName should equal those from recursive algorithm for triangle with #rowsAmount rows"() {
        given:
          def recursiveAlgorithm = new RecursiveMinimalTrianglePathCalculator()
          def triangle = randomTriangle rowsAmount

        when:
          def recursiveAlgorithmResult = recursiveAlgorithm.calculateFrom triangle
          def algorithmResult = algorithm.calculateFrom triangle

        then:
          algorithmResult == recursiveAlgorithmResult

        where:
          algorithmAndRow << ALGORITHMS_AND_ROW_AMOUNTS
          rowsAmount = algorithmAndRow.first() as int
          algorithm = algorithmAndRow.second() as MinimalTrianglePathCalculator
    }

}
