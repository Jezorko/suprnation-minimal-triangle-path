package jezorko.github.minimaltrianglepath;

import jezorko.github.minimaltrianglepath.domain.cli.CommandLineArguments;
import jezorko.github.minimaltrianglepath.domain.input.InputResult;
import jezorko.github.minimaltrianglepath.domain.input.TriangleInputReaderStaticFactory;
import jezorko.github.minimaltrianglepath.domain.input.TriangleReader;
import jezorko.github.minimaltrianglepath.domain.output.StandardOutputTrianglePathPrinter;

import static java.lang.System.err;
import static java.util.Optional.of;
import static jezorko.github.minimaltrianglepath.domain.input.TriangleInputReaderStaticFactory.standardInputStreamReader;
import static jezorko.github.minimaltrianglepath.domain.minimalpath.MinimalPathCalculatorStaticFactory.defaultCalculator;

public final class MinimalTrianglePathApplication {

    public static void main(String... args) {
        new CommandLineArguments(args).getInputFile()
                                      .map(TriangleInputReaderStaticFactory::fileReader)
                                      .or(() -> of(standardInputStreamReader()))
                                      .map(TriangleReader::get)
                                      .orElseGet(InputResult::empty)
                                      .doOnSuccess(triangle -> of(triangle).map(defaultCalculator()::calculateFrom)
                                                                           .ifPresent(StandardOutputTrianglePathPrinter::print))
                                      .doOnError(err::println);
    }

}
