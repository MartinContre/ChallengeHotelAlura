CREATE DATABASE  IF NOT EXISTS `hotel_alura`;
USE `hotel_alura`;

--
-- Table structure for table `reservas`
--

DROP TABLE IF EXISTS `bookings`;
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

--
-- Dumping data for table `reservas`
--

INSERT INTO `bookings` 
(`booking_id`, `check_in`, `check_out`, `value`, `payment_method`)
VALUES 
('4798a389-132c-4c79-aa67-8569470dcf4a','2024-06-01','2024-06-23',341.00,'Dinero en Efectivo'),
('5e6f8b52-87d2-4aef-bb39-f79a1c21c2d1', '2023-12-01', '2024-01-05', 558.00, 'Dinero en Efectivo'),
('a7c0e501-5c57-45d4-bf25-9f72dcbaf91c', '2025-07-10', '2025-07-18', 124.00, 'Tarjeta de Crédito'),
('c4e6a87d-3b7f-4c59-890d-7f376e3db43e', '2024-08-15', '2024-08-20', 77.50, 'Tarjeta de Débito');

--
-- Table structure for table `huespedes`
--

DROP TABLE IF EXISTS `guests`;

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


--
-- Dumping data for table `huespedes`
--

INSERT INTO `guests`
(`name`, `surname`, `birthdate`, `nationality`, `phone`, `booking_id`)
 VALUES 
('Jhon','Walter','1990-08-31','American','11-55-88-99','4798a389-132c-4c79-aa67-8569470dcf4a'),
('Maria', 'Garcia Hdz', '1985-05-15', 'Spanish', '22-33-44-55', '5e6f8b52-87d2-4aef-bb39-f79a1c21c2d1'),
('Alex', 'Johnson', '1992-11-22', 'Canadian', '99-88-77-66', 'a7c0e501-5c57-45d4-bf25-9f72dcbaf91c'),
('Sophie', 'Zavala Gutierrez', '1988-03-10', 'French', '33-22-11-00', 'c4e6a87d-3b7f-4c59-890d-7f376e3db43e');

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(25) NOT NULL,
  `user_category` varchar(25) NOT NULL,
  `password` varchar(64) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--


INSERT INTO `users` 
(`user_name`, `user_category`, `password`)
VALUES 
('Martin_C','Manager','03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4'),
('JoseO','Receptionist','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3'),
('Maria','Receptionist','9a620c1ea7e2ba81544537bdb41d53457e281a1887f86b4ac527bbff0b8db0fa'),
('alura1234','Manager','03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4');


-- Dump completed on 2023
