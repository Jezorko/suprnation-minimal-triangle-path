package jezorko.github.minimaltrianglepath.domain.output


import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

import static jezorko.github.minimaltrianglepath.TestUtils.node
import static jezorko.github.minimaltrianglepath.TestUtils.path

class TrianglePathStringFormatterSpecTest extends Specification {

    @Subject
    def formatter = new TrianglePathStringFormatter()

    @Unroll
    "should format path #pathAsString as '#expectedResult'"() {
        when:
          def actualResult = formatter.format givenPath

        then:
          actualResult == expectedResult

        where:
          givenPath                            || expectedResult
          null                                 || "null"
          path([], 0)                          || "? = 0"
          path([node(1)], 1)                   || "1 = 1"
          path([node(1), node(2), node(3)], 6) || "1 + 2 + 3 = 6"
          pathAsString = givenPath?.nodes
    }

}
