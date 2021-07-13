<?php
    
    if($_SERVER['REQUEST_METHOD']=='POST') {
        require_once 'Connect.php';

        $TeamCode = $_POST['TeamCode'];

            $sql = "SELECT * FROM Teams WHERE ID = '$TeamCode'";

            $response = mysqli_query($conn, $sql);
    
                if (mysqli_num_rows($response) === 1 ) {
                
                    $row = mysqli_fetch_assoc($response);
                                            
                        $result['success'] = "0";
                        $result['ID'] = $row['ID'];
                        $result['TeamName'] = $row['TeamName'];
                        $result['Capacity'] = $row['Capacity'];
                        $result['Shift_Start'] = $row['Shift_Start'];
                        $result['Shift_End'] = $row['Shift_End'];
                    
                        echo json_encode($result);
                    
                        mysqli_close($conn);
                    
                
                }else {
                
                    $result['success'] = "2";
                
                    echo json_encode($result);
                
                    mysqli_close($conn);
                
                }
            
        }

?>