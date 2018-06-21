package jezorko.github.minimaltrianglepath.domain.cli

import spock.lang.Specification
import spock.lang.Unroll

import static java.util.Optional.empty
import static java.util.Optional.of

class CommandLineArgumentsSpecTest extends Specification {

    @Unroll
    "should return input file #expectedPath for arguments #givenArguments"() {
        given:
          def commandLineArguments = new CommandLineArguments(givenArguments as String[])

        when:
          def result = commandLineArguments.getInputFile()

        then:
          result.map { it.getPath() } == expectedPath

        where:
          givenArguments           || expectedPath
          []                       || empty()
          ["--input-file", "test"] || of("test")
          ["-i", "test"]           || of("test")

    }

}
