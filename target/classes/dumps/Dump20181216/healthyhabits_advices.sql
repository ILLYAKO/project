-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: localhost    Database: healthyhabits
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `advices`
--

DROP TABLE IF EXISTS `advices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `advices` (
  `advice_id` varchar(45) NOT NULL,
  `advice_name` varchar(45) DEFAULT NULL,
  `advice_type` varchar(45) DEFAULT NULL,
  `advice_part` varchar(45) DEFAULT NULL,
  `advice_description` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`advice_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Advices';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `advices`
--

LOCK TABLES `advices` WRITE;
/*!40000 ALTER TABLE `advices` DISABLE KEYS */;
INSERT INTO `advices` VALUES ('598ab101-2706-4bb5-baf9-aa7fa343707d','adv-01','musculoskeletal','back pain-01','fun fun fun'),('67fb8286-0eee-4a21-a5c3-61683473e6d5','relax back ','musculoskeletal','back pain','many relax '),('93400c41-9026-4528-b319-cfc6ea3a8c27','relax','eye','far vision','give your eye to relax'),('f570f140-6e54-4f27-8ecc-ec1c394dba30','eye and computer','eye','computer using','rest every 45 min');
/*!40000 ALTER TABLE `advices` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-12-16 13:58:28
