-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 17, 2020 at 03:25 PM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.4.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `buecherverwaltung`
--

-- --------------------------------------------------------

--
-- Table structure for table `autoren`
--

CREATE TABLE `autoren` (
  `aid` int(11) NOT NULL,
  `anamen` varchar(60) COLLATE latin1_german1_ci NOT NULL,
  `ageburtsdatum` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_german1_ci;

--
-- Dumping data for table `autoren`
--

INSERT INTO `autoren` (`aid`, `anamen`, `ageburtsdatum`) VALUES
(1, 'Kofler Michael', '1979-10-01'),
(2, 'Kramer David', '1962-10-23'),
(3, 'Orfali Robert', '1954-03-05'),
(4, 'Harkey Dan', '1975-07-26'),
(5, 'Edwards Jeri', '1986-03-21'),
(6, 'Ratschiller Tobias', '1952-07-09'),
(7, 'Gerken Till', '1973-03-06'),
(12, 'Yarger Randy Jay', '1977-12-01'),
(13, 'Reese Georg', '1973-09-10'),
(14, 'King Tim', '1965-07-17'),
(15, 'Date Chris', '1983-09-25'),
(16, 'Darween Hugh', '1955-06-23'),
(17, 'Krause Jörg', '1951-10-02'),
(19, 'Pohl Peter', '1983-12-06'),
(20, 'Kopka Helmut', '1980-11-27'),
(21, 'Komma Michael', '1985-12-09'),
(22, 'Bitsch Gerhard', '1977-12-23'),
(23, 'Holz Helmut', '1980-02-03'),
(24, 'Schmitt Bernd', '1959-04-02'),
(25, 'Tikart Andreas', '1951-11-03'),
(26, 'Garfinkel Simon', '1966-06-07'),
(30, 'DuBois Paul', '1986-09-18'),
(37, 'Theodor Kallifatides', '1976-07-12'),
(38, 'Goosens Michael', '1961-10-17'),
(39, 'Rahtz Sebastian', '1953-08-13'),
(47, 'Pollack Martin', '1954-09-28'),
(48, 'Gilmore W.J.', '1968-12-10'),
(51, 'Wellington Luke', '1968-07-16'),
(52, 'Thomson Laura', '1984-11-16'),
(53, 'Monjiam Bruce', '1980-08-21'),
(55, 'Mankell Henning', '1980-10-28'),
(56, 'Krüger Guido', '1979-04-20'),
(57, 'Knausgård Karl Ove', '1970-03-16'),
(58, 'Suter Martin', '1974-04-02'),
(60, 'Öggl Bernd', '1967-11-05'),
(62, 'Asimov Isaac', '1955-09-11'),
(64, 'Laborenz Kai', '1950-10-04'),
(65, 'Wolfgarten Sebastian', '1958-07-16'),
(66, 'Atwood Margaret', '1976-09-13'),
(67, 'Bear Greg', '1984-06-20'),
(68, 'Coetzee J. M.', '1983-02-09'),
(69, 'Gardell Jonas', '1967-09-18'),
(70, 'Ibsen Henrik', '1956-10-27'),
(71, 'Johnson Eyvind', '1951-05-26'),
(73, 'Nesser Håkan', '1952-02-12'),
(74, 'Riel Joern', '1976-04-04'),
(75, 'Söderberg Hjalmar', '1954-02-17'),
(76, 'Saramago Jose', '1975-08-17'),
(77, 'van Heijden Adrianus Fr. Th.', '1959-12-23'),
(78, 'Hauser Tobias', '1969-02-17'),
(81, 'Lendecke Volker', '1975-03-22'),
(82, 'Eller Frank', '1955-01-08'),
(83, 'Schwichtenberg Holger', '1952-02-01'),
(86, 'Wall Larry', '1974-04-03'),
(87, 'Christiansen Tom', '1968-09-13'),
(88, 'Orwant Jon', '1976-04-21'),
(89, 'Gräbe Hans-Gert', '1971-02-14');

-- --------------------------------------------------------

--
-- Table structure for table `kategorien`
--

CREATE TABLE `kategorien` (
  `Kategoriename` varchar(60) COLLATE latin1_german1_ci NOT NULL,
  `Hauptkategorienummer` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_german1_ci;

-- --------------------------------------------------------

--
-- Table structure for table `sprachen`
--

CREATE TABLE `sprachen` (
  `Sprachenname` varchar(60) COLLATE latin1_german1_ci NOT NULL,
  `Sprachnnummer` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_german1_ci;

--
-- Dumping data for table `sprachen`
--

INSERT INTO `sprachen` (`Sprachenname`, `Sprachnnummer`) VALUES
('englisch', 1),
('deutsch', 2),
('svensk', 3),
('norsk', 6);

-- --------------------------------------------------------

--
-- Table structure for table `verlage`
--

CREATE TABLE `verlage` (
  `Verlagname` varchar(50) COLLATE latin1_german1_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_german1_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `autoren`
--
ALTER TABLE `autoren`
  ADD PRIMARY KEY (`aid`);

--
-- Indexes for table `sprachen`
--
ALTER TABLE `sprachen`
  ADD PRIMARY KEY (`Sprachnnummer`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `autoren`
--
ALTER TABLE `autoren`
  MODIFY `aid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=90;

--
-- AUTO_INCREMENT for table `sprachen`
--
ALTER TABLE `sprachen`
  MODIFY `Sprachnnummer` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
