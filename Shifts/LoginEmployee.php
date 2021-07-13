<?php
    
if($_SERVER['REQUEST_METHOD']=='POST') {
    $email = $_POST['email'];
    $password = $_POST['password'];
    
    require_once 'Connect.php';

    $sql = "SELECT Firstname,Employees.ID,VacationStatus,Email,Teams_Code,ShiftType,TeamName,User_Password FROM shiftsdb.Employees,shiftsdb.EmployeesContactDetails,shiftsdb.employeesrequirements,shiftsdb.Teams Where Employees.ID = EmployeesContactDetails.ID AND Employees.ID = EmployeesRequirements.ID AND Teams.ID = EmployeesRequirements.Teams_Code AND Email = '$email'";


    $response = mysqli_query($conn, $sql);

    $result = array();
    $result['login'] = array();

    if (mysqli_num_rows($response) === 1 ) {

        $row = mysqli_fetch_assoc($response);

        if ( password_verify($password, $row['User_Password']) ) {

            $index['name'] = $row['Firstname'];
            $index['ID'] = $row['ID'];
            $index['VacationStatus'] = $row['VacationStatus'];
            $index['email'] = $row['Email'];
            $index['team_code'] = $row['Teams_Code'];
            $index['ShiftType'] = $row['ShiftType'];
            $index['TeamName'] = $row['TeamName'];
            
            array_push($result['login'], $index);

            $result['success'] = "1";
            $result['message'] = "success";

            echo json_encode($result);

            mysqli_close($conn);

        } else {

            $result['success'] = "0";
            $result['message'] = "error";

            echo json_encode($result);

            mysqli_close($conn);

        }

    }


}

?>