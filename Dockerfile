# Imagen base
FROM openjdk:17-jdk-slim

#Directorio de trabajo
WORKDIR / app

#Copiar archivo JAR
COPY build/libs/*.jar app.jar

#Exponer el puerto de la aplicación
EXPOSE  8080

#Inicio
ENTRYPOINT ["java", "-jar", "app.jar"]
