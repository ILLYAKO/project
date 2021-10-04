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
-- Table structure for table `user_complaint`
--

DROP TABLE IF EXISTS `user_complaint`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user_complaint` (
  `user_complaint_id` varchar(45) NOT NULL,
  `user_id` varchar(45) DEFAULT NULL,
  `complaint_id` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`user_complaint_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_complaint`
--

LOCK TABLES `user_complaint` WRITE;
/*!40000 ALTER TABLE `user_complaint` DISABLE KEYS */;
INSERT INTO `user_complaint` VALUES ('6cba3132-eb76-4e72-a526-762f19467d5f','46586e9f-19b3-48e2-a7bc-d34fb0f62bb1','93a23e55-796d-49b5-8ff4-ec399dcdbeee'),('ba30b3b6-ed16-4983-9622-33a7107dc03a','dd81d3b1-8dca-493b-81a6-801fca7dd47e','eeb42f9c-e2ab-497a-8b6a-0590cba8a6eb'),('c0fa1f88-c921-45d8-90d5-992bba046aed','46586e9f-19b3-48e2-a7bc-d34fb0f62bb1','b4894ae8-3228-46bd-8f06-2f12e91a1419');
/*!40000 ALTER TABLE `user_complaint` ENABLE KEYS */;
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
