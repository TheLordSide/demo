<?php
require_once "../config/db.php";
include_once "../config/json-header.php";

$pdo = getconnect();

function deleteone()
{
    global $pdo;
       try {
        $delete = $pdo->prepare("DELETE from discussion where Iddiscussion =:valeur1");
        $delete->bindParam(':valeur1', $_GET['Iddiscussion']);
        $delete->execute();
        $response["message"] = "Suppression réussie ";
        $response["success"] = true;
        echo json_encode($response);
       } catch (Exception $exce) {
        $response["success"] = false;
        $response["message"] = $exce;
        echo json_encode($response);
       }

}

if ($_SERVER['REQUEST_METHOD']=='DELETE'){
    deleteone();
}