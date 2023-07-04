<?php
require_once("../config/db.php");
include_once("../config/json-header.php");
$pdo = getconnect();

function editAccount() {
    if (!empty($_POST['Telcompte']) && !empty($_POST['role']) && $_POST['role'] == 'user') {
        updateUsertoAdmin($_POST['Telcompte']);
    } elseif (!empty($_POST['Telcompte']) && !empty($_POST['role']) && $_POST['role'] == 'admin') {
        updateAdmintoUser($_POST['Telcompte']);
    } else {
        $response["success"] = false;
        $response["message"] = "Numéro de téléphone ou rôle manquants";
        echo json_encode($response);
    }
}

function updateUsertoAdmin($tel) {
    global $pdo;
    try {
        $requete = $pdo->prepare("UPDATE `compte` SET `role` = 'admin' WHERE `compte`.`Telcompte` = :val_tel;");
        $requete->bindParam(':val_tel', $tel);
        $requete->execute();
        $response["message"] = "Compte mis à jour";
        $response["success"] = true;
        echo json_encode($response);
    } catch (Exception $excep) {
        $response["success"] = false;
        $response["message"] = $excep->getMessage();
        echo json_encode($response);
    }
}

function updateAdmintoUser($tel) {
    global $pdo;
    try {
        $requete = $pdo->prepare("UPDATE `compte` SET `role` = 'user' WHERE `compte`.`Telcompte` = :val_tel;");
        $requete->bindParam(':val_tel', $tel);
        $requete->execute();
        $response["message"] = "Compte mis à jour";
        $response["success"] = true;
        echo json_encode($response);
    } catch (Exception $excep) {
        $response["success"] = false;
        $response["message"] = $excep->getMessage();
        echo json_encode($response);
    }
}

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    editAccount();
}
