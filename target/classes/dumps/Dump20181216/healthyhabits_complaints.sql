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
-- Table structure for table `complaints`
--

DROP TABLE IF EXISTS `complaints`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `complaints` (
  `complaint_id` varchar(45) NOT NULL,
  `problematicpart_id` varchar(45) DEFAULT NULL,
  `complaint_description` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`complaint_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `complaints`
--

LOCK TABLES `complaints` WRITE;
/*!40000 ALTER TABLE `complaints` DISABLE KEYS */;
INSERT INTO `complaints` VALUES ('02d07e5b-824c-41f0-860a-d014f157e307','73469688-4a8b-4b5a-8de9-1c450c6516f6','computer using'),('0a414272-2e7a-41b3-a144-a3ba2381f213','b5960296-8570-453a-b8c0-423d85d2114c','back to  school'),('14371b4f-f45b-48db-9c96-5ddb7928c1b9','4a1ba148-2f48-41ab-8c97-afecbba1bf29','pain in my back'),('152cdb38-3f06-4976-af1c-bfeda7cb86a0','ae4b35e0-cacc-441a-9cbd-5c01cafe7ec5','fun'),('30def0cb-2480-4c32-999b-466c6d515126','dfbe4396-944c-4041-929a-4caa50f90e17','fun'),('3a559ef3-6f14-4660-8784-772d9da4cfc6','0456e3ac-9f8d-4543-9afa-0a44afa06ffe','fun-01'),('4400ae64-c337-482c-8777-54c139b394a2','663907e1-0108-4dc7-b953-a77ffab325e2','fun'),('6b213f1d-1992-46e8-8745-04440f679a16','1c24870a-5e56-4fdd-a3cf-83b8e0d595b6','fun-02'),('6e47df82-d83c-4e1e-b78c-ea6d0df1eed4','b1aa0f80-68a7-49f0-ba2b-60350fb6a9fb','fun'),('715e60f8-2ff7-4388-b6ab-9c25254507eb','3df6a240-9d24-4c84-8261-bffaf50256ad','fun-01'),('8d7a8e11-ce3b-4f2d-a7e4-5352ae6ce2a3','6586d835-5d07-43b0-83e7-229780f2f194','fun fun'),('93a23e55-796d-49b5-8ff4-ec399dcdbeee','f44aa698-f7cf-4878-abc3-0e68e3ebf4c7','run 1000 steps'),('981009de-49c9-4060-9135-92a4c11d7a44','dedbe53c-96ed-4811-9047-7a0cda54024b','fun-01'),('a0c1f90e-213e-472c-8b62-10a077a93726','49dd8b9a-d19a-454c-9e5f-e9f25cc24e5b','fun-02'),('a1867446-89ce-434b-b619-bab074d9c49a','bb4c8abe-b705-4854-a5bc-307ec03d7341','fun - 01'),('b4894ae8-3228-46bd-8f06-2f12e91a1419','a5b58fcf-30f8-4f76-95d7-d62f539c1fe3','run 1000 steps'),('dbdb2ea2-1d61-4b06-a774-a2c0f28e709b','f654fef1-ef71-4034-9276-8e2d43b87a71','8:08'),('eeb42f9c-e2ab-497a-8b6a-0590cba8a6eb','483241a7-1b46-4203-a285-e1fb6b5ae32a','far vision'),('f893ec6b-6765-4f01-9e98-ef35016907d2','a7442b7f-b7a0-4a76-9745-d4b65341fbdd','run'),('fb6951f0-4dc6-4a22-82e7-4b6ca31482ef','f0e163b7-b834-428b-9372-7d727bde408b','fun 11:15');
/*!40000 ALTER TABLE `complaints` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-12-16 13:58:29
