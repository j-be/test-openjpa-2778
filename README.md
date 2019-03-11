# Intorduction

This is a minimal project aiming to reproduce https://issues.apache.org/jira/browse/OPENJPA-2778.

# Setup

1. Find and download Oracle's JDBC driver and put it somewhere Maven can find it.
    * **Note:** This project assumes it has groupId `com.oracle.jdbc`, artifactId `ojdbc10`and version `19.3.0.0.0`.
1. Edit `src/main/resources/META-INF/persistence.xml` to match your Oracle database.

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

# Countercheck on Hibernate

1. Switch to branch `hibernate`
1. `mvn clean test`
