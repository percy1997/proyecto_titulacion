# =========================
# ETAPA 1: BUILD
# =========================
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

# Copiamos pom y descargamos dependencias
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiamos el resto del proyecto
COPY src ./src

# Construimos el jar
RUN mvn clean package -DskipTests


# =========================
# ETAPA 2: RUNTIME
# =========================
FROM eclipse-temurin:17-jdk

WORKDIR /app

# Copiamos el jar desde la etapa build
COPY --from=build /app/target/*.jar app.jar

# Render usa el puerto dinámico
EXPOSE 8080

# Arranque de la app
ENTRYPOINT ["java", "-jar", "app.jar"]
