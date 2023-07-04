<?php
require_once("../config/db.php");
include_once("../config/json-header.php");
$pdo = getconnect();

function chathistory()
{
    global $pdo;
    try {
        if (isset($_GET['TelClient']) && isset($_GET['Ticket'])) { // Ajout de la vérification du paramètre 'Ticket'
            $query = $pdo->prepare("SELECT * FROM discussion WHERE TelClient = :val_tel and Ticket = :val_ticket ");
            $query->bindParam(':val_tel', $_GET['TelClient']);
            $query->bindParam(':val_ticket', $_GET['Ticket']); // Correction du bindParam pour le paramètre 'Ticket'
            $query->execute();
            $response["success"] = true;
            $response["total"] = $query->rowCount();
            $response["liste"] = $query->fetchAll(PDO::FETCH_ASSOC);
            echo json_encode($response);
        } else {
            echo "Telcompte or Ticket parameter is missing."; // Mise à jour du message d'erreur pour indiquer quel(s) paramètre(s) manque(nt)
        }
    } catch (PDOException $error) {
        // Gérer les erreurs ici
        echo json_encode($error->getMessage());
    }
}

chathistory();
