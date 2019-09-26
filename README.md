# Bibliothèque

Ce dépôt est associé au projet [Développez le nouveau système d'information de la bibliothèque d'une grande ville](https://openclassrooms.com/fr/projects/124/assignment) pour [OpenClassRooms](https://openclassrooms.com).

## Technologies

- Java JDK 8
- Spring Boot 2.0.5
- PostgreSQL 10.5
- Docker
- Docker-compose
- Spring boot starter data jpa
- Swagger
- Spring boot starter security
- Spring boot starter thymeleaf
- Spring boot starter web
- Bulma 0.7.4
- mailjet-client 4.2.0

### Services

- `bibliotheque-webapp` : application web destinée aux clients
- `bibliotheque-batch` : application de relance de mails
- `bibliotheque-api` : api de la bibliothèque

### Schémas

#### Diagramme de classes

![](http://image.noelshack.com/fichiers/2019/39/4/1569508271-diagclasse.png)

#### Model physique de données

![](http://image.noelshack.com/fichiers/2019/39/4/1569508272-mpd.png)


### Déploiement
#### Base de donnée
Un jeu de données est fourni avec le projet, il se trouve dans le dossier assets sous le nom de fichier `bibliotheque-dump`.

Pour le jeu de données deux comptes sont déjà créés :
- Un utilisateur classique (un client) : `utilisateur@gmail.com` - `azerty123`
- Un bibliothécaire : `bibliothecaire@gmail.com` - `azerty123`

#### Variables d'environnement
Ce projet a été réalisé avec Intellij IDEA Ultimate.

Plusieurs variables d'environnement seront nécessaires à l'excécution du projet.

Pour le service web bibliotheque-webapp :
- `API_URL` url du service bibliotheque-api (valeur d'exemple pendant les premières versions de développement http://localhost:9090/fab24/bibliotheque-livres/1.0.0/)

Pour le service bibliotheque-batch :
- `MJ_APIKEY_PUBLIC` ayant la valeur de votre clé publique de l'API de Mailjet
- `MJ_APIKEY_PRIVATE` ayant la valeur de votre clé privée de l'API de Mailjet
- `MJ_NAME_FROM` correspond au nom d'envoi des mails de relance
- `MJ_MAIL_FROM` correspond au mail depuis lequel seront envoyés les mails de relance (il doit être relié à votre compte Mailjet pour fonctionner)
- `API_URL` url du service bibliotheque-api (valeur d'exemple pendant les premières versions de développement http://localhost:9090/fab24/bibliotheque-livres/1.0.0/)

Pour le service bibliotheque-api :
- `JDBC_DATABASE` contenant le nom de la base de données à laquelle le programme doit être connecté
- `JDBC_USERNAME` contenant le nom d'utilisateur pour se connecter à la base de donnée
- `JDBC_PASSWORD` contenant le mot de passe pour se connecter à la base de donnée

Pour générer la base de données :
- Si le programme est bien connecté à une base de donnée PostgreSQL, une fois celui-ci exécuté, il créera lui-même la structure de la base de donnée avec Spring Data JPA
- Pour utiliser le jeu de données il faut utiliser le fichier bibliotheque-dump dans le dossier assets

#### Exécution du projet

Pour l'exécution du projet vous aurez besoin de Docker et de Docker-compose. Une fois installés il vous suffira d'ouvrir un terminal dans le dossier contenant le fichier `docker-compose.yml` (présent par défaut à la racine du projet) et d'entrer la commande `docker-compose up` dans votre terminal.
Il vous est déconseillé de laisser les variables par défaut dans le fichier `docker-compose.yml`, mettez vos proprez variables.