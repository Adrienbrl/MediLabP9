# Architecture cible

## Modules

- `patient-service` : microservice SQL pour le dossier patient.
- `notes-service` : microservice NoSQL pour les notes du médecin.
- `assessment-service` : microservice sans base dédiée, chargé du calcul de risque.
- `gateway-service` : point d'entrée unique de l'application.
- `front-service` : interface utilisateur sobre et évolutive sur les trois sprints.

## Répartition par sprint

### Sprint 1

- poser l'architecture microservices ;
- développer `patient-service` ;
- exposer les routes via `gateway-service` ;
- créer la première interface dans `front-service`.

### Sprint 2

- développer `notes-service` avec MongoDB ;
- intégrer l'affichage et l'ajout des notes dans la page patient ;
- faire évoluer `gateway-service` et `front-service`.

### Sprint 3

- développer `assessment-service` ;
- interroger `patient-service` et `notes-service` ;
- afficher le niveau de risque sur la page patient.

## Choix structurants

- SQL pour les données patient : structure stable, normalisation 3NF, intégrité forte.
- NoSQL pour les notes : texte libre, volumétrie variable, conservation du format d'origine.
- Gateway dédié : unifie l'accès, simplifie la sécurité et évite d'exposer chaque service séparément.
- Front séparé : permet de faire évoluer l'interface sans mélanger la logique métier avec les APIs.

## Prochaine étape recommandée

La prochaine brique à implémenter est `patient-service`, car elle constitue la base des sprints 2 et 3.
