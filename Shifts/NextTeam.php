<?php
    
    if($_SERVER['REQUEST_METHOD']=='POST') {

        require_once 'Connect.php';

        $TeamCode = $_POST['TeamCode'];
        
        $max = "SELECT MAX(ID) AS Biggest FROM Teams";
        
        $responsemax = mysqli_query($conn,$max);

            if (mysqli_num_rows($responsemax) === 1 ) {

                $rowmax = mysqli_fetch_assoc($responsemax);

                $result['Biggest'] = $rowmax['Biggest'];

                if($rowmax['Biggest'] >= $TeamCode + 1){

                    $sql2 = "SELECT * FROM Teams WHERE ID = '$TeamCode' + 1";

                    $response2 = mysqli_query($conn, $sql2);

                    if(mysqli_num_rows($response2) === 1){

                        $row = mysqli_fetch_assoc($response2);

                        $result['success'] = "1";
                        $result['ID'] = $row['ID'];
                        $result['TeamName'] = $row['TeamName'];
                        $result['Capacity'] = $row['Capacity'];
                        $result['Shift_Start'] = $row['Shift_Start'];
                        $result['Shift_End'] = $row['Shift_End'];

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