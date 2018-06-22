package jezorko.github.minimaltrianglepath.domain.input

import jezorko.github.minimaltrianglepath.domain.input.exception.InvalidTriangleNodeValueException
import jezorko.github.minimaltrianglepath.domain.input.exception.TooFewTriangleNodesException
import spock.lang.Specification
import spock.lang.Unroll

import static jezorko.github.minimaltrianglepath.TestUtils.*

class TriangleRowsReaderSpecTest extends Specification {

    @Unroll
    "should read #expectedRows from #formattedInput"() {
        given:
          def rows = new TriangleRowsReader(inputAsBufferedReader(input))
          def result = []

        when:
          rows.forEach { result.add it }

        then:
          result == expectedRows

        where:
          input           || expectedRows
          "1"             || [[node(1)]]
          "1\n2 3"        || [[node(1)], [node(2), node(3)]]
          "1\n2 3\n4 5 6" || [[node(1)], [node(2), node(3)], [node(4), node(5), node(6)]]
          formattedInput = formatTriangleInput input
    }

    @Unroll
    "should throw #expectedExceptionType.simpleName for input #formattedInput"() {
        given:
          def rows = new TriangleRowsReader(inputAsBufferedReader(input))

        when:
          for (def row : rows) {
              /* do nothing, expect exception */
          }

        then:
          thrown expectedExceptionType

        where:
          input         || expectedExceptionType
          "bla"         || InvalidTriangleNodeValueException
          "\n1"         || InvalidTriangleNodeValueException
          ""            || TooFewTriangleNodesException
          "1\n2"        || TooFewTriangleNodesException
          "1\n2 3\n4 5" || TooFewTriangleNodesException
          formattedInput = formatTriangleInput input
    }

}
