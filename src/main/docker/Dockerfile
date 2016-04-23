FROM java:8
VOLUME /tmp
ENV aws.sqs.destination=PostcodeLocationFinderQueue
ADD JmsSqs-0.0.1-SNAPSHOT.jar app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
EXPOSE 8080
