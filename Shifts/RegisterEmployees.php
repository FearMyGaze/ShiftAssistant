<?php

if($_SERVER['REQUEST_METHOD'] =='POST'){

    //Employees
    $RegisterName = $_POST['RegisterName'];
    $RegisterSurname = $_POST['RegisterSurname'];
    $RegisterPassword = $_POST['RegisterPassword'];
    $RegisterGender = $_POST['RegisterGender'];
    $RegisterBirthDay = $_POST['RegisterBirthDate'];
    $RegisterNationality = $_POST['RegisterNationality'];
    $RegisterPassword = password_hash( $RegisterPassword , PASSWORD_DEFAULT);

    //EmployeesContactDetails
    $RegisterEmail = $_POST['RegisterEmail'];
    $RegisterLandLine = $_POST['RegisterLandLine'];
    $RegisterCellPhone = $_POST['RegisterCellPhone'];
    $RegisterStreetAddress = $_POST['RegisterStreetAddress'];
    $RegisterNumber = $_POST['RegisterNumber'];
    $RegisterPostalCode = $_POST['RegisterPostalCode'];
    
    //EmployeesRequirements
    $TIN = $_POST['RegisterAFM'];
    $ShiftType = $_POST['RegisterShiftType'];
    $WorkHours = $_POST['RegisterWorkHours'];
    $Teams_Code = $_POST['RegisterTeamCode'];
    
    $switcher = $_POST['Switcher'];

    if($switcher == '1' or $switcher == '0') {

        require 'Connect.php';
    
        $sql = "SELECT * FROM Employees ORDER BY ID DESC";
        if(mysqli_query($conn,$sql)){
            $row1 = mysqli_fetch_assoc(mysqli_query($conn,$sql));
            $result["success"] = "0";
            if($row1 != null){
                $result['ID'] = $row1['ID'];
            } else {
                $result['ID'] = "0";
            }
    
            echo json_encode($result);
            mysqli_close($conn);
        } else {
            $result["success"] = "1";
    
            echo json_encode($result);
            mysqli_close($conn);
        }
        
    
     } else {

    require 'Connect.php';

    $checker0 = "SELECT Employees.ID,TIN,Email FROM shiftsdb.Employees,shiftsdb.EmployeesContactDetails,shiftsdb.EmployeesRequirements WHERE Employees.ID = EmployeesContactDetails.ID AND Employees.ID = EmployeesRequirements.ID AND TIN = '$TIN' AND Email = '$RegisterEmail'";

    $checker1 = "SELECT Employees.ID,TIN,Email FROM shiftsdb.Employees,shiftsdb.EmployeesContactDetails,shiftsdb.EmployeesRequirements WHERE Employees.ID = EmployeesContactDetails.ID AND Employees.ID = EmployeesRequirements.ID AND Email = '$RegisterEmail'";

    $checker2 = "SELECT Employees.ID,TIN,Email FROM shiftsdb.Employees,shiftsdb.EmployeesContactDetails,shiftsdb.EmployeesRequirements WHERE Employees.ID = EmployeesContactDetails.ID AND Employees.ID = EmployeesRequirements.ID AND TIN = '$TIN'";

    $checker3 = "SELECT * FROM shiftsdb.teams WHERE teams.ID = '$Teams_Code'";

    $response0 = mysqli_query($conn, $checker0);
    $response1 = mysqli_query($conn, $checker1);
    $response2 = mysqli_query($conn, $checker2);
    $response3 = mysqli_query($conn, $checker3);

    $sql0 = "INSERT INTO Employees (Firstname, Lastname, User_Password, Gender, Birthday, Citizenship, VacationStatus) VALUES ('$RegisterName' , '$RegisterSurname', '$RegisterPassword', '$RegisterGender', '$RegisterBirthDay', '$RegisterNationality', 0)";
    if (mysqli_num_rows($response3) === 1){
        if (mysqli_num_rows($response2) === 0){
            if (mysqli_num_rows($response1) === 0) {
                if(mysqli_num_rows($response0) === 0 ) {
                    if ( mysqli_query($conn, $sql0) ) {
        
                        $last_id = mysqli_insert_id($conn);
        
                        $sql1 = "INSERT INTO EmployeesContactDetails (ID, Email, Landline, Mobile, Address_Street, Address_Number, Postal_Code) VALUES ('$last_id', '$RegisterEmail', '$RegisterLandLine', '$RegisterCellPhone', '$RegisterStreetAddress', '$RegisterNumber', '$RegisterPostalCode')";
    
                        $sql2 = "INSERT INTO EmployeesRequirements (TIN, ShiftType, Workhours, ID, Teams_Code) VALUES ('$TIN', '$ShiftType', '$WorkHours', '$last_id', '$Teams_Code')";  
                
                        mysqli_query($conn, $sql1);
                        mysqli_query($conn, $sql2);
                
                        $result["success"] = "0";            
                        echo json_encode($result);
                        mysqli_close($conn);
                
                    }
                } else {
                    $result["success"] = "1";        
                    echo json_encode($result);
                    mysqli_close($conn);
                }
            } else {
                $result["success"] = "2";            
                echo json_encode($result);
                mysqli_close($conn);
            }
        } else {
            $result["success"] = "3";        
            echo json_encode($result);
            mysqli_close($conn);
        }    
    } else {
        $result["success"] = "4";    
        echo json_encode($result);
        mysqli_close($conn);
    }
}

     }
?>