package jezorko.github.minimaltrianglepath;

import java.util.Optional;

public final class MinimalTrianglePathApplication {

    public static void main(String[] args) {
        Optional.of("Hello Java 10 World!")
                .ifPresentOrElse(System.out::println,
                                 () -> System.out.println("wops!"));
    }

}
