[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0) [![Build Status](https://staging.travis-ci.org/Jezorko/suprnation-minimal-triangle-path.svg?branch=master)](https://staging.travis-ci.org/Jezorko/suprnation-minimal-triangle-path)

## Minimal triangle path calculating application

### Task specification

[Description PDF](exercise%20description.pdf)

### Requirements

 * Java 10
   * JDK for building
   * JVM for running
 * Maven

### Building

To build an executable `.jar`, execute:

```mvn package```

### Running

To run the `.jar`:

```java -jar minimal-triangle-path-1.0-SNAPSHOT-jar-with-dependencies.jar```

#### Parameters

Application accepts a single parameter, `--input-file <PATH>` (or `-i <PATH>` for short).
If this parameter is provided, input is read from given file.
Otherwise, application will read the input from `stdin`.