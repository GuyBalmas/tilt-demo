FROM openjdk:8-jre-alpine

RUN apk add entr

WORKDIR /workspace
ADD BOOT-INF/lib /workspace/lib
ADD META-INF /workspace/META-INF
ADD BOOT-INF/classes /workspace

ENTRYPOINT java -cp .:./lib/* org.example.app.ExampleApp