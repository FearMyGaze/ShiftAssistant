<?php
    
    if($_SERVER['REQUEST_METHOD']=='POST') {
        
        require_once 'Connect.php';

        $Email = $_POST['Email'];

        $sql = "SELECT DISTINCT Employees.ID,Firstname,Lastname,Teams_Code,WorkHours,VacationStatus,ShiftType FROM shiftsdb.Employees,shiftsdb.EmployeesRequirements WHERE Employees.ID = EmployeesRequirements.ID AND VacationStatus = 0";
        
        $response = mysqli_query($conn, $sql);

        if (mysqli_num_rows($response) >= 1 ) {
    
            $json = mysqli_fetch_all ($response, MYSQLI_ASSOC);
            $result['success'] = "1";
            $result['message'] = "success";
            $result['Employees'] = $json;
    
            echo json_encode($result);
            mysqli_close($conn);
            
    
        }else {
    
            $result['success'] = "0";
            $result['message'] = "error";
    
            echo json_encode($result);
    
            mysqli_close($conn);
    
        }

    }

?>