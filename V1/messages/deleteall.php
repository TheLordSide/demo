<?php
require_once "../config/db.php";
include_once "../config/json-header.php";

$pdo = getconnect();

function delete()
{
    global $pdo;
    $requete = $pdo->prepare("select * from discussion");
    $requete->execute();
    if ($requete->rowcount()) {
        $delete = $pdo->prepare("Delete from discussion");
        $delete->execute();
        $response["message"] = "Suppression réussie ";
        $response["success"] = true;
        echo json_encode($response);
    } 
    else {
        $response["message"] = "Impossible de continuer car aucune discussion présente";
        $response["success"] = true;
        echo json_encode($response);
    }
}

if ($_SERVER['REQUEST_METHOD']=='DELETE'){
    delete();
}