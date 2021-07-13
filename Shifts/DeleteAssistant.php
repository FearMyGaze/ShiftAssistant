<?php

    require 'connect.php';

    $AssistantID = $_POST['AssistantID'];

    if($_SERVER['REQUEST_METHOD']=='POST') {
    
        $sql1 = "DELETE FROM Owners WHERE ID='$AssistantID'";
        $sql2 = "DELETE FROM OwnersContactDetails WHERE OwnerID='$AssistantID'";
        $sql3 = "DELETE FROM OwnersRequirements WHERE OwnersID='$AssistantID'";
        $restoreIDstep1 = "SET @autoid := 0;";
        $restoreIDstep2 = "UPDATE Owners SET ID = @autoid := (@autoid + 1)";
        $restoreIDstep3 = "ALTER TABLE Owners AUTO_INCREMENT = 1;";

        if (mysqli_query($conn, $sql3)) {
            if (mysqli_query($conn, $sql2)) {
                if (mysqli_query($conn, $sql1)) {
                    if(mysqli_query($conn, $restoreIDstep1)){
                        if(mysqli_query($conn, $restoreIDstep2)){
                            if(mysqli_query($conn, $restoreIDstep3)){
                                $result['success'] = "0";
                                echo json_encode($result);
                                mysqli_close($conn);
                            }
                        }
                    }
                }
                else {
                    $result['success'] = "1";
                    echo json_encode($result);
                    mysqli_close($conn);
                }
            }
            else {
                $result['success'] = "1";
                echo json_encode($result);
                mysqli_close($conn);
            }
        } else {
            $result['success'] = "3";
            echo json_encode($result);
            mysqli_close($conn);
        }

    }

?>