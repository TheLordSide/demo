<?php
require_once "../config/db.php";
include_once "../config/json-header.php";

$pdo = getconnect();

function ajouter()
{
    if (!empty($_POST['Contentnotif'])) {
        notification($_POST['Contentnotif'], $_POST['auteur']);
    }
    else {
        $response["success"]= false;
        $response["message"]="Vous devez entrez une notification";
        echo json_encode($response);
     }
}

function notification($Contentnotif, $auteur)
{
    global $pdo;
    try {
        $query = $pdo->prepare("INSERT INTO notification (Contentnotif, Datenotif,auteur) VALUES (:message, NOW(), :auteur)");
        $query->bindParam(':message', $Contentnotif);
        $query->bindParam(':auteur', $auteur);
        $query->execute();
        $response["success"] = true;
        $response["message"] = "notification envoyee";
        echo json_encode($response);
    } catch (Exception $excep) {
        $response["success"] = false;
        $response["message"] = $excep;
        echo json_encode($response);
    }
}
if ($_SERVER['REQUEST_METHOD']=='POST'){
    ajouter();
}