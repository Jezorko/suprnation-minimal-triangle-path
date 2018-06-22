package jezorko.github.minimaltrianglepath.domain.input

import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

import static jezorko.github.minimaltrianglepath.TestUtils.node
import static jezorko.github.minimaltrianglepath.TestUtils.triangle

class InputStreamTriangleReaderSpecTest extends Specification {

    def rows = Mock TriangleRowsReader

    @Subject
    def reader = new InputStreamTriangleReader(rows)

    @Unroll
    "should transform #givenRows to #expectedResult"() {
        when: "a triangle is read"
          def actualResult = reader.get()

        then: "the rows are read one by one using iterator"
          1 * rows.iterator() >> givenRows.iterator()

        and: "a triangle is built from given nodes"
          actualResult.doOnSuccess({ assert it == expectedResult })
                      .doOnError({ throw it })

        where:
          givenRows                                                          || expectedResult
          rows([[node(1)]])                                                  || triangle(node(1))
          rows([[node(1)], [node(2), node(3)]])                              || triangle(node(1, node(2), node(3)))
          rows([[node(1)], [node(2), node(3)], [node(4), node(5), node(6)]]) || triangle(node(1, node(2, node(4), node(5)), node(3, node(5), node(6))))
    }

    def "should reuse references to nodes"() {
        given: "nodes that should share other nodes"
          def top = node(1)
          def secondRow = [node(2), node(3)]
          def sharedNode = node(5)
          def thirdRow = [node(4), sharedNode, node(6)]
          def givenRows = rows([[top], secondRow, thirdRow])

        when: "a triangle is read"
          def actualResult
          reader.get().doOnSuccess({ actualResult = it })

        then: "the rows are read one by one using iterator"
          1 * rows.iterator() >> givenRows.iterator()

        and: "top node was not recreated"
          actualResult != null
          actualResult.top == top

        and: "shared node was reused"
          actualResult.top.left.right.is sharedNode
          actualResult.top.right.left.is sharedNode
          actualResult.top.left.right.is actualResult.top.right.left
    }

    static rows(givenRows) {
        givenRows.collect { it as TriangleNode[] }
    }

}
