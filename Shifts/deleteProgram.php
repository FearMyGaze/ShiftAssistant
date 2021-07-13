<?php

    require 'Connect.php';

    $delete = "DELETE FROM Program";

    if(mysqli_query($conn,$delete)){

        $result['success'] = "0";

        echo json_encode($result);

        mysqli_close($conn);

    } else {

        $result['success'] = "1";

        echo json_encode($result);

        mysqli_close($conn);

    }


?>