<?php
include("../config/json-header.php");
require_once('../vendor/autoload.php');
// Chargement du Dotenv
$dotenv = Dotenv\Dotenv::createImmutable(dirname(__DIR__));
$dotenv->load();

function getConnect()
{
    try {
        // Informations de connexion à la base de données
        $host = $_ENV['DB_HOST'];
        $user = $_ENV['DB_USER'];
        $pass = $_ENV['DB_PASSWORD'];
        $db = $_ENV['DB_NAME'];
        
        $options = [
            PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION,
            PDO::ATTR_PERSISTENT => true // Définit la connexion persistante
        ];

        $pdo = new PDO('mysql:host=' . $host . ';dbname=' . $db , $user, $pass, $options);
        $isPersistent = $pdo->getAttribute(PDO::ATTR_PERSISTENT);
/*
        if ($isPersistent) {
            echo json_encode ("La connexion persistante est activée.");
        } else {
            echo json_encode ("La connexion persistante n'est pas activée.");
        }
 */       
        $response["success"] = true;
        $response["message"] = "Connexion réussie";
        $response["status"] = 200;



        //echo json_encode($response);
        return $pdo;
    } catch (Exception $except) {
        $response["success"] = false;
        $response["message"] = $except->getMessage();
        echo json_encode($response);
    }
}