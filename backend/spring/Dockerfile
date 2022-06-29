# Stage 1 - Build war file
FROM openjdk:16 AS TEMP_BUILD_IMAGE
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY build.gradle settings.gradle gradlew $APP_HOME
RUN mkdir -p $APP_HOME/gradle
COPY gradle $APP_HOME/gradle/
RUN ./gradlew build || return 0 
COPY . .
RUN ./gradlew build

# Stage 2 - Run war file in Tomcat
FROM tomcat:jdk16-openjdk-slim
ENV JAVA_TOOL_OPTIONS="-XX:+UseSerialGC -Xms250M -Xmx250M -XX:+UseContainerSupport -Dspring.profiles.active=prod"
ENV GOOGLE_APPLICATION_CREDENTIALS=$APP_HOME/src/main/resources/firebase.json
ENV ARTIFACT_NAME=mefit-0.0.1-SNAPSHOT.war
ENV APP_HOME=/usr/app
WORKDIR $APP_HOME
COPY --from=TEMP_BUILD_IMAGE $APP_HOME/build/libs/$ARTIFACT_NAME .
RUN rm -rf /usr/local/tomcat/webapps/*
COPY $APP_HOME/${ARTIFACT_NAME} /usr/local/tomcat/webapps/ROOT.war
CMD ["catalina.sh", "run"]

