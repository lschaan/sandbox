
SET NAMES utf8mb4 ;

DROP TABLE IF EXISTS `songs`;

SET character_set_client = utf8mb4 ;
CREATE TABLE `songs` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`)
) ;


LOCK TABLES `songs` WRITE;

INSERT INTO `songs` VALUES (1,'Old Town Road'),(2, 'Bad Guy'),(3,'Wow'),(4,'Beat The Devils Tattoo'),(5,'Hysteria'),(6,'The Poison'),(7,'Miss Nothing'),(8,'Last Resort'),(9,'Symphony Of Destruction'),(10,'Guardians Of Asgaard'),(11,'Work'),(12,'Telephone'),(13,'Dollhouse'),(14,'Bury a Friend'),(15,'Rude Boy');

UNLOCK TABLES;
