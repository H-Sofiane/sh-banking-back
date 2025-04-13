FROM maven:3.8.6-openjdk-17-slim AS build
COPY . .
RUN mvn clean package -DskipTests



# Utiliser une image officielle de Java
FROM openjdk:17-jdk-slim

# Définir le répertoire de travail dans le conteneur
WORKDIR /app

# Copier le fichier JAR généré dans le conteneur
COPY target/shbanking-backend-0.0.1-SNAPSHOT.jar app.jar

# Exposer le port utilisé par l'application
EXPOSE 8085

# Commande pour exécuter l'application
ENTRYPOINT ["java", "-jar", "app.jar"]
