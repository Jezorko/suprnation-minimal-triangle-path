package jezorko.github.minimaltrianglepath.domain.input

import jezorko.github.minimaltrianglepath.domain.input.exception.NotATriangleException
import spock.lang.Specification
import spock.lang.Unroll

import static jezorko.github.minimaltrianglepath.domain.input.TestUtils.*

class InputStreamTriangleReaderSpecTest extends Specification {

    def "should return an empty optional if input does not contain any more triangles"() {
        setup:
          def reader = new InputStreamTriangleReader(inputAsStream(""))

        when:
          def result = reader.get()

        then:
          !result.isPresent()
    }

    @Unroll
    "should read '#formattedInput' as #expectedResult"() {
        setup:
          def reader = new InputStreamTriangleReader(inputAsStream(input))

        when:
          def actualResult = reader.get()

        then:
          actualResult.isPresent()
          actualResult.get() == expectedResult

        where:
          input           || expectedResult
          "1"             || triangle(node(1L))
          "1\n2 3"        || triangle(node(1L, node(2L), node(3L)))
          "1\n2 3\n4 5 6" || triangle(node(1L, node(2L, node(4L), node(5L)), node(3L, node(5L), node(6L))))
          formattedInput = formatTriangleInput input
    }

    @Unroll
    "should throw #expectedException.simpleName for given input '#formattedInput'"() {
        setup:
          def reader = new InputStreamTriangleReader(inputAsStream(input))

        when:
          reader.get()

        then:
          thrown expectedException

        where:
          input       || expectedException
          "NaN"       || NumberFormatException
          "1\n2 NaN"  || NumberFormatException
          "1\n2\n3 4" || NotATriangleException
          formattedInput = formatTriangleInput input
    }

}
