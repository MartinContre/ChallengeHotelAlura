CREATE DATABASE  IF NOT EXISTS `hotel_alura` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `hotel_alura`;

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
-- Table structure for table `guests`
--

DROP TABLE IF EXISTS `guests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
	CREATE TABLE `guests` (
	  `id` int NOT NULL AUTO_INCREMENT,
	  `name` varchar(25) NOT NULL,
	  `surname` varchar(25) NOT NULL,
	  `birthdate` date NOT NULL,
	  `nationality` varchar(35) NOT NULL,
	  `phone` varchar(30) NOT NULL,
	  `booking_id` varchar(36) DEFAULT NULL,
	  PRIMARY KEY (`id`),
	  KEY `booking_id` (`booking_id`),
	  CONSTRAINT `guests_ibfk_1` FOREIGN KEY (`booking_id`) REFERENCES `bookings` (`booking_id`)
	) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guests`
--

LOCK TABLES `guests` WRITE;
/*!40000 ALTER TABLE `guests` DISABLE KEYS */;
INSERT INTO `guests`
 VALUES 
(1,'Jhon','Walter','1990-08-31','American','11-55-88-99','4798a389-132c-4c79-aa67-8569470dcf4a'),
(2,'Maria', 'Garcia Hdz', '1985-05-15', 'Spanish', '22-33-44-55', '5e6f8b52-87d2-4aef-bb39-f79a1c21c2d1'),
(3,'Alex', 'Johnson', '1992-11-22', 'Canadian', '99-88-77-66', 'a7c0e501-5c57-45d4-bf25-9f72dcbaf91c'),
(4,'Sophie', 'Zavala Gutierrez', '1988-03-10', 'French', '33-22-11-00', 'c4e6a87d-3b7f-4c59-890d-7f376e3db43e');
/*!40000 ALTER TABLE `guests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bookings`
--

DROP TABLE IF EXISTS `bookings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bookings` (
  `id` int NOT NULL AUTO_INCREMENT,
  `booking_id` varchar(36) NOT NULL,
  `check_in` date NOT NULL,
  `check_out` date NOT NULL,
  `value` decimal(9,2) NOT NULL,
  `payment_method` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `booking_id` (`booking_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookings`
--
/*!40000 ALTER TABLE `bookings` DISABLE KEYS */;
INSERT INTO `bookings` 
VALUES 
(1,'4798a389-132c-4c79-aa67-8569470dcf4a','2024-06-01','2024-06-23',341.00,'Dinero en Efectivo'),
(2,'5e6f8b52-87d2-4aef-bb39-f79a1c21c2d1', '2023-12-01', '2024-01-05', 558.00, 'Dinero en Efectivo'),
(3,'a7c0e501-5c57-45d4-bf25-9f72dcbaf91c', '2025-07-10', '2025-07-18', 124.00, 'Tarjeta de Crédito'),
(4,'c4e6a87d-3b7f-4c59-890d-7f376e3db43e', '2024-08-15', '2024-08-20', 77.50, 'Tarjeta de Débito');
/*!40000 ALTER TABLE `bookings` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(25) NOT NULL,
  `user_category` varchar(25) NOT NULL,
  `password` varchar(64) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` 
VALUES 
(1,'Martin_C','Manager','03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4'),
(2,'JoseO','Receptionist','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3'),
(3,'Maria','Receptionist','9a620c1ea7e2ba81544537bdb41d53457e281a1887f86b4ac527bbff0b8db0fa'),
(4,'alura1234','Manager','03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4');
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
-- Dump completed on 2023
