# Supercell ex 1

Solution for exercise 1 of [Supercell 2023 SWE Intern Exercise](https://sc-id-intern-exercise.s3.us-east-1.amazonaws.com/intern.pdf)

## Building and running

Requirements:
- [Maven](https://maven.apache.org/)
- Java19 (tested on OpenJDK19)

To **build** the jar file:
```bash
mvn clean update
```
The resulting .jar file should be named supercell-1.jar. The file is shaded, meaning all dependencies are already included.

To **run** the jar file:
```bash
java -jar supercell-1.jar -i input_file.txt
```
