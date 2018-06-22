package jezorko.github.minimaltrianglepath.domain.input

import spock.lang.Specification
import spock.lang.Unroll

import static jezorko.github.minimaltrianglepath.TestUtils.node

class TriangleNodeSpecTest extends Specification {

    @Unroll
    "should consider two instances of #getNode.call() equal because their values and values of their children are equal"() {
        when: "two nodes are created with the same method"
          def firstNode = getNode()
          def secondNode = getNode()

        then: "references are different but instances are equal"
          !firstNode.is(secondNode)
          firstNode == secondNode

        where:
          getNode << [
                  { node(0L) },
                  { node(1L, node(2L), node(3L)) }
          ]
    }

    @Unroll
    "should consider #givenNode as #leafOrNot"() {
        expect:
          givenNode.isLeaf() == (expectedToBeLeaf as boolean)

        where:
          givenNode                    | expectedToBeLeaf
          node(0L)                     | true
          node(1L, node(2L), node(3L)) | false
          leafOrNot = expectedToBeLeaf ? "a leaf" : "not a leaf"

    }

}
