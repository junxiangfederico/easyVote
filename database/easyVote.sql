-- -------------------------------------------------------------
-- TablePlus 4.6.6(422)
--
-- https://tableplus.com/
--
-- Database: easyVote
-- Generation Time: 2022-06-15 10:51:02.7300
-- -------------------------------------------------------------


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


DROP TABLE IF EXISTS `session`;
CREATE TABLE `session` (
  `id` int NOT NULL AUTO_INCREMENT,
  `text` text,
  `candidati` json DEFAULT NULL,
  `isOpen` tinyint(1) NOT NULL DEFAULT '1',
  `type` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `iduser` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `lastname` varchar(50) NOT NULL,
  `birthdate` date NOT NULL,
  `birthplace` varchar(50) NOT NULL,
  `codicefiscale` char(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'UNIQUE',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'UNIQUE',
  `password` varchar(255) NOT NULL,
  `isadmin` tinyint(1) NOT NULL,
  PRIMARY KEY (`iduser`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `voto`;
CREATE TABLE `voto` (
  `idSession` int NOT NULL,
  `idUser` int NOT NULL,
  `selection` json NOT NULL,
  PRIMARY KEY (`idUser`,`idSession`),
  UNIQUE KEY `singoloVoto` (`idSession`,`idUser`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `session` (`id`, `text`, `candidati`, `isOpen`, `type`) VALUES
(78, '', '{\"candidato1\": \"fede\", \"candidato2\": \"angelo\", \"candidato3\": \"alessia\", \"candidato4\": \"amelia\", \"candidato5\": \"christian\"}', 1, 'Ordinale'),
(79, 'National Elections', '{\"candidato1\": \"Fede\", \"candidato2\": \"Angelo\", \"candidato3\": \"Sofia\"}', 1, 'Categorico'),
(80, 'Elezione nazionale', '{\"candidato1\": \"Carlo\", \"candidato2\": \"Alberto\", \"candidato3\": \"Marco\"}', 1, 'Ordinale'),
(81, 'Elezioni europee', '{\"candidato1\": \"Angelo\", \"candidato2\": \"Micheal\"}', 1, 'Ordinale'),
(82, 'Giuridice', NULL, 1, 'Referendum');

INSERT INTO `users` (`iduser`, `name`, `lastname`, `birthdate`, `birthplace`, `codicefiscale`, `username`, `password`, `isadmin`) VALUES
(19, 'federico', 'zhou', '2022-03-17', 'itayl', 'zhsjandmsjxkzldm', 'blabla', 'blabla', 0),
(20, 'fsdafasd', 'ffasd', '2022-03-10', 'fasd', 'FDSASDFGHJKLKJHG', 'fsdafas', '600bd0c82e706574a870de4c0f8e58f0a538b6c97a2b07f2469c606d117d9781', 0),
(21, 'Alessia', 'Pena', '2022-03-16', 'america', 'AMSKDLFMENSKALSM', 'ale', '7ba0ea6b73e7f545df5981ea7eb6b7125052d108e88933cea431a27b4128164c', 0),
(24, 'federico', 'blabla', '2022-05-02', 'ita', '1234567890123456', 'bloblo', '314bd8632af57e977fa1b76e43b03c2b1d03b2a92b9895c2b93d66247d6fb508', 1),
(25, 'ffdsdas', 'fsdafdsa', '2022-06-08', 'fadsfads', '1234567890123456', 'bleble', '708b1a7a6c6507b55ab537894493b61d46230ceeb21b605838a050a75c7ef0bb', 0),
(26, 'fdsafads', 'fsadfdas', '2022-06-01', 'fsdafdsa', '1234567890123456', 'admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 0);

INSERT INTO `voto` (`idSession`, `idUser`, `selection`) VALUES
(78, 25, '{\"selection\": \"angelo; fede; alessia; amelia\"}');



/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;