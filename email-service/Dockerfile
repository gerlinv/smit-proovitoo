FROM openjdk:17-jdk
WORKDIR /workspace/app/email-service
COPY . .
RUN microdnf install findutils
RUN bash -c "if [ -f gradlew ]; then chmod +x gradlew; fi"
ENTRYPOINT ["./gradlew", "bootRun"]