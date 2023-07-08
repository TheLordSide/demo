<?php
require_once("../config/db.php");
include_once("../config/json-header.php");
$pdo = getConnect();

function ajouter()
{
    if (!empty($_POST['nom']) || !empty($_POST['ville']) || !empty($_POST['quartier']) || !empty($_POST['tel'])) {
        centerExist(
            $_POST['nom'],
            $_POST['ville'],
            $_POST['quartier'],
            $_POST['tel']
        );
    } else {
        $response["success"] = false;
        $response["message"] = "Plusieurs valeurs manquantes";
        echo json_encode($response);
    }
}

function centerExist($nom, $ville, $qtier, $tel)
{
    global $pdo;
    $requete = $pdo->prepare("SELECT * FROM commerciaux WHERE tel = :val_tel AND nom = :val_nom");
    $requete->bindParam(':val_tel', $tel);
    $requete->bindParam(':val_nom', $nom);
    $requete->execute();
    
    if ($requete->rowCount()) {
        $response["message"] = "Impossible de continuer car ce numero agent existe déjà";
        $response["success"] = false;
        echo json_encode($response);
    } else {
        try {
            $insertion = $pdo->prepare("INSERT INTO commerciaux (nom, ville, quartier, tel) VALUES (:val_nom, :val_ville, :val_qtier, :val_tel)");
            $insertion->bindParam(':val_nom', $nom);
            $insertion->bindParam(':val_ville', $ville);
            $insertion->bindParam(':val_qtier', $qtier);
            $insertion->bindParam(':val_tel', $tel);
            $insertion->execute();
            $response["success"] = true;
            $response["message"] = "Le client est enregistré";
            echo json_encode($response);
        } catch (Exception $excep) {
            $response["success"] = false;
            $response["message"] = $excep->getMessage();
            echo json_encode($response);
        }
    }
}

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    ajouter();
}
