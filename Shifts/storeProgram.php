<?php

require 'Connect.php';

$sql = "CREATE TABLE Program (
    ID INT(10) UNSIGNED AUTO_INCREMENT,
    DayOfTheWeek VARCHAR(10),
    Employee VARCHAR(10),
    Shift VARCHAR(10),
    Email VARCHAR(50),
    Reg_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(ID),
    CONSTRAINT FK_OwnersXPrograms
    FOREIGN KEY (Email) REFERENCES OwnersContactDetails(Email)
    )";

if (mysqli_query($conn, $sql)) {
    echo "Table Program created successfully";
} else {
    echo "Error creating table: " . mysqli_error($conn);
}

mysqli_close($conn);

?>