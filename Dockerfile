# Use a build argument for version
ARG VERSION=0.0.1-SNAPSHOT

FROM eclipse-temurin:17
VOLUME /tmp

COPY data-service-${VERSION}.jar laa-ccms-caab-ebs-api.jar

EXPOSE 8080
RUN addgroup --system --gid 800 customgroup \
    && adduser --system --uid 800 --ingroup customgroup --shell /bin/sh customuser
RUN chown customuser:customgroup laa-ccms-caab-ebs-api.jar
USER 800

ENV TZ=Europe/London
ENV JAVA_TOOL_OPTIONS="-XX:InitialRAMPercentage=50.0 -XX:MaxRAMPercentage=80.0"
CMD java -jar laa-ccms-caab-ebs-api.jar
