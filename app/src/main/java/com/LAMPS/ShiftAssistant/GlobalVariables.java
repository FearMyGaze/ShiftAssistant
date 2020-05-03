package com.LAMPS.ShiftAssistant;

public class GlobalVariables {

    //Setup
    private String Protocol = "http://";
    private String IP = "192.168.1.8";

    private String LoginEmployees = "/Shifts/LoginEmployee.php";
    private String LoginOwners ="/Shifts/LoginOwner.php";
    private String RegisterEmployees = "/Shifts/RegisterEmployees.php";
    private String RegisterOwners = "/Shifts/RegisterOwners.php";
    private String ProgramGenerate = "/Shifts/ProgramGen.php";
    private String FetchEmployees = "/Shifts/FetchEmployees.php";
    private String FetchOwners = "0";
    private String Teams = "0";
    private String DeleteTeams = "/shifts/DeleteTeams.php";
    private String RegisterTeams = "/Shifts/RegisterTeams.php";
    private String Vacation = "/Shifts/Vacation.php";

    //Output
    private String LoginEmployees_URL = Protocol + IP + LoginEmployees;
    private String RegisterEmployees_URL = Protocol + IP + RegisterEmployees;
    private String FetchEmployees_URL = Protocol + IP + FetchEmployees;
    private String LoginOwners_URL = Protocol + IP + LoginOwners;
    private String RegisterOwners_URL = Protocol + IP + RegisterOwners;
    private String FetchOwners_URL = Protocol + IP + FetchOwners;
    private String Teams_URL = Protocol + IP + Teams;
    private String DeleteTeams_URL = Protocol + IP + DeleteTeams;
    private String RegisterTeams_URL = Protocol + IP + RegisterTeams;
    private String ProgramGenerate_URL = Protocol + IP + ProgramGenerate;
    private String Vacation_URL = Protocol + IP + Vacation;

    //TestPilot
    private String Empty = "0";
    private String Empty_URL = Protocol + IP + Empty;

    //==================================================Getters=========================================


    protected String getIP() {
        return IP;
    }

    protected String getLoginEmployees_URL() {
        return LoginEmployees_URL;
    }

    protected String getRegisterEmployees_URL() {
        return RegisterEmployees_URL;
    }

    protected String getFetchEmployees_URL() {
        return FetchEmployees_URL;
    }

    protected String getLoginOwners_URL() {
        return LoginOwners_URL;
    }

    protected String getRegisterOwners_URL() {
        return RegisterOwners_URL;
    }

    protected String getFetchOwners_URL(){return FetchOwners_URL;}

    protected String getTeams_URL() {
        return Teams_URL;
    }

    public String getDeleteTeams_URL() {
        return DeleteTeams_URL;
    }

    public String getRegisterTeams_URL() {
        return RegisterTeams_URL;
    }

    protected String getProgramGenerate_URL() {
        return ProgramGenerate_URL;
    }

    public String getVacation_URL() {
        return Vacation_URL;
    }




    public String getEmpty_URL() {
        return Empty_URL;
    }

}
