-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : ven. 09 juin 2023 à 12:16
-- Version du serveur : 10.4.28-MariaDB
-- Version de PHP : 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `demodb`
--

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE `client` (
  `Idclient` int(11) NOT NULL,
  `Nomclient` varchar(300) NOT NULL,
  `Prenomclient` varchar(300) NOT NULL,
  `Sexeclient` varchar(2) NOT NULL,
  `Naissanceclient` varchar(15) NOT NULL,
  `Fachatclient` varchar(10) NOT NULL,
  `Statutclient` varchar(15) NOT NULL,
  `Villeclient` varchar(30) NOT NULL,
  `Quartierclient` varchar(30) NOT NULL,
  `Paysclient` varchar(30) NOT NULL,
  `Telcompte` varchar(15) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `compte`
--

CREATE TABLE `compte` (
  `Idcompte` int(11) NOT NULL,
  `Telcompte` varchar(15) NOT NULL,
  `Mdpcompte` varchar(255) NOT NULL,
  `role` varchar(15) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `compte`
--

INSERT INTO `compte` (`Idcompte`, `Telcompte`, `Mdpcompte`, `role`) VALUES
(1, '8888', '575a73fbec3ee66e0911aef2de9f74f05c6a979e', 'admin');

-- --------------------------------------------------------

--
-- Structure de la table `discussion`
--

CREATE TABLE `discussion` (
  `Iddiscussion` int(11) NOT NULL,
  `Ticket` varchar(11) DEFAULT NULL,
  `QuestionClient` varchar(255) DEFAULT NULL,
  `ReponseAdmin` varchar(255) DEFAULT NULL,
  `TelClient` varchar(15) DEFAULT NULL,
  `Datequestion` date DEFAULT NULL,
  `Datereponse` date DEFAULT NULL,
  `Statudiscussion` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `discussion`
--

INSERT INTO `discussion` (`Iddiscussion`, `Ticket`, `QuestionClient`, `ReponseAdmin`, `TelClient`, `Datequestion`, `Datereponse`, `Statudiscussion`) VALUES
(1, '7888', 'Que faire d e ma vie', 'en attente', '99516128', '2023-06-09', NULL, 1);

-- --------------------------------------------------------

--
-- Structure de la table `notification`
--

CREATE TABLE `notification` (
  `Idnotif` int(11) NOT NULL,
  `Contentnotif` varchar(200) NOT NULL,
  `Datenotif` date NOT NULL,
  `auteur` varchar(30) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `notification`
--

INSERT INTO `notification` (`Idnotif`, `Contentnotif`, `Datenotif`, `auteur`) VALUES
(1, 'jour de feted', '2023-06-09', 'admin');

-- --------------------------------------------------------

--
-- Structure de la table `question`
--

CREATE TABLE `question` (
  `idquestion` int(11) NOT NULL,
  `contenuquestion` varchar(50) NOT NULL,
  `datequestion` date NOT NULL,
  `auteurquestion` varchar(15) NOT NULL,
  `datesoumission` date NOT NULL,
  `active` int(1) NOT NULL,
  `Ticket` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`Idclient`);

--
-- Index pour la table `compte`
--
ALTER TABLE `compte`
  ADD PRIMARY KEY (`Idcompte`);

--
-- Index pour la table `discussion`
--
ALTER TABLE `discussion`
  ADD PRIMARY KEY (`Iddiscussion`);

--
-- Index pour la table `notification`
--
ALTER TABLE `notification`
  ADD PRIMARY KEY (`Idnotif`);

--
-- Index pour la table `question`
--
ALTER TABLE `question`
  ADD PRIMARY KEY (`idquestion`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `client`
--
ALTER TABLE `client`
  MODIFY `Idclient` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `compte`
--
ALTER TABLE `compte`
  MODIFY `Idcompte` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `discussion`
--
ALTER TABLE `discussion`
  MODIFY `Iddiscussion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `notification`
--
ALTER TABLE `notification`
  MODIFY `Idnotif` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `question`
--
ALTER TABLE `question`
  MODIFY `idquestion` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
