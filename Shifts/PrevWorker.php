<?php

    if($_SERVER['REQUEST_METHOD']=='POST') {

        require_once 'Connect.php';

        $EmployeeCode = $_POST['WorkerID'];

        $min = "SELECT MIN(employees.ID) AS Smallest FROM employees , employeesContactDetails , employeesrequirements WHERE employees.id = employeesContactDetails.id and employees.ID = employeesrequirements.id";

        $responsemin = mysqli_query($conn,$min);

        if (mysqli_num_rows($responsemin) === 1 ) {

            $rowmin = mysqli_fetch_assoc($responsemin);
            $result['Smallest'] = $rowmin['Smallest'];

            if($rowmin['Smallest'] <= $EmployeeCode - 1){

                $sql1 = "SELECT * FROM employees , employeesContactDetails , employeesrequirements WHERE employees.ID = employeesContactDetails.ID and employees.ID = employeesrequirements.id AND employees.id = '$EmployeeCode' - 1";
                $response1 = mysqli_query($conn, $sql1);
                if(mysqli_num_rows($response1) === 1){

                    $row = mysqli_fetch_assoc($response1);

                    $result['success'] = "-1";
                    $result['ID'] = $row['ID'];
                    $result['Firstname'] = $row['Firstname'];
                    $result['Lastname'] = $row['Lastname'];
                    $result['TIN'] = $row['TIN'];
                    $result['User_Password'] = $row['User_Password'];
                    $result['Landline'] = $row['Landline'];
                    $result['Mobile'] = $row['Mobile'];
                    $result['Address_Street'] = $row['Address_Street'];
                    $result['Address_Number'] = $row ['Address_Number'];
                    $result['Postal_Code'] = $row['Postal_Code'];
                    $result['Gender'] = $row['Gender'];
                    $result['Birthday'] = $row['Birthday'];
                    $result['Citizenship'] = $row['Citizenship'];
                    $result['WorkHours'] = $row['WorkHours'];
                    $result['Teams_Code'] = $row['Teams_Code'];
                    $result['ShiftType'] = $row['ShiftType'];

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