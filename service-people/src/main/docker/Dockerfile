FROM openjdk:8-jdk-alpine
MAINTAINER wenthywang
VOLUME /urs/local/wwh/tmp
ADD @project.build.finalName@.jar @project.build.finalName@.jar
RUN sh -c 'touch /@project.build.finalName@.jar'
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=docker  -jar /@project.build.finalName@.jar" ]