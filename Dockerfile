FROM openjdk:21-jdk-slim

WORKDIR /app

COPY gradlew .
COPY gradle gradle
COPY build.gradle settings.gradle ./
COPY config config
COPY src src

RUN ./gradlew build --no-daemon

WORKDIR /app/build/libs

CMD ["java", "-jar", "mcloude-core-2.0.0-SNAPSHOT.jar"]