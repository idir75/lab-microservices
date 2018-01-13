Ce lab a pour objectif la création d'une application de microservices

Première application avec Spring Boot
* Initialiser l'application web avec Spring boot
* Création du service
* Ajout des tests unitaires avec Mockito
* Lancement de l'application avec la commande : mvnw spring-boot:run
* L'application se lance sur http://localhost:8080/index.html

Compléter l'implémentation du domaine
Compléter l'implémentation des tests unitaires
Integration de lombok
* Installation du plugin Lombok dans IntelliJ

Implémentation de la logique business
Implémentation de la couche Présentation (API REST)
* Implémentation des controllers en utilisant l'annotation @RestController
* Annotations @GetMapping pour les requêtes GET et @PostMapping pour les requêtes POST
* Annotation @RequestBody
* Classe ResponseEntity

Ajout du client web

Ajout de la couche de persistence de données
* Utilisation de Hibernate avec JPA pour la persistence des données
* Configuration de la base de données H2 : console 
* Compléter l'implémentation du modèle
* Implémentation du repository : classes permettant d'effectuer les opérations CRUD par entité