-- phpMyAdmin SQL Dump
-- version 4.7.1
-- https://www.phpmyadmin.net/
--
-- Host: sql10.freemysqlhosting.net
-- Generation Time: May 31, 2020 at 10:43 PM
-- Server version: 5.5.62-0ubuntu0.14.04.1
-- PHP Version: 7.0.33-0ubuntu0.16.04.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sql10344429`
--
CREATE DATABASE IF NOT EXISTS `sql10344429` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `sql10344429`;

-- --------------------------------------------------------

--
-- Table structure for table `Datos_ducha`
--

CREATE TABLE `Datos_ducha` (
  `Fecha` varchar(20) NOT NULL,
  `Gasto` double NOT NULL,
  `Tiempo` varchar(20) NOT NULL,
  `Costo` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Datos_ducha`
--

INSERT INTO `Datos_ducha` (`Fecha`, `Gasto`, `Tiempo`, `Costo`) VALUES
('2020-05-22 23:22', 8.38256, '00:00:25', 26),
('2020-05-22 23:34', 21.96437, '00:01:13', 67);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Datos_ducha`
--
ALTER TABLE `Datos_ducha`
  ADD PRIMARY KEY (`Fecha`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
