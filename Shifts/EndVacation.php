<?php
  
  if($_SERVER['REQUEST_METHOD']=='POST') {

    require_once 'Connect.php';

    $sql = "SELECT employees.ID,IF(TIMESTAMPDIFF(SECOND,employees.LastVacation,CURRENT_TIMESTAMP) >= 604800,'YES','NO') AS END FROM employees";
        
    $response = mysqli_query($conn, $sql);

    if (mysqli_num_rows($response) >= 1 ) {

      while($row = mysqli_fetch_assoc($response)){

        $row['END'];
        $row['ID'];

        $id = $row['ID'];

        if($row['END'] == 'YES'){
          
          $onUpdate = "UPDATE Employees SET VacationStatus = 0 WHERE employees.id = $id";

          mysqli_query($conn, $onUpdate);

        }

      }

        $result['success'] = "0";

        echo json_encode($result);

        mysqli_close($conn);

    }else {

        $result['success'] = "1";

        echo json_encode($result);

        mysqli_close($conn);

    }

  }

?>