<?php
require_once "../config/db.php";
include_once "../config/json-header.php";

$pdo = getconnect();

function delete()
{
    global $pdo;
    $requete = $pdo->prepare("select * from notification");
    $requete->execute();
    if ($requete->rowcount()) {
        $delete = $pdo->prepare("Delete from notification");
        $delete->execute();
        $response["message"] = "Suppression réussie ";
        $response["success"] = true;
        echo json_encode($response);
    } 
    else {
        $response["message"] = "Impossible de continuer car aucune notification enregistrée";
        $response["success"] = true;
        echo json_encode($response);
    }
}

if ($_SERVER['REQUEST_METHOD']=='DELETE'){
    delete();
}