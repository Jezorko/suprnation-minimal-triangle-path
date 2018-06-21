package jezorko.github.minimaltrianglepath

import spock.lang.Specification
import spock.lang.Unroll

class MinimalTrianglePathApplicationITSpecTest extends Specification {

    @Unroll
    "should read the input file '#inputFilePath' and throw no exceptions"() {
        when:
          MinimalTrianglePathApplication.main("--input-file", inputFilePath)

        then:
          noExceptionThrown()

        where:
          inputFilePath << [
                  "src/test/resources/data_tiny.txt",
                  "src/test/resources/data_small.txt",
                  "src/test/resources/data_big.txt"
          ]
    }

}
