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
-- Table structure for table `problematicparts`
--

DROP TABLE IF EXISTS `problematicparts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `problematicparts` (
  `problematicpart_id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `problemtype_id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `problematicpart_name` varchar(45) NOT NULL,
  `problematicpart_description` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`problematicpart_id`),
  UNIQUE KEY `problematicpart_id_UNIQUE` (`problematicpart_id`),
  KEY `FK_001_idx` (`problemtype_id`),
  CONSTRAINT `FK_001` FOREIGN KEY (`problemtype_id`) REFERENCES `problemtypes` (`problemtype_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `problematicparts`
--

LOCK TABLES `problematicparts` WRITE;
/*!40000 ALTER TABLE `problematicparts` DISABLE KEYS */;
INSERT INTO `problematicparts` VALUES ('0456e3ac-9f8d-4543-9afa-0a44afa06ffe','13','back pain','fun-01'),('1c24870a-5e56-4fdd-a3cf-83b8e0d595b6','13','back pain','fun-02'),('3df6a240-9d24-4c84-8261-bffaf50256ad','13','back pain','fun-01'),('483241a7-1b46-4203-a285-e1fb6b5ae32a','07','far vision','far vision'),('49dd8b9a-d19a-454c-9e5f-e9f25cc24e5b','13','back pain','fun-02'),('4a1ba148-2f48-41ab-8c97-afecbba1bf29','13','back pain','pain in my back'),('5261a08d-4222-4af4-a232-d39a7bc6e39b','13','back pain','fun fun'),('650fd59b-3165-4b13-836b-dd982ac891df','13','back pain','fun fun'),('6586d835-5d07-43b0-83e7-229780f2f194','13','back pain','fun fun'),('663907e1-0108-4dc7-b953-a77ffab325e2','13','back pain','fun'),('73469688-4a8b-4b5a-8de9-1c450c6516f6','07','computer using','computer using'),('a5b58fcf-30f8-4f76-95d7-d62f539c1fe3','13','back pain','run 1000 steps'),('a7442b7f-b7a0-4a76-9745-d4b65341fbdd','13','back pain','run'),('ae4b35e0-cacc-441a-9cbd-5c01cafe7ec5','06','back pain','fun'),('b1aa0f80-68a7-49f0-ba2b-60350fb6a9fb','13','back pain','fun'),('b5960296-8570-453a-b8c0-423d85d2114c','13','back pain','back to  school'),('bb4c8abe-b705-4854-a5bc-307ec03d7341','13','back pain','fun - 01'),('c619876d-b11f-4126-8ad0-6f9150d195c9','13','back pain','145'),('dedbe53c-96ed-4811-9047-7a0cda54024b','13','back pain','fun-01'),('dfbe4396-944c-4041-929a-4caa50f90e17','13','back pain','fun'),('f0e163b7-b834-428b-9372-7d727bde408b','13','back pain','fun 11:15'),('f44aa698-f7cf-4878-abc3-0e68e3ebf4c7','13','back pain','run 1000 steps'),('f654fef1-ef71-4034-9276-8e2d43b87a71','09','back pain','8:08');
/*!40000 ALTER TABLE `problematicparts` ENABLE KEYS */;
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
