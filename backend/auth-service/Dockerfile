FROM amazoncorretto:17

# Создаем рабочую директорию внутри контейнера
WORKDIR /app

# Копируем скомпилированный jar-файл в контейнер
# Убедись, что jar-файл называется правильно (может отличаться версией)
COPY build/libs/auth-service-0.0.1-SNAPSHOT.jar auth-service.jar

# Пробрасываем порт 8080 (или тот, что указан в application.properties)
EXPOSE 8082

# Запускаем приложение
ENTRYPOINT ["java", "-jar", "auth-service.jar"]
