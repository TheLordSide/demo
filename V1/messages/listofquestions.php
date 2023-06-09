<?php
require_once("../config/db.php");
include_once("../config/json-header.php");
$pdo = getconnect();
function listofquestion()
{
    global $pdo;
    try {
        $query = $pdo->prepare("SELECT * FROM discussion where ReponseAdmin = 'en attente'");
        $query->execute();
        $response["success"] = true;
        $response["total"] = $query->rowCount();
        $response["liste"] = $query->fetchAll(PDO::FETCH_ASSOC);
        echo json_encode($response);
    } catch (PDOException $error) {
        // Gérer les erreurs ici
        echo json_encode($error->getMessage());
    }
}


if ($_SERVER['REQUEST_METHOD']=='GET'){
    listofquestion();
}

