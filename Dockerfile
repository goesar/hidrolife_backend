# Imagen base con Maven y Java 17
FROM maven:3.9.6-eclipse-temurin-17

WORKDIR /app

# Copiar todo el proyecto
COPY . .

# Construir el jar
RUN mvn clean package -DskipTests

# Render asigna el puerto por variable PORT
ENV PORT=8080

EXPOSE 8080

# Ejecutar la aplicación usando el puerto dinámico
CMD ["sh", "-c", "java -jar target/*.jar --server.port=$PORT"]
