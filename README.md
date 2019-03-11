# Intorduction

This is a minimal project aiming to reproduce https://issues.apache.org/jira/browse/OPENJPA-2778.

# Setup

1. Find and download Oracle's JDBC driver and put it somewhere Maven can find it.
    * **Note:** This project assumes it has groupId `com.oracle.jdbc`, artifactId `ojdbc7`and version `12.1.0.2`.
1. Edit `src/main/resources/META-INF/persistence.xml` to match your Oracle database.
1. Generate the database setup SQL files using `mvn clean compile openjpa:sql`
    * This creates `target/database.sql`, which needs to be executed on your database.

# Build instructions

Build with Maven:

```
mvn clean compile
```

# Reproduce the bug

Build and run the test using:

```
mvn clean test
```

