<?php
require_once("../config/db.php");
include_once("../config/json-header.php");
$pdo = getconnect();


function sendanswer()
{
    global $pdo;
    try {
        $update = $pdo->prepare("UPDATE `discussion` SET `ReponseAdmin` = :val_reponse, `Datereponse` = Now()  WHERE `Ticket` = :val_ticket and 
        `TelClient` = :val_tel and ReponseAdmin='en attente';");
        $update->bindParam('val_reponse', $_POST['ReponseAdmin']);
        $update->bindParam('val_ticket', $_POST['Ticket']);
        $update->bindParam('val_tel', $_POST['TelClient']);
        $update->execute();
        $response["success"] = true;
        $response["message"] = "La réponse a été envoyée";
        echo json_encode($response);
    } catch (Exception $excep) {
        $response["success"] = false;
        $response["message"] = $excep->getMessage();
        echo json_encode($response);
    }
}


if ($_SERVER['REQUEST_METHOD']=='POST'){
    sendanswer();
}

