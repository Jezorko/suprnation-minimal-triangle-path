package jezorko.github.minimaltrianglepath

import jezorko.github.minimaltrianglepath.domain.input.Triangle
import jezorko.github.minimaltrianglepath.domain.input.TriangleNode
import jezorko.github.minimaltrianglepath.domain.minimalpath.TrianglePath
import org.spockframework.util.Pair

import static jezorko.github.minimaltrianglepath.TestUtils.node
import static jezorko.github.minimaltrianglepath.TestUtils.triangle

class TestDataSets {

    static List<Pair<Triangle, TrianglePath>> KNOWN_TRIANGLES_AND_MINIMAL_PATHS = [
            Pair.of(triangle(
                    node(7,
                         node(6,
                              node(3,
                                   node(11),
                                   node(2)
                              ),
                              node(8,
                                   node(2),
                                   node(10)
                              )
                         ),
                         node(3,
                              node(8,
                                   node(2),
                                   node(10)),
                              node(5,
                                   node(10),
                                   node(9)
                              )
                         )
                    )
            ), new TrianglePath([node(7), node(6), node(3), node(2)] as TriangleNode[], 18g))
    ]

}
