<?php

    if($_SERVER['REQUEST_METHOD'] =='POST'){

        require 'Connect.php';

        $Day = $_POST['nameOfTheDay'];
        $ID = $_POST['ID'];
        $Email = $_POST['Email'];
        $Shift = $_POST['Shift'];

           $sql = "INSERT INTO Program(DayOfTheWeek,Employee,Shift,Email) VALUES ('$Day', '$ID', '$Shift', '$Email')";

           if(mysqli_query($conn, $sql)){

               $result["success"] = "0";

               echo json_encode($result);
               mysqli_close($conn);

           } else {
               
               $result["success"] = "1";

               echo json_encode($result);
               mysqli_close($conn);
           }


        } 

?>