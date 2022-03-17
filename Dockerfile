
FROM adoptopenjdk:8
ARG JAR_FILE=build/libs/P8_gpsUtil-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} gpsutil.jar
ENTRYPOINT ["java","-jar","/gpsutil.jar"]