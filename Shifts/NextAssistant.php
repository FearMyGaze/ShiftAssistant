<?php
    
    if($_SERVER['REQUEST_METHOD']=='POST') {

        require_once 'Connect.php';

        $AssistantCode = $_POST['AssistantID'];
        
        $max = "SELECT MAX(owners.ID) AS Biggest FROM owners , ownerscontactdetails , ownersRequirements WHERE owners.id = ownerscontactdetails.OwnerID and owners.ID = ownersRequirements.OwnersID";
        
        $responsemax = mysqli_query($conn,$max);

            if (mysqli_num_rows($responsemax) === 1 ) {

                $rowmax = mysqli_fetch_assoc($responsemax);

                $result['Biggest'] = $rowmax['Biggest'];

                if($rowmax['Biggest'] >= $AssistantCode + 1){

                    $sql2 = "SELECT * ,MAX(owners.ID) AS Biggest FROM owners , ownerscontactdetails , ownersRequirements WHERE owners.id = ownerscontactdetails.OwnerID and owners.ID = ownersRequirements.OwnersID AND owners.id = '$AssistantCode' + 1";
                    $response2 = mysqli_query($conn, $sql2);

                    if(mysqli_num_rows($response2) === 1){

                        $row = mysqli_fetch_assoc($response2);

                        $result['success'] = "1";
                        $result['ID'] = $row['ID'];
                        $result['Email'] = $row['Email'];
                        $result['Firstname'] = $row['Firstname'];
                        $result['SurName'] = $row['SurName'];
                        $result['TIN'] = $row['TIN'];
                        $result['Owner_password'] = $row['Owner_password'];
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

                    } else {
                        $result['success'] = "view";
            
                        echo json_encode($result);
                
                        mysqli_close($conn);
                    }

                } else {
                    $result['success'] = "2";
        
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