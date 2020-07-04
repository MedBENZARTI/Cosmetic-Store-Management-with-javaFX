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
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `ProductID` varchar(100) NOT NULL,
  `ProductName` varchar(100) DEFAULT NULL,
  `BuyingPrice` double DEFAULT NULL,
  `SellingPrice` double DEFAULT NULL,
  `ProductCat` varchar(100) DEFAULT NULL,
  `Mark` varchar(100) DEFAULT NULL,
  `Qte` int DEFAULT '0',
  PRIMARY KEY (`ProductID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES ('08d4b1ae','Crème solaire bio',30,33,'Crèmes solaires','Eq Evoa',195),('31b6019e','Brume solaire',22,29,'Crèmes solaires','Mixa',52),('6bf5b455','Lait solaire bio',42,46,'Crèmes solaires','Biosolls',50),('70cd1a82','rouge à lèvres',15,22.9,'lèvres','ESSENCE',142),('85ebce69','eaux de toilette',200,289,'eaux de toilette','Hugo Boss',8),('8b67349b','Savon déodorant',1.5,2.1,'Produits d\'hygiéne','FA',48),('a7deda3a','Shampoo',7,9.9,'Nettoyage cheveux','Head&Shoulders',38),('c8db0b77','Crayons à lèvres',10,17.9,'lèvres','Daylong',100),('d4c4555e','crème',27,49,'ecran solaire','pharmaceris',140),('f8b04fb0','Sublime sun protect',50,58,'Crèmes solaires','LOréal Paris',148);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
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
