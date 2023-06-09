<?php
require_once("../config/db.php");
include_once("../config/json-header.php");

$pdo=getconnect();

#fonction de connexion utlisateur



function seconnecter(){

    global $pdo;

    if (!empty($_POST['Telcompte']) && !empty($_POST['Mdpcompte'])){
        try{
            global $pdo;
            $passhassed = sha1($_POST['Mdpcompte']);
            $requete = $pdo->prepare("SELECT role FROM compte where Telcompte =:valeur1 
            and Mdpcompte =:valeur2;");
            $requete->bindParam(':valeur1', $_POST['Telcompte']);
            $requete->bindParam(':valeur2', $passhassed);
            $requete->execute();
            $data= $requete->fetch();
            if ($requete->rowcount()) {
                session_start();
                $_SESSION["Telcompte"] = $_POST['Telcompte'];
                $_SESSION["role"] = $data['role'];
                $response["success"] = true;
                $response["message"] = MESSAGE_AUTH_SUCCESS;
                $response["tel"] = $_POST['Telcompte'];
                $response["role"] = $data['role'];
                echo json_encode($response);

            } else {
                $response["success"] = false;
                $response["message"] = MESSAGE_INVALID_LOGIN;
                echo json_encode($response);
            }
        }
        catch(Exception $ex){
            echo json_encode($ex);
        }
    }
    else{
        $response["success"] = false;
        $response["message"] = MESSAGE_MISSING_INFO;
        echo json_encode($response);
    }
}

if ($_SERVER['REQUEST_METHOD']=='POST'){
        seconnecter();
}