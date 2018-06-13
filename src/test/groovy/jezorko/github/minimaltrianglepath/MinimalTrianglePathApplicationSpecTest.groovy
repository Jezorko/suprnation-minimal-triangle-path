package jezorko.github.minimaltrianglepath

import spock.lang.Specification

class MinimalTrianglePathApplicationSpecTest extends Specification {

    def "should run main method without throwing any exceptions"() {
        when:
          MinimalTrianglePathApplication.main()

        then:
          noExceptionThrown()
    }

}
