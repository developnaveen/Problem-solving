# 📦 Maven Commands – Complete Developer Guide

This document lists commonly used Maven commands with clear explanations.
Useful for daily development, CI/CD.

---

## 🔹 Prerequisites

- Java installed
- Maven installed OR Maven Wrapper (`mvnw`)
- Run commands from the directory containing `pom.xml`

Check versions:
```bash
java -version
mvn -version
```

---

## 🧹 Clean Commands

Clean the project (deletes the `target/` directory):
```bash
mvn clean
```
---

## ⚙️ Build Lifecycle Commands

Compile source code:
```bash
mvn compile
```
Compile test code:
```bash
mvn test-compile
```
Run tests:
```bash
mvn test
```
Package the application (JAR/WAR):
```bash
mvn package
```
Full build + install to local repository:
```bash
mvn install
```
Clean + full build:
```bash
mvn clean install
```
---

## 🚀 Run Application

Spring Boot project:
```bash
mvn spring-boot:run
```
---

## ⏭️ Skip Tests

Skip running tests:
```bash
mvn clean install -DskipTests
```
Skip compiling and running tests:
```bash
mvn clean install -Dmaven.test.skip=true
```
---

## 📦 Dependency Management

Download dependencies:
```bash
mvn dependency:resolve
```
Show dependency tree:
```bash
mvn dependency:tree
```
Purge local dependencies:
```bash
mvn dependency:purge-local-repository
```
---

## 🔍 Project Information

Validate project structure:
```bash
mvn validate
```
Show effective POM:
```bash
mvn help:effective-pom
```
Show active profiles:
```bash
mvn help:active-profiles
```
Describe a plugin:
```bash
mvn help:describe -Dplugin=org.springframework.boot:spring-boot-maven-plugin
```
---

## 🧩 Profiles

Run with a specific profile:
```bash
mvn clean install -Pdev
```
Run with multiple profiles:
```bash
mvn clean install -Pdev,db
```
---

## 🧪 Debug & Troubleshooting

Enable debug logs:
```bash
mvn clean install -X
```
Show error stack trace:
```bash
mvn clean install -e
```
Offline build:
```bash
mvn clean install -o
```
---

## 🧱 Maven Wrapper Commands

Windows:
```bash
mvnw clean install
```
Linux / Mac:
```bash
./mvnw clean install
```
---

## 🧠 Common Developer Workflows

Before pushing code:
```bash
mvn clean install
```
Quick build without tests:
```bash
mvn clean package -DskipTests
```
CI/CD pipeline build:
```bash
mvn clean verify
```
---

## 📁 Important Maven Directories
| path | Description |
|------|-------------|
|src/main/java      | Application source code
|src/main/resources | Configuration files
|src/test/java      | Test cases
|target/            | Build output
|.m2/repository     | Local Maven repository
