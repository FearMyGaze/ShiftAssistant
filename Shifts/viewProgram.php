<?php

    if($_SERVER['REQUEST_METHOD']=='POST') {

        require 'Connect.php';

        $id = $_POST['ID'];

        $sql = "SELECT * FROM Program WHERE Employee = '$id'";

        $response = mysqli_query($conn,$sql);
    
        if(mysqli_num_rows($response) >= 1){
    
            $json = mysqli_fetch_all ($response, MYSQLI_ASSOC);
    
            $result['success'] = "0";
            if($json != null){
                $result['Shifts'] = $json;
            } else {
                $json = "empty";
                $result['Shifts'] = $json;
            }

    
            echo json_encode($result);
            mysqli_close($conn);
    
    
        } else {
    
            $result['success'] = "1";
    
            echo json_encode($result);
            mysqli_close($conn);
    
        }
        
    }

?>