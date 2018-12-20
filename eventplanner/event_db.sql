CREATE DATABASE  IF NOT EXISTS `event_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `event_db`;
-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: localhost    Database: event_db
-- ------------------------------------------------------
-- Server version	8.0.12

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
-- Table structure for table `attending`
--

DROP TABLE IF EXISTS `attending`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `attending` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `event_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKlpat2h57j3gwf6ixplmhuwl80` (`event_id`),
  KEY `FKsoald6xl4kn442jc88gphlhxh` (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attending`
--

LOCK TABLES `attending` WRITE;
/*!40000 ALTER TABLE `attending` DISABLE KEYS */;
INSERT INTO `attending` VALUES (46,NULL,NULL,3,2),(32,NULL,NULL,9,3),(35,NULL,NULL,3,3),(36,NULL,NULL,2,3),(50,NULL,NULL,10,1),(49,NULL,NULL,3,1),(48,NULL,NULL,9,1);
/*!40000 ALTER TABLE `attending` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `events`
--

DROP TABLE IF EXISTS `events`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `events` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `city` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `event_date` datetime DEFAULT NULL,
  `event_name` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKat8p3s7yjcp57lny4udqvqncq` (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `events`
--

LOCK TABLES `events` WRITE;
/*!40000 ALTER TABLE `events` DISABLE KEYS */;
INSERT INTO `events` VALUES (9,'West Covina','2018-10-23 20:35:35','2019-01-31 00:00:00','Matt\'s Birthday Extravaganza ','CA','2018-10-24 08:50:58',1),(10,'New Orleans ','2018-10-24 08:52:45','2019-03-05 00:00:00','Mardi Gras ','LA',NULL,1),(3,'Los Angeles','2018-10-23 13:58:13','2019-11-04 00:00:00','Melissa\'s Birthday','CA',NULL,2);
/*!40000 ALTER TABLE `events` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `messages`
--

DROP TABLE IF EXISTS `messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `messages` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `message_content` varchar(255) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `event_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3uxwdyxikw5y631g0ktdvrpqa` (`event_id`),
  KEY `FKpsmh6clh3csorw43eaodlqvkn` (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messages`
--

LOCK TABLES `messages` WRITE;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
INSERT INTO `messages` VALUES (1,'2018-10-23 14:45:47','Happy birthday Matt!','2018-10-23 14:45:47',1,2),(2,'2018-10-23 14:51:11','Happy birthday Mel!','2018-10-23 14:51:11',3,1),(3,'2018-10-23 15:02:48','Eat a deck Matt.','2018-10-23 15:02:48',1,3),(4,'2018-10-23 16:20:58','Gonna be tight!','2018-10-23 16:20:58',2,3),(5,'2018-10-24 09:59:53','MARDI GRAS BABY!','2018-10-24 09:59:53',10,1);
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `city` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'West Covina','2018-10-21 21:37:18','mattc@email.com','Matthew','Calimbas','$2a$10$PMp0fx4nJcQLsfrkk.dRguvXVTFjyj6ZJfnTfhjz67tlTTGU0Z4XS','CA',NULL),(2,'Los Angeles','2018-10-21 21:38:42','meladia@email.com','Melissa','Adia','$2a$10$0O9W7mDtFZemwKkgfK4.uOHfIPIyOPMDAMpvT8pRkb8od5BLi1uNu','CA',NULL),(3,'El Monte','2018-10-23 15:01:45','jermc@email.com','Jeremy','Calimbas','$2a$10$bT86qiCc0tAsrqYauiR7pu/.lbkEvznyKeAeS4iOSwK8K3mNZUVnS','CA',NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-10-24 10:10:06
