<?php

    if($_SERVER['REQUEST_METHOD']=='POST') {

        require 'Connect.php';

        $Email = $_POST['Email'];

        $sql1 ="SELECT DISTINCT TIMESTAMPDIFF(SECOND,LastVacation,CURRENT_TIMESTAMP) as Diff From shiftsdb.employees,shiftsdb.employeescontactdetails Where employees.id = employeescontactdetails.id AND Email = '$Email'";
        
        $response1 = mysqli_query($conn, $sql1);

        $row1 = mysqli_fetch_assoc($response1);

        $index['Seconds'] = $row1['Diff'];

        if($row1['Diff'] >= 15811200){ 

            $sql = "UPDATE employees,employeescontactdetails SET LastVacation = CURRENT_TIMESTAMP , VacationStatus = 1 WHERE employees.ID = employeescontactdetails.ID AND Email = '$Email'";

            if (mysqli_query($conn, $sql)) {
                $result['success'] = "1";
                $result['difference'] = $row1['Diff'];
                echo json_encode($result);
                mysqli_close($conn);
            }

        } else {
            $result['success'] = "0";
            $result['difference'] = $row1['Diff'];
            echo json_encode($result);
            mysqli_close($conn);
        }
        
    }

?>