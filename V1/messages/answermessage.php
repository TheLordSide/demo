<?php
require_once("../config/db.php");
include_once("../config/json-header.php");
$pdo = getconnect();


function sendanswer()
{
    global $pdo;
     


    try {
        $update = $pdo->prepare("UPDATE `discussion` SET `ReponseAdmin` = :val_reponse, `Datereponse` = NOW()  
            WHERE `Iddiscussion` = (SELECT `Iddiscussion` FROM `discussion` WHERE `Ticket` = :val_ticket AND `TelClient` = :val_tel AND `ReponseAdmin` = 'en attente' 
            ORDER BY `Iddiscussion` DESC LIMIT 1)");
        $update->bindParam(':val_reponse', $_POST['ReponseAdmin']);
        $update->bindParam(':val_ticket', $_POST['Ticket']);
        $update->bindParam(':val_tel', $_POST['TelClient']);
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

