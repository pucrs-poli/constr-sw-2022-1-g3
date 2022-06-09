FROM maven:3-jdk-11

ENV APP_NAME reservas

RUN mkdir /app

WORKDIR /app

COPY ./target/${APP_NAME}.jar /app/${APP_NAME}.jar

CMD java -jar ${APP_NAME}.jar

EXPOSE 8001