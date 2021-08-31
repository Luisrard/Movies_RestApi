-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 31, 2021 at 06:09 AM
-- Server version: 10.4.20-MariaDB
-- PHP Version: 8.0.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `movies`
--

-- --------------------------------------------------------

--
-- Table structure for table `movie`
--

CREATE TABLE `movie` (
  `id` mediumint(9) NOT NULL,
  `duration` mediumint(9) NOT NULL,
  `image` varchar(255) NOT NULL,
  `name` varchar(60) NOT NULL,
  `rating` enum('G','PG','PG_13','R','NC_17') NOT NULL DEFAULT 'G',
  `resume` varchar(255) NOT NULL,
  `score` float NOT NULL DEFAULT 0,
  `votes` mediumint(9) NOT NULL DEFAULT 0,
  `director` mediumint(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `movie`
--

INSERT INTO `movie` (`id`, `duration`, `image`, `name`, `rating`, `resume`, `score`, `votes`, `director`) VALUES
(1, 178, 'https://m.media-amazon.com/images/M/MV5BM2MyNjYxNmUtYTAwNi00MTYxLWJmNWYtYzZlODY3ZTk3OTFlXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_UY1200_CR107,0,630,1200_AL_.jpg', 'The godfather', 'NC_17', 'An organized crime dynasty\'s aging patriarch transfers control of his clandestine empire to his reluctant son. The Godfather \"Don\" Vito Corleone is the head of the Corleone mafia family in New York. ', 5, 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `person`
--

CREATE TABLE `person` (
  `id` mediumint(9) NOT NULL,
  `name` varchar(80) NOT NULL,
  `birth_date` date DEFAULT NULL,
  `nickname` varchar(60) DEFAULT NULL,
  `bio` varchar(400) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `person`
--

INSERT INTO `person` (`id`, `name`, `birth_date`, `nickname`, `bio`) VALUES
(1, 'Francis Ford Copola', '1939-04-07', 'Copola', 'Francis Ford Coppola is an American film director, producer, and screenwriter. He was a central figure in the New Hollywood filmmaking movement of the 1960s and 1970s, and is widely considered to be one of the greatest filmmakers of all time. His accolades include five Academy Awards, six Golden Globe Awards, two Palmes d\'Or, and a British Academy Film Award. ');

-- --------------------------------------------------------

--
-- Table structure for table `token`
--

CREATE TABLE `token` (
  `id` mediumint(9) NOT NULL,
  `token` varchar(100) NOT NULL,
  `created_at` datetime NOT NULL,
  `expired_at` datetime NOT NULL,
  `confirmed_at` datetime DEFAULT NULL,
  `user` mediumint(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` mediumint(9) NOT NULL,
  `mail` varchar(80) NOT NULL,
  `nickname` varchar(60) NOT NULL,
  `password` varchar(100) NOT NULL,
  `enabled` tinyint(4) NOT NULL,
  `locked` tinyint(4) NOT NULL,
  `role` enum('USER','ADMIN') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `mail`, `nickname`, `password`, `enabled`, `locked`, `role`) VALUES
(1, 'luis2002chavez2002@gmail.com', 'Luigi', '$2a$10$SwiJJCSgsJIr29QgWMp20uTg1XQzOmp7nt76t91XxAATtnmms9f3a', 1, 0, 'USER');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `movie`
--
ALTER TABLE `movie`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_ogko73h53na6cby69s2l8747a` (`name`),
  ADD KEY `fk1_movie_idx` (`director`);

--
-- Indexes for table `person`
--
ALTER TABLE `person`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name_UNIQUE` (`name`);

--
-- Indexes for table `token`
--
ALTER TABLE `token`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_1_token_idx` (`user`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `mail_UNIQUE` (`mail`),
  ADD UNIQUE KEY `nickname_UNIQUE` (`nickname`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `movie`
--
ALTER TABLE `movie`
  MODIFY `id` mediumint(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `person`
--
ALTER TABLE `person`
  MODIFY `id` mediumint(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` mediumint(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `movie`
--
ALTER TABLE `movie`
  ADD CONSTRAINT `fk1_movie` FOREIGN KEY (`director`) REFERENCES `person` (`id`);

--
-- Constraints for table `token`
--
ALTER TABLE `token`
  ADD CONSTRAINT `fk_1_token` FOREIGN KEY (`user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
