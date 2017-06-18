FROM openjdk:8-jre-alpine

RUN mkdir -p /usr/src/myapp
COPY "./build/libs/overwatchdashboard-1.0-SNAPSHOT.jar" /usr/src/myapp
COPY ./static /usr/src/myapp/static
COPY ./tags.json /usr/src/myapp
WORKDIR /usr/src/myapp
CMD ["java", "-jar", "overwatchdashboard-1.0-SNAPSHOT.jar"]
