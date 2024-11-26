# Imagen base de OpenJDK
FROM openjdk:21-jdk-slim

# Crear directorio de trabajo y copiar el JAR
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# Exponer el puerto donde Spring Boot ejecutará la API
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app.jar"]