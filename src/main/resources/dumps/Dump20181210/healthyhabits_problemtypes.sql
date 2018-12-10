CREATE DATABASE  IF NOT EXISTS `healthyhabits` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin */;
USE `healthyhabits`;
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
-- Table structure for table `problemtypes`
--

DROP TABLE IF EXISTS `problemtypes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `problemtypes` (
  `problemtype_id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `problemtype_shortname` varchar(45) NOT NULL,
  `problemtype_fullname` varchar(255) NOT NULL,
  `problemtype_description` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`problemtype_id`),
  UNIQUE KEY `problemtype_id_UNIQUE` (`problemtype_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `problemtypes`
--

LOCK TABLES `problemtypes` WRITE;
/*!40000 ALTER TABLE `problemtypes` DISABLE KEYS */;
INSERT INTO `problemtypes` VALUES ('01','parasites','Certain infectious and parasitic Problems','Certain infectious and parasitic Problems'),('02','neoplasms','Neoplasms','Neoplasms'),('03','blood','Problems of the blood and blood-forming organs and certain disorders involving the immune mechanism','Problems of the blood and blood-forming organs and certain disorders involving the immune mechanism'),('04','metabolic','Endocrine, nutritional and metabolic Problems','Endocrine, nutritional and metabolic Problems'),('05','mental','Mental and behavioural disorders','Mental and behavioural disorders'),('06','nervous','Problems of the nervous system','Problems of the nervous system'),('07','eye','Problems of the eye and adnexa','Problems of the eye and adnexa'),('08','ear','Problems of the ear and mastoid process','Problems of the ear and mastoid process'),('09','circulatory','Problems of the circulatory system','Problems of the circulatory system'),('10','respiratory','Problems of the respiratory system','Problems of the respiratory system'),('11','digestive','Problems of the digestive system','Problems of the digestive system'),('12','skin','Problems of the skin and subcutaneous tissue','Problems of the skin and subcutaneous tissue'),('13','musculoskeletal','Problems of the musculoskeletal system and connective tissue','Problems of the musculoskeletal system and connective tissue'),('14','genitourinary','Problems of the genitourinary system','Problems of the genitourinary system'),('15','pregnancy','Pregnancy, childbirth and the puerperium','Pregnancy, childbirth and the puerperium'),('16','perinatal','Certain conditions originating in the perinatal period','Certain conditions originating in the perinatal period'),('17','chromosomal','Congenital malformations, deformations and chromosomal abnormalities','Congenital malformations, deformations and chromosomal abnormalities'),('18','notClassified','Symptoms, signs and abnormal clinical and laboratory findings, not elsewhere classified','ymptoms, signs and abnormal clinical and laboratory findings, not elsewhere classified'),('19','injury','Injury, poisoning and certain other consequences of external causes','Injury, poisoning and certain other consequences of external causes'),('20','externalCauses','External causes of morbidity and mortality','External causes of morbidity and mortality'),('21','factors','Factors influencing health status and contact with health services','Factors influencing health status and contact with health services'),('22','goToHospital','Go to the hospital','Go to the hospital');
/*!40000 ALTER TABLE `problemtypes` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-12-10  0:23:08
