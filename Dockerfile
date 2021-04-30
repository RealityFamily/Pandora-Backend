FROM adoptopenjdk:11-jdk-openj9 AS build
COPY --chown=1000 . /home/gradle/src
WORKDIR /home/gradle/src
RUN chmod 777 *
RUN ./gradlew assemble

FROM adoptopenjdk:11-jre-openj9
EXPOSE 8082
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/pandora-backend.jar
ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/pandora-backend.jar"]
