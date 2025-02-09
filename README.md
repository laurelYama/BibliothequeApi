# Bibliotheque API

## Description
Bibliotheque API est une application permettant de gérer l'emprunt et le retour de livres dans une bibliothèque. Elle permet aux utilisateurs de s'authentifier, d'emprunter des livres et de consulter leur historique.

## Prérequis
Avant d'exécuter le projet, assurez-vous d'avoir installé :

- [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [MySQL](https://dev.mysql.com/downloads/installer/)
- [Maven](https://maven.apache.org/install.html)
- [Postman](https://www.postman.com/) (pour tester l'API)

## Installation

1. Clonez le projet :
   ```sh
   git clone https://github.com/laurelYama/BibliothequeApi.git
   cd bibliotheque-api
   ```

2. Configurez la base de données :
   - Créez une base de données MySQL :
     ```sql
     CREATE DATABASE bibliotheque;
     ```
   - Configurez `application.properties` dans `src/main/resources` :
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/bibliotheque
     spring.datasource.username=root
     spring.datasource.password=motdepasse
     spring.jpa.hibernate.ddl-auto=update
     spring.jpa.show-sql=true
     ```

3. Installez les dépendances et compilez le projet :
   ```sh
   mvn clean install
   ```

## Exécution du projet

Démarrez l'API avec :
```sh
mvn spring-boot:run
```
L'API sera accessible à l'adresse : `http://localhost:9090`

## Routes principales
L'API utilise JWT pour l'authentification.
### Authentification
- `POST /api/auth/login` : Connexion avec email et mot de passe
- `POST /api/auth/register` : Inscription d'un utilisateur

### Gestion des livres
- `GET /api/livres` : Liste des livres disponibles
- `POST /api/livres` : Ajouter un livre

### Gestion des emprunts
- `POST /api/user/emprunts` : Emprunter un livre
- `PUT /api/user/emprunts/{id}/retourner` : Retourner un livre
- `GET /api/user/emprunts` : Voir les emprunts de l'utilisateur connecté

## Tests de l'API

Vous pouvez utiliser [Postman](https://www.postman.com/) ou [Swagger](http://localhost:9090/swagger-ui.html) pour tester les routes.

## Auteur
- **Laurel YAMA** - [Votre Profil GitHub](https://github.com/laurelYama)

## Licence
Ce projet est sous licence MIT.

