-- MySQL dump 10.13  Distrib 8.0.32, for Linux (x86_64)
--
-- Host: localhost    Database: db_name
-- ------------------------------------------------------
-- Server version	8.0.32-0ubuntu0.20.04.2

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Affitti`
--

DROP TABLE IF EXISTS `Affitti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Affitti` (
  `IdAffitto` int NOT NULL AUTO_INCREMENT,
  `data` date DEFAULT NULL,
  `Importo` int DEFAULT NULL,
  `codappartamento` int DEFAULT NULL,
  PRIMARY KEY (`IdAffitto`),
  KEY `codappartamento` (`codappartamento`),
  CONSTRAINT `Affitti_ibfk_1` FOREIGN KEY (`codappartamento`) REFERENCES `Appartamenti` (`CodAppartamenti`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Affitti`
--

LOCK TABLES `Affitti` WRITE;
/*!40000 ALTER TABLE `Affitti` DISABLE KEYS */;
INSERT INTO `Affitti` VALUES (1,'2015-03-25',500,6),(2,'2019-03-25',333,4),(3,'2020-03-25',164,4),(4,'2010-03-25',164,2);
/*!40000 ALTER TABLE `Affitti` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Appartamenti`
--

DROP TABLE IF EXISTS `Appartamenti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Appartamenti` (
  `CodAppartamenti` int NOT NULL AUTO_INCREMENT,
  `superficie` int DEFAULT NULL,
  `vani` int DEFAULT NULL,
  `citta` char(20) DEFAULT NULL,
  `nome_inquilino` char(20) DEFAULT NULL,
  `codproprietario` int DEFAULT NULL,
  PRIMARY KEY (`CodAppartamenti`),
  KEY `codproprietario` (`codproprietario`),
  CONSTRAINT `Appartamenti_ibfk_1` FOREIGN KEY (`codproprietario`) REFERENCES `Proprietario` (`CodProprietario`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Appartamenti`
--

LOCK TABLES `Appartamenti` WRITE;
/*!40000 ALTER TABLE `Appartamenti` DISABLE KEYS */;
INSERT INTO `Appartamenti` VALUES (1,95,2,'Lecco','Ghezzi',1),(2,105,2,'Lecco','Ghezzi',1),(4,105,2,'Como','Ghezzi',4),(5,300,2,'Lecco','Ghezzi',2),(6,NULL,NULL,'Milano','Giulia è gay',5);
/*!40000 ALTER TABLE `Appartamenti` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Proprietario`
--

DROP TABLE IF EXISTS `Proprietario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Proprietario` (
  `CodProprietario` int NOT NULL AUTO_INCREMENT,
  `nome` char(20) DEFAULT NULL,
  `cognome` char(20) DEFAULT NULL,
  `citta` char(20) DEFAULT NULL,
  `telefono` int DEFAULT NULL,
  PRIMARY KEY (`CodProprietario`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Proprietario`
--

LOCK TABLES `Proprietario` WRITE;
/*!40000 ALTER TABLE `Proprietario` DISABLE KEYS */;
INSERT INTO `Proprietario` VALUES (1,'Samuele','Patisso','Lecco',346),(2,'Samuel','Pitasso','Lecco',347),(4,'Giulia','Muntoni','Como',348),(5,'milanese','imbruttito','Milano',120);
/*!40000 ALTER TABLE `Proprietario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Spese`
--

DROP TABLE IF EXISTS `Spese`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Spese` (
  `IdSpesa` int NOT NULL AUTO_INCREMENT,
  `Data` date DEFAULT NULL,
  `importo` int DEFAULT NULL,
  `codproprietario` int DEFAULT NULL,
  `codappartamento` int DEFAULT NULL,
  PRIMARY KEY (`IdSpesa`),
  KEY `codproprietario` (`codproprietario`),
  KEY `codappartamento` (`codappartamento`),
  CONSTRAINT `Spese_ibfk_1` FOREIGN KEY (`codproprietario`) REFERENCES `Proprietario` (`CodProprietario`) ON DELETE CASCADE,
  CONSTRAINT `Spese_ibfk_2` FOREIGN KEY (`codappartamento`) REFERENCES `Appartamenti` (`CodAppartamenti`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Spese`
--

LOCK TABLES `Spese` WRITE;
/*!40000 ALTER TABLE `Spese` DISABLE KEYS */;
INSERT INTO `Spese` VALUES (1,'2008-02-24',100,1,2),(2,'2022-05-12',150,4,4),(3,'2012-05-12',450,2,5),(4,'2022-05-05',100,2,5);
/*!40000 ALTER TABLE `Spese` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-23 16:10:50
