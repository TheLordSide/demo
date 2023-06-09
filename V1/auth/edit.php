<?php
require_once("../config/db.php");
include_once("../config/json-header.php");
$pdo = getconnect();

function editAccount(){
    if (!empty($_POST['Telcompte'])){
       
        update($_POST['Telcompte']);
    }
    else {
       $response["success"]= false;
       $response["message"]="Numéro de téléphone ou mot de passe manquants";
       echo json_encode($response);
    }

}

function update($tel){
    global $pdo;
    try {
    $requete = $pdo->prepare("UPDATE `compte` SET `role` = 'admin' WHERE `compte`.`Telcompte` =:val_tel;");
    $requete->bindParam('val_tel', $tel);
    $requete->execute();
    $response["message"]="Compte mis à jour";
    $response["success"]=false;
    echo json_encode($response);
    }   catch (Exception $excep) {
    $response["success"] = false;
    $response["message"] = $excep->getMessage();
    echo json_encode($response);
}
}


if ($_SERVER['REQUEST_METHOD']=='POST'){
    editAccount();
}