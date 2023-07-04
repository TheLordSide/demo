<?php
require_once("../config/db.php");
include_once("../config/json-header.php");
$pdo = getconnect();

function history()
{
    global $pdo;
    try {
        if (isset($_GET['Telcompte'])) {
            $query = $pdo->prepare("SELECT * FROM discussion WHERE TelClient = :val_tel group by Ticket");
            $query->bindParam(':val_tel', $_GET['Telcompte']);
            $query->execute();
            $response["success"] = true;
            $response["total"] = $query->rowCount();
            $response["liste"] = $query->fetchAll(PDO::FETCH_ASSOC);
            echo json_encode($response);
        } else {
            echo "Telcompte parameter is missing.";
        }
    } catch (PDOException $error) {
        // Gérer les erreurs ici
        echo json_encode($error->getMessage());
    }
}

history();
