# Utilisation de l'image officielle OpenJDK
FROM openjdk:17-jdk-slim

# Définition du répertoire de travail
WORKDIR /app

# Copier le fichier JAR de l'application
COPY target/bibliotheque-api-0.0.1-SNAPSHOT.jar app.jar

# Exposer le port de l'application
EXPOSE 9090

# Commande pour exécuter l'application
ENTRYPOINT ["java", "-jar", "app.jar"]
