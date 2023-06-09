<?php
require_once("../config/db.php");
include_once("../config/json-header.php");
$pdo = getconnect();
function ajouter()
{
    if (!empty($_POST['Nomclient']) || !empty($_POST['Prenomclient'])) {
        userexist(
            $_POST['Nomclient'],
            $_POST['Prenomclient'],
            $_POST['Sexeclient'],
            $_POST['Naissanceclient'],
            $_POST['Fachatclient'],
            $_POST['Villeclient'],
            $_POST['Quartierclient'],
            $_POST['Paysclient'],
            $_POST["Telcompte"]
        );
    } else {
        $response["success"] = false;
        $response["message"] = "Nom du client ou Prenom manquants";
        echo json_encode($response);
    }
}
function userexist($nom, $pren, $sexe, $naiss, $fachat, $ville, $qtier, $pays, $tel)
{
    global $pdo;
    $requete = $pdo->prepare("SELECT * from client where Telcompte=:val_tel");
    $requete->bindParam('val_tel', $tel);
    $requete->execute();
    if ($requete->rowcount()) {
        $response["message"] = "Impossible de continuer car ce numero client existe déjà";
        $response["success"] = false;
        echo json_encode($response);
    } else {
        try {
            $insertion = $pdo->prepare("INSERT INTO `client` (`Nomclient`, `Prenomclient`, `Sexeclient`, 
            `Naissanceclient`, `Fachatclient`, `Statutclient`, `Villeclient`, `Quartierclient`, `Paysclient`,`Telcompte`) 
            VALUES (:val_nom, :val_pren, :val_sexe, :val_naiss, 
            :val_fachat, 'actif', :val_ville , :val_qtier, :val_pays, :val_tel);");
            $insertion->bindParam('val_nom', $nom);
            $insertion->bindParam('val_pren', $pren);
            $insertion->bindParam('val_sexe', $sexe);
            $insertion->bindParam('val_naiss', $naiss);
            $insertion->bindParam('val_fachat', $fachat);
            $insertion->bindParam('val_ville', $ville);
            $insertion->bindParam('val_qtier', $qtier);
            $insertion->bindParam('val_pays', $pays);
            $insertion->bindParam('val_tel', $tel);
            $insertion->execute();
            $response["success"] = true;
            $response["message"] = "Le client est enregistre";
            echo json_encode($response);
        } catch (Exception $excep) {
            $response["success"] = false;
            $response["message"] = $excep;
            echo json_encode($response);
        }
    }
}

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    ajouter();
}
