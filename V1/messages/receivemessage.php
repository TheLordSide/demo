<?php
require_once("../config/db.php");
include_once("../config/json-header.php");
$pdo = getconnect();


try {
    // $Ticket = generateTicketDiscussion();
     $insertion = $pdo->prepare("INSERT INTO `discussion` ( `Ticket`, `QuestionClient`, `ReponseAdmin`, 
     `TelClient`, `Datequestion`, `Datereponse`, `Statudiscussion`) 
     VALUES ( :val_ticket, NULL,:val_reponse, :val_TelClient, NULL, Now(), 0); ");
     $insertion->bindParam('val_ticket', $_POST['Ticket']);
     $insertion->bindParam('val_reponse', $_POST['ReponseAdmin']);
     $insertion->bindParam('val_TelClient', $_POST['TelClient']);
     $insertion->execute();
     $response["success"] = true;
     $response["message"] = "Message envoyé";
     echo json_encode($response);
 } catch (Exception $excep) {
     $response["success"] = false;
     $response["message"] = $excep->getMessage();
     echo json_encode($response);
 }


    