FROM maven:3.5-jdk-8 as maven
RUN mkdir /myapp
COPY . /myapp
WORKDIR /myapp
RUN mvn package

FROM openjdk:8-jre-stretch
RUN mkdir /myapp
COPY --from=maven /myapp/target/recepcion-1.0-SNAPSHOT.jar /myapp/recepcion.jar
COPY --from=maven /myapp/src/main/resources /myapp/src/main/resources
WORKDIR /myapp
