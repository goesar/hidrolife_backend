# Usar imagen oficial de Maven con Java 17
FROM maven:3.9.6-eclipse-temurin-17

WORKDIR /app

# Copiar todo el proyecto
COPY . .

# Construir el jar
RUN mvn clean package -DskipTests

# Ejecutar la aplicación
CMD ["java", "-jar", "target/*.jar"]

