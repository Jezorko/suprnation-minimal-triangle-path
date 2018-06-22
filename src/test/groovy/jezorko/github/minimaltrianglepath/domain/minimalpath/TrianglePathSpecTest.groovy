package jezorko.github.minimaltrianglepath.domain.minimalpath


import jezorko.github.minimaltrianglepath.domain.input.TriangleNode
import spock.lang.Specification
import spock.lang.Unroll

import static jezorko.github.minimaltrianglepath.TestUtils.node

class TrianglePathSpecTest extends Specification {

    @Unroll
    "should stringify #givenPath as '#expectedString'"() {
        given:
          def value = givenPath.collect { it.value }
                               .collect { BigInteger.valueOf(it) }
                               .inject(BigInteger.ZERO) { a, b -> a + b }

        when:
          def actualString = new TrianglePath(givenPath as TriangleNode[], value as BigInteger).toString()

        then:
          actualString == expectedString

        where:
          givenPath          || expectedString
          [node(1)]          || "1 = 1"
          [node(1), node(2)] || "1 + 2 = 3"
    }

}
