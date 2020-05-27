FROM openjdk:8-jdk-alpine
VOLUME /tmp
WORKDIR /app
ADD target/seckill-1.0.0.jar /app/seckill-1.0.0.jar
CMD ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/seckill-1.0.0.jar"]
