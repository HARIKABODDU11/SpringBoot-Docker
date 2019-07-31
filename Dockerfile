FROM java:8-jdk-alpine

COPY ./target/Muzix-0.0.1-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch Muzix-0.0.1-SNAPSHOT.jar'

ENTRYPOINT ["java","-jar","Muzix-0.0.1-SNAPSHOT.jar"]