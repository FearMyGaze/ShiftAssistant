<?php

if($_SERVER['REQUEST_METHOD'] =='POST'){
//Teams
$id = $_POST['ID'];
$TeamName = $_POST['TeamName'];
$Capacity = $_POST['Capacity'];
$Shift_Start = $_POST['Shift_Start'];
$Shift_End = $_POST['Shift_End'];
$switcher = $_POST['Switcher'];

 if($switcher == 1 or $switcher == 3) {

    require 'Connect.php';

    $sql = "SELECT * FROM Teams ORDER BY ID DESC";
    if(mysqli_query($conn,$sql)){
        $row1 = mysqli_fetch_assoc(mysqli_query($conn,$sql));

        if($row1 != null){
                        
        $result["success"] = "0";
        $result['ID'] = $row1['ID'];
        $result['TeamName'] = $row1['TeamName'];
        $result['Capacity'] = $row1['Capacity'];
        $result['Shift_Start'] = $row1['Shift_Start'];
        $result['Shift_End'] = $row1['Shift_End'];

        
            echo json_encode($result);
        
            mysqli_close($conn);
        } else {
            $result["success"] = "0";
            $result['ID'] = "0";
            $result['TeamName'] = "0";
            $result['Capacity'] = "0";
            $result['Shift_Start'] = "0";
            $result['Shift_End'] = "0";


            echo json_encode($result);
        
            mysqli_close($conn);
        }

    } else {
        $result["success"] = "1";

        echo json_encode($result);
        mysqli_close($conn);
    }
    

 } 
 else {
   
       require 'Connect.php';
       
       $sql0 = "INSERT INTO Teams(ID, TeamName, Capacity, Shift_Start, Shift_End) VALUES ('$id', '$TeamName ', '$Capacity', '$Shift_Start', '$Shift_End')";
   
       if ( mysqli_query($conn, $sql0) ) {
   
               $result["success"] = "0";
               $result["message"] = "success";
               echo json_encode($result);
               mysqli_close($conn);
        
       } else {
           $result["success"] = "1";
           $result["message"] = "error";
       
           echo json_encode($result);
           mysqli_close($conn);
       }
 }
}

?>