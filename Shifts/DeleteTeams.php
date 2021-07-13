<?php

    require 'connect.php';

    $TeamID = $_POST['TeamID'];

    if($_SERVER['REQUEST_METHOD']=='POST') {
    
        $sql = "DELETE FROM Teams WHERE ID='$TeamID'";
        $restoreIDstep1 = "SET @autoid := 0;";
        $restoreIDstep2 = "UPDATE Teams SET ID = @autoid := (@autoid + 1)";
        $restoreIDstep3 = "ALTER TABLE Teams AUTO_INCREMENT = 1;";

        if (mysqli_query($conn, $sql)) {
            if(mysqli_query($conn, $restoreIDstep1)){
                if(mysqli_query($conn, $restoreIDstep2)){
                    if(mysqli_query($conn, $restoreIDstep3)){

                        $sql1 = "SELECT * FROM Teams WHERE ID = '$TeamID' -1";
                        $response1 = mysqli_query($conn, $sql1);

                        if(mysqli_num_rows($response1) === 1){

                            $row = mysqli_fetch_assoc($response1);
        
                            $result['success'] = "0";
                            $result['ID'] = $row['ID'];
                            $result['TeamName'] = $row['TeamName'];
                            $result['Capacity'] = $row['Capacity'];
                            $result['Shift_Start'] = $row['Shift_Start'];
                            $result['Shift_End'] = $row['Shift_End'];
        
                            echo json_encode($result);
            
                            mysqli_close($conn);
                        } else {
                            $result['success'] = "noTeam";
                
                            echo json_encode($result);
                    
                            mysqli_close($conn);
                        }
                        
                    }
                }
            }
        } else {
            $result['success'] = "1";
            echo json_encode($result);
            mysqli_close($conn);
        }

    }

   
                

?>