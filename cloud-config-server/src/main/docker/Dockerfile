FROM openjdk:8-jdk-alpine
MAINTAINER wenthywang
ADD @project.build.finalName@.jar @project.build.finalName@.jar
ENV JAVA_OPTS=""
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime &&\
    echo $TZ > /etc/timezone
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=docker  -jar /@project.build.finalName@.jar" ]