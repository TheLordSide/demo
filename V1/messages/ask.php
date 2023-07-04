<?php
require_once("../config/db.php");
include_once("../config/json-header.php");
$pdo = getconnect();


function sendmessage()
{
    global $pdo;
    try {
        $Ticket = generateTicketDiscussion();
        $insertion = $pdo->prepare("INSERT INTO `discussion` ( `Ticket`, `QuestionClient`, `ReponseAdmin`, 
        `TelClient`, `Datequestion`, `Datereponse`, `Statudiscussion`) 
        VALUES ( :val_ticket, :val_QuestionClient,'en attente', :val_TelClient, Now(), NULL, 0); ");
        $insertion->bindParam('val_ticket', $Ticket);
        $insertion->bindParam('val_QuestionClient', $_POST['QuestionClient']);
        $insertion->bindParam('val_TelClient', $_POST['TelClient']);
        $insertion->execute();

        saveticket($Ticket,$_POST['TelClient']);
        $response["success"] = true;
        $response["message"] = "La question a été correctement soumise";
        echo json_encode($response);
    } catch (Exception $excep) {
        $response["success"] = false;
        $response["message"] = $excep->getMessage();
        echo json_encode($response);
    }
}

function generateTicketDiscussion(){
    $Ticket = rand(100000000, 999999999);
    return $Ticket;
}

function saveticket($ticket,$tel){
    try {
        global $pdo;
        $insertion = $pdo->prepare("INSERT INTO `ticketgenereted` ( `Ticket`, `TelClient`, `Date`) 
        VALUES ( :val_ticket, :val_TelClient, Now()); ");
        $insertion->bindParam('val_ticket', $ticket);
        $insertion->bindParam('val_TelClient',$tel);
        $insertion->execute();
    } catch (Exception $excep) {
        $response["success"] = false;
        $response["message"] = $excep->getMessage();
        echo json_encode($response);
    }


}


if ($_SERVER['REQUEST_METHOD']=='POST'){
    sendmessage();
}

