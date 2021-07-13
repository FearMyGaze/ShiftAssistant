<?php
    
if($_SERVER['REQUEST_METHOD']=='POST') {
    $email = $_POST['email'];
    $password = $_POST['password'];
    
    require_once 'Connect.php';

    $sql = "SELECT * FROM shiftsdb.Owners,shiftsdb.OwnersContactDetails Where Owners.ID = OwnersContactDetails.OwnerID AND Email = '$email'";

    $response = mysqli_query($conn, $sql);

    $result = array();
    $result['login'] = array();

    if (mysqli_num_rows($response) === 1 ) {

        $row = mysqli_fetch_assoc($response);

        if ( password_verify($password, $row['Owner_password']) ) {

            $index['name'] = $row['Firstname'];
            $index['email'] = $row['Email'];

            array_push($result['login'], $index);

            $result['success'] = "1";
            $result['message'] = "success";

            echo json_encode($result);

            mysqli_close($conn);

        } else {

            $result['success'] = "0";
            $result['message'] = "error";

            echo json_encode($result);

            mysqli_close($conn);

        }

    }


}

?>