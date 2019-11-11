## Procédure de tests

Nous avions l'intention d'implémenté 4 types de tests.

### JUnit

Nous avons utilisé JUnit pour tester les models. Ce sont des tests simples pour vérifier le bon fonctionnement des classes.

### Archillian

Nous avons utilisé Archillian pour tester les DAO.

Pour faire fonctionner Archillian, nous utilisons une topologie différente. Il faut aller dans ```\topology\dockerAequilian\topologies``` et faire un ``docker-compose up --build``pour lancer les containers permettant le fonctionnement d'Archilian. Puis clic droit sur le dossier test dans Intelliji pour lancer les tests Archillian.

Archillian n'est pas agréable à utiliser. Il est lent à effectuer les tests et nécessite une configuration particulière pour son fonctionnement.

### Mockito

Nous avions pensé utilisé Mockito pour les tests sur les parties business et presentation.

### JMeter

Nous comptions utiliser JMeter pour tester la pagination et les tests de charges.