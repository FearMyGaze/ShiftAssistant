<?php
    
    if($_SERVER['REQUEST_METHOD']=='POST') {

        require_once 'Connect.php';

        $tin = $_POST['TIN'];
        $WorkerID = $_POST['ID'];
        $pointer = $_POST['pointer'];

        if($pointer == 1){

            $sql = "SELECT Employees.ID,Firstname,Lastname,User_Password,Email,Landline,Mobile,Address_Street,Address_Number,Postal_Code,Gender,Birthday,Citizenship,TIN,WorkHours,Teams_Code,ShiftType FROM employees , employeesContactDetails , employeesrequirements WHERE employees.id = employeesContactDetails.id and employees.ID = employeesrequirements.id AND employees.id = '$WorkerID'";

        } else {

            $sql = "SELECT Employees.ID,Firstname,Lastname,User_Password,Email,Landline,Mobile,Address_Street,Address_Number,Postal_Code,Gender,Birthday,Citizenship,TIN,WorkHours,Teams_Code,ShiftType FROM employees , employeesContactDetails , employeesrequirements WHERE employees.id = employeesContactDetails.id and employees.ID = employeesrequirements.id AND employeesrequirements.TIN = '$tin'";
        
        }
            $response = mysqli_query($conn, $sql);
    
                if (mysqli_num_rows($response) === 1 ) {
                
                    $row = mysqli_fetch_assoc($response);
                
                    $result['success'] = "1";
                    $result['ID'] = $row['ID'];
                    $result['Firstname'] = $row['Firstname'];
                    $result['Lastname'] = $row['Lastname'];
                    $result['User_Password'] = $row['User_Password'];
                    $result['Landline'] = $row['Landline'];
                    $result['Mobile'] = $row['Mobile'];
                    $result['Email'] = $row['Email'];
                    $result['Address_Street'] = $row['Address_Street'];
                    $result['Address_Number'] = $row ['Address_Number'];
                    $result['Postal_Code'] = $row['Postal_Code'];
                    $result['Gender'] = $row['Gender'];
                    $result['Birthday'] = $row['Birthday'];
                    $result['Citizenship'] = $row['Citizenship'];
                    $result['TIN'] = $row['TIN'];
                    $result['WorkHours'] = $row['WorkHours'];
                    $result['Teams_Code'] = $row['Teams_Code'];
                    $result['ShiftType'] = $row['ShiftType'];

                    echo json_encode($result);
                
                    mysqli_close($conn);
                
                }else {
                
                    $result['success'] = "2";
                
                    echo json_encode($result);
                
                    mysqli_close($conn);
                
                }

    }

?>