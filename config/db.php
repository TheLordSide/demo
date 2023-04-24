<?php
include("../config/json-header.php");
require_once('../vendor/autoload.php');
//chargement du Dotenv
$dotenv = Dotenv\Dotenv::createImmutable(dirname(__DIR__));
$dotenv->load();

function getConnect()
{
    try {
        //informations de connexion DB
        $host = $_ENV['DB_HOST'];
        $user = $_ENV['DB_USER'];
        $pass = $_ENV['DB_PASSWORD'];
        $db= $_ENV['DB_NAME'];
        $pdo = new PDO('mysql:host=' . $host . ';dbname='.$db , $user, $pass);
        $response["success"] = true;
        $response["message"] = "Connexion avec succes";
        $response["status"] = 200;
        //echo json_encode($response);
        return $pdo;
    } catch (Exception $execpt) {
        $response["success"] = false;
        $response["message"] = $execpt;
        echo json_encode($response);
    }
}