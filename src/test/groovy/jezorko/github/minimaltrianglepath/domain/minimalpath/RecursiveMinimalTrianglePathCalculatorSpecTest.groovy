package jezorko.github.minimaltrianglepath.domain.minimalpath

import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Timeout
import spock.lang.Unroll

import static java.util.concurrent.TimeUnit.SECONDS
import static jezorko.github.minimaltrianglepath.TestDataSets.KNOWN_TRIANGLES_AND_MINIMAL_PATHS
import static jezorko.github.minimaltrianglepath.TestUtils.randomTriangle
import static jezorko.github.minimaltrianglepath.TestUtils.shallowPathsEquals

class RecursiveMinimalTrianglePathCalculatorSpecTest extends Specification {

    @Subject
    def calculator = new RecursiveMinimalTrianglePathCalculator()

    @Unroll
    "should calculate path of #triangle as #expectedPath"() {
        when:
          def result = calculator.calculateFrom triangle

        then:
          shallowPathsEquals(result, expectedPath)

        where:
          triangleAndPath << KNOWN_TRIANGLES_AND_MINIMAL_PATHS
          triangle = triangleAndPath.first()
          expectedPath = triangleAndPath.second()
    }

    @Unroll
    @Timeout(value = 1, unit = SECONDS)
    "should not throw and calculate minimal path of a triangle with size=#size in less than one second"() {
        when:
          def result = calculator.calculateFrom triangle

        then: "no exception is thrown"
          noExceptionThrown()

        and: "result and it's value exists"
          result != null
          result.value != null

        and: "there are as many nodes in path as rows in the triangle"
          result.nodes != null
          result.nodes.size() == size as int

        where:
          size << (1..15)
          triangle = randomTriangle size
    }

}
