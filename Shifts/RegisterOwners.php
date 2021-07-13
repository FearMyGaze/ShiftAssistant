<?php

if($_SERVER['REQUEST_METHOD'] =='POST'){

    //Employees
    $RegisterName = $_POST['RegisterName'];
    $RegisterSurname = $_POST['RegisterSurname'];
    $RegisterGender = $_POST['RegisterGender'];
    $RegisterPassword = $_POST['RegisterPassword'];
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

    $switcher = $_POST['Switcher'];

    if($switcher == "1"){
        require 'Connect.php';
        $sql = "SELECT Owners.ID,Firstname, SurName, Owner_Password, Gender, Birthday, Citizenship, Email, LandLine, Mobile, Address_Street, Address_Number, Postal_Code,TIN FROM Owners,OwnersContactDetails,OwnersRequirements WHERE Owners.ID = OwnersContactDetails.OwnerID AND Owners.ID = OwnersRequirements.OwnersID ORDER BY ID DESC";
        if(mysqli_query($conn,$sql)){
            $row1 = mysqli_fetch_assoc(mysqli_query($conn,$sql));

            if($row1 != null){

            $result["success"] = "0";
            $result['ID'] = $row1['ID'];
            $result['Firstname'] = $row1['Firstname'];
            $result['Owner_Password'] = $row1['Owner_Password'];
            $result['SurName'] = $row1['SurName'];
            $result['Gender'] = $row1['Gender'];
            $result['Birthday'] = $row1['Birthday'];
            $result['Citizenship'] = $row1['Citizenship'];
            $result['Email'] = $row1['Email'];
            $result['LandLine'] = $row1['LandLine'];
            $result['Mobile'] = $row1['Mobile'];
            $result['Address_Street'] = $row1['Address_Street'];
            $result['Address_Number'] = $row1['Address_Number'];
            $result['Postal_Code'] = $row1['Postal_Code'];
            $result['TIN'] = $row1['TIN'];
            
                echo json_encode($result);
            
                mysqli_close($conn);
            } else {
            $result["success"] = "0";
            $result['ID'] = "0";
            $result['Firstname'] = "0";
            $result['Owner_Password'] = "0";
            $result['SurName'] = "0";
            $result['Gender'] = "0";
            $result['Birthday'] = "0";
            $result['Citizenship'] = "0";
            $result['Email'] = "0";
            $result['LandLine'] = "0";
            $result['Mobile'] = "0";
            $result['Address_Street'] = "0";
            $result['Address_Number'] = "0";
            $result['Postal_Code'] = "0";
            $result['TIN'] = "0";


                echo json_encode($result);
            
                mysqli_close($conn);
            }

        } else {
            $result["success"] = "1";

            echo json_encode($result);
            mysqli_close($conn);
        }
    } else {

    require 'Connect.php';
    
    $sql0 = "INSERT INTO Owners(Firstname, SurName, Gender, Owner_Password, Birthday, Citizenship) VALUES ('$RegisterName ', '$RegisterSurname', '$RegisterGender', '$RegisterPassword', '$RegisterBirthDay', '$RegisterNationality')";        
    
    $checker0 = "SELECT * FROM shiftsdb.Owners,shiftsdb.ownerscontactdetails,shiftsdb.ownersrequirements WHERE owners.ID = ownerscontactdetails.OwnerID AND owners.ID = ownersrequirements.OwnersID AND ownerscontactdetails.Email = '$RegisterEmail' AND ownersrequirements.TIN = '$TIN'";
    $checker1 = "SELECT * FROM shiftsdb.Owners,shiftsdb.ownerscontactdetails,shiftsdb.ownersrequirements WHERE owners.ID = ownerscontactdetails.OwnerID AND owners.ID = ownersrequirements.OwnersID AND ownerscontactdetails.Email = '$RegisterEmail'";
    $checker2 ="SELECT * FROM shiftsdb.Owners,shiftsdb.ownerscontactdetails,shiftsdb.ownersrequirements WHERE owners.ID = ownerscontactdetails.OwnerID AND owners.ID = ownersrequirements.OwnersID AND ownersrequirements.TIN = '$TIN'";

    $response0 = mysqli_query($conn, $checker0);
    $response1 = mysqli_query($conn, $checker1);
    $response2 = mysqli_query($conn, $checker2);

        if (mysqli_num_rows($response2) === 0){
            if (mysqli_num_rows($response1) === 0) {
                if(mysqli_num_rows($response0) === 0 ) {
                    if ( mysqli_query($conn, $sql0) ) {
        
                        $last_id = mysqli_insert_id($conn);
        
                        $sql1 = "INSERT INTO OwnersContactDetails(Email, LandLine, Mobile, Address_Street, Address_Number, Postal_Code, OwnerID) VALUES ('$RegisterEmail', '$RegisterLandLine', '$RegisterCellPhone', '$RegisterStreetAddress', '$RegisterNumber', '$RegisterPostalCode', '$last_id')";
                        $sql2 = "INSERT INTO OwnersRequirements(TIN, OwnersID) VALUES ('$TIN', '$last_id')";
                
                        mysqli_query($conn, $sql1);
                        mysqli_query($conn, $sql2);
                
                        $result["success"] = "0";
                        $result["message"] = "success";
            
                        echo json_encode($result);
                        mysqli_close($conn);
                
                    }
                } else {
                    $result["success"] = "1";
                    $result["message"] = "error";
        
                    echo json_encode($result);
                    mysqli_close($conn);
                }
            } else {
                $result["success"] = "2";
                $result["message"] = "error";
            
                echo json_encode($result);
                mysqli_close($conn);
            }
        } else {
            $result["success"] = "3";
            $result["message"] = "error";
        
            echo json_encode($result);
            mysqli_close($conn);
        }    
    
}
    }

?>