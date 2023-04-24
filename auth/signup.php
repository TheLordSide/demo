<?php
require_once("../config/db.php");
include_once("../config/json-header.php");

$pdo=getconnect();

function enregistrer(){
    if (!empty($_POST['Telcompte']) || !empty($_POST['Mdpcompte'])){
       
        compteexite($_POST['Telcompte'],sha1($_POST['Mdpcompte']));
    }
    else {
       $response["success"]= false;
       $response["message"]="Numéro de téléphone ou mot de passe manquants";
       echo json_encode($response);
    }

}

function compteexite($tel,$pass){
    global $pdo;
    $requete = $pdo->prepare("SELECT * from compte where Telcompte =:val_tel");
    $requete->bindParam('val_tel', $tel);
    $requete->execute();
    if ($requete->rowcount()) {
        $response["message"]="Impossible de continuer car le compte existe déjà";
        $response["success"]=false;
        echo json_encode($response);
    } else {
        try {
            $insertion= $pdo->prepare("INSERT INTO `compte` (`Telcompte`, `Mdpcompte`,`role`) VALUES ( :val_tel, :val_mdp, 'user');");
            $insertion->bindParam('val_tel', $tel);
            $insertion->bindParam('val_mdp', $pass);
            $insertion->execute();
            $response["success"] = true;
            $response["message"] = "Le compte a été créé";
            echo json_encode($response);
        } catch (Exception $excep) {
            $response["success"] = false;
            $response["message"] = $excep;
            echo json_encode($response);
        }

    }
}


if ($_SERVER['REQUEST_METHOD']=='POST'){
    enregistrer();
}