package jezorko.github.minimaltrianglepath.domain.cli;

import com.martiansoftware.jsap.FlaggedOption;
import com.martiansoftware.jsap.JSAP;
import com.martiansoftware.jsap.JSAPResult;
import lombok.SneakyThrows;

import java.io.File;
import java.util.Optional;

import static com.martiansoftware.jsap.JSAP.STRING_PARSER;

public class CommandLineArguments {

    private static final String INPUT_FILE_OPTION_NAME = "input-file";

    private final JSAPResult parsingResult;

    @SneakyThrows
    public CommandLineArguments(String... args) {
        JSAP jsap = new JSAP();
        jsap.registerParameter(inputFileOption());

        parsingResult = jsap.parse(args);
    }

    public Optional<File> getInputFile() {
        return Optional.of(INPUT_FILE_OPTION_NAME)
                       .map(parsingResult::getString)
                       .map(File::new);
    }

    private static FlaggedOption inputFileOption() {
        return new FlaggedOption(INPUT_FILE_OPTION_NAME).setStringParser(STRING_PARSER)
                                                        .setRequired(false)
                                                        .setShortFlag('i')
                                                        .setLongFlag(INPUT_FILE_OPTION_NAME);
    }

}
