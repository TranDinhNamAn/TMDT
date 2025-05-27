-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: tmdt2
-- ------------------------------------------------------
-- Server version	8.0.40

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
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address` (
  `AddressID` int NOT NULL AUTO_INCREMENT,
  `Street` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `WardOrcommune` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `District` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `ProvinceOrCity` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`AddressID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,'18','Linh Trung','Thủ Đức','TPHCM');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart_items`
--

DROP TABLE IF EXISTS `cart_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart_items` (
  `CartID` int NOT NULL AUTO_INCREMENT,
  `UserID` int NOT NULL,
  `ProductID` int NOT NULL,
  `Quantity` int NOT NULL,
  `Created_at` date NOT NULL,
  PRIMARY KEY (`CartID`),
  KEY `fk_cart_user_1` (`UserID`),
  KEY `fk_cart_product_1` (`ProductID`),
  CONSTRAINT `fk_cart_product_1` FOREIGN KEY (`ProductID`) REFERENCES `products` (`ProductID`),
  CONSTRAINT `fk_cart_user_1` FOREIGN KEY (`UserID`) REFERENCES `users` (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_items`
--

LOCK TABLES `cart_items` WRITE;
/*!40000 ALTER TABLE `cart_items` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `CategoriesID` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `Description` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `CreateDate` date DEFAULT NULL,
  `LastUpdateDate` date DEFAULT NULL,
  PRIMARY KEY (`CategoriesID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'ChargingCable',NULL,'2025-04-01','2025-04-01'),(2,'Headphone',NULL,'2025-04-01','2025-04-01'),(3,'HeadphoneCase',NULL,'2025-04-01','2025-04-01'),(4,'AirpodCase',NULL,'2025-04-01','2025-04-01'),(5,'Popsocket',NULL,'2025-04-01','2025-04-01'),(6,'Iring',NULL,'2025-04-01','2025-04-01');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment_ratings`
--

DROP TABLE IF EXISTS `comment_ratings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment_ratings` (
  `RatingID` int NOT NULL AUTO_INCREMENT,
  `CommentID` int NOT NULL,
  `UserID` int NOT NULL,
  `Star` int NOT NULL,
  `Created_at` date NOT NULL,
  PRIMARY KEY (`RatingID`),
  KEY `fk_rating_user` (`UserID`),
  KEY `fk_rating_comment` (`CommentID`),
  CONSTRAINT `fk_rating_comment` FOREIGN KEY (`CommentID`) REFERENCES `comments` (`CommentID`),
  CONSTRAINT `fk_rating_user` FOREIGN KEY (`UserID`) REFERENCES `users` (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment_ratings`
--

LOCK TABLES `comment_ratings` WRITE;
/*!40000 ALTER TABLE `comment_ratings` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment_ratings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comments` (
  `CommentID` int NOT NULL AUTO_INCREMENT,
  `UserID` int NOT NULL,
  `ProductID` int NOT NULL,
  `Content` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `Created_at` date NOT NULL,
  `Updated_at` date NOT NULL,
  `Status` int NOT NULL,
  PRIMARY KEY (`CommentID`),
  KEY `fk_cart_product` (`ProductID`),
  KEY `fk_cart_user` (`UserID`),
  CONSTRAINT `fk_cart_product` FOREIGN KEY (`ProductID`) REFERENCES `products` (`ProductID`),
  CONSTRAINT `fk_cart_user` FOREIGN KEY (`UserID`) REFERENCES `users` (`UserID`),
  CONSTRAINT `fk_comment_product` FOREIGN KEY (`ProductID`) REFERENCES `products` (`ProductID`),
  CONSTRAINT `fk_comment_user` FOREIGN KEY (`UserID`) REFERENCES `users` (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `complaints`
--

DROP TABLE IF EXISTS `complaints`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `complaints` (
  `ComplaintID` int NOT NULL AUTO_INCREMENT,
  `UserID` int NOT NULL,
  `OrderID` int NOT NULL,
  `Description` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `Created_at` date NOT NULL,
  `Status` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `Response` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`ComplaintID`),
  KEY `OrderID` (`OrderID`),
  KEY `UserID` (`UserID`),
  CONSTRAINT `OrderID` FOREIGN KEY (`OrderID`) REFERENCES `orders` (`OrderID`),
  CONSTRAINT `UserID` FOREIGN KEY (`UserID`) REFERENCES `users` (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `complaints`
--

LOCK TABLES `complaints` WRITE;
/*!40000 ALTER TABLE `complaints` DISABLE KEYS */;
/*!40000 ALTER TABLE `complaints` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employees` (
  `EmployeeID` int NOT NULL AUTO_INCREMENT,
  `UserID` int DEFAULT NULL,
  `LastName` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `FirstName` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `Position` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `Email` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `PhoneNumber` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `DateOfBirth` date DEFAULT NULL,
  `Gender` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `TotalSalary` float DEFAULT NULL,
  PRIMARY KEY (`EmployeeID`) USING BTREE,
  KEY `fk_employees_user` (`UserID`) USING BTREE,
  CONSTRAINT `fk_employees_user` FOREIGN KEY (`UserID`) REFERENCES `users` (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees`
--

LOCK TABLES `employees` WRITE;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favoriteproducts`
--

DROP TABLE IF EXISTS `favoriteproducts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `favoriteproducts` (
  `FVID` int NOT NULL AUTO_INCREMENT,
  `UserID` int DEFAULT NULL,
  `ProductID` int DEFAULT NULL,
  PRIMARY KEY (`FVID`) USING BTREE,
  KEY `fk_favoriteproducts_user` (`UserID`) USING BTREE,
  KEY `fk_favoriteproducts_product` (`ProductID`) USING BTREE,
  CONSTRAINT `ProductID` FOREIGN KEY (`ProductID`) REFERENCES `products` (`ProductID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favoriteproducts`
--

LOCK TABLES `favoriteproducts` WRITE;
/*!40000 ALTER TABLE `favoriteproducts` DISABLE KEYS */;
/*!40000 ALTER TABLE `favoriteproducts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `imgproducts`
--

DROP TABLE IF EXISTS `imgproducts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `imgproducts` (
  `IProductID` int NOT NULL AUTO_INCREMENT,
  `ProductID` int DEFAULT NULL,
  `AltText` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `LastUpdateDate` date DEFAULT NULL,
  `imgURL` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`IProductID`) USING BTREE,
  KEY `fk_imgproducts_product_idx` (`ProductID`) USING BTREE,
  CONSTRAINT `fk_imgproducts_product` FOREIGN KEY (`ProductID`) REFERENCES `products` (`ProductID`)
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `imgproducts`
--

LOCK TABLES `imgproducts` WRITE;
/*!40000 ALTER TABLE `imgproducts` DISABLE KEYS */;
INSERT INTO `imgproducts` VALUES (1,1,NULL,'2025-04-01','1.jpg'),(2,2,NULL,'2025-04-01','2.jpg'),(3,3,NULL,'2025-04-01','3.jpg'),(4,4,NULL,'2025-04-01','4.jpg'),(5,5,NULL,'2025-04-01','5.jpg'),(6,6,NULL,'2025-04-01','6.jpg'),(7,7,NULL,'2025-04-01','7.jpg'),(8,8,NULL,'2025-04-01','8.jpg'),(9,9,NULL,'2025-04-01','9.jpg'),(10,10,NULL,'2025-04-01','10.jpg'),(11,11,NULL,'2025-04-01','11.jpeg'),(12,12,NULL,'2025-04-01','12.jpeg'),(13,13,NULL,'2025-04-01','13.jpeg'),(14,14,NULL,'2025-04-01','14.jpg'),(15,15,NULL,'2025-04-01','15.jpg'),(16,16,NULL,'2025-04-01','16.jpg'),(17,17,NULL,'2025-04-01','17.jpg'),(18,18,NULL,'2025-04-01','18.jpg'),(19,19,NULL,'2025-04-01','19.jpg'),(20,20,NULL,'2025-04-01','20.jpeg'),(21,21,NULL,'2025-04-01','21.jpg'),(22,22,NULL,'2025-04-01','22.jpg'),(23,23,NULL,'2025-04-01','23.jpg'),(24,24,NULL,'2025-04-01','24.jpg'),(25,25,NULL,'2025-04-01','25.jpg'),(26,26,NULL,'2025-04-01','26.jpg'),(27,27,NULL,'2025-04-01','27.jpg'),(28,28,NULL,'2025-04-01','28.jpg'),(29,29,NULL,'2025-04-01','29.jpg'),(30,30,NULL,'2025-04-01','30.jpg'),(31,31,NULL,'2025-04-01','31.jpeg'),(32,32,NULL,'2025-04-01','32.jpeg'),(33,33,NULL,'2025-04-01','33.jpeg'),(34,34,NULL,'2025-04-01','34.jpeg'),(35,35,NULL,'2025-04-01','35.jpeg'),(36,36,NULL,'2025-04-01','36.jpeg'),(37,37,NULL,'2025-04-01','37.jpeg'),(38,38,NULL,'2025-04-01','38.jpeg'),(39,39,NULL,'2025-04-01','39.jpeg'),(40,40,NULL,'2025-04-01','40.jpeg'),(41,41,NULL,'2025-04-01','41.jpeg'),(42,42,NULL,'2025-04-01','42.jpeg'),(43,43,NULL,'2025-04-01','43.jpeg'),(44,44,NULL,'2025-04-01','44.jpeg'),(45,45,NULL,'2025-04-01','45.jpeg'),(46,46,NULL,'2025-04-01','46.jpeg'),(47,47,NULL,'2025-04-01','47.jpeg'),(48,48,NULL,'2025-04-01','48.jpeg'),(49,49,NULL,'2025-04-01','49.jpeg'),(50,50,NULL,'2025-04-01','50.jpeg'),(51,51,NULL,'2025-04-01','51.jpg'),(52,52,NULL,'2025-04-01','52.jpg'),(53,53,NULL,'2025-04-01','53.jpg'),(54,54,NULL,'2025-04-01','54.jpg'),(55,55,NULL,'2025-04-01','55.jpg'),(56,56,NULL,'2025-04-01','56.jpg'),(57,57,NULL,'2025-04-01','57.jpg'),(58,58,NULL,'2025-04-01','58.jpg'),(59,59,NULL,'2025-04-01','59.jpg'),(60,60,NULL,'2025-04-01','60.jpg'),(61,61,NULL,'2025-04-01','61.jpg'),(62,62,NULL,'2025-04-01','62.jpg'),(63,63,NULL,'2025-04-01','63.jpg'),(64,64,NULL,'2025-04-01','64.jpg'),(65,65,NULL,'2025-04-01','65.jpg'),(66,66,NULL,'2025-04-01','66.jpg'),(67,67,NULL,'2025-04-01','67.jpg'),(68,68,NULL,'2025-04-01','68.jpg'),(69,69,NULL,'2025-04-01','69.jpg'),(70,70,NULL,'2025-04-01','70.jpeg'),(71,71,NULL,'2025-04-01','1.jpg'),(72,72,NULL,'2025-04-01','1.jpg'),(73,73,NULL,'2025-04-01','1.jpg'),(74,74,NULL,'2025-04-01','1.jpg'),(75,75,NULL,'2025-04-01','1.jpg'),(76,76,NULL,'2025-04-01','1.jpg'),(77,77,NULL,'2025-04-01','1.jpg'),(78,78,NULL,'2025-04-01','1.jpg'),(79,79,NULL,'2025-04-01','1.jpg'),(80,80,NULL,'2025-04-01','1.jpg'),(81,81,NULL,'2025-04-01','11.jpg'),(82,82,NULL,'2025-04-01','12.jpg'),(83,83,NULL,'2025-04-01','13.jpg'),(84,84,NULL,'2025-04-01','14.jpg'),(85,85,NULL,'2025-04-01','15.jpg'),(86,86,NULL,'2025-04-01','21.jpg'),(87,87,NULL,'2025-04-01','22.jpg'),(88,88,NULL,'2025-04-01','23.jpg'),(89,89,NULL,'2025-04-01','24.jpg'),(90,90,NULL,'2025-04-01','25.jpg'),(91,91,NULL,'2025-04-01','31.jpeg'),(92,92,NULL,'2025-04-01','32.jpeg'),(93,93,NULL,'2025-04-01','33.jpeg'),(94,94,NULL,'2025-04-01','34.jpeg'),(95,95,NULL,'2025-04-01','35.jpeg'),(96,96,NULL,'2025-04-01','36.jpeg'),(97,97,NULL,'2025-04-01','37.jprg'),(98,98,NULL,'2025-04-01','38.jpeg'),(99,99,NULL,'2025-04-01','39.jpeg'),(100,100,NULL,'2025-04-01','40.jpeg'),(101,101,NULL,'2025-04-01','41.jpeg'),(102,102,NULL,'2025-04-01','42.jpeg'),(103,103,NULL,'2025-04-01','43.jpeg'),(104,104,NULL,'2025-04-01','44.jpeg'),(105,105,NULL,'2025-04-01','45.jpeg'),(106,106,NULL,'2025-04-01','46.jpeg'),(107,107,NULL,'2025-04-01','47.jpeg'),(108,108,NULL,'2025-04-01','48.jpeg'),(109,109,NULL,'2025-04-01','49.jpeg');
/*!40000 ALTER TABLE `imgproducts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderdetails`
--

DROP TABLE IF EXISTS `orderdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orderdetails` (
  `OrderDetailID` int NOT NULL AUTO_INCREMENT,
  `OrderID` int DEFAULT NULL,
  `ProductID` int DEFAULT NULL,
  `Quantity` int DEFAULT NULL,
  `Price` decimal(15,2) DEFAULT NULL,
  `DateAdd` date NOT NULL,
  PRIMARY KEY (`OrderDetailID`) USING BTREE,
  KEY `OrderID` (`OrderID`) USING BTREE,
  KEY `ProductID` (`ProductID`) USING BTREE,
  CONSTRAINT `orderdetails_ibfk_1` FOREIGN KEY (`OrderID`) REFERENCES `orders` (`OrderID`),
  CONSTRAINT `orderdetails_ibfk_2` FOREIGN KEY (`ProductID`) REFERENCES `products` (`ProductID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderdetails`
--

LOCK TABLES `orderdetails` WRITE;
/*!40000 ALTER TABLE `orderdetails` DISABLE KEYS */;
/*!40000 ALTER TABLE `orderdetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `OrderID` int NOT NULL AUTO_INCREMENT,
  `UserID` int DEFAULT NULL,
  `AddressID` int DEFAULT NULL,
  `Status` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `CustomerNote` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `CreateDate` date NOT NULL,
  `ShippingAddress` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `PaymentMethod` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `TotalAmount` decimal(15,2) NOT NULL,
  PRIMARY KEY (`OrderID`) USING BTREE,
  KEY `fk_orders_addressid` (`AddressID`) USING BTREE,
  KEY `fk_orders_userid` (`UserID`) USING BTREE,
  CONSTRAINT `fk_orders_userid` FOREIGN KEY (`UserID`) REFERENCES `users` (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `password_reset_tokens`
--

DROP TABLE IF EXISTS `password_reset_tokens`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `password_reset_tokens` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `token` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `expires_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `password_reset_tokens_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `password_reset_tokens`
--

LOCK TABLES `password_reset_tokens` WRITE;
/*!40000 ALTER TABLE `password_reset_tokens` DISABLE KEYS */;
/*!40000 ALTER TABLE `password_reset_tokens` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productdescriptions`
--

DROP TABLE IF EXISTS `productdescriptions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productdescriptions` (
  `ProductID` int DEFAULT NULL,
  `DescriptionDetails` text COLLATE utf8mb4_general_ci,
  KEY `fk_productdescription_product` (`ProductID`) USING BTREE,
  CONSTRAINT `fk_productdescription_product` FOREIGN KEY (`ProductID`) REFERENCES `products` (`ProductID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productdescriptions`
--

LOCK TABLES `productdescriptions` WRITE;
/*!40000 ALTER TABLE `productdescriptions` DISABLE KEYS */;
/*!40000 ALTER TABLE `productdescriptions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `ProductID` int NOT NULL AUTO_INCREMENT,
  `NameProduct` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `Description` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `Price` double DEFAULT NULL,
  `Stock` int DEFAULT NULL,
  `CategoriesID` int DEFAULT NULL,
  `CreateDate` date DEFAULT NULL,
  `LastUpdateDate` date DEFAULT NULL,
  PRIMARY KEY (`ProductID`) USING BTREE,
  KEY `CategoriesID` (`CategoriesID`) USING BTREE,
  CONSTRAINT `products_ibfk_1` FOREIGN KEY (`CategoriesID`) REFERENCES `categories` (`CategoriesID`)
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'Dây sạc',NULL,120000,40,1,'2025-04-01','2025-04-01'),(2,'Dây sạc',NULL,120000,40,1,'2025-04-01','2025-04-01'),(3,'Dây sạc',NULL,120000,40,1,'2025-04-01','2025-04-01'),(4,'Dây sạc',NULL,120000,40,1,'2025-04-01','2025-04-01'),(5,'Dây sạc',NULL,120000,40,1,'2025-04-01','2025-04-01'),(6,'Dây sạc',NULL,120000,40,1,'2025-04-01','2025-04-01'),(7,'Dây sạc',NULL,120000,40,1,'2025-04-01','2025-04-01'),(8,'Dây sạc',NULL,120000,40,1,'2025-04-01','2025-04-01'),(9,'Dây sạc',NULL,12000,40,1,'2025-04-01','2025-04-01'),(10,'Dây sạc',NULL,120000,40,1,'2025-04-01','2025-04-01'),(11,'Tai nghe',NULL,120000,40,2,'2025-04-01','2025-04-01'),(12,'Tai nghe',NULL,120000,40,2,'2025-04-01','2025-04-01'),(13,'Tai nghe',NULL,120000,40,2,'2025-04-01','2025-04-01'),(14,'Tai nghe',NULL,120000,40,2,'2025-04-01','2025-04-01'),(15,'Tai nghe',NULL,120000,40,2,'2025-04-01','2025-04-01'),(16,'Tai nghe',NULL,120000,40,2,'2025-04-01','2025-04-01'),(17,'Tai nghe',NULL,120000,40,2,'2025-04-01','2025-04-01'),(18,'Tai nghe',NULL,120000,40,2,'2025-04-01','2025-04-01'),(19,'Tai nghe',NULL,12000,40,2,'2025-04-01','2025-04-01'),(20,'Tai nghe',NULL,120000,40,2,'2025-04-01','2025-04-01'),(21,'Hộp đựng tay nghe',NULL,120000,40,3,'2025-04-01','2025-04-01'),(22,'Hộp đựng tay nghe',NULL,120000,40,3,'2025-04-01','2025-04-01'),(23,'Hộp đựng tay nghe',NULL,120000,40,3,'2025-04-01','2025-04-01'),(24,'Hộp đựng tay nghe',NULL,120000,40,3,'2025-04-01','2025-04-01'),(25,'Hộp đựng tay nghe',NULL,120000,40,3,'2025-04-01','2025-04-01'),(26,'Hộp đựng tay nghe',NULL,120000,40,3,'2025-04-01','2025-04-01'),(27,'Hộp đựng tay nghe',NULL,120000,40,3,'2025-04-01','2025-04-01'),(28,'Hộp đựng tay nghe',NULL,120000,40,3,'2025-04-01','2025-04-01'),(29,'Hộp đựng tay nghe',NULL,12000,40,3,'2025-04-01','2025-04-01'),(30,'Hộp đựng tay nghe',NULL,120000,40,3,'2025-04-01','2025-04-01'),(31,'Hộp đựng tay nghe airpod',NULL,120000,40,4,'2025-04-01','2025-04-01'),(32,'Hộp đựng tay nghe airpod',NULL,120000,40,4,'2025-04-01','2025-04-01'),(33,'Hộp đựng tay nghe airpod',NULL,120000,40,4,'2025-04-01','2025-04-01'),(34,'Hộp đựng tay nghe airpod',NULL,120000,40,4,'2025-04-01','2025-04-01'),(35,'Hộp đựng tay nghe airpod',NULL,120000,40,4,'2025-04-01','2025-04-01'),(36,'Hộp đựng tay nghe airpod',NULL,120000,40,4,'2025-04-01','2025-04-01'),(37,'Hộp đựng tay nghe airpod',NULL,120000,40,4,'2025-04-01','2025-04-01'),(38,'Hộp đựng tay nghe airpod',NULL,120000,40,4,'2025-04-01','2025-04-01'),(39,'Hộp đựng tay nghe airpod',NULL,12000,40,4,'2025-04-01','2025-04-01'),(40,'Hộp đựng tay nghe airpod',NULL,120000,40,4,'2025-04-01','2025-04-01'),(41,'Popsocket',NULL,120000,40,5,'2025-04-01','2025-04-01'),(42,'Popsocket',NULL,120000,40,5,'2025-04-01','2025-04-01'),(43,'Popsocket',NULL,120000,40,5,'2025-04-01','2025-04-01'),(44,'Popsocket',NULL,120000,40,5,'2025-04-01','2025-04-01'),(45,'Popsocket',NULL,120000,40,5,'2025-04-01','2025-04-01'),(46,'Popsocket',NULL,120000,40,5,'2025-04-01','2025-04-01'),(47,'Popsocket',NULL,120000,40,5,'2025-04-01','2025-04-01'),(48,'Popsocket',NULL,120000,40,5,'2025-04-01','2025-04-01'),(49,'Popsocket',NULL,12000,40,5,'2025-04-01','2025-04-01'),(50,'Popsocket',NULL,120000,40,5,'2025-04-01','2025-04-01'),(51,'Popsocket',NULL,120000,40,5,'2025-04-01','2025-04-01'),(52,'Popsocket',NULL,120000,40,5,'2025-04-01','2025-04-01'),(53,'Popsocket',NULL,120000,40,5,'2025-04-01','2025-04-01'),(54,'Popsocket',NULL,120000,40,5,'2025-04-01','2025-04-01'),(55,'Popsocket',NULL,120000,40,5,'2025-04-01','2025-04-01'),(56,'Popsocket',NULL,120000,40,5,'2025-04-01','2025-04-01'),(57,'Popsocket',NULL,120000,40,5,'2025-04-01','2025-04-01'),(58,'Popsocket',NULL,120000,40,5,'2025-04-01','2025-04-01'),(59,'Popsocket',NULL,120000,40,5,'2025-04-01','2025-04-01'),(60,'Popsocket',NULL,120000,40,5,'2025-04-01','2025-04-01'),(61,'Iring',NULL,120000,40,6,'2025-04-01','2025-04-01'),(62,'Iring',NULL,120000,40,6,'2025-04-01','2025-04-01'),(63,'Iring',NULL,120000,40,6,'2025-04-01','2025-04-01'),(64,'Iring',NULL,120000,40,6,'2025-04-01','2025-04-01'),(65,'Iring',NULL,120000,40,6,'2025-04-01','2025-04-01'),(66,'Iring',NULL,120000,40,6,'2025-04-01','2025-04-01'),(67,'Iring',NULL,120000,40,6,'2025-04-01','2025-04-01'),(68,'Iring',NULL,120000,40,6,'2025-04-01','2025-04-01'),(69,'Iring',NULL,12000,40,6,'2025-04-01','2025-04-01'),(70,'Iring',NULL,120000,40,6,'2025-04-01','2025-04-01'),(71,'Dây sạc',NULL,120000,40,1,'2025-04-01','2025-04-01'),(72,'Dây sạc',NULL,120000,40,1,'2025-04-01','2025-04-01'),(73,'Dây sạc',NULL,120000,40,1,'2025-04-01','2025-04-01'),(74,'Dây sạc',NULL,120000,40,1,'2025-04-01','2025-04-01'),(75,'Dây sạc',NULL,120000,40,1,'2025-04-01','2025-04-01'),(76,'Dây sạc',NULL,120000,40,1,'2025-04-01','2025-04-01'),(77,'Dây sạc',NULL,120000,40,1,'2025-04-01','2025-04-01'),(78,'Dây sạc',NULL,120000,40,1,'2025-04-01','2025-04-01'),(79,'Dây sạc',NULL,12000,40,1,'2025-04-01','2025-04-01'),(80,'Dây sạc',NULL,120000,40,1,'2025-04-01','2025-04-01'),(81,'Tai nghe',NULL,120000,40,2,'2025-04-01','2025-04-01'),(82,'Tai nghe',NULL,120000,40,2,'2025-04-01','2025-04-01'),(83,'Tai nghe',NULL,120000,40,2,'2025-04-01','2025-04-01'),(84,'Tai nghe',NULL,120000,40,2,'2025-04-01','2025-04-01'),(85,'Tai nghe',NULL,120000,40,2,'2025-04-01','2025-04-01'),(86,'Hộp đựng tay nghe',NULL,120000,40,3,'2025-04-01','2025-04-01'),(87,'Hộp đựng tay nghe',NULL,120000,40,3,'2025-04-01','2025-04-01'),(88,'Hộp đựng tay nghe',NULL,120000,40,3,'2025-04-01','2025-04-01'),(89,'Hộp đựng tay nghe',NULL,12000,40,3,'2025-04-01','2025-04-01'),(90,'Hộp đựng tay nghe',NULL,120000,40,3,'2025-04-01','2025-04-01'),(91,'Hộp đựng tay nghe airpod',NULL,120000,40,4,'2025-04-01','2025-04-01'),(92,'Hộp đựng tay nghe airpod',NULL,120000,40,4,'2025-04-01','2025-04-01'),(93,'Hộp đựng tay nghe airpod',NULL,120000,40,4,'2025-04-01','2025-04-01'),(94,'Hộp đựng tay nghe airpod',NULL,120000,40,4,'2025-04-01','2025-04-01'),(95,'Hộp đựng tay nghe airpod',NULL,120000,40,4,'2025-04-01','2025-04-01'),(96,'Hộp đựng tay nghe airpod',NULL,120000,40,4,'2025-04-01','2025-04-01'),(97,'Hộp đựng tay nghe airpod',NULL,120000,40,4,'2025-04-01','2025-04-01'),(98,'Hộp đựng tay nghe airpod',NULL,120000,40,4,'2025-04-01','2025-04-01'),(99,'Hộp đựng tay nghe airpod',NULL,12000,40,4,'2025-04-01','2025-04-01'),(100,'Hộp đựng tay nghe airpod',NULL,120000,40,4,'2025-04-01','2025-04-01'),(101,'Popsocket',NULL,120000,40,5,'2025-04-01','2025-04-01'),(102,'Popsocket',NULL,120000,40,5,'2025-04-01','2025-04-01'),(103,'Popsocket',NULL,120000,40,5,'2025-04-01','2025-04-01'),(104,'Popsocket',NULL,120000,40,5,'2025-04-01','2025-04-01'),(105,'Popsocket',NULL,120000,40,5,'2025-04-01','2025-04-01'),(106,'Popsocket',NULL,120000,40,5,'2025-04-01','2025-04-01'),(107,'Popsocket',NULL,120000,40,5,'2025-04-01','2025-04-01'),(108,'Popsocket',NULL,120000,40,5,'2025-04-01','2025-04-01'),(109,'Popsocket',NULL,12000,40,5,'2025-04-01','2025-04-01');
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `promotions`
--

DROP TABLE IF EXISTS `promotions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `promotions` (
  `PromotionID` int NOT NULL AUTO_INCREMENT,
  `PromoCode` int NOT NULL,
  `DiscountType` int NOT NULL,
  `StartDate` date NOT NULL,
  `EndDate` date NOT NULL,
  `UsageLimit` int NOT NULL,
  `MinOrder` int NOT NULL,
  PRIMARY KEY (`PromotionID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promotions`
--

LOCK TABLES `promotions` WRITE;
/*!40000 ALTER TABLE `promotions` DISABLE KEYS */;
/*!40000 ALTER TABLE `promotions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `useraddresses`
--

DROP TABLE IF EXISTS `useraddresses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `useraddresses` (
  `AddressUserID` int NOT NULL AUTO_INCREMENT,
  `AddressID` int DEFAULT NULL,
  `UserID` int NOT NULL,
  `FullnameReceiver` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `PhoneReceiver` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `AddressType` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `IsPrimary` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `Description` tinyint DEFAULT NULL,
  `CreateDate` date DEFAULT NULL,
  `LastUpdateDate` date DEFAULT NULL,
  `UserType` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`AddressUserID`) USING BTREE,
  KEY `UserID_idx` (`UserID`) USING BTREE,
  KEY `fk_useraddresses_user` (`UserID`) USING BTREE,
  KEY `fk_useraddresses_address_idx` (`AddressID`) USING BTREE,
  CONSTRAINT `fk_useraddresses_address` FOREIGN KEY (`AddressID`) REFERENCES `address` (`AddressID`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `useraddresses`
--

LOCK TABLES `useraddresses` WRITE;
/*!40000 ALTER TABLE `useraddresses` DISABLE KEYS */;
INSERT INTO `useraddresses` VALUES (1,1,1,'Thi Ly','0398422345','nhà riêng','1',0,'2025-05-01','2025-05-27','regular');
/*!40000 ALTER TABLE `useraddresses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `UserID` int NOT NULL AUTO_INCREMENT,
  `Email` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `PhoneNumber` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `UserName` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `Epassword` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `DayOfBirth` date DEFAULT NULL,
  `Gender` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `IsBlocked` tinyint NOT NULL,
  `Status` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `Rank` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `Point` int NOT NULL,
  `CreateDate` date NOT NULL,
  `LastUpdateDate` date NOT NULL,
  `LastLoginDate` date NOT NULL,
  `FacebookID` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `GoogleID` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `Provider` enum('FACEBOOK','GOOGLE','LOCAL') COLLATE utf8mb4_general_ci DEFAULT 'LOCAL',
  `ProfilePicture` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `Name` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`UserID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'li','0123456789','li126','$2a$10$fVMyn2x24PU7.Okvy.PYlOm2weVw.jR9xDz7RnLlYo.cOyHVdVDlm',NULL,'',0,'active','silver',150,'2025-05-04','2025-05-27','2025-05-04',NULL,'google123','GOOGLE','https://th.bing.com/th/id/OIP.bvgvfalKJGM4rU-nDAJ3aQHaHa?rs=1&pid=ImgDetMain','li'),(2,'hoa@example.com','0987654321','hoalee','hashedpassword2',NULL,'',0,'active','gold',230,'2025-05-02','2025-05-03','2025-05-04','facebook456',NULL,'FACEBOOK','https://example.com/hoa.jpg','Hoa Lee'),(3,'minh@example.com',NULL,'minhnguyen','hashedpassword3',NULL,'',0,'active','bronze',80,'2025-05-01','2025-05-04','2025-05-04',NULL,NULL,'LOCAL',NULL,'Minh Nguyen'),(4,'20130443@st.hcmuaf.edu.vn','','toi','$2a$10$X2NfqT3z7SeO8qahX9MM4Ooc6mD.JGapNB2MiNF8N/vxUeqwfQZJm',NULL,'',0,'active','newbie',0,'2025-05-05','2025-05-05','2025-05-05',NULL,NULL,'LOCAL','https://th.bing.com/th/id/OIP.bvgvfalKJGM4rU-nDAJ3aQHaHa?rs=1&pid=ImgDetMain','toi');
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

-- Dump completed on 2025-05-27 15:42:57
