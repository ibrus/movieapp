FROM amazoncorretto:17

# Создаем рабочую директорию внутри контейнера
WORKDIR /app

# Копируем скомпилированный jar-файл в контейнер
# Убедись, что jar-файл называется правильно (может отличаться версией)
COPY build/libs/user-service-0.0.1-SNAPSHOT.jar user-service.jar

# Пробрасываем порт 8080 (или тот, что указан в application.properties)
EXPOSE 8081

# Запускаем приложение
ENTRYPOINT ["java", "-jar", "user-service.jar"]
