-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Wersja serwera:               10.4.24-MariaDB - mariadb.org binary distribution
-- Serwer OS:                    Win64
-- HeidiSQL Wersja:              12.0.0.6468
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Zrzut struktury bazy danych psw
CREATE DATABASE IF NOT EXISTS `psw` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `psw`;

-- Zrzut struktury tabela psw.logowanie
CREATE TABLE IF NOT EXISTS `logowanie` (
  `id_konta` int(10) NOT NULL AUTO_INCREMENT,
  `imie` varchar(50) DEFAULT NULL,
  `login` varchar(50) DEFAULT NULL,
  `nazwisko` varchar(50) DEFAULT NULL,
  `haslo` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `uprawnienia` varchar(20) DEFAULT NULL,
  `data_rejestracji` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`id_konta`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4;

-- Zrzucanie danych dla tabeli psw.logowanie: ~9 rows (około)
INSERT INTO `logowanie` (`id_konta`, `imie`, `login`, `nazwisko`, `haslo`, `email`, `uprawnienia`, `data_rejestracji`) VALUES
	(2, 'Ula', 'ulastrach', 'Strach', 'ula123', 'ulastrach@wp.pl', 'user', '2023-04-19 22:19:10'),
	(3, 'Helena', 'helenapoliczek', 'Policzek', 'helena123', 'helenapoliczek@wp.pl', 'user', '2023-04-19 22:19:32'),
	(5, 'Gaweł', 'admin5', 'Fragment', 'password5', 'gawelfragment@gmial.com', 'admin', '2023-04-19 22:21:06'),
	(6, 'Kinga', 'kingaksiazka', 'Książka', 'kinga123', 'kingaksiazka@onet.pl', 'user', '2023-04-19 22:22:07'),
	(8, 'Damian', 'damianoko', 'Oko', 'damian123', 'damian@wp.pl', 'user', '2023-04-19 22:23:09'),
	(11, 'a', 'a', 'a', 'a', 'a@gmail.com', 'admin', '2023-04-19 22:23:48'),
	(12, 'u', 'u', 'u', 'u', 'u@gmail.com', 'user', '2023-04-19 22:24:09'),
	(14, 'q', 'q', 'q', 'q', 'q@wp.pl', 'user', '2023-04-19 22:24:26');

-- Zrzut struktury tabela psw.wydarzenia
CREATE TABLE IF NOT EXISTS `wydarzenia` (
  `id_wydarzenia` int(10) NOT NULL AUTO_INCREMENT,
  `nazwa_wydarzenia` varchar(300) NOT NULL DEFAULT 'brak_nazwy',
  `agenda` varchar(300) NOT NULL DEFAULT 'brak_agendy',
  `termin_wydarzenia` date NOT NULL,
  PRIMARY KEY (`id_wydarzenia`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- Zrzucanie danych dla tabeli psw.wydarzenia: ~3 rows (około)
INSERT INTO `wydarzenia` (`id_wydarzenia`, `nazwa_wydarzenia`, `agenda`, `termin_wydarzenia`) VALUES
	(1, 'Konferencja naukowa "Postępy w technologiach infor', 'Prezentacje i dyskusje naukowe z zakresu nowych technologii informatycznych', '2025-11-22'),
	(2, 'Festiwal muzyki elektronicznej "Electric Dreams"', 'Koncerty i występy artystów muzyki elektronicznej, prezentacje sprzętu muzycznego', '2024-10-01'),
	(4, 'Konferencja biznesowa "Nowe trendy w marketingu"', 'Prezentacje i dyskusje naukowe na temat nowych trendów w marketingu, networking biznesowy', '2023-08-13');

-- Zrzut struktury tabela psw.zapisy
CREATE TABLE IF NOT EXISTS `zapisy` (
  `id_konta` int(10) NOT NULL,
  `id_wydarzenia` int(10) NOT NULL,
  `typ_uczestnictwa` varchar(50) NOT NULL,
  `wyzywienie` varchar(50) NOT NULL,
  `status_zapisu` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_konta`,`id_wydarzenia`),
  KEY `FK_zapisy_wydarzenia` (`id_wydarzenia`),
  CONSTRAINT `FK_zapisy_logowanie` FOREIGN KEY (`id_konta`) REFERENCES `logowanie` (`id_konta`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_zapisy_wydarzenia` FOREIGN KEY (`id_wydarzenia`) REFERENCES `wydarzenia` (`id_wydarzenia`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Zrzucanie danych dla tabeli psw.zapisy: ~6 rows (około)
INSERT INTO `zapisy` (`id_konta`, `id_wydarzenia`, `typ_uczestnictwa`, `wyzywienie`, `status_zapisu`) VALUES
	(2, 4, 'Autor', 'Bezglutenowe', 'potwierdzam'),
	(3, 2, 'Słuchacz', 'Wegetarianskie', 'potwierdzam'),
	(3, 4, 'Organizator', 'Bez preferencji', 'potwierdzam'),
	(6, 1, 'Autor', 'Wegetarianskie', 'potwierdzam'),
	(8, 1, 'Sponsor', 'Bez preferencji', 'potwierdzam'),
	(8, 4, 'Organizator', 'Bezglutenowe', 'potwierdzam');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
