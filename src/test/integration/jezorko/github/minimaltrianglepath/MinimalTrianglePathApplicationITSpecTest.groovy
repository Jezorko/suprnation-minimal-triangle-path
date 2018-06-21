package jezorko.github.minimaltrianglepath


import spock.lang.Specification
import spock.lang.Unroll

import static java.util.Arrays.asList

class MinimalTrianglePathApplicationITSpecTest extends Specification {

    static TEST_DATA_FILES_DIRECTORY = "src/test/resources/triangle_data_files"

    @Unroll
    "should read the input file '#inputFileRelativePath' and throw no exceptions"() {
        when:
          MinimalTrianglePathApplication.main("--input-file", inputFilePath)

        then:
          noExceptionThrown()

        where:
          inputFilePath << asList(new File(TEST_DATA_FILES_DIRECTORY).listFiles()).collect { it.absolutePath }
          inputFileRelativePath = inputFilePath.replaceAll(".*${TEST_DATA_FILES_DIRECTORY}/", "")
    }

}
