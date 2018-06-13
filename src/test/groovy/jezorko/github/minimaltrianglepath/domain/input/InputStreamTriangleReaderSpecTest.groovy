package jezorko.github.minimaltrianglepath.domain.input

import jezorko.github.minimaltrianglepath.domain.input.dto.Triangle
import jezorko.github.minimaltrianglepath.domain.input.dto.TriangleRow
import jezorko.github.minimaltrianglepath.domain.input.exception.NotATriangleException
import spock.lang.Specification
import spock.lang.Unroll

import static java.nio.charset.StandardCharsets.UTF_8
import static org.apache.commons.io.IOUtils.toInputStream

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
          "1"             || triangle([row([1L])])
          "1\n2 3"        || triangle([row([1L]), row([2L, 3L])])
          "1\n2 3\n4 5 6" || triangle([row([1L]), row([2L, 3L]), row([4L, 5L, 6L])])
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

    static formatTriangleInput(String input) {
        input.replaceAll("\n", "\\\\n")
    }

    static inputAsStream(String input) {
        toInputStream(input, UTF_8)
    }

    def triangle(List<TriangleRow> values) {
        new Triangle(values)
    }

    def row(List<Long> values) {
        new TriangleRow(values)
    }

}
