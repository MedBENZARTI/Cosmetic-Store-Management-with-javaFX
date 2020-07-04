-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: localhost    Database: shop
-- ------------------------------------------------------
-- Server version	8.0.20

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
-- Table structure for table `sale`
--

DROP TABLE IF EXISTS `sale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sale` (
  `ClientID` varchar(100) DEFAULT NULL,
  `SaleID` varchar(100) NOT NULL,
  `employeeID` varchar(100) DEFAULT NULL,
  `ProductID` varchar(100) DEFAULT NULL,
  `SaleDate` datetime DEFAULT NULL,
  `Qte` int DEFAULT NULL,
  `TotalePrice` double DEFAULT NULL,
  PRIMARY KEY (`SaleID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sale`
--

LOCK TABLES `sale` WRITE;
/*!40000 ALTER TABLE `sale` DISABLE KEYS */;
INSERT INTO `sale` VALUES ('bb47561b','3fc240ce','08d4b1ae','9b366595','2020-06-19 18:17:26',5,165),('5af157c1','47dbb0b9','70cd1a82','85edfb15','2020-06-19 15:53:12',2,45.8),('eb6ccc0a','4bef6854','31b6019e','85edfb15','2020-06-19 15:57:09',5,145),('6a480cb7','5ab8fd3c','70cd1a82','b7dc57b8','2020-06-19 14:03:52',2,45.8),('eb6ccc0a','968fd52a','70cd1a82','b7dc57b8','2020-06-19 18:14:58',4,91.6),('dc976c93','9776faaa','31b6019e','9dcdfddb','2020-06-19 18:16:11',1,29),('eb6ccc0a','a01bc6b2','f8b04fb0','b7dc57b8','2020-06-19 18:22:17',2,116),('5af157c1','aacd0980','85ebce69','9b366595','2020-06-19 18:16:34',5,1445),('830830ff','bbc0587e','85ebce69','9b366595','2020-06-19 17:03:19',1,289),('455bbf9d','cef63837','8b67349b','85edfb15','2020-06-19 18:15:47',2,4.2),('cda4067c','cf7ed9b9','85ebce69','9dcdfddb','2020-06-19 18:16:53',1,289),('3d3c1b3f','d377edaf','a7deda3a','9dcdfddb','2020-06-19 15:52:10',2,19.8),('cda4067c','e5cf2230','31b6019e','9dcdfddb','2020-06-19 11:58:22',2,58);
/*!40000 ALTER TABLE `sale` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-22 19:17:28
