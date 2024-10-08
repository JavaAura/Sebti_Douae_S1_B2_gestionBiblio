# Système de Gestion de Bibliothèque Municipale
Voici un modèle de fichier `README.md` adapté aux exigences que vous avez spécifiées :


# Library Management System

## Description du projet

Le système de gestion de bibliothèque est une application Java conçue pour gérer les opérations d'une bibliothèque. Il prend en charge la gestion des documents (livres, magazines, thèses, journaux scientifiques), la gestion des comptes utilisateurs (étudiants et professeurs), ainsi que la gestion des emprunts et réservations.

## Objectif général de l'application

L'objectif principal de cette application est de fournir une solution complète pour la gestion des documents d'une bibliothèque, la gestion des utilisateurs, et le suivi des emprunts et réservations de documents. L'application facilite également la gestion des droits d'emprunt en fonction du type d'utilisateur.

## Technologies utilisées

- **Langage**: Java 8
- **Base de données**: PostgreSQL
- **JDBC Driver**: PostgreSQL JDBC Driver
- **API Java**: Stream API, Optional
- **Design Patterns**: DAO, Singleton
- **ORM**: Table Inheritance pour la base de données

## Structure du projet

- **src**
  - **utilitaire**
    - `DateUtils.java`: Méthodes utilitaires pour la gestion des dates.
    - `InputValidator.java`: Méthodes de validation des entrées utilisateur.
    - `BorrowReserveValidator.java`: Méthodes de gestion des droits d'emprunt selon le type de l'utilisateur.
    - 
  - **presentation**
    - `ConsoleUI.java`: Interface utilisateur en ligne de commande pour interagir avec le système.
    - `EmpruntUI.java`: Interface pour gérer les emprunts et retours de documents.
    - `ReservationUI.java`: Interface pour gérer les reservations et l'annulation.
    - `DocumentUI.java`: Interface pour la gestion des documents.
    - `UserUI.java`: Interface pour la gestion des utilisateurs.
    
  - **DB**
    - `DatabaseConnection.java`: Classe Singleton pour la gestion des connexions à la base de données.
  - **Dao**
    - `UtilisateurDao.java`: Interface pour les opérations DAO sur les utilisateurs.
    - `DocumentDao.java`: Interface pour les opérations DAO sur les documents.
    - `EmpruntDao.java`: Interface pour les opérations DAO sur les emprunts.
    - `ReservationDao.java`: Interface pour les opérations DAO sur les reservations.
    - 
  - **DaoImpl**
    - **documents**
      - `LivreDaoImpl.java`
      - `MagazineDaoImpl.java`
      - `TheseUniversitaireDaoImpl.java`
      - `JournalScientifiqueDaoImpl.java`
    - **users**
      - `EtudiantDaoImpl.java`
      - `ProfesseurDaoImpl.java`
    - `EmpruntDaoImpl.java`
    - `ReservationDaoImpl.java`
    
  - **entities**
    - **documents**
      - `Document.java`
      - `Livre.java`
      - `Magazine.java`
      - `TheseUniversitaire.java`
      - `JournalScientifique.java`
    - **users**
      - `Utilisateur.java`
      - `Etudiant.java`
      - `Professeur.java`
    - `Emprunt.java`
    - `Reservation.java`
      
  - **services**
      - `BibliothequeService.java`
      - `DocumentService.java`
      - `UtilisateurService.java`
      - `EmpruntService.java`
      - `ReservationService.java`
  - **interfaces**
    - `Empruntable.java`
    - `Reservable.java`

## Description brève de l'architecture adoptée

L'architecture de l'application est basée sur le modèle DAO (Data Access Object) pour séparer la logique de persistance des données de la logique métier. 
La base de données est gérée à l'aide de PostgreSQL avec un design de tables en héritage pour représenter les différents types de documents et d'utilisateurs.
Les couches Dao, service, presentation et entities sont utilisées pour séparer les préoccupations entre la logique métier, l'interface utilisateur et l'accès aux données.

## Instructions d'installation et d'utilisation

### Prérequis

- **Java 8**: Assurez-vous que Java 8 est installé et configuré sur votre système.
- **PostgreSQL**: Installez PostgreSQL et configurez une base de données pour le projet.
- **JDBC Driver**: Téléchargez et ajoutez le JDBC driver pour PostgreSQL à votre projet.

### Étapes pour configurer la base de données

1. **Clonez le dépôt**:
   ```bash
   git clone https://github.com/Douaesb/BibliothequeV2.git
   cd BibliothequeV2
   ```

2. **Configurez la base de données**:
    - Créez une base de données PostgreSQL.
    - Importez le schéma de la base de données fourni dans le fichier `dbBiblioV2.sql`.

3. **Configurez les paramètres de connexion**:
    - Modifiez les paramètres de connexion dans `DatabaseConnection.java` pour correspondre à votre configuration de base de données.

### Comment lancer l'application

1. **Compilez le projet**:
   ```bash
   javac -d bin src/**/*.java
   ```

2. **Exécutez l'application**:
   ```bash
   java -cp out presentation.ConsoleUI
   ```
   
### Exécution de l'application
Vous pouvez exécuter l'application directement en utilisant Java Jar :

java -jar out/production/Bibliotheque/bibliotheque.jar

## Améliorations futures possibles

- **Interface utilisateur améliorée**: Développer une interface graphique pour une meilleure expérience utilisateur.
- **Gestion des utilisateurs**: Ajouter des fonctionnalités pour la gestion des rôles et permissions plus avancées.
- **Optimisation des performances**: Améliorer les performances de l'application et des requêtes SQL.

## Idées pour étendre ou améliorer le projet

- **Intégration d'une API Web**: Créer une API RESTful pour permettre l'accès à l'application depuis des clients web ou mobiles.
- **Fonctionnalités de recherche avancée**: Ajouter des fonctionnalités de recherche et de filtrage plus complexes pour les documents et les utilisateurs.
- **Notifications**: Implémenter un système de notifications pour informer les utilisateurs des emprunts et réservations.

## Auteur et contact

**Auteur**: [Douae Sebti]

**Contact**: [douaesebti33@gmail.com]

### Conception du projet (Diagramme UML)

- Vous trouverez un fichier format pdf nommer diagramsBiblioV2.pdf

- **Diagramme des cas d'utilsations** Vous pouvez le consulter pour une meilleure compréhension du projet.
- **Diagramme des classes** Vous pouvez mieux comprendre l'architecture de ce projet.

### Lien du JIRA pour poursuivre les etapes de réalisation et gestion du projet

https://douaesb411.atlassian.net/jira/software/projects/BB/boards/5/backlog?atlOrigin=eyJpIjoiMTA1MTBjOTI4NDU0NDA2OWI1NjYwOTE3NTQxMjJlNjEiLCJwIjoiaiJ9

