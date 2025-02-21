# Bibliotheque API

## Description
Bibliotheque API est une application permettant de gérer l'emprunt et le retour de livres dans une bibliothèque. Elle permet aux utilisateurs de s'authentifier, d'emprunter des livres et de consulter leur historique.

## Prérequis
Avant d'exécuter le projet, assurez-vous d'avoir installé :

- Java 17
- Spring Boot
- MySQL
- Maven
- Postman (pour tester l'API)
- Docker et Docker Compose (pour l'exécution avec conteneurisation)

## Installation
### Installation manuelle

Clonez le projet :
```sh
git clone https://github.com/laurelYama/BibliothequeApi.git
cd bibliotheque-api
```

Configurez la base de données :

Créez une base de données MySQL :
```sql
CREATE DATABASE bibliotheque;
```

Configurez `application.properties` dans `src/main/resources` :
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/bibliotheque
spring.datasource.username=root
spring.datasource.password=motdepasse
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

Installez les dépendances et compilez le projet :
```sh
mvn clean install
```

### Installation avec Docker

Le projet est prévu pour être exécuté dans des conteneurs Docker. Assurez-vous que Docker et Docker Compose sont installés sur votre machine.

1. **Démarrez l'application avec Docker Compose** :
   ```sh
   docker-compose up -d --build
   ```

2. **Vérifiez que les services sont bien lancés** :
   ```sh
   docker ps
   ```

3. **Accédez à l'API** :
   L'API sera accessible à l'adresse : [http://localhost:9090](http://localhost:9090)

Si vous souhaitez arrêter les conteneurs :
```sh
docker-compose down
```

## Configuration de Docker

### Fichier `Dockerfile`
Le `Dockerfile` est utilisé pour créer l'image Docker de l'application Spring Boot.
```dockerfile
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
```

### Fichier `docker-compose.yml`
Le fichier `docker-compose.yml` définit les services nécessaires au bon fonctionnement de l'application.
```yaml
version: '3.8'

services:
  mysql:
    image: mysql:8.0
    container_name: bibliotheque-mysql
    restart: always
    environment:
      MYSQL_ROOT_PASSWORD: 123456789
      MYSQL_DATABASE: bibliotheque
    ports:
      - "3307:3306"
    volumes:
      - mysql_data:/var/lib/mysql
    networks:
      - bibliotheque-net

  api:
    build: .
    container_name: bibliotheque-api
    depends_on:
      - mysql
    ports:
      - "9090:9090"
    environment:
      - SPRING_DATASOURCE_URL=jdbc:mysql://bibliotheque-mysql:3306/bibliotheque
      - SPRING_DATASOURCE_USERNAME=root
      - SPRING_DATASOURCE_PASSWORD=123456789
    networks:
      - bibliotheque-net

volumes:
  mysql_data:

networks:
  bibliotheque-net:
```

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

## 🔑 Authentification et Rôle Administrateur
L'API nécessite une authentification basée sur JWT. Pour accéder aux fonctionnalités administratives, il faut être connecté en tant qu'ADMIN.

### 🛠 Initialisation d'un Administrateur par Défaut
Un administrateur par défaut est créé lors de l'initialisation si aucun n'existe.

📍 **Fichier concerné** : `src/main/java/com/esiitech/bibliotheque_api/config/DatabaseInitializer.java`

📌 **Détails du compte administrateur par défaut** :
- **Email** : `admin@bibliotheque.com`
- **Mot de passe** : `admin123`

## 📖 Documentation API avec Swagger
L'API est documentée avec Swagger, permettant de tester les endpoints directement depuis un navigateur.

🔗 **Accéder à Swagger** :
- Localement : [http://localhost:9090/swagger-ui.html](http://localhost:9090/swagger-ui.html)

### 🛠 Comment utiliser Swagger ?
1. Démarrer le serveur Spring Boot
2. Ouvrir un navigateur et accéder à l'URL ci-dessus
3. Explorer les endpoints

## 📌 Collection Postman - Bibliothèque API
Dans le repository GitHub, une collection Postman est incluse pour tester les différentes fonctionnalités de l'API.

🚀 **Tester l'API avec Postman**
1. **Téléchargez la collection Postman** :
   📥 [Bibliotheque_API.postman_collection.json](https://github.com/laurelYama/BibliothequeApi)
2. **Ouvrir Postman**
3. **Importer la collection** :
   - Allez dans **Fichier** → **Importer**
   - Sélectionnez le fichier `.json`
4. **Lancez les requêtes et testez l'API !** 🚀

## Auteur
**Laurel YAMA** - [Mon Profil GitHub](https://github.com/laurelYama)

## Licence
Ce projet est sous licence MIT.

