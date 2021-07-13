<?php
require 'Connect.php';

$sql0 = "DROP DATABASE shiftsDB";


if (mysqli_query($conn,$sql0)) {
    echo "DataBase shiftsDB deleted successfully";
}else {
    echo "Error droping DataBase: " . mysqli_error($conn);
}

?>