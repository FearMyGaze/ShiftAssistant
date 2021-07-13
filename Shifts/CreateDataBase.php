<?php
$servername = "localhost";
$username = "SuperUser";
$THEpassword = "SuPeRuSeR";
$dbname = "test";

$conn = mysqli_connect($servername, $username, $THEpassword, $dbname);
// Checking for the appropriate tools
if (!$conn) {
    die("Connection failed: " . mysqli_connect_error());
}else{  
//Wood Carving Time !!!
$sql0 = "CREATE DATABASE shiftsDB";

$sql1 = "CREATE TABLE Employees(
    ID INT(10) UNSIGNED AUTO_INCREMENT,
    Firstname VARCHAR(30) NOT NULL,
    Lastname VARCHAR(30) NOT NULL,
    User_Password TEXT NOT NULL,
    Gender VARCHAR(50) NOT NULL,
    Birthday DATE NOT NULL,
    Citizenship VARCHAR(50) NOT NULL,
    Reg_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    LastVacation TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    VacationStatus BOOLEAN,
    PRIMARY KEY(ID)
    )";

$sql2 = "CREATE TABLE EmployeesContactDetails(
    ID INT(10) UNSIGNED,
    Email VARCHAR(50) NOT NULL,
    Landline VARCHAR(50) NOT NULL,
    Mobile VARCHAR(50) NOT NULL,
    Address_Street VARCHAR(50) NOT NULL,
    Address_Number INT(5) NOT NULL,
    Postal_Code INT(50) NOT NULL,
    PRIMARY KEY(Email),
    CONSTRAINT FK_EmployeesXContactDetails 
    FOREIGN KEY (ID) REFERENCES Employees(ID) ON UPDATE CASCADE ON DELETE CASCADE
    )";

$sql3 = "CREATE TABLE EmployeesRequirements (
    TIN INT(15) NOT NULL,
    ShiftType VARCHAR(10) NOT NULL,
    WorkHours INT(1) NOT NULL,
    ID INT(10) UNSIGNED NOT NULL,
    Teams_Code INT(10) UNSIGNED NOT NULL,
    PRIMARY KEY(TIN),
    CONSTRAINT FK_EmployeesXRequirements
    FOREIGN KEY (ID) REFERENCES Employees(ID) ON UPDATE CASCADE ON DELETE CASCADE
    )";

$sql4 = "CREATE TABLE Owners(
    ID INT(10) UNSIGNED AUTO_INCREMENT,
    Firstname VARCHAR(50) NOT NULL,
    SurName VARCHAR(50) NOT NULL,
    Gender VARCHAR(50) NOT NULL,
    Owner_password TEXT NOT NULL,
    Birthday DATE NOT NULL,
    Citizenship VARCHAR(50) NOT NULL,
    Reg_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    LastProgramGeneration TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (ID)
    )";

$sql5 = "CREATE TABLE OwnersContactDetails(
    Email VARCHAR(50) NOT NULL,
    LandLine VARCHAR(50) NOT NULL,
    Mobile VARCHAR(50) NOT NULL,
    Address_Street VARCHAR(50) NOT NULL,
    Address_Number INT(5) NOT NULL,
    Postal_Code INT(50) NOT NULL,
    OwnerID INT(10) UNSIGNED NOT NULL,
    PRIMARY KEY (Email),
    CONSTRAINT FK_OwnerXContactDetails
    FOREIGN KEY (OwnerID) REFERENCES Owners(ID) ON UPDATE CASCADE ON DELETE CASCADE
    )";

$sql6 = "CREATE TABLE OwnersRequirements(
    TIN INT(15) NOT NULL,
    OwnersID INT(10) UNSIGNED NOT NULL,
    PRIMARY KEY (TIN),
    CONSTRAINT FK_OwnerXRequirements2
    FOREIGN KEY (OwnersID) REFERENCES Owners(ID) ON UPDATE CASCADE ON DELETE CASCADE
    )";    

$sql7 = "CREATE TABLE Teams(
    ID INT(10) UNSIGNED AUTO_INCREMENT,
    TeamName varchar(20) NOT NULL,
    Capacity int(3) NOT NULL,
    Shift_Start TEXT NOT NULL,
    Shift_End TEXT NOT NULL,
    PRIMARY KEY(ID)
    )";

$sql9 = "CREATE TABLE Program (
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


//Job done ??¿¿ 
if (mysqli_query($conn, $sql0)) {
    echo "Database ShiftsDB created successfully";
    $conn = mysqli_connect($servername, $username, $THEpassword, "ShiftsDB");

} else {
    echo "Error creating database: " . mysqli_error($conn);
}

echo "<br>";

if (mysqli_query($conn, $sql7)) {
    echo "Table Teams created successfully";
} else {
    echo "Error creating table: " . mysqli_error($conn);
}

echo "<br>";

if (mysqli_query($conn, $sql1)) {
    echo "Table Employees created successfully";
} else {
    echo "Error creating table: " . mysqli_error($conn);
}

echo "<br>";

if (mysqli_query($conn, $sql2)) {
    echo "Table EmployeesRequirements created successfully";
} else {
    echo "Error creating table: " . mysqli_error($conn);
}

echo "<br>";

if (mysqli_query($conn, $sql3)) {
    echo "Table EmployeesContatctDetails created successfully";
} else {
    echo "Error creating table: " . mysqli_error($conn);
}

echo "<br>";

if (mysqli_query($conn, $sql4)) {
    echo "Table Owners created successfully";
} else {
    echo "Error creating table: " . mysqli_error($conn);
}

echo "<br>";

if (mysqli_query($conn, $sql5)) {
    echo "Table OwnersContactDetails created successfully";
} else {
    echo "Error creating table: " . mysqli_error($conn);
}

echo "<br>";

if (mysqli_query($conn, $sql6)) {
    echo "Table OwnersRequirements created successfully";
} else {
    echo "Error creating table: " . mysqli_error($conn);
}

echo "<br>";

if (mysqli_query($conn, $sql9)) {
    echo "Table Program created successfully";
} else {
    echo "Error creating table: " . mysqli_error($conn);
}

}//else bracket
mysqli_close($conn);  
?>