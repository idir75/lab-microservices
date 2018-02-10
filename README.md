Ce lab a pour objectif la création d'une application de microservices

Nous allons commencer par la réalisation d'une application web classique (monolithique)
Première application avec Spring Boot
* Initialiser l'application web avec Spring boot
* Création du service
* Ajout des tests unitaires avec le framework Mockito
* Lancement de l'application avec la commande : `mvnw spring-boot:run`
* Aller sur : `http://localhost:8080/index.html`

Implémentation du domaine
Implémentation des tests unitaires
Integration de Lombok
* Installation du plugin Lombok dans IntelliJ

Implémentation de la logique business
Implémentation de la couche Présentation (API REST)
* Implémentation des controllers en utilisant l'annotation `@RestController`
* Annotations `@GetMapping` pour les requêtes GET et `@PostMapping` pour les requêtes POST
* Annotation `@RequestBody`
* Classe `ResponseEntity`

Ajout du client web

Ajout de la couche de persistence de données
* Utilisation de Hibernate avec JPA pour la persistence des données
* Configuration de la base de données H2 : console `http://localhost:8080/h2-console/`
* Amélioration du modèle
* Implémentation du repository : opérations CRUD par entité

Maintenant, nous allons implémenter les microservices de l'application

Configuration de RabbitMQ
* Modification du fichier pom.xml pour déclarer la dépendance au projet RabbitMQ. Pour cela :
```
<dependency>
 <groupId>org.springframework.boot</groupId>
 <artifactId>spring-boot-starter-amqp</artifactId>
</dependency>
```
* Ajout de la classe de configuration `RabbitMQConfiguration` avec l'annotation `@configuration`. Celle-ci est utilisée par Spring pour générer les beans
à l'initialisaton du contexte de l'application.

Implémentation du microservice Multiplication
* `MultiplicationSolvedEvent` pour implémenter l'event de résolution d'une multiplication
* `EventDispatcher` pour envoyer les events `MultiplicationSolvedEvent`
* Modification du service `MultiplicationServiceImpl` pour envoyer l'event `MultiplicationSolvedEvent` avec le `EventDispatcher`
```
    eventDispatcher.send(new MultiplicationSolvedEvent(
                    checkedAttempt.getId(),
                    checkedAttempt.getUser().getId(),
                    checkedAttempt.isCorrect()));
```
* Mise à jour des tests unitaires

Implémentation du microservice Gamification
* Création du projet Gamification avec Spring Initializr
* Configuration des dépendances pour Lombock, H2, AMQP (comme cela a déjà été fait pour le projet précédent)
* Optionnel : ajout du projet en tant que projet maven dans IntelliJ
* Implémentation du domaine, du modèle de données en utilisant JPQL ainsi que la logique métier
* Implémentation de la réception des events avec RabbitMQ

Installation de RabbitMQ
Test des events avec RabbitMQ

Installation et configuration du serveur Jetty

Autorisation du CORS (Cross-Origin Resource Sharing)

Isolation de la couche UI dans une application séparée
* Utilisation de Bootstrap

Lancement des applications
* Démarrer le broker RabbitMQ
* Démarrer le microservice de multiplication
* Démarrer le microservice de gamification
* Lancer l'application UI avec Jetty
```
    java -jar [JETTY_HOME_FOLDER]/start.jar
```

TODO
Utiliser les WebSockets pour les notifications serveur