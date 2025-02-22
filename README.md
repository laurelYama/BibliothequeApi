# Bibliotheque API

## 📖 Description
**Bibliotheque API** est une application permettant de gérer l'emprunt et le retour de livres dans une bibliothèque. Elle permet aux utilisateurs de :
- S'authentifier via JWT
- Emprunter et retourner des livres
- Consulter leur historique d'emprunts

---

## ✅ Prérequis
Avant d'exécuter le projet, assurez-vous d'avoir installé :
- **Java 17**
- **Spring Boot**
- **MySQL**
- **Maven**
- **Postman** (pour tester l'API)
- **Docker et Docker Compose** (pour l'exécution avec conteneurisation)

---

## 🚀 Installation

### 🔹 Installation manuelle
1️⃣ **Clonez le projet** :
```bash
git clone https://github.com/laurelYama/BibliothequeApi.git
cd bibliotheque-api
```

2️⃣ **Configurez la base de données** :
- **Créez une base de données MySQL** :
```sql
CREATE DATABASE bibliotheque;
```
- **Modifiez `application.properties`** dans `src/main/resources` :
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/bibliotheque
spring.datasource.username=root
spring.datasource.password=motdepasse
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

3️⃣ **Installez les dépendances et compilez le projet** :
```bash
mvn clean install
```

### 🔹 Installation avec Docker
Le projet peut être exécuté dans des conteneurs Docker.

1️⃣ **Démarrez l'application avec Docker Compose** :
```bash
docker-compose up -d --build
```

2️⃣ **Vérifiez que les services sont bien lancés** :
```bash
docker ps
```

3️⃣ **Accédez à l'API** :
L'API sera accessible à l'adresse : [http://localhost:9090](http://localhost:9090)

📌 **Pour arrêter les conteneurs** :
```bash
docker-compose down
```

---

## ⚙️ Configuration de Docker

### 📌 **Fichier `Dockerfile`**
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

### 📌 **Fichier `docker-compose.yml`**
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

---

## 🔗 Routes principales
L'API utilise **JWT** pour l'authentification.

### 🔑 **Authentification**
- `POST /api/auth/login` → Connexion avec email et mot de passe
- `POST /api/auth/register` → Inscription d'un utilisateur

### 📚 **Gestion des livres**
- `GET /api/livres` → Liste des livres disponibles
- `POST /api/livres` → Ajouter un livre (ADMIN)

### 📖 **Gestion des emprunts**
- `POST /api/user/emprunts` → Emprunter un livre
- `PUT /api/user/emprunts/{id}/retourner` → Retourner un livre
- `GET /api/user/emprunts` → Voir les emprunts de l'utilisateur connecté

---

## 🔐 Authentification et Rôle Administrateur
L'API nécessite une authentification basée sur JWT.

📌 **Pour accéder aux fonctionnalités administratives, il faut être connecté en tant qu'ADMIN.**

### 🛠 **Initialisation d'un Administrateur par Défaut**
Un administrateur par défaut est créé lors de l'initialisation si aucun n'existe.

📍 **Fichier concerné** : `src/main/java/com/esiitech/bibliotheque_api/config/DatabaseInitializer.java`

📌 **Détails du compte administrateur par défaut** :
- **Email** : `admin@bibliotheque.com`
- **Mot de passe** : `admin123`

---

## 📖 Documentation API avec Swagger
L'API est documentée avec **Swagger**, permettant de tester les endpoints directement depuis un navigateur.

🔗 **Accéder à Swagger** :
- **Localement** : [http://localhost:9090/swagger-ui.html](http://localhost:9090/swagger-ui.html)

📌 **Comment utiliser Swagger ?**
1️⃣ **Démarrer le serveur Spring Boot**
2️⃣ **Ouvrir un navigateur et accéder à l'URL Swagger**
3️⃣ **Explorer et tester les endpoints**

---

## 📌 Collection Postman - Bibliothèque API
Une **collection Postman** est incluse pour tester l'API.

🚀 **Tester l'API avec Postman** :
1️⃣ **Téléchargez la collection Postman** : 📥 `Bibliotheque_API.postman_collection.json`
2️⃣ **Ouvrir Postman**
3️⃣ **Importer la collection** :
   - Allez dans **Fichier → Importer**
   - Sélectionnez le fichier `.json`
4️⃣ **Lancez les requêtes et testez l'API !** 🚀

---

## 👨‍💻 Auteur
**Laurel YAMA** - [Mon Profil GitHub](https://github.com/laurelYama)

## 📜 Licence
Ce projet est sous licence **MIT**.

---

