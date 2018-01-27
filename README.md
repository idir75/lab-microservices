Ce lab a pour objectif la création d'une application de microservices

Nous allons commencer par la réaltion d'une application web classique (monolithique)
Première application avec Spring Boot
* Initialiser l'application web avec Spring boot
* Création du service
* Ajout des tests unitaires avec le framework Mockito
* Lancement de l'application avec la commande : mvnw spring-boot:run
* Pour visualiser le résultat : http://localhost:8080/index.html

Implémentation du domaine
Implémentation des tests unitaires
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
* Configuration de la base de données H2 : console http://localhost:8080/h2-console/
* Améliorations du modèle
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
* Ajout de la classe de configuration RabbitMQConfiguration avec l'annotation @configuration. Celle-ci est utilisée par Spring pour générer les beans
à l'initialisaton du contexte de l'application.

Création du microservice de multiplication
* MultiplicationSolvedEvent pour implémenter l'event de résolution d'une multiplication
* EventDispatcher pour envoyer les events MultiplicationSolvedEvent
* Modification du service MultiplicationServiceImpl pour envoyer l'event MultiplicationSolvedEvent avec le EventDispatcher :
    eventDispatcher.send(new MultiplicationSolvedEvent(
                    checkedAttempt.getId(),
                    checkedAttempt.getUser().getId(),
                    checkedAttempt.isCorrect()));
* Mise à jour des tests unitaires
