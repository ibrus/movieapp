FROM amazoncorretto:17

# Создаем рабочую директорию внутри контейнера
WORKDIR /app

# Копируем скомпилированный jar-файл в контейнер
# Убедись, что jar-файл называется правильно (может отличаться версией)
COPY build/libs/notification-service-0.0.1-SNAPSHOT.jar notification-service.jar

# Пробрасываем порт 8080 (или тот, что указан в application.properties)
EXPOSE 8085

# Запускаем приложение
ENTRYPOINT ["java", "-jar", "notification-service.jar"]
