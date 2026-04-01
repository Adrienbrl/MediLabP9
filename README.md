# MediLab P9

Base de travail du projet MediLabo Solutions.

## Architecture du repository

- `patient-service` : gestion des données personnelles du patient en base SQL.
- `notes-service` : gestion des notes du médecin en base NoSQL.
- `assessment-service` : calcul du risque de diabète à partir des données patient et des notes.
- `gateway-service` : point d'entrée unique avec Spring Cloud Gateway.
- `front-service` : interface utilisateur commune aux trois sprints.
- `docs/architecture.md` : synthèse du découpage et des choix de conception.

## Stack retenue

- Java 21 comme cible de compilation
- Maven en monorepo multi-modules
- Spring Boot pour les microservices
- Spring Security pour l'authentification
- Spring Cloud Gateway pour le routage
- SQL pour `patient-service`
- MongoDB pour `notes-service`

## Ordre de travail recommandé

1. Implémenter `patient-service`.
2. Mettre en place `gateway-service`.
3. Créer la première version de `front-service`.
4. Ajouter `notes-service`.
5. Ajouter `assessment-service`.

## Etat actuel

Le repository contient maintenant l'ossature des modules. Le prochain incrément utile est la mise en place du sprint 1 dans `patient-service`.
