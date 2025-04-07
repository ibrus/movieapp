FROM amazoncorretto:17

# Создаем рабочую директорию внутри контейнера
WORKDIR /app

# Копируем скомпилированный jar-файл в контейнер
# Убедись, что jar-файл называется правильно (может отличаться версией)
COPY build/libs/movie-service-0.0.1-SNAPSHOT.jar movie-service.jar

# Пробрасываем порт 8080 (или тот, что указан в application.properties)
EXPOSE 8083

# Запускаем приложение
ENTRYPOINT ["java", "-jar", "movie-service.jar"]
