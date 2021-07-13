<?php
    
    if($_SERVER['REQUEST_METHOD']=='POST') {
        require_once 'Connect.php';

        $AssistantCode = $_POST['ID'];

        $sql = "SELECT Owners.ID,Firstname, SurName, Owner_Password, Gender, Birthday, Citizenship, Email, LandLine, Mobile, Address_Street, Address_Number, Postal_Code,TIN FROM owners,ownerscontactdetails,ownersRequirements WHERE owners.id = ownerscontactdetails.OwnerID and owners.ID = ownersRequirements.OwnersID and owners.id = '$AssistantCode'";

            $response = mysqli_query($conn, $sql);
    
                if (mysqli_num_rows($response) === 1 ) {
                
                    $row = mysqli_fetch_assoc($response);
                
                        $result['success'] = "1";
                        $result['ID'] = $row['ID'];
                        $result['Email'] = $row['Email'];
                        $result['Firstname'] = $row['Firstname'];
                        $result['SurName'] = $row['SurName'];
                        $result['TIN'] = $row['TIN'];
                        $result['Owner_Password'] = $row['Owner_Password'];
                        $result['LandLine'] = $row['LandLine'];
                        $result['Mobile'] = $row['Mobile'];
                        $result['Address_Street'] = $row['Address_Street'];
                        $result['Address_Number'] = $row ['Address_Number'];
                        $result['Postal_Code'] = $row['Postal_Code'];
                        $result['Gender'] = $row['Gender'];
                        $result['Birthday'] = $row['Birthday'];
                        $result['Citizenship'] = $row['Citizenship'];
                
                    echo json_encode($result);
                
                    mysqli_close($conn);
                
                }else {
                
                    $result['success'] = "2";
                
                    echo json_encode($result);
                
                    mysqli_close($conn);
                
                }
            
        }

?>