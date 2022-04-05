-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 09. Jul 2020 um 12:26
-- Server-Version: 10.1.28-MariaDB
-- PHP-Version: 7.1.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `movie-db`
--
CREATE DATABASE IF NOT EXISTS `movie-db` DEFAULT CHARACTER SET latin1 COLLATE latin1_german1_ci;
USE `movie-db`;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `movies`
--

CREATE TABLE `movies` (
  `id` int(11) NOT NULL,
  `title` varchar(200) COLLATE latin1_german1_ci NOT NULL,
  `year` int(11) NOT NULL,
  `published` tinyint(1) DEFAULT '0',
  `owner` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_german1_ci;

--
-- Daten für Tabelle `movies`
--

INSERT INTO `movies` (`id`, `title`, `year`, `published`, `owner`) VALUES
(1, 'Iron Man', 2008, 1, 1),
(2, 'Thor', 2011, 1, 2),
(3, 'Capitain America', 2001, 0, 1);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(100) COLLATE latin1_german1_ci NOT NULL,
  `passwordhash` varchar(64) COLLATE latin1_german1_ci NOT NULL,
  `firstname` varchar(200) COLLATE latin1_german1_ci NOT NULL,
  `secondname` varchar(200) COLLATE latin1_german1_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_german1_ci;

--
-- Daten für Tabelle `users`
--

INSERT INTO `users` (`id`, `username`, `passwordhash`, `firstname`, `secondname`) VALUES
(1, 'sepp', 'e4cb2fdd444063e20e9a4ba740256ee023316e86db290174b74cd64491f547e9', 'Sepp', 'Hintner'),
(2, 'resi', 'b1ba04c9fb199173c6eab83d67f68490cabec07148c60650a2c53f43c36fd72a', 'Resi', 'Rettich'),
(3, 'rudi', '802a32cf806c5c8586c70b3b9e5aa2198b7f4ab0b8259d7ce4fe782a468be335', 'Rudi', 'Rüpel');

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `movies`
--
ALTER TABLE `movies`
  ADD PRIMARY KEY (`id`),
  ADD KEY `owner` (`owner`);

--
-- Indizes für die Tabelle `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `movies`
--
ALTER TABLE `movies`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT für Tabelle `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `movies`
--
ALTER TABLE `movies`
  ADD CONSTRAINT `movies_ibfk_1` FOREIGN KEY (`owner`) REFERENCES `users` (`id`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
