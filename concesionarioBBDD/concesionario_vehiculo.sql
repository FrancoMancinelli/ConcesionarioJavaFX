-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: concesionario
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `vehiculo`
--

DROP TABLE IF EXISTS `vehiculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehiculo` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_cliente` int DEFAULT NULL,
  `id_concesionario` int DEFAULT NULL,
  `matricula` varchar(15) DEFAULT '0',
  `modelo` varchar(50) DEFAULT NULL,
  `color` varchar(50) DEFAULT NULL,
  `precio` double DEFAULT NULL,
  `tipo` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_id_cliente` (`id_cliente`),
  KEY `fk_id_concesionario3` (`id_concesionario`),
  CONSTRAINT `fk_id_cliente` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_id_concesionario3` FOREIGN KEY (`id_concesionario`) REFERENCES `concesionario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehiculo`
--

LOCK TABLES `vehiculo` WRITE;
/*!40000 ALTER TABLE `vehiculo` DISABLE KEYS */;
INSERT INTO `vehiculo` VALUES (1,1,1,'4132 ABC','Seat Leon','Rojo',12000,'Coche'),(2,2,1,'8253 JKS','Opel Corsa','Blanco',9000,'Moto'),(3,3,1,'9204 LOP','Volkswagen Golf','Negro',11500,'Coche'),(4,4,1,'9201 DKS','Audi A3','Plata',17000,'Ciclomotor'),(5,5,1,'3792 IOE','Peugeot 308','Blanco',11000,'Coche'),(6,6,1,'8245 XHE','Renault Megane','Gris',12000,'Moto'),(7,7,1,'1268 MNR','Opel Astra','Rojo',11000,'Moto'),(8,8,1,'9239 HAW','Volkswagen Polo','Azul',10000,'Moto'),(9,NULL,1,'7821 JTY','Ford Focus','Azul',10500,'Coche'),(10,NULL,1,'2833 SDO','Renault Clio','Gris',9500,'Ciclomotor'),(11,NULL,1,'6193 AZU','Citroen C4','Rojo',10500,'Coche'),(12,NULL,1,'4881 MLP','Fiat Punto','Rojo',8000,'Coche'),(13,NULL,1,'0922 HZX','Seat Ibiza','Azul',9500,'Moto'),(14,NULL,1,'9402 MFF','Ford Fiesta','Negro',9500,'Coche'),(15,NULL,1,'6583 HDK','Citroen DS3','Blanco',10500,'Ciclomotor');
/*!40000 ALTER TABLE `vehiculo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-03-09  7:47:37
