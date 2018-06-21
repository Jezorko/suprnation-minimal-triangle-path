package jezorko.github.minimaltrianglepath;

import jezorko.github.minimaltrianglepath.domain.cli.CommandLineArguments;
import jezorko.github.minimaltrianglepath.domain.input.InputResult;
import jezorko.github.minimaltrianglepath.domain.input.InputStreamTriangleReader;
import lombok.SneakyThrows;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Optional;

import static java.lang.System.err;
import static java.lang.System.in;

public final class MinimalTrianglePathApplication {

    public static void main(String... args) {
        new CommandLineArguments(args).getInputFile()
                                      .map(MinimalTrianglePathApplication::inputFileAsStream)
                                      .or(() -> Optional.of(in))
                                      .map(InputStreamTriangleReader::new)
                                      .map(InputStreamTriangleReader::get)
                                      .orElseGet(InputResult::empty)
                                      .doOnSuccess(triangle -> {
                                          /* TODO: calculate path */
                                      })
                                      .doOnError(err::print);
    }

    @SneakyThrows
    private static InputStream inputFileAsStream(File file) {
        return new FileInputStream(file);
    }

}
