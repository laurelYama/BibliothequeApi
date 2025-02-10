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


## 📖 Documentation API avec Swagger

L'API est documentée avec **Swagger**, ce qui permet de tester les endpoints directement depuis un navigateur.

### 🔗 Accéder à Swagger :
- **Localement** : [http://localhost:9090/swagger-ui.html](http://localhost:9090/swagger-ui.html)

### 🛠 Comment utiliser Swagger ?
1. **Démarrer le serveur Spring Boot**  
2. **Ouvrir un navigateur** et accéder à l'URL ci-dessus  
3. **Explorer les endpoints** :
   - Consulter la documentation
   - Tester les requêtes directement via l'interface  
   - Voir les schémas des requêtes et des réponses

---

📌 Collection Postman - Bibliothèque API
Dans mon repository GitHub, j'ai inclus une collection Postman permettant de tester facilement les différentes fonctionnalités de l'API Bibliothèque. Cette collection contient plusieurs requêtes organisées pour interagir avec l'application, notamment :

Authentification : Connexion (/api/auth/login), inscription (/api/auth/register).
Utilisateurs : Récupération de la liste des utilisateurs, création, modification et suppression.
Livres : Ajout, suppression, mise à jour et récupération des livres disponibles dans la bibliothèque.
Emprunts : Gestion des emprunts de livres par les utilisateurs, avec suivi des retours et des retards.
Administration : Routes réservées aux administrateurs pour gérer les utilisateurs, surveiller les emprunts et voir les livres en circulation.

## 🚀 Tester l'API avec Postman
1. **Télécharge la collection Postman** :  
   [📥 Télécharger Bibliotheque_API.postman_collection.json](./Bibliotheque_API.postman_collection.json)
2. **Ouvre Postman**  
3. **Importe la collection** :
   - Allez dans **Fichier → Importer**
   - Sélectionnez le fichier `.json`
4. **Configure l’environnement (facultatif)**
5. **Lance les requêtes et teste l'API ! 🚀**


## Auteur
- **Laurel YAMA** - [Votre Profil GitHub](https://github.com/laurelYama)

## Licence
Ce projet est sous licence MIT.

