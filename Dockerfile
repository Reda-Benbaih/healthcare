# Étape 1 : Compilation (Build) à l'intérieur de Docker
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# 1. Copier le fichier de configuration Maven pour mettre en cache les dépendances
COPY pom.xml .
RUN mvn dependency:go-offline -B

# 2. Copier le code source de l'application
COPY src ./src

# 3. Compiler et empaqueter l'application (génère le .jar dans l'environnement virtuel)
RUN mvn clean package -DskipTests

# Étape 2 : Image finale d'exécution (Runtime)
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copier le fichier .jar généré à l'étape précédente (build)
COPY --from=build /app/target/*.jar app.jar

# Exposer le port de l'application (8080 pour correspondre à votre docker-compose)
EXPOSE 8080

# Commande d'exécution
ENTRYPOINT ["java", "-jar", "app.jar"]