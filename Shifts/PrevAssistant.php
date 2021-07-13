<?php

    if($_SERVER['REQUEST_METHOD']=='POST') {

        require_once 'Connect.php';

        $AssistantCode = $_POST['AssistantID'];

        $min = "SELECT MIN(owners.ID) AS Smallest FROM owners , ownersContactDetails , ownersrequirements WHERE owners.id = ownersContactDetails.OwnerID and owners.ID = ownersrequirements.OwnersID";

        $responsemin = mysqli_query($conn,$min);

        if (mysqli_num_rows($responsemin) === 1 ) {

            $rowmin = mysqli_fetch_assoc($responsemin);
            $result['Smallest'] = $rowmin['Smallest'];

            if($rowmin['Smallest'] <= $AssistantCode - 1){

                $sql1 = "SELECT * , MIN(owners.ID) AS Smallest FROM owners , ownersContactDetails , ownersrequirements WHERE owners.id = ownersContactDetails.OwnerID and owners.ID = ownersrequirements.OwnersID AND owners.ID ='$AssistantCode' - 1";
                $response1 = mysqli_query($conn, $sql1);
                if(mysqli_num_rows($response1) === 1){

                    $row = mysqli_fetch_assoc($response1);

                        $result['success'] = "-1";
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