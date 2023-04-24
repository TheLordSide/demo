<?php
require_once "../config/db.php";
include_once "../config/json-header.php";

$pdo = getconnect();

function createnotification($Contentnotif, $auteur)
{
    global $pdo;

    // Préparer la requête d'insertion
    $query = $pdo->prepare("INSERT INTO notification (Contentnotif, Datenotif,auteur) VALUES (:message, NOW(), :auteur)");

    // Assigner les valeurs des paramètres
    $query->bindParam(':message', $Contentnotif);
    $query->bindParam(':author', $auteur);

    // Exécuter la requête
    if ($query->execute()) {
        // La notification a été enregistrée avec succès
        return true;
    } else {
        // Il y a eu une erreur lors de l'enregistrement de la notification
        return false;
    }
}
