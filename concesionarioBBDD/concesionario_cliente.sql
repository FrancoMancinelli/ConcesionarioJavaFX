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
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `id` int NOT NULL AUTO_INCREMENT,
  `dni` varchar(10) NOT NULL,
  `id_concesionario` int DEFAULT NULL,
  `nombre` varchar(30) NOT NULL,
  `apellido` varchar(50) DEFAULT NULL,
  `direccion` varchar(50) DEFAULT NULL,
  `telefono` int NOT NULL,
  `correo` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_id_concesionario2` (`id_concesionario`),
  CONSTRAINT `fk_id_concesionario2` FOREIGN KEY (`id_concesionario`) REFERENCES `concesionario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'33333333C',1,'Antonio','Martínez','Calle Granada 3',600111222,'antonio.martinez@gmail.com'),(2,'44444444C',1,'María','Pérez','Calle Málaga 8',600222333,'maria.perez@gmail.com'),(3,'55555555C',1,'Juan','López','Calle Córdoba 15',600333444,'juan.lopez@gmail.com'),(4,'66666666C',1,'Ana','González','Calle Sevilla 20',600444555,'ana.gonzalez@gmail.com'),(5,'77777777C',1,'Pedro','Sánchez','Calle Alameda 22',600555666,'pedro.sanchez@gmail.com'),(6,'88888888C',1,'Carmen','Fernández','Calle Reyes Católicos 30',600666777,'carmen.fernandez@gmail.com'),(7,'99999999C',1,'Javier','Gómez','Calle Puerto 40',600777888,'javier.gomez@gmail.com'),(8,'11111111C',1,'Sara','Ruiz','Calle Alcazabilla 50',600888999,'sara.ruiz@gmail.com'),(9,'22222222C',1,'David','Torres','Calle Carretería 60',600999111,'david.torres@gmail.com'),(10,'33333333C',1,'Cristina','Márquez','Calle Martínez Maldonado 70',600111222,'cristina.marquez@gmail.com'),(11,'10101010C',1,'Carlos','Rodríguez','Calle Larios 12, Málaga',696123456,'carlos.rodriguez@gmail.com'),(12,'11111111A',NULL,'ana','carmem','dgds',444444444,'adadn');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-03-09 10:42:50
