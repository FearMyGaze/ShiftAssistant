<?php

    if($_SERVER['REQUEST_METHOD']=='POST') {

        require 'Connect.php';

        $Email = $_POST['Email'];

        $sql = "UPDATE owners,ownerscontactdetails SET LastProgramGeneration = CURRENT_TIMESTAMP WHERE owners.ID = ownerscontactdetails.OwnerID AND Email = '$Email'";

        if (mysqli_query($conn, $sql)) {
            $result['success'] = "1";
            
            echo json_encode($result);
            mysqli_close($conn);
        } else {
            $result['success'] = "0";
           
            echo json_encode($result);
            mysqli_close($conn);
        }
        
    }

?>