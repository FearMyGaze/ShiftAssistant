<?php

    require 'connect.php';

    $EmployeeID = $_POST['EmployeeID'];

    if($_SERVER['REQUEST_METHOD']=='POST') {
    
        $sql1 = "DELETE FROM Employees WHERE ID='$EmployeeID'";
        $sql2 = "DELETE FROM EmployeesContactDetails WHERE ID='$EmployeeID'";
        $sql3 = "DELETE FROM EmployeesRequirements WHERE ID='$EmployeeID'";
        $restoreIDstep1 = "SET @autoid := 0;";
        $restoreIDstep2 = "UPDATE Employees SET ID = @autoid := (@autoid + 1)";
        $restoreIDstep3 = "ALTER TABLE employees AUTO_INCREMENT = 1;";

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