<?php

    if($_SERVER['REQUEST_METHOD']=='POST') {

        require_once 'Connect.php';

        $TeamCode = $_POST['TeamCode'];

        $min = "SELECT MIN(ID) AS Smallest FROM Teams";

        $responsemin = mysqli_query($conn,$min);

        if (mysqli_num_rows($responsemin) === 1 ) {

            $rowmin = mysqli_fetch_assoc($responsemin);
            $result['Smallest'] = $rowmin['Smallest'];

            if($rowmin['Smallest'] <= $TeamCode - 1){

                $sql1 = "SELECT * FROM Teams WHERE ID = '$TeamCode' - 1";
                $response1 = mysqli_query($conn, $sql1);
                if(mysqli_num_rows($response1) === 1){

                    $row = mysqli_fetch_assoc($response1);

                    $result['success'] = "-1";
                    $result['ID'] = $row['ID'];
                    $result['TeamName'] = $row['TeamName'];
                    $result['Capacity'] = $row['Capacity'];
                    $result['Shift_Start'] = $row['Shift_Start'];
                    $result['Shift_End'] = $row['Shift_End'];

                    echo json_encode($result);
    
                    mysqli_close($conn);
                } else {
                    $result['success'] = "view";
        
                    echo json_encode($result);
            
                    mysqli_close($conn);
                }

            } else {
                $result['success'] = "-2";
        
                echo json_encode($result);
        
                mysqli_close($conn);
            }

        } else {
            $result['success'] = "-3";
    
            echo json_encode($result);
        
            mysqli_close($conn);
        }

    }

?>